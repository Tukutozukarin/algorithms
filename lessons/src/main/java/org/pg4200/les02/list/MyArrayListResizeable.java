package org.pg4200.les02.list;

public class MyArrayListResizeable<T> extends MyArrayList<T>{

    public MyArrayListResizeable(){
        super();
    }

    public MyArrayListResizeable(int capacity){
        super(capacity);
    }

    @Override
    public void add(int index, T value) {

        if(data.length == size) {

            Object[] temp = data;
            data = new Object[temp.length*2];

            for( int i=0; i < temp.length; i++){
                data[i] = temp[i];
            }
         //   System.arraycopy(temp, 0,data,0,temp.length);
        }
        super.add(index, value);
    }
}