# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikkaa testataan monella eri tasolla automatisoidusti.

Ylimmällä tasolla on itse [pelin testaus](https://github.com/ikanher/otm-harjoitustyo/blob/master/Hex/src/test/java/GameTest.java), joka pitää huolen siitä että pelin vuoronvaihto toimii, peli tunnistaa voittaja ja ettei esimerkiksi toisen pelaajan omistamaan soluun pysty pelaamaan.

Hieman alemmalla tasolla on itse [pelilogiikan testaus](https://github.com/ikanher/otm-harjoitustyo/blob/master/Hex/src/test/java/GameLogicTest.java). Pelilogiikkaa testataan hieman syvemmällä tasolla kuin itse peliä. Mm. pelivuorojen vaihto ei ole enää logiikan harteilla vaan testataan tarkemmin erilaisia peliskenaarioita ja johtivatko ne voittoon.

Vielä alemmalla tasolla on itse [pelilaudan testit](https://github.com/ikanher/otm-harjoitustyo/blob/master/Hex/src/test/java/BoardTest.java). Nyt testauksen alla on tiedot siitä, että itse pelilauta on rakennettu oikein.

Näiden lisäksi testataan vielä [pelitilastoja tallettavan tietokannan toimintaa](https://github.com/ikanher/otm-harjoitustyo/blob/master/Hex/src/test/java/ResultDatabaseTest.java). Tietokannan testaus suoritetaan tyhjentämällä aluksi testitietokanta, jonka jälkeen tietokantaan ajetaan joitakin pelituloksia ja tutkitaan vastaavatko tietokannasta kysellyt asiat niitä, mitä sinne on testiä varten talletettu.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 88% ja haarautumakattavuus 95%.

<img src="https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/images/jacoco-report-v1.png" width="800">

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla Linux-ympäristössä siten, että sovelluksen käynnistyshakemistossa on ollut käyttöohjeen kuvauksen mukainen _config.properties_-tiedosto.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/vaatimusmaarittely.md#perusversion-tarjoama-toiminnallisuus) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi.

## Sovellukseen jääneet laatuongelmat

Mikäli tietokantayhteydessä ilmenee ongelmia, tästä ei tule ilmoitusta graafiseen käyttöliittymään, vaan ainoastaan konsolin logiin.

