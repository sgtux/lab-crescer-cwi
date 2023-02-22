package br.com.cwi.shop.controllers;

import br.com.cwi.shop.entities.Produto;
import br.com.cwi.shop.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController extends BaseController {

    @Autowired
    public ProdutoRepository repository;

    @GetMapping("/produto")
    public List<Produto> buscarProdutos(@RequestParam String categoria) {
        return repository.buscarPorCategoria(categoria);
    }
}
