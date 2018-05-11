# Arkkitehtuurikuvaus

## Rakenne

Ohjelma on rakenteeltaan jaettu kolmeen eri pakkaukseen.

* hex.ui

Sisältää sovelluksen käyttöliittymään liittyvän koodin, joka on JavaFX:llä toteutettu ja hyödyntää itse pelilaudan rakentamisessa upeata [Hexameter](https://github.com/Hexworks/hexameter) kirjastoa.

* hex.domain

Sisältää ei-logiikkaa sisältävät pelin osat kuten pelilauta, pelisolu, pelaaja.

* hex.logic

Sisältää itse pelin logiikkaan liittyvät luokat.

* hex.database

Tietokantatoiminnallisuuden sisältävät luokat.

## Luokkakaavio

Kuvataan vielä eri luokkien välisiä yhteyksiä luokkakaaviolla

<img src="https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/images/class-diagram-v4.png" />

## Käyttöliittymä

Käyttöliittymän rakentava koodi löytyy luokasta [GUI](https://github.com/ikanher/otm-harjoitustyo/blob/master/Hex/src/main/java/hex/ui/GUI.java) ja se on JavaFX:llä rakennettu.

Käyttölittymä sisältää kolme erilaista näkymää

* aloitusnäkymä
* pelinäkymä
* loppunäkymä

Jokainen näkymistä luo omana [Scene](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/arkkitehtuuri.md#sovelluslogiikka) olionaan. Näkymistä yksi kerrallaan on näkyvänä ja sijoitettu siis silloin sovelluksen [stageen](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/arkkitehtuuri.md#sovelluslogiikka).

Aloitusnäkymä sisältää pääasiassa pelin konfigurointiin liittyviä kysymyksiä, kuten pelaajien nimet ja pelilaudan koko. Tästä vastaa GUI luokan metodi startScreen.

Pelinäkymä on monimutkaisin kaikista käyttöliittymäkomponenteista ja sen rakentamiseen on käytetty [Hexameter](https://github.com/Hexworks/hexameter) kirjastoa.

Hexameter kirjaston solut muunnet JavaFX:n [Polygon](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Polygon.html) objekteiksi, jonka jälkeen jokaiseen Polygoniin kiinnitetään oma onClick -käsittelijänsä, joka yhdistää pelaajien klikkaukset pelilogiikkaan.

Pelinäkymän luomisesta vastaa GUI luokan metodi gameScreen.

Lopetusnäkymä sisältää tällä hetkellä tiedon pelin voittajasta, mahdollisuuden pelata uusintapeli, palata aloitusruutuun tai lopettaa peli, sekä tilastoja voittajan aiemmista voitoista. Lopetusnäkymän piirtämisestä vastaa GUI luokan metodi gameEndScreen.

## Päätoiminnallisuudet

Pelin suurin toiminnallisuus on yksittäisen solun klikkaaminen ja kuvataankin sitä hieman tarkemmin sekvenssikaavion avulla

### Pelisolun klikkaaminen

Kun käyttäjä klikkaa pelilaudalla tyhjään soluun niin, että peli ei päädy voittoon toimii sovelluslogiikka seuraavasti:

<img src="https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/images/click-on-cell-sequence-v3.png" />

