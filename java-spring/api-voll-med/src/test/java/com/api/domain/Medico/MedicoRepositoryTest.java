package com.api.domain.Medico;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicoRepositoryTest {
    @Test
    void testEscolherMedicoLivre() {

    }

    @Test
    void testFindAllByAtivoTrue() {

    }

    @Test
    void testFindAtivoById() {

    }
}
