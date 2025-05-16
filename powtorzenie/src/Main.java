import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        String filePath = "resources/zgony.csv"; // względna ścieżka do pliku


//        // Zad.1 Dany jest plik CSV zawierający statystykę zgonów w Polsce w 2019 roku z podziałem na grupy wiekowe i przyczyny. Przyczyny są zapisane w pierwszej kolumnie z użyciem kodu ICD-10. Druga kolumna zawiera sumaryczną liczbę zgonów z podanej przyczyny, a kolejne kolumny - liczby zgonów w przedziałach wiekowych opisanych w nagłówku.
        //Napisz klasę DeathCauseStatistic, posiadającą jako prywatne pola kod ICD-10 choroby oraz tablicę liczb zgonów w kolejnych grupach wiekowych.
        //Napisz publiczną, statyczną metodę fromCsvLine zwracającą obiekt DeathCauseStatistic na podstawie pojedynczej linii pliku CSV. Uwzględnij, że w pliku po nazwie kodu znajduje się tabulator.
        //Napisz akcesor do kodu ICD-10 przyczyny zgonu.
//
//        ArrayList<DeathCauseStatistic> stats = new ArrayList<DeathCauseStatistic>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            // pomijamy 2 pierwsze wiersze, są bezużyteczne
//            br.readLine();
//            br.readLine();
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                DeathCauseStatistic stat = DeathCauseStatistic.fromCsvLine(line);
//                stats.add(stat);
//                System.out.println(stat.getIcd10Code());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Zad.2 Wewnątrz klasy DeathCauseStatistic zdefiniuj publiczną klasę AgeBracketDeaths posiadającą publiczne, ostateczne pola typu int: young, old, deathCount zawierające granice grupy wiekowej i liczbę zgonów.
        //W klasie DeathCauseStatistic napisz metodę przyjmującą wiek i zwracającą obiekt AgeBracketDeaths odpowiadający temu wiekowi.
//
//        System.out.println(stats.get(22).getAgeBracket(22));
//
//         Zad.3 Napisz klasę DeathCauseStatisticList, wewnątrz której, w wybranej strukturze danych przetrzymywana będzie informacja o wszystkich obiektach DeathCauseStatistic. Napisz metodę repopulate, przyjmującą ścieżkę do pliku, która usuwa istniejące dane z tej struktury i zapełnia ją danymi z pliku CSV.
            //W klasie DeathCauseStatisticsList napisz metodę mostDeadlyDiseases, która przyjmie wiek oraz liczbę n, mniejszą od liczby wymienionych chorób. Metoda powinna zwrócić n-elementową listę referencji na obiekty DeathCauseStatistic odpowiadające chorobom powodującym największą liczbę zgonów w grupie wiekowej do której przynależy podany wiek. Lista powinna być posortowana malejąco.

        DeathCauseStatisticList list = new DeathCauseStatisticList();
        list.repopulate("resources/zgony.csv");

        int age = 75;
        int topN = 5;

        System.out.println("Top " + topN + " najśmiertelniejszych chorób dla wieku " + age + ":");
        List<DeathCauseStatistic> topDiseases = list.mostDeadlyDiseases(age, topN);
        for (DeathCauseStatistic stat : topDiseases) {
            System.out.println(stat.getIcd10Code());
        }
//
//        // Zad. 4
//
        //Kody ICD-10 zawsze rozpoczynają się od pojedynczej litery oraz dwóch cyfr. Podczas sprawdzania poprawności kodu należy ograniczyć się do tego warunku.
       // Dany jest plik tekstowy zawierający opis kodów listy chorób. Właściwe dane znajdują się w nim od linii 88. Większość linii ma postać:
        //KOD OPIS
       // np.
                //J12.81 Pneumonia due to SARS-associated coronaviru
        //jednak niektóre zawierają dodatkowe informacje lub są kontynuacją opisów nie mieszczących się w pojedynczej linii i powinny zostać pominięte.

       // Napisz interfejs ICDCodeTabular posiadający jedną, publiczną metodę getDescription, która dla podanego kodu choroby zwróci jej opis, lub gdy takiego kodu nie odnajdzie rzuci wyjątek IndexOutOfBoundsException. Zaimplementuj ten interfejs w dwóch klasach ICDCodeTabularOptimizedForTime oraz ICDCodeTabularOptimizedForMemory.
                //Klasa ICDCodeTabularOptimizedForTime powinna jednorazowo załadować wszystkie kody i opisy z pliku, a jej metoda getDescription powinna zwracać wartości z wybranej struktury danych w pamięci tymczasowej.
                //Klasa ICDCodeTabularOptimizedForMemory nie powinna przetrzymywać danych w pamięci operacyjnej, ale za każdym wywołaniem metody getDescription powinna otwierać plik i wyszukiwać w nim opisu.
//        ICDCodeTabular timeOptimized = new ICDCodeTabularOptimizedForTime("resources/icd10_descriptions.txt");
//        ICDCodeTabular memoryOptimized = new ICDCodeTabularOptimizedForMemory("resources/icd10_descriptions.txt");
//
//        String descTime = "N/A";
//        String descMemory = "N/A";
//        try {
//            descTime = timeOptimized.getDescription("A00");
//        } catch (IndexOutOfBoundsException e) {
//            descTime = "(not found)";
//        }
//        System.out.println("  Description (time-optimized): " + descTime);
//
//        try {
//            descMemory = memoryOptimized.getDescription("A01.00");
//        } catch (IndexOutOfBoundsException e) {
//            descMemory = "(not found)";
//        }
//        System.out.println("  Description (memory-optimized): " + descMemory);
//
//    }
    }
}