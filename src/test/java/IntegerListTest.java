import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.IntegerList;
import pro.sky.java.course2.IntegerListImpl;
import pro.sky.java.course2.Main;

import java.util.Arrays;
import java.util.stream.Stream;

public class IntegerListTest {
    private IntegerList integerList;

    @ParameterizedTest
    @MethodSource("provideParametersForTest")
    public void sortTest(Integer[] arr) {
        IntegerList integerList = new IntegerListImpl(arr);
        Integer[] result = integerList.sort();
        Arrays.sort(arr);
        Assertions.assertArrayEquals(arr, result);
    }

    @ParameterizedTest
    @MethodSource("provideParametersForTest")
    public void containsTest(Integer[] arr) {
        IntegerList integerList = new IntegerListImpl(arr);
        Assertions.assertTrue(integerList.contains(arr[0]));
        Assertions.assertTrue(integerList.contains(arr[5]));
        Assertions.assertFalse(integerList.contains(100));
        Assertions.assertFalse(integerList.contains(-1));

    }

    public static Stream<Arguments> provideParametersForTest() {
        return Stream.of(
                Arguments.of((Object) Main.generateRandomArray(10, 50)),
                Arguments.of((Object) Main.generateRandomArray(10, 50)),
                Arguments.of((Object) Main.generateRandomArray(10, 50)),
                Arguments.of((Object) Main.generateRandomArray(10, 50)),
                Arguments.of((Object) Main.generateRandomArray(10, 50))
        );
    }
}
