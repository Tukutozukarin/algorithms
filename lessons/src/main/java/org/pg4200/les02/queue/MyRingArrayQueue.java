package org.pg4200.les02.queue;

public class MyRingArrayQueue<T> implements MyQueue<T> {
    protected Object[] data;


    private int head = -1;
    private int tail = -1;

    public MyRingArrayQueue(){
        this(10);
    }

    public MyRingArrayQueue(int capacity){
        data = new Object[capacity];
    }

    @Override
    public void enqueue(T value)
    {

        if(isEmpty())
        {
            head = 0;
            tail = 0;
        } else if(head <= tail){

            if(tail < data.length - 1) {
                tail++;
            } else
            {
                if(head != 0){
                    tail = 0;
                } else
                {

                    Object[] temp = new Object[data.length * 2];

                    for(int i=0; i<data.length; i++)
                    {
                        temp[i] = data[i];
                    }
                    data = temp;
                    tail++;
                }
            }
        } else {
            assert tail < head;

            if(tail < head - 1)
            {
                tail++;
            } else {

                Object[] temp = new Object[data.length * 2];

                int k = data.length - head;
                for(int i=0; i<k; i++)
                {
                    temp[i] = data[head + i];
                }

                for(int i=0; i< (tail+1); i++)
                {
                    temp[k + i] = data[i];
                }

                head = 0;
                tail = data.length;
                data = temp;
            }
        }

        data[tail] = value;
    }

    @Override
    public T dequeue()
    {
        if(isEmpty())
        {
            throw new RuntimeException();
        }

        T value = (T) data[head];

        if(size() == 1)
        {
            head = -1;
            tail = -1;
        } else
        {
            head++;
            if(head >= data.length)
            {
                head = 0;
            }
        }

        return value;
    }

    @Override
    public T peek() {
        if(isEmpty())
        {
            throw new RuntimeException();
        }

        return (T) data[head];
    }

    @Override
    public int size()
    {

        if(head < 0)
        {
            return 0;
        } else if(head == tail)
        {
            return 1;
        } else if(head < tail)
        {

            return (tail - head) + 1;
        } else {
            int size = 0;

            size += (data.length - head);

            size += tail + 1;

            return size;
        }
    }
}
