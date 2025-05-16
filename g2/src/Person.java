import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;


public class Person implements Comparable<Person>{
    private String name;
    // private int age;
    private String surname;
    private LocalDate date;
    private LocalDate deathDate;
    private Set<String> hobbies;
    private Set<Person> friends;
    private Set<Person> children;
    private Set<Person> parents;

    public void setName(String name){
        this.name=name;
    }

   // public void setAge(int age){
     //   this.age=age;
   // }

    public Set<Person> getParents() {
        return parents;
    }

    public String getName(){
        return name;
    }

   // public int getAge(){
      //  return age;
   // }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDate(LocalDate date){
        this.date=date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public LocalDate getDeathDate(){
        return deathDate;
    }
    public Person (String name, String surname, LocalDate date, LocalDate deathDate){
        this.name=name;
        //this.age=age;
        this.surname=surname;
        this.date=date;
        this.deathDate=getDeathDate();
        this.hobbies= new HashSet<>();
        this.friends=new HashSet<>();
        this.children=new HashSet<>();
        this.parents = new HashSet<>();
    }

    public boolean addHobby(String hobby){
        if (hobby == null) return false;
        return this.hobbies.add(hobby);
    }

    public boolean addFriend(Person person){
        if(person==null) return false;
        return this.friends.add(person);
    }
    public boolean adopt(Person child){
        if(child==null) return false;
        return this.children.add(child);
    }

    //public boolean isYoungerThan( Person other ){
      //  if(other==null) return false;
      //  return this.age>other.age;
    //}

    public Person getYoungestChild(){
        if(this.children == null || this.children.isEmpty()) return null;
        Person youngest = this.children.iterator().next();

        for (Person child : this.children) {
            if (youngest.compareTo(child) < 0) youngest = child;
        }
        return youngest;
    }

   // public Person findYoungest(Set<Person> people){
    //    if(people==null || people.isEmpty()) return null;
    //    Person youngest = null;
     //   for(Person p : people){
     //       if(youngest == null || p.isYoungerThan(youngest) ) {
      //          youngest=p;
      //      }
      //  }
      //  return youngest;

    //}

   // public Person getOldestChild(){
        //if(children == null || this.children.isEmpty()) return null;
        //Person oldest=null;
        ///for(Person p : children){
         //   if(oldest==null || p.age>oldest.age){
         //       oldest=p;
          //  }
        //} return oldest;
    //}

    public List<String> getChildrenNames(){
        List <String> names = new ArrayList<>();
        for(Person child :  children){
            names.add(child.name);
        }
        return names;
    }

    //public boolean hasChildrenOlderThan(int age){
     //   if(children==null || children.isEmpty() ) return false;
      //  int c=0;
      //  for (Person p : children){
       //     if (p.age>age) c++;
     //   } return true;
  //  }

    public int getNumberOfChildren(){
        if (children==null || children.isEmpty()) return 0;
        int c=0;
        for(Person p : children){
            c++;
        } return c;
    }

    public int getNumberOfChildren2(){
        return (children == null ? 0 : children.size());
    }

   // public int compareTo(Person other){
    //    return Integer.compare(this.age, other.age);
   // }

    public int compareTo(Person other) {
        if (this.date.isAfter(other.getDate())) {
            return 1;
        } else if (this.date.isBefore(other.getDate())) {
            return -1;
        } else {
            return 0;
        }
    }
    public static List<Person> sortPeople(Set<Person> people){
        List <Person> sortedList = new ArrayList<>(people); // konwersja Set → List
        Collections.sort(sortedList); // sortowanie wg compareTo()
        return sortedList;
    }

    //Ponieważ starsza osoba ma mniejszy wiek, to najmłodsza osoba będzie na końcu posortowanej listy.
    //jeśli children jest puste → return null
    //w przeciwnym razie sortuje zbiór dzieci i zwraca ostatni element listy

    public Person getYoungestChildren2(){
        if(children==null||children.isEmpty()) return null;
        List<Person> sortedChildren = new ArrayList<>(children);
        Collections.sort(sortedChildren);
        return sortedChildren.get(sortedChildren.size()-1);
    }

    public List <Person> getChildren(){
        List <Person> xx = new ArrayList<>(children);
        Collections.sort(xx);
       // xx.sort(Collections.reverseOrder()); // sortowanie malejąco po wieku
        return xx;
    }

    public  static Person fromCsvLine(String line) throws NegativeLifespanException {
        String[] tekst=line.split(", ");
        //String[] nameSurname = tekst[0].split(" ");
        String name = tekst[0].split(" ")[0];
        String surname = tekst[1].split(" ")[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate date = LocalDate.parse(tekst[1],formatter);
        LocalDate deathDate = null;
        if (!tekst[2].isEmpty()) {
            deathDate = LocalDate.parse(tekst[2], formatter);
            System.out.println("Data urodzin = " + date);
            System.out.println("Data śmierci = " + deathDate);
            if (deathDate.isBefore(date)) {
                throw new NegativeLifespanException("Data śmierci osoby " + name + " " + surname + " nie zgadza się");
            }
        }
        return new Person(name, surname, date, deathDate);
    }
    //public static Person fromCsvLine(String line) {
      //  String[] fields = line.split(",");
        //String[] imieNazwisko = fields[0].split(" ");

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        //String birthString = fields[1];
       // String deathString = fields[2];
        //LocalDate birth = null, death = null;
        //if(!birthString.isEmpty())
        //    birth = LocalDate.parse(birthString, formatter);
        //if(!deathString.isEmpty())
          //  death = LocalDate.parse(deathString, formatter);

        //  return new Person(imieNazwisko[0], imieNazwisko[1], birth, death);
    //}

    public String describe(){
            return name + " " + surname + " urodzony " + date + (deathDate!=null ? "zmarl " + deathDate : " zyje");
    }

    public static List<Person> fromCsv(String path) throws NegativeLifespanException{

        List<Person> ppl = new ArrayList<>();
        Set<String> pplFullNames = new HashSet<>();
        Map<String, Person> pplMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = reader.readLine()) != null) { // 3 - parent1, 4 - parnet2
                Person person = Person.fromCsvLine(line);
                String[] lineParts = line.split(",");
                String fullName = person.getName() + " " + person.getSurname();
                if (pplFullNames.contains(fullName)) {
                    throw new AmbiguousPersonException("W pliku istnieje osoba o takim imieniu");
                }
                if (person != null) {
                    pplFullNames.add(fullName);
                    ppl.add(person);
                    pplMap.put(fullName, person);

                    if (lineParts.length > 3){
                        if (!lineParts[3].isEmpty()) {
                            Person parent1 = pplMap.get(lineParts[3]);
                            if (parent1 != null) {
                                try {
                                    checkParentngAge(parent1, person);
                                    parent1.adopt(person);
                                } catch (ParentingAgeException e) {
                                    System.out.println("Błąd " + e.getMessage());
                                    System.out.println("Czy na pewno dodać?");
                                    Scanner scanner = new Scanner(System.in);
                                    String input = scanner.nextLine();
                                    if (input.equalsIgnoreCase("y")) {
                                        parent1.adopt(person);
                                    }
                                }

                            }
                        }
                    }

                    if (lineParts.length > 4) {
                        if (!lineParts[4].isEmpty()) {
                            Person parent2 = pplMap.get(lineParts[4]);
                            if (parent2 != null) {
                                try {
                                    checkParentngAge(parent2, person);
                                    parent2.adopt(person);
                                } catch (ParentingAgeException e) {
                                    System.out.println("Błąd " + e.getMessage());
                                    System.out.println("Czy na pewno dodać?");
                                    Scanner scanner = new Scanner(System.in);
                                    String input = scanner.nextLine();
                                    if (input.equalsIgnoreCase("y")) {
                                        parent2.adopt(person);
                                    }
                                }
                            }
                        }
                    }

                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaloeziono pliku w metodzie fromCsv w klasie Pearson");
        } catch (IOException e) {
            System.out.println("Błąd IOException w metodzie fromCsv w klasie Pearson");
        } catch (AmbiguousPersonException e) {
            throw new RuntimeException(e);
        }
        return ppl;
    }

    /*
    public static List<Person> fromCsv(String path) throws IOException {
        List<Person> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Person person;
            String line;
            br.readLine();
            String[] parentsName;
            while ((line = br.readLine()) != null) {
                person = Person.fromCsvLine(line);
                person.validateLifespan();
                person.validateAmbiguity(result);
                result.add(person);
                //br.close();
                //dodanie dziecka do rodziców
                parentsName = line.split(","); //skladowe 3 i 4
                if(parentsName.length>3) {
                    for (Person p : result) {
                        if ((p.imie + " " + p.nazwisko).equals(parentsName[3])) {
                            p.adopt(person);
                            p.validateParentingAge();

                        }
                        if (parentsName.length == 5 && (p.imie + " " + p.nazwisko).equals(parentsName[4])) {
                            p.adopt(person);
                            p.validateParentingAge();

                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Plik " + path + " nie istnieje.");
        } catch (NegativeLifespanException | AmbiguousPersonException | ParentingAgeException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

 Zadanie 1.
Zmodyfikuj klasę Person, by uwzględnić datę śmierci. Na tym etapie pomiń tworzenie referencji między obiektami rodziców i dzieci.
Napisz metodę wytwórczą fromCsvLine() klasy Person przyjmującą jako argument linię opisanego pliku.

Zadanie 2.
Napisz metodę fromCsv(), która przyjmie ścieżkę do pliku i zwróci listę obiektów typu Person.

Zadanie 3.
Napisz klasę NegativeLifespanException. Rzuć jej obiekt jako wyjątek, jeżeli data śmierci osoby jest wcześniejsza niż data urodzin. Wywołanie metody getMessage() powinno wyświetlić stosowną informację zawierającą przyczyny rzucenia wyjątku.

Zadanie 4.
Napisz klasę AmbiguousPersonException. Rzuć jej obiekt jako wyjątek, jeżeli w pliku pojawi się więcej niż jedna osoba o tym samym imieniu i nazwisku. Wywołanie metody getMessage() powinno wyświetlić stosowną informację zawierającą przyczyny rzucenia wyjątku.

Zadanie 5.
Zmodyfikuj metodę fromCsv(), by w obiektach rodziców ustawiała referencje do obiektów dzieci.

Zadanie 6.
Napisz klasę ParentingAgeException. Rzuć jej obiekt jako wyjątek, jeżeli rodzic jest młodszy niż 15 lat lub nie żyje w chwili narodzin dziecka. Przechwyć ten wyjątek tak, aby nie zablokował dodania takiej osoby, a jedynie poprosił użytkownika o potwierdzenie lub odrzucenie takiego przypadku za pomocą wpisania znaku “Y” ze standardowego wejścia.

Zadanie 7.
W klasie Person napisz statyczne metody toBinaryFile i fromBinaryFile, które zapiszą i odczytają listę osób do i z pliku binarnego.
*/

    public static void checkParentngAge(Person parent, Person child) throws ParentingAgeException {
        if (parent.getDate().plusYears(15).isAfter(child.getDate())) {
            throw new ParentingAgeException("Rodzic jest młodszy niz 15 lat");
        }
        if (parent.getDeathDate() != null && parent.getDeathDate().isBefore(child.getDate())) {
            throw new ParentingAgeException("Rodzic nie żyje w chwili urodzin dziecka");
        }
    }


    public static void toBinaryFile(List<Person> persons, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(persons);
            System.out.println("Zapisano osoby do pliku binarnego: " + path);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu do pliku binarnego: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Person> fromBinaryFile(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                return (List<Person>) obj;
            } else {
                System.out.println("Plik nie zawiera listy osób.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd podczas odczytu z pliku binarnego: " + e.getMessage());
        }
        return new ArrayList<>();
    }
    // Zadanie 2
    //W klasie Person napisz bezargumentową metodę, która zwróci napis sformatowany według składni PlantUML. Napis, korzystając z diagramu obiektów, powinien przedstawiać obiekt osoby na rzecz której została wywołana metoda oraz jej rodziców (o ile są zdefiniowani). Obiekty powinny zawierać nazwę osoby. Od dziecka do rodziców należy poprowadzić strzałki.


    public String toPlantUmlObjectDiagram() {
        String result = "@startuml\n";
        String personId = "p_" + this.name + "_" + this.surname;

        result += "object " + personId + " {\n" + " " + this.name + " " + this.surname + "\n" + "}\n";

        for (Person parent : this.parents) {
            String parentId = "p_" + parent.getName() + "_" + parent.getSurname();

            result += "object " + parentId + " {\n" + " " + parent.getName() + " " + parent.getSurname() + "\n" + "}\n";
            result += personId + " --> " + parentId + " : dziecko\n";
        }
        result += "@enduml\n";

        return result;
    }

    // Zadanie 3
    // W klasie Person napisz statyczną metodę, która przyjmie listę osób. Lista powinna zwrócić podobny jak w poprzedni zadaniu napis. Tym razem powinien on zawierać wszystkie osoby w liście i ich powiązania

    public static String toPlantUmlObjectDiagram(List<Person> persons) {
        String result = "@startuml\n";

        Map<Person, String> idMap = new HashMap<>();


        // Kaza osoba ma przypisany identyfikator Imie_Nazwisko
        for (Person person : persons) {
            idMap.put(person, "p_" + person.getName() + "_" + person.getSurname());
        }

        // dodajemy wszystkie osoby
        for (Person person : persons) {
            String id = idMap.get(person);
            result += "object " + id + " {\n" + " " + person.getName() + " " + person.getSurname() + "\n" + "}\n";
        }

        // Relacje dziecko rodzic
        for (Person person : persons) {
            for (Person parent : person.getParents()) {
                if (idMap.containsKey(parent)) {
                    result += idMap.get(person) + " --> " + idMap.get(parent) + " : dziecko\n";;
                }

            }
        }
        result += "@enduml\n";
        return result;
    }

    // Zadanie 4
    //W klasie Person napisz statyczną metodę, która przyjmie listę osób oraz napis substring. Metoda powinna zwrócić listę osób z listy wejściowej, ograniczoną do osób, których nazwa zawiera substring.

    public static List<Person> filterByNameSubstring(List<Person> persons, String substring) {
        if (substring == null || substring.isEmpty()) {return null;}

        String lowerSubstring = substring.toLowerCase();
        List<Person> result = new ArrayList<>();

        for (Person person : persons) {
            if (person.getName().toLowerCase().contains(lowerSubstring) || person.getSurname().toLowerCase().contains(lowerSubstring)) {
                result.add(person);
            }
        }

        return result;
    }

    // Zadanie
    // 5 W klasie Person napisz statyczną metodę, która przyjmie listę osób. Metoda powinna zwrócić listę osób z listy wejściowej, posortowanych według roku urodzenia

    // p -> - lambda, czyli funkcja anonimowa, czyli funkcja zdefiniowana bez imienia.
    // bardzo przydatne gdy chcemy utworzyć jakś prościutką funkcje np a + b

    public static List<Person> sortByBirthYear(List<Person> persons) {
        List<Person> sorted = new ArrayList<>(persons);
        sorted.sort(Comparator.comparing(p -> p.getDate().getYear()));
        return sorted;
    }

    // Zadanie 6
    // W klasie Person napisz statyczną metodę, która przyjmie listę osób. Metoda powinna zwrócić listę zmarłych osób z listy wejściowej, posortowanych malejąco według długości życia.

    public long getLifespan(){
        if (deathDate == null) {return -1;}
        return ChronoUnit.DAYS.between(date, deathDate);
    }

    public static List<Person> getDeceasedSortedByLifespan(List<Person> persons) {
        List<Person> sorted = new ArrayList<>(persons);

        for (Person person : persons) {
            if (person.getDeathDate() != null) {
                sorted.add(person);
            }
        }

        Collections.sort(sorted, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Long.compare(p2.getLifespan(), p1.getLifespan());
            }
        });

        return sorted;
    }

    // Zadanie 7
    // W klasie Person napisz statyczną metodę, która przyjmie listę osób. Metoda powinna zwrócić najstarszą żyjącą osobę.

    public static Person getOldestLivingPerson(List<Person> persons) {
        Person oldest = null;

        for (Person person : persons) {
            if (person.getDeathDate() == null) { // żyje
                if (oldest == null || person.getDate().isBefore(oldest.getDate())) {
                    oldest = person;
                }
            }
        }

        return oldest;
    }

    // Zadanie 8
    // Zmodyfikuj metodę zadania 2. poprzez dodanie do jej argumentów obiektu Function<String, String> postProcess. Funkcja powinna przekształcić wszystkie linie odpowiadające obiektom za pomocą tej funkcji. Przetestuj metodę z dwiema funkcjami: funkcją zmieniającą kolor obiektu na żółty oraz funkcją nie wprowadzającą żadnych zmian.

    public String toPlantUMLWithParents(Function<String, String> postProcess) {
        String result = "@startuml\n";
        String personId = "obj" + System.identityHashCode(this);
        String personLine = "object \"" + this.name + " " + this.surname + "\" as" + personId;
        result += postProcess.apply(personLine);
        result += "\n";

        for (Person parent : this.parents) {
            String parentId = "obj" + System.identityHashCode(this);
            String parentLine = "object \"" + parent.getName() + " " + parent.getSurname() + "\" as" + parentId;
            result += postProcess.apply(parentLine);
            result += "\n";
            result += personId + " --> " + parentId + "\n";
        }
        result += "@enduml\n";

        return result;
    }

    // Zadanie 9
    // Zmodyfikuj metodę z poprzedniego zadania poprzez dodanie do jej argumentów obiektu Predicate<Person> condition. Metoda postProcess powinna wywołać się wyłącznie dla osób spełniających warunek condition. Przetestuj metodę z danymi wygenerowanymi w zadaniach 6. i 7.
    public static String toUML(List<Person> people, Function<String, String> postProcess, Predicate<Person> condition) {
        String result = "@startuml\n";

        Map<Person, String> idMap = new HashMap<>();

        for (Person person : people) {
            String personId = "obj" + System.identityHashCode(person);
            idMap.put(person, personId);
            String personLine = "object \"" + person.getName() + " " + person.getSurname() + "\" as" + personId;

            if (condition.test(person)) {
                personLine = postProcess.apply(personLine);
            }

            result += personLine + "\n";

        }

        for (Person person : people) {
            for (Person child : person.getChildren()) {
                String parentId = idMap.get(person);
                String childId = idMap.get(child);
                result += parentId + " --> " + childId + "\n";
            }
        }

        result += "@enduml\n";
        return result;

    }



    public String toString (){
        return name + " " + date ;
    }
    public String introduce(){
        return "czesc jestem " + name + " i urodzilem/am sie w " + date + " roku";
    }
}
