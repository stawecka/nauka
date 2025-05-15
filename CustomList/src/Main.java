
public class Main {

    public static void main(String[] args) {
        CustomList<Integer> list = new CustomList<>();
        list.addLast(11);
        list.addLast(3);
        list.addLast(7);
        System.out.println(list.size());
        System.out.println(list.get(1));
        System.out.println(list.get(2));

        list.stream()
                .map(o -> o + "_")
                .forEach(System.out::println);

        // Tworzymy listę obiektów różnych klas
        CustomList<Object> mixedList = new CustomList<>();
        mixedList.addLast("Hello");
        mixedList.addLast(42);
        mixedList.addLast(3.14);
        mixedList.addLast("World");
        mixedList.addLast(true);

    }
}