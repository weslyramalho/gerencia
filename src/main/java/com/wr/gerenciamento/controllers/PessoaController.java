package com.wr.gerenciamento.controllers;

import com.wr.gerenciamento.entities.Pessoa;
import com.wr.gerenciamento.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas(){
        List<Pessoa> list = service.listarPessoas();
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ResponseEntity<Pessoa> novaPessoa(@RequestBody Pessoa obj){
        obj = service.novaPessoa(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> consultaPessoa(@PathVariable UUID id){
        Pessoa obj = service.consultaUmaPessoa(id);
        return ResponseEntity.ok().body(obj);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> editar(@PathVariable UUID id, @RequestBody Pessoa obj){
        obj = service.editarPessoa(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
