# Käyttöohje

Lataa tiedosto [Hex-0.1.jar](https://github.com/ikanher/otm-harjoitustyo/releases/tag/loppupalautus)

## Konfigurointi

Ohjelma olettaa, että _src/main/resources_ hakemistossa on konfiguraatiotiedosto _config.properties_, joka määrittelee polun, sekä tiedoston, johon tietokanta luodaan. Tiedoston muoto on seuraava

```
dbPath=.
dbFile=hex.db
```

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar Hex-0.1.jar
```

## Peli

### Pelin aloitus

Pelaajat voivat syöttää nimensä, mikäli nimiä ei anneta tulee punaisen pelaajan nimeksi "Red" ja sinisen pelaajan nimeksi "Blue".

Pelaajat voivat myös valita pelilaudan koon, mikäli kokoa ei valita tulee vakio kokoinen _13x13_ pelilauta käyttöön.

Peli alkaa "Play!" nappia klikkaamalla.

### Pelin pelaaminen

Pelin tarkoituksena on yhdistää yhtenäisellä ketjulla pelaajan omat laudan päät yhteen. Sininen pelaa vasemmalta oikealla ja punainen ylhäältä alas. Sininen pelaaja siis pyrkii yhtenäisellä ketjulla yhdistämään pelilaudan vasemman ja oikean reunan.

Pelin yläreunassa lukee tieto siitä kumpi pelaaja pelaa seuraavaksi.

Itse peliä pelataan klikkaamalla vuorotellen pelisoluihin, kunnes jompikumpi pelaajista on voittanut pelin. Pelissä ei ole mahdollista päätyä tasapeliin.

Kun jompikumpi pelaajista voittaa pelin, pelimoottori tunnistaa voiton ja siirtää pelaajan lopetusruutuun.

### Pelin lopetusruutu

Lopetusruudussa ilmoitetaan pelin voittaja ja annetaan mahdollisuus pelata uudellee, palata aloitusruutuun tai lopettaa pelin pelaaminen.

Lisäksi lopetusruudussa näytetään tilastoja niistä pelaajista, jotka ovat myös hävinneet pelin voittajalle.

