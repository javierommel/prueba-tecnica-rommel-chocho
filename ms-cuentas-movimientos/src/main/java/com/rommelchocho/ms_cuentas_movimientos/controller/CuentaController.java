package com.rommelchocho.ms_cuentas_movimientos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;
import com.rommelchocho.ms_cuentas_movimientos.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<?> createCuenta(@RequestBody Cuenta cuenta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.createCuenta(cuenta));
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> getAllCuentas() {
        return ResponseEntity.ok(cuentaService.getAllCuentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCuentaById(@PathVariable Long id) {
        Optional<Cuenta> cuenta = cuentaService.getCuentaById(id);
        if (cuenta.isPresent()) {
            return ResponseEntity.ok(cuenta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        Optional<Cuenta> cuentaUpdated = cuentaService.updateCuenta(id, cuenta);
        if (cuentaUpdated.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cuentaUpdated.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id) {
        Optional<Cuenta> cuenta = cuentaService.getCuentaById(id);
        if (cuenta.isPresent()) {
            cuentaService.deleteCuenta(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
