package com.banco_real.api_transacoes.models.value_objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NationalIdTest
        extends BaseValueObjectTest<NationalId> {

    @Override
    protected NationalId createValidValueObject() {
        String validCpf = "28720746865"; // Assumindo uma string limpa válida
        NationalId cpf = new NationalId(validCpf);
        return cpf;
    }

    @Override
    protected Object getExpectedRawValue(NationalId valueObject) {
        return "28720746865";
    }

    @Override
    protected void createInvalidValueObject() {
        new NationalId("123456");
    }

    @Override
    protected void createNullValueObject() {
        new NationalId(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456", "287207468651", "111.111.111-11", "287.207468-65", "287.207.46865"})
    void shouldThrowExceptionForMultipleInvalidScenarios(String invalidCpf) {
        assertThrows(IllegalArgumentException.class, () -> new NationalId(invalidCpf));
    }

}
