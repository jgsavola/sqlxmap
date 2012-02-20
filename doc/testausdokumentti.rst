SQLxMap
=======

SQLxMap --- geometriatietoa sisältävien SQL-kyselyjen visualisoiminen

Jonne Savolainen

Testaus
-------

Sovelluksen testaaminen vaatii toimivan PostgreSQL-tietokantayhteyden
olemassaoloa. TietokantayhteysTest käyttää projektin juurihakemistossa
olevaa tiedostoa nimeltä "testdatabase.properties", jonka muoto on
seuraava::

    dbHost=somehost
    dbPort=someport
    dbName=somedb
    dbUser=someuser
    dbPassword=somepassword

Yksikkötestaus on keskittynyt `sqlxmap`-pakkaukseen,
käyttöliittymäluokille ei ole yksikkötestejä. Merkittävä osa
käyttöliittymästäkin soveltuisi testattavaksi, mutta se vaatisi
tarkempaa erottelua käyttöliittymän ja sovelluslogiikan välillä.

Lisäksi esimerkiksi tietokantayhteden testaaminen asettaa haasteita
riippuvuudellaan tietokantayhteyden tiedoista, kuten
käyttäjätunnuksista ja salasanoista.

Testiluokat testaavat seuraavia asioita:

:DatabaseInfoTest:
	JDBC-URL:n muodostaminen.

	Yhteystietojen lataaminen olemattomasta
	properties-tiedostosta.

	Yhteystietojen tallentaminen ja lataaminen
	properties-tiedostosta.

:LayerDataTest:
	Geometrioiden lisääminen eri tavoin LayerData-olioon suurentaa
	ympäröivää suorakaidetta oikealla tavalla.

:SQLTarkkailtavaTest:
	Tarkista, että asetettu SQL-lause välitetään tarkkailijalle.

:SatunnainenVariTest:
	Kaksi peräkkäistä väriä ovat eri värejä.

:SettingsTest:
	Asetukset todella tallennetaan asetushakemistoon.

:TietokantayhteysTest:
	Tietokantayhteyden muodostaminen onnistuu.

	Tietokantakyselyn tekeminen onnistuu.

Eniten testausta tarvitsisivat ne toiminnot, jotka ovat
käyttöliittymäluokkien sisällä tai muuten riippuvaisia graafisesta
käyttöliittymästä. Näiden testaaminen on kuitenkin vaikeaa ennen kuin
käyttöliittymän ja sovelluslogiikan rajaa on tarkennettu.
