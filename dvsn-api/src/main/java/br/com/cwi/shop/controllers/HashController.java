package br.com.cwi.shop.controllers;

import br.com.cwi.shop.dtos.HashDto;
import br.com.cwi.shop.entities.RainbowTableHash;
import br.com.cwi.shop.repository.RainbowTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hash")
public class HashController {

    @Autowired
    private RainbowTableRepository rainbowTableRepository;

    @PostMapping("rainbowtable")
    public ResponseEntity rainbowtable(@RequestBody HashDto hashDto) {

        RainbowTableHash rainbowTableHash = null;

        var tipo = hashDto.getTipo();

        if(tipo == 1)
            rainbowTableHash = rainbowTableRepository.findByMd5(hashDto.getHash());
        else if(tipo == 2)
            rainbowTableHash = rainbowTableRepository.findBySha1(hashDto.getHash());

        return ResponseEntity.ok(rainbowTableHash);
    }
}