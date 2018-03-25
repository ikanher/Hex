/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author akir
 */
public class KassapaateTest {
    
    Kassapaate paate = new Kassapaate();
    Maksukortti korttiJossaTarpeeksi = new Maksukortti(10);
    Maksukortti korttiJossaLiianVahan = new Maksukortti(1);

    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void edullistenLounaidenMaaraAlussaOikein() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaidenLounaidenMaaraAlussaOikein() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void rahamaaraKasvaaEdullisenLounaanHinnallaKateisMaksu() {
        paate.syoEdullisesti(5 * 100);
        assertEquals(100250, paate.kassassaRahaa());
    }

    @Test
    public void rahamaaraKasvaaMaukkaanLounaanHinnallaKateisMaksu() {
        paate.syoMaukkaasti(5 * 100);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisenVaihtorahaOnOikein() {
        int vaihtoraha = paate.syoEdullisesti(5 * 100);
        assertEquals(250, vaihtoraha);
    }

    @Test
    public void maukkaanVaihtorahaOnOikein() {
        int vaihtoraha = paate.syoMaukkaasti(5 * 100);
        assertEquals(100, vaihtoraha);
    }
    
    @Test
    public void edullinenLounasKasvattaaMaaraa() {
        paate.syoEdullisesti(5 * 100);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukasLounasKasvattaaMaaraa() {
        paate.syoMaukkaasti(5 * 100);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenLounasKunRahaEiRiitaEiKasvataMaaraa() {
        paate.syoEdullisesti(100);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukasLounasKunRahaEiRiitaEiKasvataMaaraa() {
        paate.syoMaukkaasti(100);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenLounasKunRahaEiRiitaVaihtoRahaOnOikein() {
        int vaihtoraha = paate.syoEdullisesti(100);
        assertEquals(vaihtoraha, 100);
    }

    @Test
    public void maukasLounasKunRahaEiRiitaVaihtoRahaOnOikein() {
        int vaihtoraha = paate.syoMaukkaasti(100);
        assertEquals(vaihtoraha, 100);
    }
    
    @Test
    public void edullinenLounasKunRahaEiRiitaEiKasvataKassaa() {
        paate.syoEdullisesti(100);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void maukasLounasKunRahaEiRiitaEiKasvataKassaa() {
        paate.syoMaukkaasti(100);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanKortillaOstoToimii() {
        assertTrue(paate.syoEdullisesti(korttiJossaTarpeeksi));
    }

    @Test
    public void maukkaanLounaanKortillaOstoToimii() {
        assertTrue(paate.syoMaukkaasti(korttiJossaTarpeeksi));
    }
    
    @Test
    public void edullinenLounasVahentaaKortinSaldoa() {
        paate.syoEdullisesti(korttiJossaTarpeeksi);
        assertEquals("saldo: 7.50", korttiJossaTarpeeksi.toString());
    }

    @Test
    public void maukasLounasVahentaaKortinSaldoa() {
        paate.syoMaukkaasti(korttiJossaTarpeeksi);
        assertEquals("saldo: 6.0", korttiJossaTarpeeksi.toString());
    }
    
    @Test
    public void edullinenLounasKortillaKasvattaaMaaraa() {
        paate.syoEdullisesti(korttiJossaTarpeeksi);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukasLounasKortillaKasvataaMaaraa() {
        paate.syoMaukkaasti(korttiJossaTarpeeksi);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullistaEiVoiOstaaKunKortillaEiRahaa() {
        assertEquals(false, paate.syoEdullisesti(korttiJossaLiianVahan));
    }

    @Test
    public void maukastaEiVoiOstaaKunKortillaEiRahaa() {
        assertEquals(false, paate.syoMaukkaasti(korttiJossaLiianVahan));
    }

    @Test
    public void edullistenMaaraEiMuutuKunKortillaEiRahaa() {
        paate.syoEdullisesti(korttiJossaLiianVahan);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaidenMaaraEiMuutuKunKortillaEiRahaa() {
        paate.syoMaukkaasti(korttiJossaLiianVahan);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenEiMuutaKortinMaaraaKunRahaEiRiita() {
        paate.syoEdullisesti(korttiJossaLiianVahan);
        assertEquals("saldo: 1.0", korttiJossaLiianVahan.toString());
    }

    @Test
    public void maukasEiMuutaKortinMaaraaKunRahaEiRiita() {
        paate.syoMaukkaasti(korttiJossaLiianVahan);
        assertEquals("saldo: 1.0", korttiJossaLiianVahan.toString());
    }
    
    @Test
    public void edullisenKorttiOstoEiMuutaKassaa() {
        paate.syoEdullisesti(korttiJossaTarpeeksi);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void maukkaanKorttiOstoEiMuutaKassaa() {
        paate.syoMaukkaasti(korttiJossaTarpeeksi);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void rahaaLadattaessaKortinSaldoKasvaa() {
        paate.lataaRahaaKortille(korttiJossaLiianVahan, 500);
        assertEquals("saldo: 6.0", korttiJossaLiianVahan.toString());
    }

    @Test
    public void rahaaLadattaessaKassaKasvaa() {
        paate.lataaRahaaKortille(korttiJossaLiianVahan, 500);
        assertEquals(100500, paate.kassassaRahaa());
    }
    
    @Test
    public void rahaaLadattaessaNegatiivinenSummaEiKasvataKassaa() {
        paate.lataaRahaaKortille(korttiJossaLiianVahan, -500);
        assertEquals(100000, paate.kassassaRahaa());        
    }
}
