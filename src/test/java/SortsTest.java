import java.util.Arrays;
import java.util.Random;

public class SortsTest {

    public static void main(String[] args) {
        measureRunTime();
    }
    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    public static int[] generateRandomArray(int arraylength, int bound) {
        Random random = new Random();
        int[] arr = new int[arraylength];
        for (int i = 0; i < arraylength; i++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }
    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void measureRunTime() {
        int numTests = 10;
        long start;
        long[] bubbleRes = new long[numTests];
        long[] selectionRes = new long[numTests];
        long[] insertionRes = new long[numTests];
        int[] work;

        for (int i = 0; i < numTests; i++) {
            int[] arr = generateRandomArray(10_000, 5_000);

            work = Arrays.copyOf(arr, arr.length);
            start = System.currentTimeMillis();
            sortBubble(work);
            bubbleRes[i] = System.currentTimeMillis() - start;

            work = Arrays.copyOf(arr, arr.length);
            start = System.currentTimeMillis();
            sortSelection(work);
            selectionRes[i] = System.currentTimeMillis() - start;

            work = Arrays.copyOf(arr, arr.length);
            start = System.currentTimeMillis();
            sortInsertion(work);
            insertionRes[i] = System.currentTimeMillis() - start;
        }

        System.out.println("bubbleRes = \t" + Arrays.toString(bubbleRes));
        System.out.println("selectionRes = \t" + Arrays.toString(selectionRes));
        System.out.println("insertionRes = \t" + Arrays.toString(insertionRes));

        System.out.println(Arrays.stream(bubbleRes).sum() * 1. / bubbleRes.length);
        System.out.println(Arrays.stream(selectionRes).sum() * 1. / selectionRes.length);
        System.out.println(Arrays.stream(insertionRes).sum() * 1. / insertionRes.length);
    }
}
