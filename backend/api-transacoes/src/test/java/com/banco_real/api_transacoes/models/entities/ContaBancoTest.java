package com.banco_real.api_transacoes.models.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ContaBancoTest {

    @Test
    @Tag("Entity")
    @Tag("Happy Path")
    void deveLancarExcecaoQuandoDebitoForMaiorQueCapacidadeTotal() {
        ContaBanco conta = new ContaBanco(
            new BigDecimal("100.00"),
            new BigDecimal("50.00")
        );
        BigDecimal valorTransferencia = new BigDecimal("200.00");

        assertThrows(IllegalArgumentException.class, () ->
            conta.debitar(valorTransferencia)
        );
    }

    @Test
    @Tag("Entity")
    void deveConsumirLimiteDeCreditoQuandoSaldoPrincipalForInsuficiente() {
        ContaBanco conta = new ContaBanco(
            new BigDecimal("100.00"),
            new BigDecimal("50.00")
        );
        BigDecimal valorTransferencia = new BigDecimal("120.00");

        conta.debitar(valorTransferencia);

        assertEquals(new BigDecimal("0.00"), conta.getSaldo());
        assertEquals(new BigDecimal("30.00"), conta.getLimiteDisponivel());
    }
}
