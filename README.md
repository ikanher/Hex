# Hex Peli

Hex lautapeli projekti Helsingin Yliopiston _Ohjelmistotekniikan menetelmät_ kurssia varten.

Pelin tarkoituksena on molempien pelaajien pyrkiä yhdistämään omat päätynsä yhtenäisellä ketjulla. Se pelaaja, kumpi ensin yhdistää päätynsä voittaa pelin. Punainen pelaa alapäädystä yläpäätyyn ja sininen vasemmalta oikealle.

[Hex peli Wikipediassa](https://en.wikipedia.org/wiki/Hex_(board_game))

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Tuntikirjanpito](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

## Releaset

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

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Hex-0.1-SNAPSHOT-jar-with-dependencies.jar_

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

### Checkstyle

```
mvn jxr:jxr checkstyle:checkstyle
```

Checkstyle raporttia voi tarkastella avaamalla selaimella tiedosto _target/site/checkstyle.html_

