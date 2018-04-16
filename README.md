# Hex Peli

Hex lautapeli projekti Helsingin Yliopiston _Ohjelmistotekniikan menetelmät_ kurssia varten.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Tuntikirjanpito](https://github.com/ikanher/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

## Komentorivitoiminnot

## Ohjelman suoritus

Ohjelman voi suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=hex.ui.GUI
```

## Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

## Checkstyle

```
mvn jxr:jxr checkstyle:checkstyle
```

Checkstyle raporttia voi tarkastella avaamalla selaimella tiedosto _target/site/checkstyle.html_

