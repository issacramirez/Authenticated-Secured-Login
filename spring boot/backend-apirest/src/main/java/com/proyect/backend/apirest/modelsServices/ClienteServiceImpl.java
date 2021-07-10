package com.proyect.backend.apirest.modelsServices;

import java.util.List;

import com.proyect.backend.apirest.models.Cliente;
import com.proyect.backend.apirest.modelsDao.IClienteDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ICLienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteDao.findById(id).get();
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }

}
