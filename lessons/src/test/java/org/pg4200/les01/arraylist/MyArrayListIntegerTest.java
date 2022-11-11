package org.pg4200.les01.arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyArrayListIntegerTest {
    private MyArrayListInteger list;

    @BeforeEach
    public void initTest() { list = new MyArrayListInteger(10);}

    @Test
    public void testEmpty() {
        assertEquals(0, list.size());
    }

    @Test
    public void testAddOneElement() {
        int n = list.size();

        list.add(3);

        assertEquals(n+1, list.size());
    }

    @Test
    public void testAddAndRetrieveElement() {

        Integer value = 3;

        list.add(value);

        Integer res = list.get(0);

        assertEquals(value, res);
    }

    @Test
    public void testAdd5Elements(){

        assertEquals(0, list.size());
        int a = 1;
        int b = 2;
        int c = 3;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(a);
        list.add(a);

        assertEquals(a, list.get(0));
        assertEquals(b, list.get(1));
        assertEquals(c, list.get(2));
        assertEquals(a, list.get(3));
        assertEquals(a, list.get(4));
    }

    @Test
    public void testOutOfIndex(){
        assertNull(list.get(-5));
        assertNull(list.get(42));
    }
}
