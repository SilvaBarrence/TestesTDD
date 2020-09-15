package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Assert;
import org.junit.Test;

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

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.000001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.000001);
    }
}
