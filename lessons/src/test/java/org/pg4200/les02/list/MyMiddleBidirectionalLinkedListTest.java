package org.pg4200.les02.list;

import org.pg4200.les02.list.MyList;
import org.pg4200.les02.list.MyListTestTemplate;
import org.pg4200.les02.list.MyMiddleBidirectionalLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MyMiddleBidirectionalLinkedListTest  extends MyListTestTemplate {

    @Override
    protected <T> MyList<T> getNewInstance(Class<T> klass) {
        return new MyMiddleBidirectionalLinkedList<>();
    }
}