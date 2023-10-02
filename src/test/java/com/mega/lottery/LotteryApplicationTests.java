package com.mega.lottery;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LotteryApplicationTests {

	@Test
	@DisplayName("Teste de Inicialização da Aplicação de Loteria - contextLoads()")
	void contextLoads() {
		assertDoesNotThrow(() -> LotteryApplication.main(new String[] {}));
	}

}
