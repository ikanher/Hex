# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoituksena on tarjota tietokoneella pelattava versio _Hex_ lautapelistä.

Lisätietoja itse pelistä löytyy esimerkiksi [Wikipediasta](https://en.wikipedia.org/wiki/Hex_(board_game)).

Perusversiossa voi pelata vain yhden kokoisella pelilaudalla, _11x11_.

## Käyttäjät

Koska sovelluksen luonne on kahden pelaajan peli, on käyttäjiä luonollisesti kaksi. Molemmat käyttäjistä ovat pelaajia.

## Käyttöliittymäluonnos

Sovellus koostuu neljästä eri näkymästä

1. Aloitusruutu, jossa kysytään pelaajien nimet
2. Peliruutu, jossa näytetään pelilauta ja jossa itse pelin pelaaminen suoritetaan
3. Lopetusruutu, joka tulee näkyviin pelin jälkeen. Lopetusruudulla näkyy pelin voittaja. Lopetusruudulla on myös mahdollisuus aloittaa peli uudelleen, siirtyä aloitusruutuun, tai siirtyä katselemaan yksinkertaisia tilastoja aiemmista peleistä.
4. Tilastoruutu, jossa näytetään yksinkertaisia tilastoja pelatuista peleistä (esim. kuinka monta peliä kukin pelaaja on voittanut)

## Perusversion tarjoama toiminnallisuus

### Ennen peliä

Pelaajat voivat syöttää nimensä.

### Pelin aikana

Pelaajat voivat vuorotellen sijoittaa pelinappuloita pelilaudalle.

Perusversiossa pelin lopun määrittelevät pelaajat itse, eli pelaajilla on mahdollisuus lopettaa peli ja ilmoittaa pelin voittaja.

Perusversiossa tuetaan vain yhden kokoista pelilautaa ja pelaajat joutuvat itse päättelemään voittajan.

### Pelin jälkeen

Käyttäjä voi aloittaa uuden pelin, vaihtaa pelaajia tai siirtyä katselemaan tilastoja muiden pelaajien voitoista ja tappioista.

### Tilastonäkymässä

Tilastonäkymässä näytetään tilastoja eri pelaajien voittojen ja tappioiden määrästä.

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- peli osaa tunnistaa, koska peli on voitettu
- peli näyttää voittavan polun pelin jälkeen
- pelilaudan koon valinta
- kattavammat tilastot
- värien konfiguroitavuus
- _Replay_ ominaisuus
- yksinpeli tietokonetta vastaan

