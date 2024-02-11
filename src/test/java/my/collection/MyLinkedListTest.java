package my.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    private MyList<Integer> testLinkedList;

    @BeforeEach
    private void addList() {
        testLinkedList = new MyLinkedList<>();
    }

    @Test
    void testAdd() {
        assertAll(
                () -> assertEquals(true, testLinkedList.add(1)),
                () -> assertEquals(true, testLinkedList.add(null))
        );
    }

    @Test
    void testConstructorAddLinkedList() {
        MyList<Integer> addLinkedList = new MyLinkedList<>();
        fullListWithIntegers(5, addLinkedList);

        assertEquals("[0, 1, 2, 3, 4]", new MyLinkedList<>(addLinkedList).toString());
    }

    @Test
    void testConstructorAddArrayList() {
        MyList<Integer> addArrayList = new MyArrayList<>();
        fullListWithIntegers(5, addArrayList);

        assertEquals("[0, 1, 2, 3, 4]", new MyLinkedList<>(addArrayList).toString());
    }

    @Test
    void testGet() {
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testLinkedList.get(0)),
                () -> {
                    fullListWithIntegers(5, testLinkedList);
                    assertEquals(0, testLinkedList.get(0));
                },
                () -> assertEquals(1, testLinkedList.get(1)),
                () -> assertEquals(2, testLinkedList.get(2)),
                () -> assertEquals(4, testLinkedList.get(4)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testLinkedList.get(5))
        );
    }

    @Test
    void remove() {
        fullListWithIntegers(10, testLinkedList);

        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testLinkedList.remove(-1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testLinkedList.remove(10)),
                () -> assertEquals(9, testLinkedList.remove(9)),
                () -> assertEquals(5, testLinkedList.remove(5)),
                () -> assertEquals(0, testLinkedList.remove(0)),
                () -> assertEquals("[1, 2, 3, 4, 6, 7, 8]", testLinkedList.toString())
        );
    }

    @Test
    void testAddAll() {
        MyList<Integer> addLinkedList = new MyLinkedList<>();
        fullListWithIntegers(5, addLinkedList);
        MyList<Integer> addArrayList = new MyArrayList<>();
        fullListWithIntegers(5, addArrayList);

        assertAll(
                () -> assertFalse(testLinkedList.addAll(new MyLinkedList<>())),
                () -> assertTrue(testLinkedList.addAll(addLinkedList)),
                () -> assertEquals("[0, 1, 2, 3, 4]", testLinkedList.toString()),
                () -> assertTrue(testLinkedList.addAll(addArrayList)),
                () -> assertEquals("[0, 1, 2, 3, 4, 0, 1, 2, 3, 4]", testLinkedList.toString())
        );
    }

    @Test
    void testSize() {
        assertAll(
                () -> assertEquals(0, testLinkedList.size()),
                () -> {
                    testLinkedList.add(1);
                    assertEquals(1, testLinkedList.size());
                },
                () -> {
                    fullListWithIntegers(20, testLinkedList);
                    assertEquals(21, testLinkedList.size());
                }
        );
    }

    @Test
    void testToString() {
        assertAll(
                () -> assertEquals("[]", testLinkedList.toString()),
                () -> {
                    testLinkedList.add(1);
                    assertEquals("[1]", testLinkedList.toString());
                },
                () -> {
                    fullListWithIntegers(5, testLinkedList);
                    assertEquals("[1, 0, 1, 2, 3, 4]", testLinkedList.toString());
                }
        );
    }

    @Test
    void toArray() {
        assertAll(
                () -> assertEquals(Arrays.toString(new Integer[0]), Arrays.toString(testLinkedList.toArray())),
                () -> {
                    fullListWithIntegers(5, testLinkedList);
                    assertEquals(Arrays.toString(new Integer[] {0, 1, 2, 3, 4}),Arrays.toString(testLinkedList.toArray()));
                }
        );
    }

    @Test
    void set() {
        fullListWithIntegers(5, testLinkedList);

        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testLinkedList.remove(-1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> testLinkedList.remove(10)),
                () -> assertEquals(0, testLinkedList.set(0, 9)),
                () -> assertEquals(4, testLinkedList.set(4, 9)),
                () -> assertEquals(2, testLinkedList.set(2, 9)),
                () -> assertEquals("[9, 1, 9, 3, 9]", testLinkedList.toString())
        );
    }

    private void fullListWithIntegers(int index, MyList<Integer> list) {
        for (int i = 0; i < index; i++) {
            list.add(i);
        }
    }
}