1. Uruchamianie

W katalogu z projektem należy w module main wykonać polecenie:

cd target
java -jar main-1.0-SNAPSHOT.jar

2. Zaimplementowany algorytm
Algorytm wykorzystuje 4 semafory:
- semafor 1: libraryAndQueue - zabezpiecza dostęp do zasobu biblioteka i kolejki.
Oznacza to, że tylko jeden wątek może jednocześnie korzystać z biblioteki i kolejki (wchodzić).
Dodatkowo nie pozwala czytelnikowi na wejscie do biblioteki jeśli jest w niej pisarz.
- semafor 2: libraryLock - zabezpiecza dostęp do biblioteki która ma 5 miejsc (reader).
Oznacza to, że tylko 5 wątków może jednocześnie korzystać z biblioteki.
- semafor 3: writerLock - zabezpiecza dostęp do pisania.
Oznacza to, że tylko jeden wątek może jednocześnie może pisać jako pisarz.
Dodatkowo nie pozwala pisarzowi na wejscie do biblioteki jeśli jest w niej czytelnik.
- semafor 4: readLock - zabezpiecza dostęp do czytania.
Oznacza to, że tylko jeden wątek może jednocześnie może zaczynać czytać jako czytelnik i kończyć czytanie.

3. Program
Program składa się z 3 modułów:
- main - zawiera klasę Main, która odpowiada za uruchomienie programu.
- logic - zawiera klasy Library, Reader i Writer które odpowiadają za obsługę biblioteki.
- utils - zawiera klasę Logging, która odpowiada za obsługę loggowania informacji błędów itp.
Testy jednostkowe zostały napisane do każdej z klas.
