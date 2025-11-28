package com.idat.biblio.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EvaluacionViewController {

	@GetMapping("/mis-evaluaciones")
	@PreAuthorize("isAuthenticated()")
	public String misEvaluaciones() {
		return "mis-evaluaciones";
	}

	@GetMapping("/admin/evaluaciones")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminEvaluaciones() {
		return "admin-evaluaciones";
	}
}