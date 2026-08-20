package com.banco_real.api_transacoes.models.value_objects;

public record CPF(String valor) {
    public CPF {
        if (valor == null || !isValido(valor)) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    // TODO
    private boolean isValido(String cpf) {
        return false;
    }
}
