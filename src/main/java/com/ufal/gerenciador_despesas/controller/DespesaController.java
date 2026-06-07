package com.ufal.gerenciador_despesas.controller;

import com.ufal.gerenciador_despesas.model.Despesa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        // remove da lista se o id for igual
        boolean removido = despesas.removeIf(despesa -> despesa.getId().equals(id));

        if (removido) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // se der tudo certo (Status 204)
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // se não existir a despesa, apresentará um erro (404)
    }

    // ATUALIZAR UMA DESPESA - (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Despesa> atualizar(@PathVariable Long id, @RequestBody Despesa despesaAtualizada) {
        // procura pela despesa com o ID informado
        for (Despesa despesa : despesas) {
            if (despesa.getId().equals(id)) {
                // atualiza os campos da despesa existente
                despesa.setDescricao(despesaAtualizada.getDescricao());
                despesa.setValor(despesaAtualizada.getValor());
                despesa.setCategoria(despesaAtualizada.getCategoria());
                despesa.setMesReferencia(despesaAtualizada.getMesReferencia());

                return new ResponseEntity<>(despesa, HttpStatus.OK); // retorna a despesa atualizada com status 200
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // se não encontrar, retorna 404
    }

    // LISTAR TOTAL DE DESPESAS - (GET)
    @GetMapping("/total")
    public ResponseEntity<Map<String, Object>> obterTotal() {
        // calcula o total de todas as despesas
        BigDecimal total = despesas.stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // cria um mapa com o total e a quantidade de despesas
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("totalGasto", total);
        resultado.put("quantidadeDespesas", despesas.size());
        resultado.put("despesas", despesas);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
