package com.ufal.gerenciador_despesas.controller;

import com.ufal.gerenciador_despesas.model.Despesa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController // web service REST
@RequestMapping("/despesas") // url base para acessar
public class DespesaController {

    // lista para guardar as despesas
    private final List<Despesa> despesas = new ArrayList<>();

    // gera id incrementando automaticamente
    private final AtomicLong geradorId = new AtomicLong();


    // LISTAR TODAS AS DESPESAS - (GET)
    @GetMapping
    public List<Despesa> listarTodas() {
        return despesas; // converte a lista em JSON automaticamente
    }

    // CADASTRAR UMA NOVA DESPESA - (POST)
    @PostMapping
    public ResponseEntity<Despesa> cadastrar(@RequestBody Despesa novaDespesa) { // @RequestBody pega o JSON enviado pelo cliente e transforma em um obj Despesa

        // gera um novo ID e atribui à despesa
        novaDespesa.setId(geradorId.incrementAndGet());

        // salva na lista despesas
        despesas.add(novaDespesa);

        // retorna a despesa criada com o status HTTP 201,, retornando que foi criado
        return  new ResponseEntity<>(novaDespesa, HttpStatus.CREATED);
    }

    // DELETAR UMA DESPESA - (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        // Remove da lista se o id for igual
        boolean removido = despesas.removeIf(despesa -> despesa.getId().equals(id));

        if (removido) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Se der tudo certo (Status 204)
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Se não existir a despesa, apresentará um erro (404)
    }

}
