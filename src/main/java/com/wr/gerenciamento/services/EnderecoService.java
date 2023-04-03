package com.wr.gerenciamento.services;

import com.wr.gerenciamento.entities.Endereco;
import com.wr.gerenciamento.entities.Pessoa;
import com.wr.gerenciamento.repositories.EnderecoRepository;
import com.wr.gerenciamento.services.exceptions.DatabaseException;
import com.wr.gerenciamento.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco novoEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarEndereco(){
        return enderecoRepository.findAll();
    }
}
