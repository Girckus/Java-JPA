package com.clubedolivro.dto;

import java.time.LocalDate;

public record RelatorioLivrosVendidosDTO(String nomeVendedor, Long quantidadeVendas, LocalDate dataUltimaVenda) {

}