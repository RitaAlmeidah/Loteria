package com.mega.lottery.util;

public class Util {
	
	public static String mascararEmail(String email) {
        int posicaoArroba = email.indexOf('@');
        if (posicaoArroba > 0) {
            String prefixo = email.substring(0, posicaoArroba);
            String dominio = email.substring(posicaoArroba);
            String prefixoMascarado = "";

            switch (prefixo.length()) {
                case 1:
                    prefixoMascarado = prefixo;
                    break;
                case 2:
                    prefixoMascarado = prefixo.substring(0, 1) + "*";
                    break;
                case 3:
                    prefixoMascarado = prefixo.substring(0, 1) + "*" + prefixo.substring(2);
                    break;
                case 4:
                    prefixoMascarado = prefixo.substring(0, 1) + "**" + prefixo.substring(3);
                    break;
                case 5:
                    prefixoMascarado = prefixo.substring(0, 1) + "***" + prefixo.substring(4);
                    break;
                default:
                    int metade = prefixo.length() / 3;
                    int metadeRestante = prefixo.length() - metade;
                    prefixoMascarado = prefixo.substring(0, metade) + "*".repeat(metadeRestante) + prefixo.substring(metadeRestante);
                    break;
            }

            return prefixoMascarado + dominio;
        }
        return email;
    }
}
