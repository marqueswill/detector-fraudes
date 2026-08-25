package com.banco_real.api_transacoes.models.value_objects;

public record NationalId(String valor) {

    public NationalId {
        if (valor == null || !isValido(valor)) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private boolean isValido(String cpf) {
        return false;
    }
}
