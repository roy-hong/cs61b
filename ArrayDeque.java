/**

 * Created by Roy on 2/4/16.

 */

public class ArrayDeque<Item> implements Deque<Item>{

    private int arraysize = 8;
    private Item[] items;
    private int size;
    private int nextFirst = arraysize / 2; //half of the size
    private int nextLast = (arraysize / 2) + 1; // half + 1
    private double memoryefficiency;

    public ArrayDeque() {

        items = (Item[]) new Object[arraysize];

        size = 0;

        memoryefficiency = size / arraysize;

    }


    @Override
    public void addFirst(Item item) {

        size += 1;

        if(size > arraysize) this.resize();

        items[nextFirst] = item;

        nextFirst -= 1;

        if (nextFirst < 0) nextFirst = arraysize - 1;// take it to the last elem

    }


    @Override
    public void addLast(Item item) {

        size += 1;

        if(size > arraysize) this.resize();

        items[nextLast] = item;

        nextLast += 1;

        if (nextLast > arraysize - 1) nextLast = 0;

    }
    @Override
    public boolean isEmpty() {

        if (size == 0) return true;

        return false;

    }
    @Override
    public int size() {

        return size;

    }
    @Override
    public void printDeque() {

        System.out.print("ArrayDeque : ");

        for(int i=0;i<arraysize;i++) {

            //regardless of nextFront & nextLast

            if(items[i] != null) {

                System.out.print(" ["+items[i]+"] ");

            } else {

                System.out.print(" null ");

            }

        }

        System.out.println(" ");

    }
    @Override
    public Item removeFirst() {

        size -= 1;

        memoryefficiency = size / arraysize;

        if(memoryefficiency < 0.063) this.resize();

        nextFirst += 1;

        if (nextFirst > arraysize - 1) nextFirst = 0;

        Item temp = items[nextFirst];

        items[nextFirst] = null;

        return temp;

    }
    @Override
    public Item removeLast() {

        size -= 1;

        memoryefficiency = size / arraysize;


        if(memoryefficiency < 0.1) this.resize();

        nextLast -= 1;

        if (nextLast < 0) nextLast = arraysize - 1;

        Item temp = items[nextLast];

        items[nextLast] = null;

        return temp;

    }

    private void resize() {

        Item[] biggerarray = (Item[]) new Object[arraysize * 2];

        Item[] smallerarray = (Item[]) new Object[arraysize * 2];

        if(size > arraysize) { //resize up

            System.arraycopy(items, nextFirst + 1, biggerarray, nextFirst + 1, arraysize - nextFirst - 1);//first half

            System.arraycopy(items, 0, biggerarray, arraysize, nextFirst + 1);//second half

            if (nextLast != 0) {

                nextLast = nextLast + arraysize;

            }

            arraysize = arraysize * 2;

            items = biggerarray;



        }

        if(memoryefficiency < 0.063) { //resize down
            System.out.println("heyheyehey resize down !!!!! size arraysize"+size+" "+arraysize);

            if(nextFirst < nextLast) {//copy once

                System.arraycopy(items, nextFirst + 1, smallerarray, nextFirst + 1, size);

            }

            else {//copy twice when elements are separated

                System.arraycopy(items, nextFirst + 1, smallerarray, (nextFirst+1)-(arraysize/2), arraysize-1-nextFirst);//first half

                System.arraycopy(items, 0, smallerarray, 0, nextLast);//second half

                nextFirst = nextFirst - (arraysize/2);

            }

            arraysize = arraysize / 2;
            memoryefficiency = size / arraysize;
            items = smallerarray;


        }

    }
    @Override
    public Item get(int index) { //returns the i th element

        int direction = nextFirst + 1 + index;

        if(direction > arraysize - 1) direction = direction - arraysize;

        return items[direction];

    }

}