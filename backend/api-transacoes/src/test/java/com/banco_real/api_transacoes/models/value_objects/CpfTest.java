package com.banco_real.api_transacoes.models.value_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CpfTest {

    @Test
    @Tag("value-object")
    @Tag("happy-path")
    void deveInstanciarCpfValido() {
        String cpfValido = "01234567890"; // Assumindo uma string limpa válida

        CPF cpf = new CPF(cpfValido);

        assertNotNull(cpf);
        assertEquals(cpfValido, cpf.valor());
    }

    @Test
    @Tag("value-object")
    void deveImpedirInstanciacaoDeCpfInvalido() {
        String cpfInvalido = "111.222.333-44"; // Formato ou cálculo incorreto

        assertThrows(IllegalArgumentException.class, () ->
            new CPF(cpfInvalido)
        );
    }
}
