package com.sena.practica_jenkins;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PracticaJenkinsApplicationTests {

	@Test
	void contextLoads() {
		// ERROR INTENCIONAL para probar notificación Discord (FAILURE)
		// Iteración 2: Simulación de fallo automático
		Assertions.fail("Error intencional - Prueba Discord FAIL");
	}

}

