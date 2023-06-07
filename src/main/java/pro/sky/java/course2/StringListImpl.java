package pro.sky.java.course2;

import pro.sky.java.course2.exceptions.IndexOutOfBoundsException;
import pro.sky.java.course2.exceptions.NoSuchElementException;
import pro.sky.java.course2.exceptions.NullArgumentException;

public class StringListImpl implements StringList {

    private int length;
    private String[] stringRepo;
    private final int initLength = 5;

    public StringListImpl() {
        this.length = 0;
        this.stringRepo = new String[initLength];
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        } else if (length>=stringRepo.length) {
            String[] workArray = new String[stringRepo.length + 1];
            System.arraycopy(stringRepo, 0, workArray, 0, stringRepo.length);
            stringRepo = workArray;
        }
        stringRepo[length] = item;
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
    public String add(int index, String item) {
        if (index > length || index < 0 ) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка!");
        }
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        }else{
            length++;
            if ((index == stringRepo.length) || (stringRepo[index] != null)) {
                if (stringRepo[stringRepo.length - 1] != null) {
                    String[] workArray = new String[stringRepo.length + 1];
                    for (int i = 0; i < stringRepo.length; i++) {
                        if (i < index) {
                            workArray[i] = stringRepo[i];
                        } else {
                            workArray[i + 1] = stringRepo[i];
                        }
                    }
                    stringRepo = workArray;
                } else {
                    String curr;
                    String prev = stringRepo[index];
                    for (int i = index + 1; i < stringRepo.length - 1; i++) {
                        curr = stringRepo[i];
                        stringRepo[i] = prev;
                        prev = curr;
                    }
                }
            }

            stringRepo[index] = item;
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
    public String set(int index, String item) {
        if (index > length || index >= stringRepo.length) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка!");
        } else if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        }else{
            stringRepo[index] = item;
            return item;
        }

    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(String item) {
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        } else {
            int i = this.indexOf(item);
            if (i<0) {
                throw new NoSuchElementException("Удаляемый элемент не найден!");
            }

            for (int j = i; j < length-1; j++) {
                stringRepo[j] = stringRepo[j+1];
            }
            stringRepo[length-1] = null;
            length--;
            return item;
        }
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        if (index > length || index >= stringRepo.length) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка!");
        }
        String res = stringRepo[index];
        for (int i = index; i < length-1; i++) {
            stringRepo[i] = stringRepo[i+1];
        }
        stringRepo[length-1] = null;
        length--;
        return res;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(String item) {
        boolean flagNotFound = true;
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        } else {
            int i = 0;
            while (flagNotFound) {
                flagNotFound = !item.equals(stringRepo[i]);
                i++;
                if (i == stringRepo.length) {
                    break;
                }
            }
            return !flagNotFound;
        }
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(String item) {
        boolean flagNotFound = true;
        int i = 0;
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        } else {
            while (flagNotFound) {
                flagNotFound = (!item.equals(stringRepo[i]));
                i++;
                if (i == stringRepo.length && flagNotFound) {
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
    public int lastIndexOf(String item) {
        boolean flagNotFound = true;
        //int res = -1;
        int i = stringRepo.length-1;
        if (item == null) {
            throw new NullArgumentException("В метод передан null аргумент!");
        } else {
            while (flagNotFound) {
                flagNotFound = (!item.equals(stringRepo[i]));
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
    public String get(int index) {
        if (index >= length) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка!");
        }
        return stringRepo[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(StringList otherList) {
        boolean isEqual = false;
        if (otherList.size() == length) {
            String[] compareArray = otherList.toArray();
            for (int i = 0; i < length; i++) {
                isEqual = compareArray[i].equals(stringRepo[i]);
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
        stringRepo = new String[initLength];
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public String[] toArray() {
        return stringRepo;
    }
}
