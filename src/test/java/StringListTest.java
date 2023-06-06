
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.StringList;
import pro.sky.java.course2.StringListImpl;
import pro.sky.java.course2.exceptions.IndexOutOfBoundsException;
import pro.sky.java.course2.exceptions.NoSuchElementException;

import java.util.stream.Stream;


public class StringListTest {

    private StringList stringList = new StringListImpl();
    private final String[] setUpValues = {"one", "two", "three"};
    private final String newElement = "Surprize!";

    @BeforeEach
    public void setUp(){
        for (String s : setUpValues) {
            stringList.add(s);
        }
    }

    @Test
    public void getTest() {
        String expected = setUpValues[1];
        String result = stringList.get(1);
        Assertions.assertEquals(expected, result);

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> stringList.get(5)
        );
    }

    @Test
    public void setTest() {
        stringList.set(1, "2");
        String[] expected = {"one", "2", "three", null, null};
        String[] result = stringList.toArray();
        Assertions.assertArrayEquals(expected, result);

        stringList.set(0, null);
        expected = new String[]{null, "2", "three", null, null};
        result = stringList.toArray();
        Assertions.assertArrayEquals(expected, result);

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> stringList.set(5, "something")
        );
    }

    @Test
    public void equals() {
        StringList otherList = new StringListImpl();
        for (String s : setUpValues) {
            otherList.add(s);
        }
        Assertions.assertTrue(stringList.equals(otherList));
        otherList.add(newElement);
        Assertions.assertFalse(stringList.equals(otherList));
    }

    @Test
    public void removeTest() {
        stringList.remove(0);
        String[] result1 = stringList.toArray();
        String[] expected1 = {"two", "three", null, null, null};
        Assertions.assertArrayEquals(expected1, result1);

        stringList.remove("two");
        String[] result2 = stringList.toArray();
        String[] expected2 = {"three", null, null, null, null};
        Assertions.assertArrayEquals(expected2, result2);

        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> stringList.remove(newElement)
        );
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> stringList.remove(4)
        );
    }

    @Test
    public void clearTest() {
        stringList.clear();
        Assertions.assertArrayEquals(stringList.toArray(), new StringListImpl().toArray());
    }

    @Test
    public void sizeTest() {
        int expected = setUpValues.length;
        int result = stringList.size();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void containsTest() {
        Assertions.assertTrue(stringList.contains("one"));
        Assertions.assertFalse(stringList.contains(newElement));
    }

    @Test
    public void indexOfTest() {

        Assertions.assertEquals(0, stringList.indexOf("one"));
        Assertions.assertEquals(2, stringList.lastIndexOf("three"));
        Assertions.assertEquals(-1, stringList.indexOf(newElement));
        Assertions.assertEquals(-1, stringList.lastIndexOf(newElement));
    }


    @ParameterizedTest
    @MethodSource("provideParametersForAddAtIndex")
    public void stringListAddAtIndex(int index, String[] expected) {
        stringList.add(index, newElement);
        String[] result = stringList.toArray();
        Assertions.assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideParametersForAddTest")
    public void stringListAddTest(String[] arguments, String[] expected) {
        for (String s : arguments) {
            stringList.add(s);
        }
        String[] result = stringList.toArray();
        Assertions.assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideParametersForEmptyTest")
    public void isEmptyTest(String[] arguments, boolean withRemoval, boolean expected) {
        stringList = new StringListImpl();
        for (String s : arguments) {
            stringList.add(s);
        }
        if (withRemoval) {
            stringList.remove(0);
        }
        boolean result = stringList.isEmpty();
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> provideParametersForAddTest() {
        return Stream.of(
                Arguments.of(new String[] {"oneMore"}, new String[] {"one", "two", "three", "oneMore", null}),
                Arguments.of(new String[] {"oneMore", "twoMore"}, new String[] {"one", "two", "three", "oneMore", "twoMore"}),
                Arguments.of(new String[] {"oneMore", null, "threeMore"}, new String[] {"one", "two", "three", "oneMore", "threeMore"})
        );
    }

    public static Stream<Arguments> provideParametersForAddAtIndex() {
        return Stream.of(
                Arguments.of(1, new String[] {"one", "Surprize!", "two", "three", null}),
                Arguments.of( 0, new String[] {"Surprize!", "one", "two", "three", null}),
                Arguments.of(3, new String[] {"one", "two", "three", "Surprize!", null})
        );
    }

    public static Stream<Arguments> provideParametersForEmptyTest() {
        return Stream.of(
                Arguments.of(new String[] {"one"}, false, false),
                Arguments.of( new String[1], false, true),
                Arguments.of(new String[] {"one"}, true, true),
                Arguments.of(new String[] {"one", "two"}, true, false)
        );
    }

}



//
//    String remove(String item);
//
//    String remove(int index);

