package com.idat.biblio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.biblio.dtos.ReservaDTO;
import com.idat.biblio.dtos.ReservaRequestDTO;
import com.idat.biblio.service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaRestController {
	private final ReservaService reservaService;

    public ReservaRestController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // POST /api/reservas  → reservar libro
    @PostMapping
    public ResponseEntity<ReservaDTO> reservar(@RequestBody ReservaRequestDTO request,
                                               Authentication authentication) {
        ReservaDTO reserva = reservaService.reservarLibro(request, authentication);
        return ResponseEntity.ok(reserva);
    }

    // GET /api/reservas/mis  → mis reservas activas
    @GetMapping("/mis")
    public ResponseEntity<List<ReservaDTO>> misReservas(Authentication authentication) {
        List<ReservaDTO> reservas = reservaService.obtenerMisReservas(authentication);
        return ResponseEntity.ok(reservas);
    }

    // DELETE /api/reservas/5  → devolver libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> devolver(@PathVariable Long id, Authentication authentication) {
        reservaService.devolverLibro(id, authentication);
        return ResponseEntity.noContent().build();
    }

}
