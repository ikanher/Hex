# Hex Peli

Hex lautapeli projekti Helsingin Yliopiston _Ohjelmistotekniikan menetelmät_ kurssia varten.

Pelin tarkoituksena on molempien pelaajien pyrkiä yhdistämään omat päätynsä yhtenäisellä ketjulla. Se pelaaja, kumpi ensin yhdistää päätynsä voittaa pelin. Punainen pelaa alapäädystä yläpäätyyn ja sininen vasemmalta oikealle.

[Hex peli Wikipediassa](https://en.wikipedia.org/wiki/Hex_(board_game))

<img src="https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/images/gameplay.gif" />

## Dokumentaatio

[Käyttöohje](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/testaus.md)

[Tuntikirjanpito](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

## Releaset

[Loppupalautus](https://github.com/ikanher/otm-harjoitustyo/releases/tag/loppupalautus)

[Viikko6](https://github.com/ikanher/otm-harjoitustyo/releases/tag/viikko6)

[Viikko5](https://github.com/ikanher/otm-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Ohjelman suoritus

Ohjelman voi suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=hex.ui.GUI
```

### Suoritettavan jarin generointi

Komento

```
mvn package
```

Generoi hakemistoon _target_ suoritettavan jar-tiedoston _Hex-1.0.jar_

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

### Checkstyle

```
mvn jxr:jxr checkstyle:checkstyle
```

Checkstyle raporttia voi tarkastella avaamalla selaimella tiedosto _target/site/checkstyle.html_

