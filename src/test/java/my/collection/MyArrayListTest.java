package my.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyList<Integer> testArrayList;

    @BeforeEach
    private void addList() {
        testArrayList = new MyArrayList<>();
    }

    @Test
    void testAdd() {
        assertAll(
                () -> assertTrue(testArrayList.add(0)),
                () -> assertEquals("[0]", testArrayList.toString()),
                () -> assertEquals(1, testArrayList.size()),
                () -> assertTrue(testArrayList.add(null))
        );
    }

    @Test
    void testGet() {
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.get(0)),
                () -> {
                    fullListWithIntegers(5, testArrayList);
                    assertEquals(0, testArrayList.get(0));
                },
                () -> assertEquals(4, testArrayList.get(testArrayList.size() - 1)),
                () -> assertEquals(2, testArrayList.get(2)),
                () -> assertEquals(5, testArrayList.size()),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.get(6))
        );
    }

    @Test
    void testRemove() {
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.remove(0)),
                () -> {
                    fullListWithIntegers(5, testArrayList);
                    assertEquals(4, testArrayList.remove(testArrayList.size() - 1));
                },
                () -> assertEquals(4, testArrayList.size()),
                () -> assertEquals(2, testArrayList.remove(2)),
                () -> assertEquals(3, testArrayList.size()),
                () -> assertEquals(0, testArrayList.remove(0)),
                () -> assertEquals(2, testArrayList.size()),
                () -> assertEquals("[1, 3]", testArrayList.toString()),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.remove(2))
        );
    }

    @Test
    void testAddAll() {
        MyList<Integer> addArrayList = new MyArrayList<>();
        fullListWithIntegers(5, addArrayList);
        MyList<Integer> addLinkedList = new MyLinkedList<>();
        fullListWithIntegers(5, addLinkedList);

        assertAll(
                () -> assertFalse(testArrayList.addAll(new MyArrayList<>())),
                () -> assertTrue(testArrayList.addAll(addArrayList)),
                () -> assertEquals("[0, 1, 2, 3, 4]", testArrayList.toString()),
                () -> assertEquals(5, testArrayList.size()),
                () -> assertTrue(testArrayList.addAll(addLinkedList)),
                () -> assertEquals("[0, 1, 2, 3, 4, 0, 1, 2, 3, 4]", testArrayList.toString()),
                () -> assertEquals(10, testArrayList.size())
        );
    }

    @Test
    void testToString() {
        fullListWithIntegers(10, testArrayList);
        assertAll(
                () -> assertEquals(Stream
                                .iterate(0, i -> i + 1)
                                .limit(10)
                                .collect(Collectors.toList())
                                .toString()
                        , testArrayList.toString()),
                () -> assertEquals("[]", new MyArrayList<>().toString())
        );
    }

    @Test
    void testConstructorAddArrayList() {
        MyList<Integer> addArrayList = new MyArrayList<>();
        fullListWithIntegers(5, addArrayList);

        assertEquals("[0, 1, 2, 3, 4]", new MyLinkedList<>(addArrayList).toString());
    }

    @Test
    void testConstructorAddLinkedList() {
        MyList<Integer> addLinkedList = new MyLinkedList<>();
        fullListWithIntegers(5, addLinkedList);

        assertEquals("[0, 1, 2, 3, 4]", new MyLinkedList<>(addLinkedList).toString());
    }

    @Test
    void testSet() {
        MyList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.set(0, 5);

        assertAll(
                () -> assertEquals("[5, 2]", list.toString()),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 0)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.set(2, 0))
        );
    }

    @Test
    void testSize() {
        assertAll(
                () -> assertEquals(0, testArrayList.size()),
                () -> {
                    fullListWithIntegers(10, testArrayList);
                    assertEquals(10, testArrayList.size());
                }
        );
    }

    @Test
    void testToArray() {
        assertAll(
                () -> assertEquals(Arrays.toString(new Integer[0]), Arrays.toString(new MyArrayList<Integer>().toArray())),
                () -> {
                    fullListWithIntegers(5, testArrayList);
                    assertEquals(Arrays.toString(new Integer[] {0, 1, 2, 3, 4}), Arrays.toString(testArrayList.toArray()));
                }
        );
    }

    private void fullListWithIntegers(int size, MyList<Integer> list) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }
}