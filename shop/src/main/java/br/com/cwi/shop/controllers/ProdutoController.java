package br.com.cwi.shop.controllers;

import br.com.cwi.shop.entities.Produto;
import br.com.cwi.shop.repository.CashCardRepository;
import br.com.cwi.shop.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    public ProdutoRepository repository;

    @Autowired
    public CashCardRepository cashCardRepository;

    @GetMapping("/product")
    public List<Produto> getProduct(){
        return repository.getAll();
    }

    @GetMapping("/produto/")
    public List<Produto> buscarPorCategoria(@RequestParam String categoria) {
        return repository.buscarPorCategoria(categoria);
    }
}
