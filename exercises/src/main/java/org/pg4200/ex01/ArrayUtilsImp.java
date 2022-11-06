package org.pg4200.ex01;


public class ArrayUtilsImp implements ArrayUtils {




    @Override
    public int min(int[] array) throws IllegalArgumentException {

        if(array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int minimum = Integer.MAX_VALUE;
        for (int elem : array ){
            if(elem < minimum) {
                minimum = elem;
            }

        }
        return minimum;
    }

    @Override
    public int max(int[] array) throws IllegalArgumentException {
        if(array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
       int maximum = Integer.MIN_VALUE;
       for (int elem : array) {
           if(elem > maximum) {
               maximum = elem;
           }
       }
       return maximum;

    }

    @Override
    public double mean(int[] array) throws IllegalArgumentException {
        if(array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int sum = 0;

        for(int elem : array) {
            sum += elem;
        }
        return sum/ array.length;
    }
}
