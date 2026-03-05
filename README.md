# Drogi Uczestniku,

Cieszę się, że wybrałeś właśnie mój warsztat :)

W celu weryfikacji środowiska na którym będziemy pracować w takcie warsztatów poproszę Cię o upewnienie się, że spełniasz wymagania dotyczące narzędzi (*Wymagania*).

## Wymagania

### Wiedza

Oczekuję od Ciebie:

- Znajomości podstaw języka Java oraz projektów typu Maven;
- Mile widziana znajomość podstaw GIT;

Niestety z uwagi na ograniczony czas warsztatów nie będzie czasu na tłumaczenie tych zagadnień od zera.

### Narzędzia

Proszę o przyniesienie ze sobą komputera z dowolnym systemem operacyjnym. Proszę o instalację:

- Wybranego IDE - najlepiej IntelliJ IDEA https://www.jetbrains.com/idea/
  lub VSCode: https://code.visualstudio.com/download

- Java JDK - wersja 17
  - http://www.oracle.com/technetwork/java/javase/downloads/

- GIT
  - Windows: https://git-scm.com/download/win
    **UWAGA: W trakcie instalacji w oknie "Adjusting your PATH environment" zaznaczcie opcje: "Use Git and optional Unix tools from the Windows Command Prompt (ostatni radio button)"**
  - Linux: https://git-scm.com/download/linux
  - Mac: https://git-scm.com/download/mac

- Node.js
  - https://nodejs.org/en

## Weryfikacja środowiska

### Java

- Wpisz w konsoli: `java -version`. Upewnij się, że została podana informacja o wersji zainstalowanej JAVY
- Wpisz w konsoli: `javac -version`. Upewnij się, że została podana informacja o wersji zainstalowanego kompilatora JAVA

### Git:

- Sklonuj repozytorium `git clone https://github.com/tklepacki/jit-academy-rest-assured`
- Wejdź do folderu z repozytorium i upewnij się, że projekt został pobrany

### IDE

- Zaimportuj projekt Maven w swoim IDE.

### Maven

- Wejdź do repozytorium.
- Uruchom polecenie `mvnw -v`. Upewnij się, że została podana informacja o wersji zainstalowanego Mavena.
- Z poziomu projektu wywołaj poniższą komendę w CMD - wykonanie jej spowoduje zbudowanie projektu oraz wykonanie jednego z testów:
  - mvnw clean install  (Windows)
  - ./mvnw clean install (Linux)

### Node.js

- Wpisz w konsoli: `node -v`. Upewnij się, iż została podana informacja o wersji zainstalowanego Node.js.

## W razie problemów
Pisz na mój adres email: *t.klepacki@wp.pl*
