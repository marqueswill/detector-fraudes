package com.banco_real.api_transacoes.models.value_objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Superclasse genérica para testes unitários de Value Objects.
 *
 * @param <T> O tipo do Value Object sendo testado.
 */
public abstract class BaseValueObjectTest<T> {

    // --- MÉTODOS ABSTRATOS QUE A SUBCLASSE DEVE PROVER ---
    /**
     * Retorna uma instância válida do VO.
     */
    protected abstract T createValidValueObject();

    /**
     * Retorna o valor interno sanitizado/esperado da instância válida.
     */
    protected abstract Object getExpectedRawValue(T valueObject);

    /**
     * Ação que tenta instanciar o VO com valor inválido (deve disparar erro).
     */
    protected abstract void createInvalidValueObject();

    /**
     * Ação que tenta instanciar o VO com valor nulo (deve disparar erro).
     */
    protected abstract void createNullValueObject();

    @Test
    @Tag("value-object")
    @Tag("happy-path")
    void shouldCreateAndSanitizeWhenValid() {
        T vo = createValidValueObject();
        assertNotNull(vo, "The Value Object should not be null.");
        assertNotNull(getExpectedRawValue(vo), "The encapsulated value should not be null.");
    }

    @Test
    @Tag("value-object")
    void shouldThrowExceptionWhenValueIsInvalid() {
        assertThrows(IllegalArgumentException.class, this::createInvalidValueObject,
                "Creating a value object with invalid values should throw IllegalArgumentException.");
    }

    @Test
    @Tag("value-object")
    void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(RuntimeException.class, this::createNullValueObject,
                "Value object should throw runtime excpetion when instantiated with null.");
    }

    @Test
    @Tag("value-object")
    void shouldGuaranteeValueEquality() {
        T instance1 = createValidValueObject();
        T instance2 = createValidValueObject();

        assertEquals(instance1, instance2, "Two instances with the same value should be equal.");
        assertEquals(instance1.hashCode(), instance2.hashCode(), "Two instances with the same value should have the same hash code.");
    }
}
