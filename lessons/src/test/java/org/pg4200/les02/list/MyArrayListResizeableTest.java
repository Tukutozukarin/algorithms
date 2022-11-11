package org.pg4200.les02.list;

public class MyArrayListResizeableTest extends MyListTestTemplate{



    @Override
    protected <T> MyList<T> getNewInstance(Class<T> klass) {
        return new MyArrayListResizeable<T>(1);
    }
}
