public class Main {
    public static void main(String[] args) {
        int [] table = new int[3];
        table[0]=2;
        table[1]=3;
        table[2]=4;

        int age=5;
        //System.out.println(Sum.sumArray((table)));
        //Sum.print();

        //System.out.println(Sum.isAdult(age));
        Book.Author author = new Book.Author("Fiodor Dostojewski", 1821);
        Book book = new Book("Zbrodnia i Kara", 1866, author);
        System.out.println(book.getAuthorAgeAtPublication());

        Student[] students = {
                new Student("Anna", 5),
                new Student("Bartek", 5),
                new Student("Celina", 5),
                new Student("Dawid", 5),
                new Student("Ewa", 5)
        };

        for (Student s : students) {
            System.out.printf("%s - Średnia ocen: %.2f%n", s.getName(), s.average());
        }

    }
}