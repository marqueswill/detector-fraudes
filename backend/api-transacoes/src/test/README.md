content = """# Guia de Implementação de Testes de API com JUnit 5

Este documento estabelece a estrutura lógica e os conceitos necessários para a criação de testes de API utilizando a biblioteca JUnit Jupiter, referenciando o documento "junit-user-guide-6.1.3.pdf".

## 1. Estrutura e Ciclo de Vida

A construção de testes de API exige controle estrito sobre o estado do sistema antes e depois das requisições. O JUnit Jupiter fornece anotações de ciclo de vida para gerenciar esse estado:

- `@BeforeAll`: Executado uma vez antes de todos os testes. Útil para inicializar servidores locais ou estabelecer conexões com banco de dados. Requer que o ciclo de vida da instância de teste seja configurado como `PER_CLASS`.
- `@BeforeEach`: Executado antes de cada teste. Utilizado para inserir dados de teste específicos no banco ou redefinir o estado da aplicação.
- `@AfterEach`: Executado após cada teste. Utilizado para limpeza estrutural, garantindo isolamento.
- `@AfterAll`: Executado após todos os testes para liberar recursos.

## 2. Asserções (Validação de Resultados)

As respostas da API devem ser validadas utilizando os métodos estáticos da classe `org.junit.jupiter.api.Assertions`.

- **Validações de Status e Corpo**: Utilize `assertEquals` para comparar códigos de status HTTP e valores de retorno.
- **Múltiplas Validações**: Utilize `assertAll` para agrupar asserções. Isso permite validar vários campos de um payload JSON simultaneamente; se uma validação falhar, o sistema reportará todas as falhas do bloco juntas.
- **Testes de Exceção**: O método `assertThrows` permite verificar se a lógica interna levanta as exceções corretas perante falhas de processamento.

## 3. Testes Parametrizados para APIs

Para testar um mesmo endpoint com diferentes conjuntos de dados (cargas úteis válidas, inválidas, limites de fronteira), utilize testes parametrizados.

- Substitua a anotação padrão `@Test` por `@ParameterizedTest`.
- Defina a fonte de dados. A anotação `@CsvSource` permite passar valores delimitados por vírgula como parâmetros para o teste.
- A anotação `@MethodSource` permite referenciar métodos que retornam streams de argumentos complexos.

## 4. Direcionamento Lógico (Pseudocódigo)

A estrutura lógica de uma classe de teste de API deve seguir o modelo abaixo. A implementação técnica de clientes HTTP deve ser acoplada a esta estrutura.

```text
CLASSE TestesDeApiDeTransacoes:
    ANOTAR com @TestInstance(Lifecycle.PER_CLASS)

    METODO prepararAmbiente (ANOTAR com @BeforeAll):
        INICIAR dependências da API (banco em memória, mocks)
        DEFINIR URL base da API

    METODO limparBanco (ANOTAR com @AfterEach):
        APAGAR dados residuais das tabelas de transação

    METODO testarTransacaoComSucesso (ANOTAR com @Test, @DisplayName("Deve retornar 200 ao criar transacao valida")):
        DEFINIR payload = { "valor": 100.0, "destino": "conta-b" }
        EXECUTAR POST na rota /transacoes com payload

        VERIFICAR (assertAll):
            assertEquals(200, resposta.status)
            assertNotNull(resposta.id_transacao)
            assertEquals("sucesso", resposta.status_transacao)

    METODO testarValidacaoDeDados (ANOTAR com @ParameterizedTest, @CsvSource("0.0", "-50.0")):
        RECEBER parametro valorInvalido
        DEFINIR payload = { "valor": valorInvalido, "destino": "conta-b" }
        EXECUTAR POST na rota /transacoes com payload

        VERIFICAR:
            assertEquals(400, resposta.status)
```
