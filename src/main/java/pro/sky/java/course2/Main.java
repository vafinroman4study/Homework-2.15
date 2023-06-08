package pro.sky.java.course2;


public class Main {
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
    }
}