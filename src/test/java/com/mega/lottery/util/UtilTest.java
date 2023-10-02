package com.mega.lottery.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    public void testMascararEmail() {
        String email1 = "joao@example.com";
        String resultado1 = Util.mascararEmail(email1);
        assertEquals("j**o@example.com", resultado1);

        String email2 = "a@example.com";
        String resultado2 = Util.mascararEmail(email2);
        assertEquals("a@example.com", resultado2);

        String email3 = "";
        String resultado3 = Util.mascararEmail(email3);
        assertEquals("", resultado3);

        String email4 = "email_sem_arroba";
        String resultado4 = Util.mascararEmail(email4);
        assertEquals("email_sem_arroba", resultado4);
    }
}
