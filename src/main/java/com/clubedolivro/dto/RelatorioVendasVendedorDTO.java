package com.clubedolivro.dto;

import java.time.LocalDate;

public record RelatorioVendasVendedorDTO(String nomeVendedor, Long quantidadeVendas, LocalDate dataUltimaVenda) {

}