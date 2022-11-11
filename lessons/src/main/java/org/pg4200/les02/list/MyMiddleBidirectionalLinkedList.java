package org.pg4200.les02.list;

// WARNING: this is one of the 12 classes you need to study and know by heart


/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyMiddleBidirectionalLinkedList<T> implements MyList<T> {

    /*
        For each "value" in the container, we create a Node object to contain it.
        Each node object will have a pointer to the next one in the list, apart
        from the last one (which will have "null")
     */
    private class ListNode{
        T value;
        ListNode next;
        ListNode previous;
    }

    /**
     * The first element in the list
     */
    private ListNode head;

    /**
     *  The last element in the list
     */
    private ListNode tail;

    private ListNode middle;
    private int middleIndex = 0;
    /**
     *  The number of elements contained in this container
     */
    private int size;


    @Override
    public void delete(int index) {

        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }


        if(index == 0){
            if(head.next != null){
                //the new head
                head = head.next;
                head.previous = null;
            } else {
                //only one element in the collection, which now becomes empty
                head = null;
                tail = null;
            }
            if (index < middleIndex) {
                middleIndex--; // so still points to current middle
            }
        } else {

            ListNode current = null;
            boolean goForward = true;
            int counter = 0;

            if(index < size/4){
                // head
                current = head;
                goForward = true;
                counter = 0;
            } else if(index > size*3/4) {
                //tail
                current = tail;
                goForward = false;
                counter = size-1;
            } else {
                // middle
                current = middle;
                counter = middleIndex;
                if (index > size/2) {
                    goForward = true;
                }
                else {
                    goForward = false;
                }
            }

            if (goForward) {
                while(counter != index - 1){
                    current = current.next;
                    counter++;
                }
            }
            else {

                while(counter != index-1) {
                    current = current.previous;
                    counter--;
                }
            }


            /*
                after that while-loop, the "current" variable does point to the node BEFORE the one we want to
                delete, as the loop has condition "counter != index - 1".
                Assume for example you want to delete the element at position index=4.
                The element before it is index=3. So, the "current" variable will point the element
                which is at position 3, and not 4. I.e., "current == list.get(3)".
                To delete an element, we need to remove/modify the "next" link from the previous node in the list
             */

            if(current.next == tail){
                //we are trying to delete the tail, so we need to update it
                tail = current;
            }

            current.next = current.next.next;
            if(current.next != null) {
                current.next.previous = current;
            }

            //del
            // Hvis index < middleIndex, er ny temp middleIndex--; hvis index == middleIndex, sett temp middle = node
            if (index < middleIndex) {
                middleIndex--; // so still points to current middle
            }
            else if (index == middleIndex) {
                // delete middle node, need a new middle
                if (size%2 == 0) {
                    // new middle = oldMiddle.previous
                    middle = current;
                    middleIndex--;
                }
                else {
                    middle = current.next;
                }
            }


            /*
                The line above could look strange...

                A -> B -> C

                here we want to remove B, where "current=A",
                and so we want to end up with

                A -> C

                B is the next of A, where C is the next of B.
                The next of B is also the "next next" of A.
             */
        }
        size--;
        updateMiddle();
    }

    private void updateMiddle() {
        int newMiddleIndex = size/2;
        if (size%2 == 0) {
            // Should always choose lower when 2 possible middles, so subtract 1.
            newMiddleIndex--;
        }
        int counter = middleIndex;
        ListNode current = middle;
        if (size == 0) {
            current = null;
            newMiddleIndex = 0;
        }
        else if (size == 1) {
            current = head;
            newMiddleIndex = 0;
        }
        else if (newMiddleIndex > middleIndex) {
            // go forward
            while(counter != newMiddleIndex){
                current = current.next;
                counter++;
            }
        }
        else {
            // go backwards
            while(counter != newMiddleIndex) {
                current = current.previous;
                counter--;
            }
        }
        middle = current;
        middleIndex = newMiddleIndex;

    }

    /*
        Is the above implementation of "delete()" better or worse than in
        MyArrayList?
        It depends...
        If we delete the first element, the list is very quick, whereas with
        array we need to shift left the entire collection.
        On the other hand, if we delete the last, there is no shift with array,
        but in list we need to traverse the entire collection, even if we do
        actually have a pointer to "tail".
     */


    public ListNode getNode(int index) {

        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }

        //need to traverse all the nodes until the "index"th one.

        ListNode current = null;
        int counter = -1;
        boolean goForward = true;


        if(index <= size/4) {
            current = head;
            counter = 0;
            goForward = true;

        } else if(index < size*3/4) {
            current = middle;
            counter = middleIndex;
            if (index >= size/2) {
                goForward = true;
            }
            else {
                goForward = false;
            }
        } else {
            current = tail;
            counter = size -1;
            goForward = false;
        }

        if(goForward) {
            while(counter <= index) {

                if(counter == index){
                    return  current;
                }

                current = current.next;
                counter++;

            }
        } else {
            while(counter >= index) {

                if(counter == index){
                    return current;
                }

                current = current.previous;
                counter--;
            }
        }


        /*
            We should never reach this code, unless bug.
            But we still need to return null, otherwise the
            compiler complains
          */
        assert false;
        return null;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(int index, T value) {

        if(index < 0 || index > size){
            //note that here "size" is a valid index
            throw new IndexOutOfBoundsException();
        }

        ListNode node = new ListNode();
        node.value = value;

        if(head == null){
            //add on empty list
            assert size == 0;
            head = node;
            tail = node;

        } else if(index == 0){
            //add at beginning of non-empty list
            node.next = head;
            node.previous = node;
            head.previous = node;
            head = node;

        } else if(index == size) {
            /*
                Add at the end of non-empty list.
                Note: using "tail" allows us for an efficient implementation
                of "add(value)", as we do not need to traverse the whole list
                to append at the end.
             */
            tail.next = node;
            node.previous = tail;
            tail = node;

        } else {
            //insertion in the middle of the list
            int counter = 0;
            ListNode previous = head;

            if(index >= size/2) {
                previous = tail;
                counter = size-1;

                while(counter != index-1) {
                    counter--;
                    previous = previous.previous;
                }
            } else {
                while(counter != index - 1){
                    previous = previous.next;
                    counter++;
                }

            }


            node.next = previous.next;
            previous.next = node;
            node.previous = previous;
            node.next.previous = node;



            /*
                We are in the case of

                ... -> A -> B -> ...

                and we want to insert X at the position of B, resulting in

                ... -> A -> X -> B -> ...

                this means that A.next will have to point to X,
                whereas X.next would be B. We do not need to modify B.
                As we need to iterate from the head, we stop at A, as we need
                to change its A.next value.
                We do not have B if we stop at A. But B can be read by simply
                noticing that B == A.next.
                Therefore, we can iterate with a "previous" variable until we reach
                A, which would be at position index-1.
             */
        }
        // add
        // Hvis index <= middleIndex, er ny temp middleIndex++;
        if (index <= middleIndex) {
            middleIndex++;
        }
        size++;
        updateMiddle();
    }

    @Override
    public int size() {
        return size;
    }
}


