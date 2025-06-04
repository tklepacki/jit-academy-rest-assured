# Drogi Uczestniku,

Cieszę się, że wybrałeś właśnie mój warsztat :)

W celu weryfikacji środowiska na którym będziemy pracować w takcie warsztatów poproszę Cię o upewnienie się, że spełniasz wymagania dotyczące narzędzi (*Wymagania*).

## Przydatne Linki

- Notatki: [https://docs.google.com/document/d/1nD0LaXUQAcfXc-sxXYAF36NUdBITMNhJqeBmf4KgAPg/edit?usp=sharing](https://docs.google.com/document/d/1WU_bLqaD57SbHK-DTpEDUedx_KHcWOK1Ys0es2zSgSU/edit?usp=sharing)
  
## Wymagania

### Narzędzia

Na warsztaty przybądź zaopatrzony w laptopa. **WAŻNE: Upewnij się, iż laptop nie jest obwarowany, żadnym blokadami, typu brak uprawnień do instalacji narzędzi, ograniczenia dostępu do sieci (VPN) itp.**

Dodatkowo proszę o instalację:

- Wybranego IDE - najlepiej IntelliJ IDEA [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/) lub Visual Studio Code: [https://www.eclipse.org/downloads/](https://code.visualstudio.com/)

- Java JDK - wersja 24.0.1
  - [http://www.oracle.com/technetwork/java/javase/downloads/](https://www.oracle.com/java/technologies/downloads/)

- GIT
  - Windows: [https://git-scm.com/download/win](https://git-scm.com/download/win)
    **UWAGA: W trakcie instalacji w oknie "Adjusting your PATH environment" zaznaczcie opcje: "Use Git and optional Unix tools from the Windows Command Prompt (ostatni radio button)"**
  - Linux: [https://git-scm.com/download/linux](https://git-scm.com/download/linux)
  - Mac: [https://git-scm.com/download/mac](https://git-scm.com/download/mac)
    
- Node.js
  - [https://nodejs.org/en](https://nodejs.org/en)

## Weryfikacja środowiska

### Java

- Wpisz w konsoli: `java -version`. Upewnij się, że została podana informacja o wersji zainstalowanej JAVA
- Wpisz w konsoli: `javac -version`. Upewnij się, że została podana informacja o wersji zainstalowanego kompilatora JAVA

### Git:

- Utwórz fork repozytorium: [https://github.com/tklepacki/jit-academy-rest-assured](https://github.com/tklepacki/jit-academy-rest-assured), a następnie sklonuj zforkowowane repozytorium 
- Wejdź do folderu z repozytorium i upewnij się, że projekt został pobrany

### IDE

- Zaimportuj projekt Maven w swoim IDE.

### Maven

- Wejdź do repozytorium.
- Uruchom polecenie `mvnw -v`. Upewnij się, że została podana informacja o wersji zainstalowanego Mavena.
- Z poziomu projektu wywołaj poniższą komendę w CMD - wykonanie jej spowoduje zbudowanie projektu oraz wykonanie jednego z testów:
  - `mvnw clean install`  (Windows)
  - `./mvnw clean install` (Linux)

### Node.js

- Wpisz w konsoli: `node -v`. Upewnij się, iż została podana informacja o wersji zainstalowanego Node.js.

## W razie problemów
Pisz na mój adres email: *t.klepacki@wp.pl*
