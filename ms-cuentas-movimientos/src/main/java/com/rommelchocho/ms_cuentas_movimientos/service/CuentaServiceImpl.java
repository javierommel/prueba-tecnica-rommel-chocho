package com.rommelchocho.ms_cuentas_movimientos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rommelchocho.ms_cuentas_movimientos.entity.ClienteReplica;
import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;
import com.rommelchocho.ms_cuentas_movimientos.exception.NotFoundException;
import com.rommelchocho.ms_cuentas_movimientos.repository.ClienteReplicaRepository;
import com.rommelchocho.ms_cuentas_movimientos.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteReplicaRepository clienteReplicaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteReplicaRepository clienteReplicaRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteReplicaRepository = clienteReplicaRepository;
    }

    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        Optional<ClienteReplica> clienteReplica = clienteReplicaRepository.findByClienteId(cuenta.getClienteId());
        if (clienteReplica.isPresent()) {
            return cuentaRepository.save(cuenta);
        } else {
            throw new NotFoundException("Cliente no existe");
        }
    }

    @Override
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);

    }

    @Override
    public List<Cuenta> getAllCuentas() {
        return (List<Cuenta>) cuentaRepository.findAll();
    }

    @Override
    public Optional<Cuenta> getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public Optional<Cuenta> updateCuenta(Long id, Cuenta cuentaNueva) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        Cuenta cuentaOptional = null;
        if (cuenta.isPresent()) {
            Cuenta cuentaDb = cuenta.orElseThrow();
            cuentaDb.setNumeroCuenta(cuentaNueva.getNumeroCuenta());
            cuentaDb.setEstado(cuentaNueva.getEstado());
            cuentaDb.setClienteId(cuentaNueva.getClienteId());
            cuentaDb.setSaldoInicial(cuentaNueva.getSaldoInicial());
            cuentaDb.setTipo(cuentaNueva.getTipo());
            cuentaOptional=cuentaRepository.save(cuentaDb);
        }
        return Optional.ofNullable(cuentaOptional);
    }

}
