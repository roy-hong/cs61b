/**
 * Created by Roy on 2/4/16.
 */

public class LinkedListDeque<Item> implements Deque<Item>{


    private Node sentinel;
    private int size;


    private class Node {
        private Item item;
        private Node prev;
        private Node next;


        private Node(Item i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }


    }


    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;

    }



    @Override
    public void addFirst(Item item) {

        if(sentinel.next == null) { // if  size == 0
            sentinel.next = new Node(item, null, null);
            sentinel.next.prev = sentinel.next;
            sentinel.next.next = sentinel.next;

        } else {

            Node oldDeque = sentinel.next;
            sentinel.next = new Node(item, oldDeque.prev, oldDeque);
            oldDeque.prev.next = sentinel.next;
            oldDeque.prev = sentinel.next;

        }

        size += 1;
    }
    @Override
    public void addLast(Item item) {

        if(sentinel.next == null) {
            sentinel.next = new Node(item, null, null);
            sentinel.next.prev = sentinel.next;
            sentinel.next.next = sentinel.next;
        } else {

            Node lastelement = new Node(item, sentinel.next.prev, sentinel.next);

            sentinel.next.prev.next = lastelement;
            sentinel.next.prev = lastelement;
        }

        size += 1;

    }

    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        } else return false;

    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        Node p = sentinel.next;

         do {
            System.out.print(" ["+p.item+"] ->");
            p = p.next;

        } while(p != sentinel.next);

        System.out.println(" ");


    }
    @Override
    public Item removeFirst() {
        if (size == 0) return null;


        Item temp = sentinel.next.item;

        if(size == 1) {
            sentinel.next = null;

        } else {
            sentinel.next.next.prev = sentinel.next.prev;
            sentinel.next.prev.next = sentinel.next.next;
            sentinel.next = sentinel.next.next;
        }


        size -= 1;


        return temp;

    }

    @Override
    public Item removeLast() {

        if (size == 0) return null;

        if(size == 1) {
            Item temp1 = sentinel.next.item;
            sentinel.next = null;

            size -= 1;

            return temp1;


        } else {
            Item temp2 = sentinel.next.prev.item;

            sentinel.next.prev.prev.next = sentinel.next;
            sentinel.next.prev = sentinel.next.prev.prev;

            size -= 1;

            return temp2;
        }

    }

    @Override
    public Item get(int index) {

        //what if the input is greater than the size?
        Node target = sentinel.next;

        while(index != 0) {

            target = target.next;
            index -= 1;
        }
        return target.item;

    }

    private Item getRecursivehelper(Node n, int index) {

        if (n == null || n == sentinel) return null;
        //base case
        if (index == 0) {
            return n.item;
        } else {
            n = n.next;
            return getRecursivehelper(n, index-1);
        }

    }



    public Item getRecursive(int index) {
        return getRecursivehelper(sentinel.next, index);

    }




}
