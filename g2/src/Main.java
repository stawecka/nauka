import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Person p1 = new Person("ola", "kowalska", LocalDate.of(2005,07,13));
        //Person p2= new Person("ania", "jablko", LocalDate.of(2003, 06, 24));
        //Person p3 = new Person ("jan", "kowalski", LocalDate.of(2002, 09, 17));
        //List<Person> people = new ArrayList<>();
        //people.add(p1);
        //people.add(p2);
        //people.add(p3);

        //Person parent = new Person ("jan", 1998, "j");
        //Person a= new Person("ania", 15,"n");
        //Person t = new Person("tomek", 24, "k");

        //parent.adopt(a);
        //parent.adopt(t);

        //System.out.println(a.introduce());
        //System.out.println(t.isYoungerThan(t));
        //Person oldest = parent.getOldestChild();
        //System.out.println(oldest);

        //Person mama = new Person ("ania", 1990, "k");
        //mama.adopt(new Person("jan", 2004, "l"));
        //mama.adopt(new Person("karol", 2009, "p"));
        //System.out.println(mama.getOldestChild());

        String line = "Marek Kowalski,15.05.1899";
        String[] tekst=line.split(",");
        String name = tekst[0];
        System.out.println(name);

        Person p1 = Person.fromCsvLine(line);

        System.out.println(p1.describe());
    }
}