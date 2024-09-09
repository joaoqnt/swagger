package com.example.swagger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swagger.model.Venda;
import com.example.swagger.service.VendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@Tag(name = "vendas", description = "Vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping("/sem-desconto")
    @Operation(
        summary = "Obter vendas sem desconto.",
        description = "Retorna todos os itens vendidos sem desconto.",
        responses = {
            @ApiResponse(description = "Sucesso.", responseCode = "200",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Venda.class)))),
            @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
        }
    )
    public List<Venda> getVendasSemDesconto() {
        return vendaService.getVendasSemDesconto();
    }

    @GetMapping("/com-desconto")
    @Operation(
        summary = "Obter vendas com desconto.",
        description = "Retorna todos os itens vendidos com desconto.",
        responses = {
            @ApiResponse(description = "Sucesso.", responseCode = "200",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Venda.class)))),
            @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
        }
    )
    public List<Venda> getVendasComDesconto() {
        return vendaService.getVendasComDesconto();
    }

    @GetMapping("/ordenado-por-valor")
    @Operation(
        summary = "Obter vendas ordenadas por valor unitário.",
        description = "Retorna todos os itens vendidos ordenados por valor unitário do maior para o menor.",
        responses = {
            @ApiResponse(description = "Sucesso.", responseCode = "200",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Venda.class)))),
            @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
        }
    )
    public List<Venda> getVendasOrdenadasPorValor() {
        return vendaService.getVendasOrdenadasPorValor();
    }

    @GetMapping("/mais-vendido-na-nf/{idNf}")
    @Operation(
        summary = "Obter o produto mais vendido em uma NF específica.",
        description = "Retorna o produto mais vendido em uma Nota Fiscal (NF) específica, identificada pelo ID fornecido.",
        responses = {
            @ApiResponse(description = "Sucesso.", responseCode = "200",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Integer.class)))),
            @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
        }
    )
    public List<Integer> getProdutoMaisVendidoNaNf(@PathVariable int idNf) {
        return vendaService.getProdutoMaisVendidoNaNf(idNf);
    }

    @GetMapping("/nf-com-mais-dez-unidades")
    @Operation(
        summary = "Obter NFs com mais de dez unidades vendidas.",
        description = "Retorna Notas Fiscais (NF) onde mais de dez unidades de pelo menos um produto foram vendidas.",
        responses = {
            @ApiResponse(description = "Sucesso.", responseCode = "200",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Integer.class)))),
            @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
        }
    )
    public List<Integer> getNfComMaisDezUnidadesVendidas() {
        return vendaService.getNfComMaisDezUnidadesVendidas();
    }

    @GetMapping("/nf-com-valor-maior-que-500")
    @Operation(
        summary = "Obter NFs com valor total maior que 500.",
        description = "Retorna Notas Fiscais (NF) com valor total maior que 500, ordenadas do maior para o menor valor.",
        responses = {
            @ApiResponse(description = "Sucesso.", responseCode = "200",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Object[].class)))),
            @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
        }
    )
    
    public List<Object[]> getNfComValorMaiorQue500() {
        return vendaService.getNfComValorMaiorQue500();
    }
}