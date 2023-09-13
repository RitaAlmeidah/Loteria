package com.mega.lottery.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class SorteioService {
   
    private Random random = new Random();

    public Long sortearSequencia() {
    
        Long numeroBilhete = random.nextLong(9999999999L);
        return numeroBilhete;
    }
}