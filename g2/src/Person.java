import java.util.*;

public class Person implements Comparable<Person>{
    private String name;
    private int age;
    private String surname;
    private Set<String> hobbies;
    private Set<Person> friends;
    private Set<Person> children;

    public void setName(String name){
        this.name=name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Person (String name, int age, String surname){
        this.name=name;
        this.age=age;
        this.surname=surname;
        this.hobbies= new HashSet<>();
        this.friends=new HashSet<>();
        this.children=new HashSet<>();
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

    public boolean isYoungerThan( Person other ){
        if(other==null) return false;
        return this.age>other.age;
    }

    public Person findYoungest(Set<Person> people){
        if(people==null || people.isEmpty()) return null;
        Person youngest = null;
        for(Person p : people){
            if(youngest == null || p.isYoungerThan(youngest) ) {
                youngest=p;
            }
        }
        return youngest;

    }

    public Person getOldestChild(){
        if(children == null || this.children.isEmpty()) return null;
        Person oldest=null;
        for(Person p : children){
            if(oldest==null || p.age>oldest.age){
                oldest=p;
            }
        } return oldest;
    }

    public List<String> getChildrenNames(){
        List <String> names = new ArrayList<>();
        for(Person child :  children){
            names.add(child.name);
        }
        return names;
    }

    public boolean hasChildrenOlderThan(int age){
        if(children==null || children.isEmpty() ) return false;
        int c=0;
        for (Person p : children){
            if (p.age>age) c++;
        } return true;
    }

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

    public int compareTo(Person other){
        return Integer.compare(this.age, other.age);
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

    public String toString (){
        return name + " " + age ;
    }
    public String introduce(){
        return "czesc jestem " + name + " i mam " + age + " lat";
    }
}
