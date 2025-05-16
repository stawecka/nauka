import java.time.LocalDate;

public class Book {
    private String title;
    private int year;
    private Author author;

    public Book(String title, int year, Author author){
        this.title=title;
        this.year=year;
        this.author= new Author(author.name, author.birthYear);
    }

    public static class Author{
        private String name;
        private int birthYear;

        public Author(String name, int birthYear){
            this.name=name;
            this.birthYear=birthYear;
        }
    }

    public int getAuthorAgeAtPublication(){
        return year-author.birthYear;
    }
}
