package br.com.caelum.leilao.test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TesteAvaliador {

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        final Usuario joao = new Usuario("João");
        final Usuario maria = new Usuario("Maria");
        final Usuario jose = new Usuario("José");

        final Leilao leilao = new Leilao("Playtation 5 Novo");

        leilao.propoe(new Lance(joao, 250));
        leilao.propoe(new Lance(maria, 300));
        leilao.propoe(new Lance(jose, 400));

        final Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 400.0;
        double menorEsperado = 250.0;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.000001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.000001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {

        Usuario joao = new Usuario("João");
        Leilao leilao = new Leilao("Playstation 5");

        leilao.propoe(new Lance(joao, 1000));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.000001);
        assertEquals(1000.0, leiloeiro.getMenorLance(), 0.000001);

    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {

        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Cadeira Gamer");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.000001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.000001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.000001);

    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(maria, 450.0));
        leilao.propoe(new Lance(joao, 120.0));
        leilao.propoe(new Lance(maria, 700.0));
        leilao.propoe(new Lance(joao, 630.0));
        leilao.propoe(new Lance(maria, 230.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {

        Usuario gabriel = new Usuario("Gabriel");
        Usuario ellen = new Usuario("Ellen");

        Leilao monitor = new Leilao("Monitor");

        monitor.propoe(new Lance(gabriel, 400.0));
        monitor.propoe(new Lance(ellen, 300.0));
        monitor.propoe(new Lance(gabriel, 200.0));
        monitor.propoe(new Lance(ellen, 100.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(monitor);

        double maiorEsperado = 400.0;
        double menorEsperado = 100.0;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.000001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.000001);

    }

    @Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(200, maiores.get(0).getValor(), 0.00001);
        assertEquals(100, maiores.get(1).getValor(), 0.00001);
    }

    @Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }

}
