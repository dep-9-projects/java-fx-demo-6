import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>();

        arr.add(10);
        arr.add(20);
        arr.add(20);
        arr.add(20);
        arr.add(30);
        arr.add(40);

        arr.remove(new Integer(20));
        arr.remove(new Integer(20));
        arr.remove(new Integer(20));
        arr.remove(new Integer(20));
        arr.remove(new Integer(20));

        System.out.println(arr);




    }
}
