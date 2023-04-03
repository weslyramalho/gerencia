package com.wr.gerenciamento.services;

import com.wr.gerenciamento.entities.Pessoa;
import com.wr.gerenciamento.repositories.PessoaRepository;
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
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa novaPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
    public Pessoa consultaUmaPessoa(UUID id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElseThrow(()-> new ResourceNotFoundException(id));
    }
    public Pessoa editarPessoa(UUID id, Pessoa pessoa){
        try {
            Pessoa pes = pessoaRepository.getReferenceById(id);
            editarDados(pes, pessoa);
            return pessoaRepository.save(pes);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
    public List<Pessoa>  listarPessoas(){
        return pessoaRepository.findAll();
    }

    public void deletar(UUID id){
        try{
            pessoaRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    private void editarDados(Pessoa pes, Pessoa pessoa){
        pes.setNome(pessoa.getNome());
        pes.setDataNascimento(pessoa.getDataNascimento());
        pes.setEnderecoPrincipal(pessoa.getEnderecoPrincipal());
        pes.setEndereco(pessoa.getEndereco());
    }

}
