package br.com.caelum.leilao.test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class leilaoTest {

    private Usuario gabriel;
    private Usuario steveJobs;
    private Usuario bill;

    @Before
    public void criarAvaliador() {
        this.gabriel = new Usuario("Gabriel");
        this.steveJobs = new Usuario("Steve Jobs");
        this.bill = new Usuario("Bill");
    }

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs, 2000.0)
                .constroi();

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosValores() {
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs, 2000.0)
                .lance(bill, 3000.0)
                .constroi();

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs, 2000.0)
                .lance(steveJobs, 3000.0)
                .constroi();

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDeCincoLancesDeUmMesmoUsuario() {
        Leilao leilao = new CriadorDeLeilao().para("Carro")
                .lance(gabriel, 13000.0)
                .lance(bill, 14000.0)
                .lance(gabriel, 15000.0)
                .lance(bill, 16000.0)
                .lance(gabriel, 17000.0)
                .lance(bill, 18000.0)
                .lance(gabriel, 19000.0)
                .lance(bill, 20000.0)
                .lance(gabriel, 21000.0)
                .lance(bill, 22000.0)
                .lance(gabriel, 23000.0) // deve ser ignorado segundo o teste
                .constroi();

        assertEquals(10, leilao.getLances().size());
        assertEquals(22000.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
    }

    @Test
    public void deveDobrarOUltimoLanceDado() {
        Leilao leilao = new CriadorDeLeilao().para("Notebook Acer")
                .lance(gabriel, 1200.0)
                .lance(bill, 1200.0)
                .constroi();

        leilao.dobraLance(gabriel);

        assertEquals(2400.0, leilao.getLances().get(2).getValor(), 0.00001);
    }

    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        leilao.dobraLance(steveJobs);
        assertEquals(0, leilao.getLances().size());
    }
}
