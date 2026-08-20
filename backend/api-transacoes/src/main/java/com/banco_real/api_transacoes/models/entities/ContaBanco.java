package com.banco_real.api_transacoes.models.entities;

import java.math.BigDecimal;

public class ContaBanco {

    private BigDecimal saldo;
    private BigDecimal limiteDisponivel;

    public ContaBanco(BigDecimal saldo, BigDecimal limiteDisponivel) {
        this.saldo = saldo;
        this.limiteDisponivel = limiteDisponivel;
    }

    public void debitar(BigDecimal valor) {
        BigDecimal capacidadeTotal = this.saldo.add(this.limiteDisponivel);

        if (valor.compareTo(capacidadeTotal) > 0) {
            throw new IllegalArgumentException(
                "Saldo e limite insuficientes para a transação."
            );
        }

        if (valor.compareTo(this.saldo) <= 0) {
            this.saldo = this.saldo.subtract(valor);
        } else {
            BigDecimal valorRestante = valor.subtract(this.saldo);
            this.saldo = BigDecimal.ZERO;
            this.limiteDisponivel = this.limiteDisponivel.subtract(
                valorRestante
            );
        }
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getLimiteDisponivel() {
        return limiteDisponivel;
    }
}
