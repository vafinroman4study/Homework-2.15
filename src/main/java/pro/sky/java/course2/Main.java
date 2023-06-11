package pro.sky.java.course2;


import java.util.*;

public class Main {
    public static Integer[] generateRandomArray(int arraylength, int bound) {
        Random random = new Random();
        Integer[] arr = new Integer[arraylength];
        for (int i = 0; i < arraylength; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

    public static void main(String[] args) {

        StringList stringList = new StringListImpl();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        stringList.add("four");
        stringList.add("five");

        for (String s : stringList.toArray()) {
            System.out.println(s);
        }
        System.out.println("stringList.size() = " + stringList.size());
        System.out.println();

        IntegerList intList = new IntegerListImpl(generateRandomArray(10, 10));
        System.out.println(Arrays.toString(intList.toArray()));

        intList.sort();


    }
}