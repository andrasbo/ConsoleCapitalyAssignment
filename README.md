# Beadandó I.
- Tárgy: Programozási technológia GY 2025/26/1
- Szerző: Boromissza András | HYCZBO

## Feladatleírás
<div style="text-align:justify;">
    <p>
        Szimuláljuk az alábbi egyszerűsített Capitaly társasjátékot! Adott néhány eltérő stratégiájú játékos és egy körpálya, amelyen különféle mezők sorakoznak egymás után. A pályát körbe-körbe újra és újra bejárják a játékosok úgy, hogy egy kockával dobva mindig annyit lépnek, amennyit a kocka mutat. A mezők három félék lehetnek: ingatlanok, szolgáltatások és szerencse mezők. Az ingatlant meg lehet vásárolni 1000 Petákért, majd újra rálépve házat is lehet rá építeni 4000 Petákért. Ha ezután más játékos erre a mezőre lép, akkor a mező tulajdonosának fizet: ha még nincs rajta ház, akkor 500 Petákot, ha van rajta ház, akkor 2000 Petákot. A szolgáltatás mezőre lépve a banknak kell befizetni a mező paramétereként megadott összeget. A szerencse mezőre lépve a mező paramétereként megadott összegű pénzt kap a játékos. Háromféle stratégiájú játékos vesz részt a játékban.
    </p>
    <p>
        Kezdetben mindenki kap egy induló tőkét (10000 Peták), majd a „mohó” játékos, ha egy még gazdátlan ingatlan mezőjére lépett, vagy övé az ingatlan, de még nincs rajta ház, továbbá van elég tőkéje, akkor vásárol. Az „óvatos” játékos egy körben csak a tőkéjének a felét vásárolja el, a „taktikus” játékos minden második vásárlási lehetőséget kihagyja. Ha egy játékosnak fizetnie kell, de nincs elégendő pénze, akkor kiesik a játékból, házai elvesznek, ingatlanjai megvásárolhatókká válnak.
    </p>
    <p>
        A játék paramétereit egy szövegfájlból olvassuk be. Ez megadja a pálya hosszát, majd a pálya egyes mezőit. Minden mezőről megadjuk annak típusát, illetve, ha szolgáltatás vagy szerencse mező, akkor annak pénzdíját. Ezt követően a fájl megmutatja a játékosok számát, majd sorban minden játékos nevét és stratégiáját. A tesztelhetőséghez fel kell készíteni a megoldó programot olyan szövegfájl feldolgozására is, amely előre rögzített módon tartalmazza a kockadobások eredményét.
    </p>
    <p>
        Adjuk meg, melyik játékos nyeri meg a játékot és mekkora vagyona (mennyi a tőkéje, milyen ingatlanokat birtokol) van ekkor!
    </p>
</div>

<div style="page-break-before:always">&nbsp;</div>
<p></p>

## Osztálydiagram
![class diagram](beadando_i.drawio.svg)

### Osztályok
- <b>Game:</b> A játékhoz szükséges elemeket (játékosok, tábla, dobások) tartalmazó osztály, felelős a játék levezetéséért
    - <b>GameSimulated:</b> Előre megadot dobásokkal példányosított játék   
    - <b>GameRandom:</b> A dobások futási időben derülnek ki
    - <b>InvalidGameParametersException:</b> Játékhoz min. 2 játékos és min. 2 mező szükséges
- <b>Player:</b> Játékos objektum osztály
    - <b>Greedy:</b> Mohó játékos
    - <b>Tactical:</b> Taktikus játékos
    - <b>Careful:</b> Óvatos játékos
    - <b>BankrupcyException:</b> Ha egy játékos nem tud fizetni, csődbe megy
- <b>Field:</b> Mező objektum osztály, tömbösítve ez alkotja a táblát
    - <b>Estate:</b> Megvásárolható és beépíthető mező
    - <b>Tax:</b> Büntető mező
    - <b>Jackpot:</b> Jutalom mező
    - <b>FullyDevelopedEstateException:</b> Egy teljesen felfejlesztett mezőt nem lehet tovább fejleszteni

<div style="page-break-before:always">&nbsp;</div>
<p></p>

## Tesztesetek
| leírás | be | ki |
|---|---|---|
| 0.txt<br>GameRandom | 0<br>0 | ERROR: Játékhoz min. 2 játékos és min. 2 mező szükséges |
| 0b.txt<br>GameSimulation | 0<br>0<br>0 | ERROR: Játékhoz min. 2 játékos és min. 2 mező szükséges |
| 1.txt<br>GameSimulation | 2<br>T 2000<br>T 2000<br>2<br>T Anna<br>G Bela<br>2 2 | ERROR: Hibás input file |
| 1b.txt<br>GameSimulation | 2<br>T 2000<br>T 2000<br>2<br>T Anna<br>G Bela<br>0 | ERROR: Hibás input file<br>megj.: szimuláció esetén a program elfogadja a 0 dobásokat |
| 2.txt<br>GameRandom | 2<br>T 2000<br>T 2000<br>2<br>T Anna<br>G Bela | Bela<br>0 |
| 2b.txt<br>GameSimulation | 2<br>T 2000<br>T 2000<br>2<br>T Anna<br>G Bela<br>2 3 4 5 6 7 8 9 10 11 12 | Bela<br>0 |
| 3.txt<br>GameRandom | 6<br>E Wellington_Heights<br>E Sterling_Manor<br>E Hamilton_Court<br>E Regency_Park<br>E Victoria_Estates<br>E Chancellor's_Hill<br>2<br>T Anna<br>G Bela | Változó, hogy ki nyer |
| 3b.txt<br>GameSimulation | 6<br>E Wellington_Heights<br>E Sterling_Manor<br>E Hamilton_Court<br>E Regency_Park<br>E Victoria_Estates<br>E Chancellor's_Hill<br>2<br>T Anna<br>G Bela<br>1 1  1 6  5 6  6 6  6 6  6 6  6 6 | Anna<br>14000<br>Sterling_Manor[Developed] |
| 4.txt<br>GameRandom | 12<br>E Wellington_Heights<br>T 1000<br>E Sterling_Manor<br>E Hamilton_Court<br>J 2000<br>T 1500<br>E Regency_Park<br>E Victoria_Estates<br>J 500<br>T 2000<br>E Chancellor's_Hill<br>J 1500<br>4<br>T Anna<br>G Bela<br>C Cecil<br>T Denes | Változó, hogy ki nyer |
| 4b.txt<br>GameSimulation | 12<br>E Wellington_Heights<br>T 1000<br>E Sterling_Manor<br>E Hamilton_Court<br>J 2000<br>T 1500<br>E Regency_Park<br>E Victoria_Estates<br>J 500<br>T 2000<br>E Chancellor's_Hill<br>J 1500<br>4<br>T Anna<br>G Bela<br>C Cecil<br>T Denes<br>7 10 6 12 3 9 8 11 2 5 10 6 12 4<br>7 8 2 9 11 5 6 7 12 4 10 8 3 9 11<br>2 6 7 5 10 8 12 4 3 11 9 7 6 5 10<br>12 2 8 11 9 4 7 6 3 12 10 5 9 11<br>2 8 7 6 12 4 3 10 9 5 11 8 6 2 7<br>12 10 4 9 5 11 3 8 6 12 7 2 2 2 2<br>2 2 8 6 12 7 3 10 4 9 11 2 | Cecil<br>12500<br>Regency_Park[Developed]<br>Sterling_Manor[Developed]<br>Hamilton_Court[Developed]<br>Hamilton_Court[Developed]<br>Regency_Park[Developed]<br>Sterling_Manor[Developed]<br>Victoria_Estates[Taken]<br>Wellington_Heights[Taken] |
