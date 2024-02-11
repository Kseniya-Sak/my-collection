package my.collection;

import org.junit.jupiter.api.Test;
import util.Cat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    static MyList<Cat> catsMyArrayList = new MyArrayList<>();
    static MyList<Cat> catsMyLinkedList = new MyLinkedList<>();
    static List<Cat> catsArrayList = new ArrayList<>();

    private void addMethod() {
        Cat cat1 = new Cat("Tom", 10);
        Cat cat2 = new Cat("Laska", 2);
        Cat cat3 = new Cat("Ann", 5);
        Cat cat4 = new Cat("Uni", 4);
        Cat cat5 = new Cat("Ann", 1);

        catsMyArrayList.add(cat1);
        catsMyArrayList.add(cat2);
        catsMyArrayList.add(cat3);
        catsMyArrayList.add(cat4);
        catsMyArrayList.add(cat5);

        catsMyLinkedList.add(cat1);
        catsMyLinkedList.add(cat2);
        catsMyLinkedList.add(cat3);
        catsMyLinkedList.add(cat4);
        catsMyLinkedList.add(cat5);

        catsArrayList.add(cat1);
        catsArrayList.add(cat2);
        catsArrayList.add(cat3);
        catsArrayList.add(cat4);
        catsArrayList.add(cat5);
    }

    @Test
    void sort() {
        Collections.sort(catsArrayList);
        MyList.sort(catsMyArrayList);
        MyList.sort(catsMyLinkedList);
        assertAll(
                () -> assertTrue(catsArrayList.toString().equals(catsMyArrayList.toString())),
                () -> assertTrue(catsArrayList.toString().equals(catsMyLinkedList.toString()))
        );
    }
}