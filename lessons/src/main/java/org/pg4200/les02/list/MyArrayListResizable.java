package org.pg4200.les02.list;

public class MyArrayListResizable<T> extends MyArrayList<T> {

    public MyArrayListResizable(){
        super();
    }

    public MyArrayListResizable(int capacity) { super(capacity);}

    @Override
    public void add(int index, T value) {

        if(data.length == size) {

            Object[] temp = data;
            data = new Object[temp.length*2];

            for(int i=0; i < temp.length; i++) {
                data[i] = temp[i];
            }
        }
        super.add(index, value);
    }
}
