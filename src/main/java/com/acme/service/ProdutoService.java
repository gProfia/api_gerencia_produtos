package com.acme.service;

import java.util.List;
import java.util.Optional;

import com.acme.ProdutoRepository.ProdutoRepository;
import com.acme.entity.Produto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;
    
    @Transactional
    public Produto cadastrar(Produto produto) {
        produto.persistAndFlush();
        return produto;
    }

    public Optional<Produto> buscar(Long id) {
        return Produto.findByIdOptional(id);
    }

    public List<Produto> listar() {
        return Produto.listAll();
    }
}
