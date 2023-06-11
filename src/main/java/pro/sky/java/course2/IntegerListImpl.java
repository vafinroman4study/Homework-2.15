package pro.sky.java.course2;

import pro.sky.java.course2.exceptions.IndexOutOfBoundsException;
import pro.sky.java.course2.exceptions.NoSuchElementException;
import pro.sky.java.course2.exceptions.NullArgumentException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private int length;
    private Integer[] integerRepo;
    private final int initLength = 5;

    private void grow() {
        Integer[] workArray = new Integer[(int) (integerRepo.length * 1.5)];
        System.arraycopy(integerRepo, 0, workArray, 0, integerRepo.length);
        integerRepo = workArray;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static int partition(Integer[] arr, int begin, int end) {
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

    public static void quickSort(Integer[] arr, int begin, int end){
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    public IntegerListImpl(Integer[] initArray) {
        if (initArray == null) {
            this.length = 0;
            this.integerRepo = new Integer[initLength];
        } else {
            this.length = initArray.length;
            this.integerRepo = initArray;
        }
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        } else if (length>=integerRepo.length) {
            grow();
        }
        integerRepo[length] = item;
        length++;
        return item;
    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public Integer add(int index, Integer item) {
        if (index > length || index < 0 ) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка!");
        }
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        }else{
            length++;
            if ((index == integerRepo.length) || (integerRepo[index] != null)) {
                if (integerRepo[integerRepo.length - 1] != null) {
                    Integer[] workArray = new Integer[integerRepo.length + 1];
                    for (int i = 0; i < integerRepo.length; i++) {
                        if (i < index) {
                            workArray[i] = integerRepo[i];
                        } else {
                            workArray[i + 1] = integerRepo[i];
                        }
                    }
                    integerRepo = workArray;
                } else {
                    Integer curr;
                    Integer prev = integerRepo[index];
                    for (int i = index + 1; i < integerRepo.length - 1; i++) {
                        curr = integerRepo[i];
                        integerRepo[i] = prev;
                        prev = curr;
                    }
                }
            }

            integerRepo[index] = item;
            return item;
        }
    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс больше
    // фактического количества элементов
    // или выходит за пределы массива.
    @Override
    public Integer set(int index, Integer item) {
        if (index > length || index >= integerRepo.length) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка!");
        } else if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        }else{
            integerRepo[index] = item;
            return item;
        }

    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public Integer remove(Integer item) {
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        } else {
            int i = this.indexOf(item);
            if (i<0) {
                throw new NoSuchElementException("Удаляемый элемент не найден!");
            }

            for (int j = i; j < length-1; j++) {
                integerRepo[j] = integerRepo[j+1];
            }
            integerRepo[length-1] = null;
            length--;
            return item;
        }
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public Integer remove(int index) {
        if (index > length || index >= integerRepo.length) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка!");
        }
        Integer res = integerRepo[index];
        for (int i = index; i < length-1; i++) {
            integerRepo[i] = integerRepo[i+1];
        }
        integerRepo[length-1] = null;
        length--;
        return res;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(Integer element) {
        int min = 0;
        int max = length - 1;
        Integer[] work = this.sort();

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element.equals(work[mid])) {
                return true;
            }

            if (element < work[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(Integer item) {
        boolean flagNotFound = true;
        int i = 0;
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        } else {
            while (flagNotFound) {
                flagNotFound = (!item.equals(integerRepo[i]));
                i++;
                if (i == integerRepo.length && flagNotFound) {
                    i = 0;
                    break;
                }
            }
            return --i;
        }
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(Integer item) {
        boolean flagNotFound = true;
        //int res = -1;
        int i = integerRepo.length-1;
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        } else {
            while (flagNotFound) {
                flagNotFound = (!item.equals(integerRepo[i]));
                i--;
                if (i == 0 && flagNotFound) {
                    i = -2;
                    break;
                }
            }
            return ++i;
        }
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public Integer get(int index) {
        if (index >= length) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка!");
        }
        return integerRepo[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(IntegerList otherList) {
        boolean isEqual = false;
        if (otherList.size() == length) {
            Integer[] compareArray = otherList.toArray();
            for (int i = 0; i < length; i++) {
                isEqual = compareArray[i].equals(integerRepo[i]);
                if (!isEqual) {
                    break;
                }
            }
        }
        return isEqual;
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        return length;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        integerRepo = new Integer[initLength];
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public Integer[] toArray() {
        return integerRepo;
    }

    @Override
    public Integer[] sort() {
        Integer[] res = Arrays.copyOf(integerRepo, length);
        int begin = 0;
        int end = length - 1;

        quickSort(res, begin, end);
        return res;
    }
}
