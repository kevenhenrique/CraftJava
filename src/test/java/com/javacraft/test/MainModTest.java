package com.javacraft.test;
import org.junit.jupiter.api.Test;
import static org .junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class MainModTest {
    @Test
    void testeSimplesSomenteJunit(){
        int resultado = 2+2;
        assertEquals(4, resultado);
    }
    @Test
    void testeComMockito(){
        Runnable mockRunnable = mock(Runnable.class);

        mockRunnable.run();
        mockRunnable.run();

        //verifica se o método foi chamado exatamente duas vezes
        verify(mockRunnable, times(2)).run();
        
    }
}
