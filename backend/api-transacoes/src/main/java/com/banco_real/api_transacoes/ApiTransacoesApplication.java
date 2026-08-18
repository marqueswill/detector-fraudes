package com.banco_real.api_transacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiTransacoesApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv
            .entries()
            .forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
            );

        SpringApplication.run(ApiTransacoesApplication.class, args);
    }
}
