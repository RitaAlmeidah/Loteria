package com.mega.lottery.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SorteioService {
	
	private static final Logger logger = LoggerFactory.getLogger(SorteioService.class);
   
    private Random random = new Random();

    public Long sortearSequencia() {
    
    	logger.info("SorteioService - Iniciando Metodo sortearSequencia()");
    	
        Long numeroBilhete = random.nextLong(9999999999L);
        logger.info("Numero do Bilhete Gerado: {}", numeroBilhete );
        return numeroBilhete;
        
    }
}