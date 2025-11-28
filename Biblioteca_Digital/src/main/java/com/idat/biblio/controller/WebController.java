package com.idat.biblio.controller;

import com.idat.biblio.entity.UsuarioEntity;
import com.idat.biblio.service.LibroService;
import com.idat.biblio.service.ReservaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final LibroService libroService;
    private final ReservaService reservaService;

    public WebController(LibroService libroService, ReservaService reservaService) {
        this.libroService = libroService;
        this.reservaService = reservaService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model, Authentication auth, HttpSession session) {
        model.addAttribute("libros", libroService.obtenerTodosLosLibros());
        if (auth != null) {
            UsuarioEntity usuario = (UsuarioEntity) auth.getPrincipal();
            session.setAttribute("usuario", usuario);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @GetMapping("/mis-reservas")
    public String misReservas(Model model, Authentication auth) {
        if (auth == null) return "redirect:/login";
        model.addAttribute("reservas", reservaService.obtenerMisReservas(auth));
        return "mis-reservas";
    }

    @GetMapping("/admin/libros")
    public String adminLibros(Model model) {
        model.addAttribute("libros", libroService.obtenerTodosLosLibros());
        return "admin/libros";
    }

    @GetMapping("/admin/libros/nuevo")
    public String nuevoLibro() {
        return "admin/libro-form";
    }

    @GetMapping("/admin/libros/editar/{id}")
    public String editarLibro(@org.springframework.web.bind.annotation.PathVariable Long id, Model model) {
        model.addAttribute("libro", libroService.obtenerLibroPorId(id));
        return "admin/libro-form";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}