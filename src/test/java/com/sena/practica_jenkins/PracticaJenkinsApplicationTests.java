package com.sena.practica_jenkins;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PracticaJenkinsApplicationTests {

	@Test
	void contextLoads() {
		// ERROR INTENCIONAL para probar la rama feature/auth-error-outlook
		org.junit.jupiter.api.Assertions.fail("Fallo simulado para validación de Outlook");
	}

}
