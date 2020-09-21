package br.com.caelum.leilao.test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class leilaoTest {

    Usuario gabriel = new Usuario("Gabriel");
    Usuario steveJobs = new Usuario("Steve Jobs");
    Usuario bill = new Usuario("Bill");

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(steveJobs, 2000.0));
        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosValores() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        leilao.propoe(new Lance(steveJobs, 2000.0));
        leilao.propoe(new Lance(bill, 3000.0));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("Macbook Pro 15");

        leilao.propoe(new Lance(steveJobs, 2000.0));
        leilao.propoe(new Lance(steveJobs, 3000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDeCincoLancesDeUmMesmoUsuario() {
        Leilao leilao = new Leilao("Carro");

        leilao.propoe(new Lance(gabriel, 13000.0));
        leilao.propoe(new Lance(bill, 14000.0));

        leilao.propoe(new Lance(gabriel, 15000.0));
        leilao.propoe(new Lance(bill, 16000.0));

        leilao.propoe(new Lance(gabriel, 17000.0));
        leilao.propoe(new Lance(bill, 18000.0));

        leilao.propoe(new Lance(gabriel, 19000.0));
        leilao.propoe(new Lance(bill, 20000.0));

        leilao.propoe(new Lance(gabriel, 21000.0));
        leilao.propoe(new Lance(bill, 22000.0));

        // deve ser ignorado segundo o teste
        leilao.propoe(new Lance(gabriel, 23000.0));

        assertEquals(10, leilao.getLances().size());
        assertEquals(22000.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
    }

    @Test
    public void deveDobrarOUltimoLanceDado() {
        Leilao leilao = new Leilao("Notebook Acer");

        leilao.propoe(new Lance(gabriel, 1200.0));
        leilao.propoe(new Lance(bill, 1200.0));
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
