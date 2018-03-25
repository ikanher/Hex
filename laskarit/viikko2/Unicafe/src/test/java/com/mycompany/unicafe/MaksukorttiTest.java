package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortiSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(3 * 100);
        assertEquals("saldo: 13.0", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(3 * 100);
        assertEquals("saldo: 7.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(15 * 100);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosRahaRiittaa() {
        assertTrue(kortti.otaRahaa(5 * 100));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosRahaEiRiita() {
        assertFalse(kortti.otaRahaa(15 * 100));
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals(10 * 100, kortti.saldo());
    }
}
