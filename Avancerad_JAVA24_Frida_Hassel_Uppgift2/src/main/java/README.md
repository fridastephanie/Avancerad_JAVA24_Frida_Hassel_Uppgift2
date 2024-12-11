# Uppgift 2 - *Filbaserat Studenthanteringssystem*

### <ins>Om projektet</ins>
Detta är ett konsolbaserat Java-system för att hantera studentuppgifter.
Systemet gör det möjligt för användare att lägga till, visa, söka efter och ta bort studenter.
Projektet använder Singleton-mönstret för att säkerställa en enda instans av studenthanteraren och en HashMap för att lagra studentdata i minnet.
Studentdata sparas också i en textfil för att bevara sparad information mellan sessioner.

Jag valde HashMap som datastruktur för projektet eftersom man får direkt åtkomst till varje student baserat på deras unika ID *(nyckel)*
och därmed blir det enkelt att lägga till, ta bort och hitta en specifik student i applikationen.

### <ins>Så här kör du programmet</ins>
1. Klona eller ladda ner projektet
2. Öppna projektet i en IDE (t.ex. IntelliJ IDEA)
3. Kör Main-klassen för att starta applikationen

### <ins>Programanvändning</ins>
Följ menyvalen som skrivs ut när applikationen startas:
1. *Lägg till ny student*
2. *Sök student via ID*
3. *Visa alla studenter (inklusive nytillagda)*
4. *Spara nytillagda studenter till fil*
5. *Visa alla sparade studenter från fil*
6. *Ta bort student*
7. *Radera alla studenter*
8. *Avsluta*

* Vid menyval 1 får användaren mata in studentens ID, namn och betyg
* Vid menyval 2 får användaren mata in ett student-ID för att visa studentens uppgifter
* Vid menyval 3 skrivs alla studenter ut, inklusive de som ännu inte sparats till textfilen
* Vid menyval 4 sparas alla tillagda studenter i textfilen
* Vid menyval 5 skrivs alla sparade studenter från textfilen ut
* Vid menyval 6 får användaren mata in ett student-ID för att ta bort en student (med bekräftelse)
* Vid menyval 7 raderas alla studenter (med bekräftelse)
* Vid menyval 8 avslutas programmet (med bekräftelse)

Programmet hanterar ogiltiga inmatningar och visar användarvänliga meddelanden.

---
*OBS! Om felmeddelandet "Fel vid filinläsning: students.txt (Det går inte att hitta filen)" dyker upp i början av programkörningen, behöver du lägga till en student med menyval 1 och sedan spara till en textfil med menyval 4. Då skapas textfilen students.txt och stannar därefter kvar i programmet, även om listan töms. Problemet uppstår om textfilen inte följer med vid projektkloningen/nedladdningen.*

---

### <ins>Projektstruktur</ins>
Applikationen är uppdelad i följande paket:
* main: innehåller klassen Main som <ins>startar applikationen</ins>
* ui: innehåller klassen Menu som hanterar användargränssnittet och felhantering
* logger: innehåller klassen FileLogger som ansvarar för att spara och läsa studentdata från textfilen "students.txt"
* student: innehåller klasser som ansvarar för att hantera studentinformation:
    * Student: en behållare för studenternas egenskaper (Id, namn och betyg)
    * StudentManager: hanterar all studentlogik och kommunicerar med FileLogger för att läsa och skriva studentdata till textfil. Singleton-mönstret används för att säkerställa en enda instans av StudentManager

### <ins>Övrigt</ins>
* Programmet är skapat i IntelliJ IDEA 2024.2.3 med JDK 23.0.1
* Utskrifterna i programmet är på svenska