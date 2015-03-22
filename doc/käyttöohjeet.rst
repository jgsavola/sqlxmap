SQLxMap
=======

SQLxMap --- geometriatietoa sisältävien SQL-kyselyjen visualisoiminen

Jonne Savolainen

Käyttöohjeet
------------

Sovelluksen käyttäminen vaatii toimivan PostgreSQL-tietokantayhteyden
olemassaoloa. Sovellus lukee tietokanta-asetukset tiedostosta, joka
muodostetaan seuraavasti::

    System.getProperty("user.home") + "/" + ".sqlxmap/databases.properties"

Tiedoston muoto on on seuraava::

    dbHost=somehost
    dbPort=someport
    dbName=somedb
    dbUser=someuser
    dbPassword=somepassword

Kyselyissä käytettävä kartta-aineisto (1:1000000 karttatietokanta) on
ladattavissa ilmaiseksi Maanmittauslaitoksen sivuilta:

  http://www.maanmittauslaitos.fi/ilmaisetaineistot

Sovelluksen käynnistäminen::

    java -cp dist/lib/jts-1.12.jar:dist/lib/postgresql-8.3-603.jdbc3.jar -jar dist/SQLxMap.jar

Sovellus suorittaa heti muutaman kysely tietokantaan ja näyttää ne
karttaikkunassa.

Sovellus täyttää Kyselyikkunan valmiilla esimerkkikyselyillä. Osa
kyselyistä tarvitsee MML:n 1:1m aineiston, mutta osa kyselyistä ei
käytä mitään taulua vaan generoi geometriat laskennallisesti
("Verkosto maantieteellisissä koordinaateissa" ja "Mandelbrot").

Huom! Karttanäkymän lähennys, loitonnus ja vierittäminen tapahtuu
tässä versiossa vakiomuutoksella. Tämä tarkoittaa, että sovelluksella
ei voi lähentää suuriin mittakaavoihin.

Toiminnot
---------

* Karttanäkymän vierittäminen:

  Nuolinäppäimet (tai "H", "J", "K", "L")

* Karttanäkymän lähentäminen:

  Plus (tai hiiren rullapainike)

* Karttanäkymän loitontaminen:

  Miinus (tai hiiren rullapainike)

* Karttatason läpinäkyvyyden muuttaminen:

  Valitse taso tasovalikosta ja muuta läpinäkyvyyttä
  liukukytkimestä. Erityisesti kysely "Hae Helsinki ja
  rekursiivistesti kaikki kunnat, jotka ..." on mielenkiintoinen (ja
  paljastaa pienen kombinatoriikkaan liittyvän ongelman rekursiivisen
  kyselyn muotoilussa...)

* SQL-kyselyn suorittaminen Kyselyikkunassa:

  1. Avaa Kyselyikkuna Tietokantakysely-painikkeesta.
  2. Muokkaa kyselyjä ja paina "Suorita"-painikketta; tai
  3. Valitse kyselyjä Kyselyikkunassa tai missä tahansa muussa
     ikkunassa (selain, tekstieditori, terminaali, ...) ja paina
     "Suorita valinta".

* SQL-kyselyn suorittaminen leikepöydältä:

  1. Valitse kyselyjä missä tahansa ikkunassa (selain, tekstieditori,
     terminaali, ...)
  2. Paina "Tietokantakysely leikepöydältä" -nappia tai välilyöntiä (SPACE).

* Kaikkien karttatasojen poistaminen:

  Paina "Poista karttatasot".

* Näytä kaikki karttatasot:

  Paina "Näytä kaikki" (tai "R")

* Näytä käyttöohjeet:

  Help ==> Contents

* Näytä tietoja ohjelmasta:

  Help ==> About

* Lopeta ohjelma:

  File ==> Exit

SQL-kyselyjä
------------

Esimerkkikyselyjä (suurin piirtein samat kyselyt ovat sovelluksen
kyselyikkunassa)::

    --
    -- Tee verkosto maantieteellissä koordinaateissa.
    --
    SELECT ST_Transform(ST_SetSRID(ST_MakePoint(x.x, y.y), 4326), 3067)
           FROM (SELECT generate_series(10, 40, 1) x) x
           JOIN (SELECT generate_series(50, 80, 1) y) y
           ON true;
    
    -- Maakuntia
    SELECT the_geom FROM miljoona.maaku1_p;
    
    -- Rannikkoviivaa
    SELECT the_geom FROM miljoona.coast_l;
    
    -- Rautateitä
    SELECT the_geom FROM miljoona.railway;
    
    -- Kaupunkeja, joissa yli 3000 asukasta
    SELECT the_geom FROM miljoona.cityp WHERE asulkm1999 >= 3000;
    
    -- Seutukuntia Ahvenanmaan suunnalla
    SELECT the_geom FROM miljoona.kunta1_p WHERE seutukunta::int BETWEEN 200 AND 300;
    
    -- Helsinki
    SELECT the_geom FROM miljoona.kunta1_p WHERE kunta_ni1 = 'Helsinki';

::
    
    --
    -- Hae Helsinki ja rekursiivisesti kaikki kunnat, jotka 'koskettavat'
    -- Helsinkiä, ts. niiden leikkaus on piste tai viiva.
    --
    -- Rekursion päättymisehto on tässä: t.n < 5
    --
    -- Varoitus! Suoritusaika kasvaa eksponentiaalisesti, 't.n < 7':kin
    -- alkoi jo epäilyttää.
    --
    WITH RECURSIVE t(the_geom, n) AS (
         SELECT the_geom, 1 FROM miljoona.kunta1_p WHERE kunta_ni1 = 'Helsinki'
       UNION ALL
         SELECT kunta1_p.the_geom, t.n + 1 
         FROM miljoona.kunta1_p, t
             WHERE ST_Touches(t.the_geom, kunta1_p.the_geom) AND t.n < 5
    )
    SELECT ST_Union(the_geom) FROM t
    GROUP BY t.n
    ORDER BY t.n DESC;
    
::

    --
    -- Mandelbrot SQL:llä
    -- (vaatii Common Table Expression (CTE)-tuen).
    --
    -- Kysely sovitettu tästä esimerkistä
    --
    --  http://wiki.postgresql.org/wiki/Mandelbrot_set
    --
    -- niin, että Ascii-merkit on korvattu erikokoisilla
    -- pisteillä.
    --
    --
    WITH RECURSIVE
    x(i)
    AS (
        VALUES(0)
    UNION ALL
        SELECT i + 1 FROM x WHERE i < 101
    ),
    Z(Ix, Iy, Cx, Cy, X, Y, I)
    AS (
        SELECT Ix, Iy, X::float, Y::float, X::float, Y::float, 0
        FROM
            (SELECT -2.2 + 0.031 * i, i FROM x) AS xgen(x,ix)
        CROSS JOIN
            (SELECT -1.5 + 0.031 * i, i FROM x) AS ygen(y,iy)
        UNION ALL
        SELECT Ix, Iy, Cx, Cy, X * X - Y * Y + Cx AS X, Y * X * 2 + Cy, I + 1
        FROM Z
        WHERE X * X + Y * Y < 16.0
        AND I < 27
    ),
    Zt (Ix, Iy, I) AS (
        SELECT Ix, Iy, MAX(I) AS I
        FROM Z
        GROUP BY Iy, Ix
        ORDER BY Iy, Ix
    )
    SELECT ST_Translate(
    	   ST_Buffer(
                   ST_MakePoint(3000*Ix, 3000*Iy), 
    	       20 + I*25),
    	       -150000, 7100000, 0)
    FROM Zt;
