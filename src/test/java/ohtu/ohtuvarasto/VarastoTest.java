package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
	
	@Test
	public void liikaaLisaaminenTayttaaMutEiMeeYli() {
		varasto.lisaaVarastoon(200);
		
		// varastossa pitäisi olla maksimimäärä, eli 10
		assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
	}
	
	@Test
	public void liikaaOttaminenEiGeneroiTyhjastaLisaa() {
		varasto.lisaaVarastoon(6);
		
		double saatuMaara = varasto.otaVarastosta(8);
		
		assertEquals(6, saatuMaara, vertailuTarkkuus);
	}
	
	@Test
	public void lisaaVarastoonNegatiivinenMaara() {
		varasto.lisaaVarastoon(-5);
		
		// varastossa pitäisi olla edelleenkin 0
		assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
	}
	
	@Test
	public void otaVarastostaNegatiivinenMaara() {
		varasto.lisaaVarastoon(5);
		
		double saatuMaara = varasto.otaVarastosta(-5);
		
		// Otetaan negatiivinen määrä, joten saatu määrä pitäisi olla 0
		assertEquals(0, saatuMaara, vertailuTarkkuus);
	}
	
	@Test
	public void testaaToStringia() {
		varasto.lisaaVarastoon(5);
		
		String testi = "saldo = 5.0, vielä tilaa 5.0";
		
		assertEquals(testi, varasto.toString());
	}
	
	@Test
	public void negatiivinenVarastoOnNollakokoinen() {
		varasto = new Varasto(-10);
		
		// negatiivisella arvolla konstruoidun varaston tilavuus pitäisi olla 0.0
		assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
	}
	
	@Test
	public void kuormitettuVarastoNegatiivinenTilavuus() {
		varasto = new Varasto(-10, 0);
		
		// negatiivisella tilavuudella konstruoidun varaston tilavuus pitäisi olla 0.0
		assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
	}
	
	@Test
	public void kuormitettuVarastoTilavuusOikein() {
		varasto = new Varasto(5, 0);
		
		assertEquals(5, varasto.getTilavuus(), vertailuTarkkuus);
	}
	
	@Test
	public void kuormitettuVarastoNegatiivinenAlkusaldo() {
		varasto = new Varasto(10, -10);
		
		// negatiivisen alkuSaldon pitäisi olla nolla
		assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
	}

}