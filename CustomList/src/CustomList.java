import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

//Zadanie 1.
//Napisz klasę szablonową CustomList implementującą strukturę listy jednokierunkowej ze wskaźnikami na początek i koniec. Zaprogramuj w niej metody:
//void addLast(T value) - dodającą wartość na koniec listy,
//T getLast() - zwracającą wartość z końca listy,
//void addFirst(T value) - dodającą wartość na początek listy,
//T getFirst() - zwracającą wartość z początku listy,
//T removeFirst() - zwracającą i usuwającą element z początku listy,
//T removeLast() - zwracającą i usuwającą element z końca listy.

public class CustomList<T> extends AbstractList<T> {

    private static class Element<T> {
        T value;
        Element<T> next;

        public Element(T value) {
            this.value = value;
        }
    }

    private Element<T> head;
    private Element<T> tail;

    // Zad.2
    //Niech klasa CustomList dziedziczy po klasie AbstractList. Wygeneruj potrzebne metody.  Nadpisz i zaprogramuj metody:
    //boolean add(T t) - działającą tak samo jak addLast i zwracającą prawdę,
    //int size() - zwracającą rozmiar listy,
    //T get(int index) - zwracającą wartość w węźle o podanym indeksie.

    private int size;

    public CustomList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addTail(T value){
        Element<T> newElement = new Element<>(value);
        if (this.head == null){
            this.head = this.tail = newElement;
        } else {
            this.tail.next = newElement;
            this.tail = newElement;
        }
        this.size++;
    }

    public T getTail(){
        if (this.tail == null){
            throw new IllegalStateException("List is empty");
        }
        return this.tail.value;
    }

    public void addHead(T value){
        Element<T> newElement = new Element<>(value);
        if (this.head == null){
            this.head = this.tail = newElement;
        } else {
            newElement.next = this.head;
            this.head = newElement;
        }
        this.size++;
    }

    public T getHead(){
        if (this.head == null){
            throw new IllegalStateException("List is empty");
        }
        return this.head.value;
    }

    public T removeHead(){
        if (this.head == null){
            throw new IllegalStateException("List is empty");
        }
        T value = this.head.value;
        this.head = this.head.next;
        if (this.head == null){
            this.tail = null;
        }
        this.size--;
        return value;
    }

    public T removeTail(){
        if (this.tail == null){
            throw new IllegalStateException("List is empty");
        }
        if (this.head == this.tail){
            T value = this.head.value;
            this.head = this.tail = null;
            return value;
        }

        Element<T> current = this.head;
        while (current.next != this.tail){
            current = current.next;
        }
        T value = this.tail.value;
        this.tail = current;
        this.tail.next = null;
        this.size--;
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Element<T> current = this.head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean add(T t) {
        addTail(t);
        return true;
    }


    // Zad. 2

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        Element<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public int size() {
        return size;
    }

    // Zad.3
    //Nadpisz i zaprogramuj metody:
    //Iterator<T> iterator() - zwracającą iterator do listy. Zdefiniuj w niej iterator,
    //Stream<T> stream() - zwracającą strumień z zawartością listy.

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {

            private Element<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if(!hasNext()){
                    throw new NoSuchElementException("No more elements");
                }
                T value = this.current.value;
                this.current = this.current.next;
                return value;
            }
        };
    }

    @Override
    public Stream<T> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
    }

    // Zad.4
    //Napisz statyczną metodę szablonową, która przyjmie jako parametry Listę obiektów typu szablonowego T oraz obiekt Class. Metoda powinna zwrócić listę obiektów, które należą do wskazanej klasy.
    //Następnie zmodyfikuj metodę tak, aby filtrowała obiekty, które dziedziczą (bezpośrednio lub pośrednio) po wskazanej klasie.

    public static <T> List<T> filterBySuperclass(List<T> list, Class<?> superClass) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (item != null && superClass.isAssignableFrom(item.getClass())) {
                result.add(item);
            }
        }
        return result;
    }

    // Zad. 5
    // Napisz predykat, który porówna, czy testowana zmienna znajduje się w otwartym przedziale, zdefiniowanym jego granicami.
    // Korzystając z niego napisz metodę statyczną, która dla listy oraz granic zakresu danych typem szablonowym zwróci liczbę elementów w tej liście, spełniających warunek predykatu.

    public static <T extends Comparable<T>> boolean isInOpenRange(T value, T min, T max) {
        return value.compareTo(min) > 0 && value.compareTo(max) < 0;
    }

    public static <T extends Comparable<T>> int countInOpenRange(List<T> list, T min, T max) {
        int count = 0;
        for (T value : list) {
            if (isInOpenRange(value, min, max)) {
                count++;
            }
        }
        return count;
    }


}
