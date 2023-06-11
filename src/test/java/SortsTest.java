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

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    public static void quickSort(int[] arr, int begin, int end){
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, left.length);
        System.arraycopy(arr, mid, right, 0, right.length);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }

    public static void measureRunTime() {
        int numTests = 10;
        long start;
        long[] bubbleRes = new long[numTests];
        long[] selectionRes = new long[numTests];
        long[] insertionRes = new long[numTests];
        long[] quickRes = new long[numTests];
        long[] mergeRes = new long[numTests];
        int[] work;

        for (int i = 0; i < numTests; i++) {
            int[] arr = generateRandomArray(100_000, 1_000_000);

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

            work = Arrays.copyOf(arr, arr.length);
            start = System.currentTimeMillis();
            quickSort(work, 0, arr.length-1);
            quickRes[i] = System.currentTimeMillis() - start;

            work = Arrays.copyOf(arr, arr.length);
            start = System.currentTimeMillis();
            mergeSort(work);
            mergeRes[i] = System.currentTimeMillis() - start;
        }

        System.out.println("bubbleRes = \t" + Arrays.toString(bubbleRes));
        System.out.println("selectionRes = \t" + Arrays.toString(selectionRes));
        System.out.println("insertionRes = \t" + Arrays.toString(insertionRes));
        System.out.println("quickRes = \t" + Arrays.toString(quickRes));
        System.out.println("mergeRes = \t" + Arrays.toString(mergeRes));

        System.out.println(Arrays.stream(bubbleRes).sum() * 1. / bubbleRes.length);
        System.out.println(Arrays.stream(selectionRes).sum() * 1. / selectionRes.length);
        System.out.println(Arrays.stream(insertionRes).sum() * 1. / insertionRes.length);
        System.out.println(Arrays.stream(quickRes).sum() * 1. / quickRes.length);
        System.out.println(Arrays.stream(mergeRes).sum() * 1. / mergeRes.length);
    }
}
