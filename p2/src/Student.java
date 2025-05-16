import java.util.LinkedList;
import java.util.Random;

public class Student {
    private String name;
    private int[] grades;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Student(String name, int Ngrades ){
        this.name=name;
        this.grades=new int[Ngrades];
        LinkedList m = new LinkedList();
        Random rand = new Random();
        for (int i = 0; i < Ngrades; i++) {
            grades[i] = 2 + rand.nextInt(4); // losuje od 2 do 5
        }
    }

    public double average() {
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.length;
    }


}
