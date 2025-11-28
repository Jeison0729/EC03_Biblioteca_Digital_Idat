package com.idat.biblio.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idat.biblio.dtos.ReservaDTO;
import com.idat.biblio.dtos.ReservaRequestDTO;
import com.idat.biblio.entity.LibroEntity;
import com.idat.biblio.entity.ReservaEntity;
import com.idat.biblio.entity.UsuarioEntity;
import com.idat.biblio.enums.EstadoReserva;
import com.idat.biblio.repository.LibroRepository;
import com.idat.biblio.repository.ReservaRepository;
import com.idat.biblio.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final LibroRepository libroRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository, LibroRepository libroRepository) {
        this.reservaRepository = reservaRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    @Transactional
    public ReservaDTO reservarLibro(ReservaRequestDTO request, Authentication authentication) {
        UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

        LibroEntity libro = libroRepository.findById(request.getLibroId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        if (libro.getCantidadDisponible() <= 0) {
            throw new RuntimeException("No hay ejemplares disponibles de este libro");
        }

        // Decrementamos stock
        libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
        libroRepository.save(libro);

        // Creamos reserva
        ReservaEntity reserva = new ReservaEntity();
        reserva.setUsuario(usuario);
        reserva.setLibro(libro);
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setEstado(EstadoReserva.ACTIVA);

        reserva = reservaRepository.save(reserva);

        return new ReservaDTO(
                reserva.getIdReserva(),
                libro.getIdLibro(),
                libro.getTitulo(),
                libro.getAutor(),
                reserva.getFechaReserva(),
                null,
                reserva.getEstado()
        );
    }

    @Override
    public List<ReservaDTO> obtenerMisReservas(Authentication authentication) {
        UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

        List<ReservaEntity> reservas = reservaRepository.findByUsuarioCodigoAndEstado(
                usuario.getCodigo(), EstadoReserva.ACTIVA);

        return reservas.stream().map(r -> new ReservaDTO(
                r.getIdReserva(),
                r.getLibro().getIdLibro(),
                r.getLibro().getTitulo(),
                r.getLibro().getAutor(),
                r.getFechaReserva(),
                r.getFechaDevolucion(),
                r.getEstado()
        )).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void devolverLibro(Long reservaId, Authentication authentication) {
        UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

        ReservaEntity reserva = reservaRepository.findByIdAndUsuarioCodigo(reservaId, usuario.getCodigo())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada o no pertenece al usuario"));

        if (reserva.getEstado() != EstadoReserva.ACTIVA) {
            throw new RuntimeException("Esta reserva ya fue devuelta o cancelada");
        }

        // Devolvemos el libro al stock
        LibroEntity libro = reserva.getLibro();
        libro.setCantidadDisponible(libro.getCantidadDisponible() + 1);
        libroRepository.save(libro);

        // Marcamos como devuelta
        reserva.setEstado(EstadoReserva.DEVUELTA);
        reserva.setFechaDevolucion(LocalDateTime.now());
        reservaRepository.save(reserva);
    }
}