import java.util.*;

public class Family {
    private Map<String, List <Person>> members;

    public Family (Map<String, Person> members ){
        this.members=new HashMap<>();
    }
    //private Map<String, List<Person>> familyMembers;

    //public Family() {
    //    this.familyMembers = new HashMap<>();
    //}

    //    public void add(Person person){
//        String key = person.getName() + " " + person.getSurname();
//         if (this.familyMembers.containsKey(key)){
//            System.out.println("Duplikat");
//             return;
//          }
//        this.familyMembers.put(key, person);
//    }

//    public void add(Person... persons){
//        for (Person p: persons) {
//            String key = p.getName() + " " + p.getSurname();
//            //        if (this.familyMembers.containsKey(key)){
//            //            System.out.println("Duplikat");
//            //            return;
//            //        }
//            this.familyMembers.put(key, p);
//        }
//    }

    public void add(Person... persons){
        for (Person p: persons) {
            String key = p.getName() + " " + p.getSurname();
            this.members.putIfAbsent(key, new ArrayList<>());
            this.members.get(key).add(p);
        }
    }

    public Person[] get(String key){
        List<Person> people = this.members.get(key);
        if (people == null || people.isEmpty()){
            return null;
        }

        people.sort(Comparator.reverseOrder());
        return people.toArray(new Person[0]);
    }



}
