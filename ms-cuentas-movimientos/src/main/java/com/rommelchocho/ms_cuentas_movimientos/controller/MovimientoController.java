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

import com.rommelchocho.ms_cuentas_movimientos.dto.MovimientoRequestDto;
import com.rommelchocho.ms_cuentas_movimientos.entity.Movimiento;
import com.rommelchocho.ms_cuentas_movimientos.service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<?> createMovimiento(@RequestBody MovimientoRequestDto movimientoDto) {
        Movimiento movimiento = movimientoService.createMovimiento(movimientoDto.getNumeroCuenta(),
                movimientoDto.getTipoMovimiento(), movimientoDto.getValor());
        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> getAllMovimientos() {
        return ResponseEntity.ok(movimientoService.getAllMovimientos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovimientoById(@PathVariable Long id) {
        Optional<Movimiento> movimiento = movimientoService.getMovimientoById(id);
        if (movimiento.isPresent()) {
            return ResponseEntity.ok(movimiento.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        Optional<Movimiento> movimientoUpdated = movimientoService.updateMovimiento(id, movimiento);
        if (movimientoUpdated.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(movimientoUpdated.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        Optional<Movimiento> movimiento = movimientoService.getMovimientoById(id);
        if (movimiento.isPresent()) {
            movimientoService.deleteMovimiento(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
