# Kleiner Crash Course für modernes Java - low level edition

In diesem Tutorial erforschen wir die Entwicklung von modernen Java-Programmen.
Um die Grundlagen zu verstehen, konzentrieren wir uns ausschließlich auf Tools,
die von der JDK mitgeliefert werden.

Selbstredend ist uns bewußt, daß professionelle Java-Entwickler in der Praxis
eine Reihe von komplexen und höherleveligen Tools nutzen (Build Tools, IDEs und
Frameworks). Allerdings fußen all diese Tools auf einem Fundament: der JDK.

Deshalb lohnt es sich ein solides Verständnis der JDK zu erlangen.

## Der Plan

Wir entwickeln eine Webserver-Applikation, die eine CSV-Datei entgegen nimmt und
diese als HTML-Tabelle rendert und serviert.

## Voraussetzungen

- [JDK 23](https://jdk.java.net/23/)
- Ein simpler Editor
- Terminal

> Für diese Übung verzichten wir auf die Bequemlichkeiten einer IDE oder eines
> Build Tools, um ein besseres Verständnis dafür zu entwickeln, was diese Tools
> eigentlich für uns leisten.

## Die verschiedenen Workflows

Generell kann unterschieden werden, zwischen der Entwicklung von _Applikationen_ und
_Bibliotheken_.
_Bibliotheken_ werden von anderen _Bibliotheken_ und _Applikationen_ konsumiert.
_Applikationen_ werden an Endkunden vertrieben.

Da die Anwendungsfälle dieser 2 Programmtypen derart anders ist, unterscheiden sich
auch deren Vertriebsformen radikal.
_Bibliotheken_ werden in Form einer _JAR_ gebündelt, damit diese von anderen Programmen
konsumiert werden können.

_Applikationen_ hingegen werden typischerweise als ausführbares Programm (aka _executable_)
oder Installer ausgeliefert.

Für Java-Programmierer heisst dies, es gibt mindestens folgende Arbeitsphasen:

- Code & Debug: Code wird geschrieben, kompiliert und dann test-weise ausgeführt
- Library Release: Code wird in einer _JAR_ gebündelt und dann verteilt
- App Release: Code wird kompiliert und dann einem _Image_ (Exe oder Installer) gebündelt

In der Praxis gibt es noch eine sehr wichtige Komponente, die alle obigen Phasen
betrifft: *Testing*.
Aber da es in der JDK kein Tool dafür gibt, ignorieren wir das Thema erst mal.

In jeder Phase kommen unterschiedliche Tools des JDKs zum Einsatz.

- Code & Debug
    - [javac](https://docs.oracle.com/en/java/javase/23/docs/specs/man/javac.html)
    - [java](https://docs.oracle.com/en/java/javase/23/docs/specs/man/java.html)
    - [javap](https://docs.oracle.com/en/java/javase/23/docs/specs/man/javap.html)
    - [jdb](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jdb.html)
    - [jconsole](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jconsole.html)
    - [jps](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jps.html)
    - [jinfo](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jinfo.html)
    - [jstack](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jstack.html)
    - [jshell](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jshell.html)
- Library Release
    - [jar](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jar.html)
    - [jarsigner](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jarsigner.html)
    - [javadoc](https://docs.oracle.com/en/java/javase/23/docs/specs/man/javadoc.html)
- App Release
    - [jlink](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jlink.html)
    - [jpackage](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jpackage.html)
    - [jstat](https://docs.oracle.com/en/java/javase/23/docs/specs/man/jstat.html)

> Es wird von dir erwartet, daß du die obigen Spezifikationen zu rate ziehst, um die
> folgenden Aufgaben zu lösen.
> Allerdings sind in diesem Kurs _alle Hilfsmittel erlaubt_.
> Egal, ob AI, Pair Programming oder StackOverflow, nutze alle Mittle, die dich
> beim Lernen unterstützen.

## Die Architektur

Um die Features der JDK zu erforschen, wird diese kleine Demo-Applikation auf eine
etwas übertrieben komplizierte Art und Weise gebaut. Allerdings wird die Komplexität
kein Level erreichen, die uns ablenken wird, vom eigentlichen Inhalt.

Die Applikation soll aus 2 Modulen bestehen:

`server`: Der Webserver. Konsumiert `lib` als _JAR_.

`cli`: Das Kommandozeilenprogramm. Konsumiert `lib` als _JAR_.

`lib`: Die Bibliothek. Enthält die Funktionalität, CSV-Dateien zu HTML-Tabellen zu konvertieren.

Die Aufteilung der Applikation in diese Module wird einige Java Features demonstrieren
und Gelegenheit bieten, gängige Muster zu nutzen.

- [Java Module System](https://openjdk.org/projects/jigsaw/quick-start)
- [Command-Line Argument Files](https://docs.oracle.com/en/java/javase/23/docs/specs/man/java.html#java-command-line-argument-files)
- [JShell](https://docs.oracle.com/en/java/javase/23/jshell/introduction-jshell.html)

> Es gibt viele Aspekte eines Software-Projekts, die in diesem Kurs absichtlich
> undefiniert gelassen wurden: zum Beispiel _Projektlayout_.
> In solchen Fällen ist die eigene Kreativität und Gusto gefragt!

### Code Qualität und Dokumentation

Als statische Code Analyse sollen die folgenden Werkzeuge der JDK eingesetzt werden:

- [`javac -Xlint:all `](https://docs.oracle.com/en/java/javase/23/docs/specs/man/javac.html#extra-options)
- [`javadoc -Xdoclint:all`](https://docs.oracle.com/en/java/javase/23/docs/specs/man/javadoc.html#extra-javadoc-options)

Das JavaDoc soll im [Markdown-Format](https://docs.oracle.com/en/java/javase/23/javadoc/using-markdown-documentation-comments.html) verfaßt werden.

## Step By Step

Bisher hat dieses Dokument eine sehr allgemeine Einleitung zu diesem Lernprojekt
geliefert.
Nun folgt eine Serie von kleinschrittigen Aufgaben, die wir im laufe mehrerer
Sitzung behandeln können.

> Bedenke die Restriktionen, die du in diesem Kurs einhalten sollst:
> - ein simpler Editor
> - Konsole
> - JDK-Tools only

### 1. JShell kennenlernen

1. Überfliege den [Java Shell User’s Guide](https://docs.oracle.com/en/java/javase/23/jshell/) und halte diesen als Referenz bereit.
2. Nutze JShell, um eine Funktion zu entwickeln, die einen CSV-String zu einer tabellarischen Datenstruktur parst:
   `String[][] parseCsv( String input ) throws IllegalArgumentException;`.
3. Sobald du mit der Implementierung zufrieden bist, speichere die Funktion als JShell-Script.

### 2. Module kennenlernen

1. Erzeuge aus dem Inhalt des vorherigen JShell-Scripts ein vollwertiges Java-Modul.
2. Dieses Modul soll die 'lib'-Bibliothek in der Architektur werden.
3. Nutze `/open TOOLING` in JShell, um das Modul mit `javac` zu bauen
4. Sobald du mit den Parametern zum Kompilieren der `lib` zufrieden bist, speichere
   diese in einer Text-Datei, und teste ob diese als Command-Line Argument File
   genutzt werden kann.

### 3. Bytecode

1. Erzeuge in JShell leere Typen aller Art:
   ```
   interface I {}
   class C {}
   enum E {}
   record R() {}
   ```
2. Inspiziere das (visualisierte) Bytecode-Format dieser Typen mit `javap`.
   ```
   /open TOOLING
   javap(I.class)
   javap(C.class)
   javap(E.class)
   javap(R.class)
   ```
    Nutze [The Java® Virtual Machine Specification](https://docs.oracle.com/javase/specs/jvms/se23/html/index.html) als Nachschlagewerk.
3. Was ist der Unterschied zwischen `<init>` und `<clinit>`? Warum existieren diese in manchen Typen und anderen nicht?
4. Vergleiche die folgenden Klassen in `javap`:
   ```java
    class A {
        A() {
            final var c = call_me();
            System.out.println(c);
        }
        private String call_me() { return "maybe" + 7;}
    }
    class B {
        private final Supplier<String> call_me = () -> "maybe" + 7;
        B() {
            final var c = call_me.get();
            System.out.println(c);
        }
    }
   ```
    Warum ist deren Bytecode derart dramatisch anders, obwohl diese semantisch "das gleiche" tun?

### 4. Bibliothek bauen

Es ist an der Zeit die Bibliothek in `lib` zu vervollständigen und in ein Format
zu packen, das andere konsumieren können.
Das Format ist eine signierte _JAR_-Datei und *andere* in diesem Kontext werden
`server` und `cli` sein.
In einem professionellen Kontext würde die JAR auf unterschiedlichste Weisen an
Dritte verteilt werden:
- Download auf einer Webseite
- Teil eines Repositories (zum Beispiel Maven Central)
- Endpunkt eines Web-Services

1. Erweitere das `lib`-Modul um Funktion, von denen du glaubst sie seien notwendig
   für `cli` und `server`
   > Das Ziel ist: Eine CSV-Datei als HTML Tabelle darzustellen
2. Packe das Modul in eine _JAR_ mit `jar`.
3. Signiere die _JAR_ mit `jarsigner` und einem [persönlichen Schlüssel](https://docs.oracle.com/en/java/javase/23/docs/specs/man/keytool.html#examples-of-tasks-in-creating-a-keystore)

### 5. JavaDoc

Besonders im Falle von Bibliotheken, ist eine extensive Dokumentation der
öffentlichen API notwendig.
Zu diesem Zwecke kommt in Java JavaDoc zum Einsatz.

JavaDoc wird als doc comments im Quellcode verfaßt, und dann mit `javadoc` zu
einem HTML-Dokument generiert.

Klassisches JavaDoc nutzt eine Untermenge von HTML5 als Markup-Sprache mit
Java-spezifischen Zusätzen, namens __tags__.

```java
/**
* Diese Methode tut <em>es</em>!
* <p>
* <b>Das Ding</b> wird ohne Umschweife erledigt. <i>Fehlerfrei!</i>
* @param input Ein Input-Parameter!
* @returns Ein Rückgabewert!
*/
public int doTheThing( String input ) { ... }
```

Modernes JavaDoc wird in Markdown verfaßt:
```java
/// Diese Methode tut __es__!
///
/// **Das Ding** wird ohne Umschweife erledigt. _Fehlerfrei!_
/// @param input Ein Input-Parameter!
/// @returns Ein Rückgabewert!
public int doTheThing( String input ) { ... }
```

1. Verfasse ausführliche Dokumentation im Markdown-Format für alle öffentlichen
    Methoden in `lib`.
2. Generiere aus dem Modul `lib` JavaDoc-HTML mit `javadoc`.

### 6. Kommandozeilentool

Als Beispiel einer Applikation bauen wir nun ein Modul `cli`, das ein Kommandozeilentool
sein soll, das die Funktionalität der `lib`-Bibliothek hinter einem Command
Line Interface (CLI) anbietet.

Es folgt eine fiktive [Manpage](https://de.wikipedia.org/wiki/Manpage) des
Kommandozeilentools, das du nutzen kannst, um das Interface zu implementieren.

#### CLI(1) Manual Page

##### NAME
**cli** - convert CSV files to HTML tables

##### SYNOPSIS
```sh
cli [options] input_file
```

##### DESCRIPTION
The **cli** command reads a CSV (Comma-Separated Values) file and outputs an HTML table representation of its contents.

##### OPTIONS
- `-h, --help`
  Display this help message and exit.

- `-o, --output`
  Specify the output HTML file. If not provided, output is written to standard output.

- `-t, --title`
  Set the title of the HTML document. Default is "CSV to HTML Table".

- `-s, --stylesheet`
  Link to an external CSS stylesheet for styling the table.

##### EXAMPLES

Convert `data.csv` to an HTML table and write to standard output:
```sh
cli data.csv
```

Convert `data.csv` to `table.html` with a custom title:
```sh
cli -o table.html -t "My Data Table" data.csv
```

Convert `data.csv` to `table.html` with an external stylesheet:
```sh
cli -o table.html -s styles.css data.csv
```

- Nutze `javac`, um das Modul `cli` und all seine Abhängigkeiten zu bauen
- Validiere die Signatur der `lib`-JAR mit `jarsigner`
- Nutze `java`, um das Modul zu starten und zu testen

### 7. Executable bauen

Nun, da wir 2 fertige Module haben
1. `lib`, für Entwickler mit JavaDoc
2. `cli` für Power-User
ist es an der Zeit, das Modul `cli` so zu bauen und zu verpacken, daß wir es an
unsere Kunden liefern können.
Da für Kommandozeilentools kein Installer nötig ist, da Power-User fähig sind diese
eigenständig in ihr System zu integrieren, liefern wir das Modul als Image (EXE) aus.

1. Kompiliere das Modul `cli` und seine Abhängigkeiten mit `javac`
2. Verlinke das Modul `cli` mit seinen JDK-Modul-Abhängigkeiten und baue eine eigene Java Runtime, die das Modul `cli` integriert mit `jlink`
3. Nutze `jpackage` und die eigene Runtime als Image (EXE) zu Bündeln

### 8. Webserver

Mit `lib` haben wir Entwickler bedient.
Mit `cli` haben wir unsere Power-User glücklich gemacht.
Nun ist es an der Zeit die unglaublichen Fähigkeiten von `lib` auf den freien Markt zu bringen und normalen Menschen zu präsentieren!

Erschaffe das Modul `server`.
Es soll eine Abhängigkeit auf `lib` haben, und ähnlich wie `cli`, dessen Funktionen nach außen präsentieren.

`server` soll einen einfachen HTTP1.1-Webserver starten und eine HTML-Seite liefern,
die die CSV-Daten in Form einer schönen HTML-Tabelle anzeigt.

> Entscheide selbst auf welche Art und Weise Menschen in der Lage sein sollen
> CSV-Daten an den Server zu senden. Dateidialog? Kommandozeilenparameter? Ordner mit CSV-Dateien?

1. Baue das Modul `server` mit `javac`
2. Teste das Modul mit `java`

### 9. Debugging

Das Modul `server` hat nun eine Komplexität erreicht, die repräsentativ für echte
Applikationen ist. Wir haben Abhängigkeiten, einen Spin-Loop und ein komplexes Protokoll.

Deswegen ist es an der Zeit sich mit dem Thema "Debugging" zu beschäftigen.
Im speziellen geht es hier, um das Tool-gestützte Debugging, denn daß sogenannte
"printf"-Debugging ist eine Kunstform für sich, die immer eingesetzt werden kann.

Tool-basiertes Debugging, also vor allem mit einem Debugger, lohnt sich immer dann,
wenn die Applikation eine gewisse Komplexität erreicht.

Allerdings gibt es neben dem Debugger noch andere Tools, die helfen eine laufende Applikation zu verstehen.

#### jps + jinfo + jstack

1. Starte das Modul `server` mit `java`
2. Finde die __PID__ des Prozesses mit `jps`
3. Inspiziere die Ergebnisse von `jinfo` und `jstack`

#### jconsole

1. Starte das Modul `server` mit `java`
2. Starte `jconsole` und selektiere den Prozess
3. Inspiziere die Informationen, die `jconsole` anzeigt

#### jdb

1. Starte das Modul `server` mit `java`
2. Finde die __PID__ des Prozesses mit `jps`
3. Starte den Debugger `jdb` mit der __PID__
4. Setze einen Breakpoint beim Rendern der Tabelle und lade die Seite neu
5. Inspiziere den Call Stack in `jdb`

---

Alle Tools, die du oben erforscht hast, haben eine zusätzliche Superkraft:
sie funktionieren über Netzwerkgrenzen hinweg (remote), wenn richtig konfiguriert.
Welche Use-Cases kannst du dir vorstellen, die diese Kräfte ermöglichen?
In diesem Beispiel, aber auch zum Beispiel in einer Client-Server-Architektur?

### 10. Installer bauen

Wir kommen zum Finale dieses Projekts.
Wir haben fast alle Phasen eines typischen Produkt-Lebenszykluses (stark vereinfacht)
in Java durchgespielt, und eine tolle Webapplikation entwickelt.

Nun fehlt nur noch ein Installer, den unserer Enterprise-Kunden nutzen können,
um die Webseite in ihrem internen Netzwerk zu veröffentlichen.

1. Baue das Modul `server` mit `javac`
2. Linke das Modul mit `jlink`
3. Baue einen Installer mit `jpackage`
4. Setze alle nötigen Metadaten des Installers, damit das Projekt professionell aussieht
5. Verschicke den Installer an einen Menschen, für den ultimativen Qualitätstest!

---

okr
