package br.com.caelum.leilao.test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.*;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TesteAvaliador {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario maria;
    private Usuario jose;
    private Usuario gabriel;
    private Usuario ellen;

    @Before
    public void criaAvalidor() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.maria = new Usuario("Maria");
        this.jose = new Usuario("José");
        this.gabriel = new Usuario("Gabriel");
        this.ellen = new Usuario("Ellen");
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        Leilao leilao = new CriadorDeLeilao().para("Playtation 5 Novo")
                .lance(joao, 1000.0)
                .lance(maria, 2000.0)
                .lance(joao, 3000.0)
                .lance(maria, 4000.0)
                .constroi();

        leiloeiro.avalia(leilao);

        double maiorEsperado = 4000.0;
        double menorEsperado = 1000.0;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.000001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.000001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
                .lance(joao, 1000)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.000001);
        assertEquals(1000.0, leiloeiro.getMenorLance(), 0.000001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Leilao leilao = new CriadorDeLeilao().para("Cadeira Gamer")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.000001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.000001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.000001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 200.0)
                .lance(maria, 450.0)
                .lance(joao, 120.0)
                .lance(maria, 700.0)
                .lance(joao, 630.0)
                .lance(maria, 230.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
        Leilao monitor = new CriadorDeLeilao().para("Monitor")
                .lance(gabriel, 400.0)
                .lance(ellen, 300.0)
                .lance(gabriel, 200.0)
                .lance(ellen, 100.0)
                .constroi();

        leiloeiro.avalia(monitor);
        double maiorEsperado = 400.0;
        double menorEsperado = 100.0;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.000001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.000001);
    }

    @Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .constroi();

        leiloeiro.avalia(leilao);
        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(200, maiores.get(0).getValor(), 0.00001);
        assertEquals(100, maiores.get(1).getValor(), 0.00001);
    }

    @Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();

        leiloeiro.avalia(leilao);
        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }

}
