package br.com.caelum.leilao.test;

import br.com.caelum.leilao.desafios.AnoBissexto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestesDosDesafios {

    @Test
    public void deveRetornarAnoBissexto() {
        AnoBissexto anoBissexto = new AnoBissexto();

        assertEquals(true, anoBissexto.getAnoBissexto(2020));
        assertEquals(true, anoBissexto.getAnoBissexto(2028));
    }

    @Test
    public void naoDeveRetornarAnoBissexto() {
        AnoBissexto anoBissexto = new AnoBissexto();

        assertEquals(false, anoBissexto.getAnoBissexto(2015));
        assertEquals(false, anoBissexto.getAnoBissexto(2011));
    }
}
