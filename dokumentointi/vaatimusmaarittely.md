# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoituksena on tarjota tietokoneella pelattava versio _Hex_ lautapelistä.

Lisätietoja itse pelistä löytyy esimerkiksi [Wikipediasta](https://en.wikipedia.org/wiki/Hex_(board_game)).

Perusversiossa voi pelata neljällä erikokoisella laudalla _7x7_, _11x11_, _13x13_ tai _19x19_.

## Käyttäjät

Koska sovelluksen luonne on kahden pelaajan peli, on käyttäjiä luonollisesti kaksi. Molemmat käyttäjistä ovat pelaajia.

## Käyttöliittymäluonnos

Sovellus koostuu kolmesta eri näkymästä

1. Aloitusruutu, jossa kysytään pelaajien nimet ja pelilaudan koko
2. Peliruutu, jossa näytetään pelilauta ja jossa itse pelin pelaaminen suoritetaan
3. Lopetusruutu, joka tulee näkyviin pelin jälkeen. Lopetusruudulla näkyy pelin voittaja. Lopetusruudulla on myös mahdollisuus aloittaa peli uudelleen, lopettaa pelin pelaaminen tai palata alkuruutuun, jossa voi muuttaa pelin asetuksia. Lopetusruudulle näytetään myös tilastoja siitä, ketkä muut pelaajat ovat hävinneet pelin voittajalle ja kuinka monta kertaa.

## Perusversion tarjoama toiminnallisuus

### Ennen peliä

Pelaajat voivat syöttää nimensä ja valita pelilaudan koon.

### Pelin aikana

Pelaajat voivat vuorotellen sijoittaa pelinappuloita pelilaudalle.

Kun jompikumpi pelaajista voittaa pelin, tunnistaa peli automaattisesti voittajan ja siirrytään lopetusruutuun.

### Pelin jälkeen

Käyttäjä voi aloittaa uuden pelin, lopettaa pelin, palata pelin asetuksiin. Lisäksi pelaajille näytetään tietoa ketkä muut pelaajat - ja kuinka monta kertaa - ovat hävinneet pelin voittajalle.

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- peli näyttää voittavan polun pelin jälkeen
- kattavammat tilastot
- värien konfiguroitavuus
- _Replay_ ominaisuus
- yksinpeli tietokonetta vastaan

