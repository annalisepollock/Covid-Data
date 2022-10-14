import java.util.ArrayList;

/**
 * Stores generic items and prioritized minimum to be removed first
 * @param <item> Generic to ensure the class can be used in multiple ways
 */
public class MinPQ <item extends Comparable<item>>{
    private Node head;
    private Node end;
    private int size = 0;
    private int Max_Size;

    MinPQ(int max){
        Max_Size = max;
    }

    /**
     * Stores data and creates links between data so queue can be traversed
     */
    public class Node{
        private item data;
        private Node next;
        private Node previous;

        /**
         * initializes individual node and links it to the rest of the nodes in the list
         * @param d: generic that stores the data to be held in that node
         * @throws: NA
         */
        Node(item d){
            data = d;
        }

    }

    /**
     * Adds new node to the end of the list. Checks if list has surpassed max value and if so calls dequeue method
     * @param data: Node that holds data that is going to be added to the queue
     * @param: NA
     * @throws: NA
     */
    public void enqueue(item data){
        Node info = new Node(data);
        //if queue empty set head and end to data
        if(size == 0 ){
            head = info;
            end = info;
            head.next = end;
            end.previous = head;
        }
        //otherwise add data node to end of queue
        else{
            end.next = info;
            info.previous = end;
            end = info;
        }
        //increment size
        size++;
        //if queue is bigger than the max sized passed by user, dequeue
        while(size > Max_Size){
            dequeue();
        }
    }

    /**
     * Loops through queue and finds Node with smallest number of new cases and removes it from the queue
     * @param: NA
     * @return: Node that holds the data that was removed from the queue
     * @throws: NA
     */
    public Node dequeue(){
        Node temp = head;
        Node min = head;
        //start at head and loop through queue to find smallest
        while(temp != null){
            if(temp.data.compareTo(min.data) < 0){
                min = temp;
            }
            temp = temp.next;
        }
        //remove min if it is head
        if(min == head){
            head = min.next;
            head.previous = null;
        }
        //remove min if it is end
        if(min == end){
            end = min.previous;
            end.next = null;
        }
        //otherwise remove from middle and reorganize next and previous
        else{
            min.previous.next = min.next;
            min.next.previous = min.previous;
        }
        size--;
        return min;
    }

    /**
     * empties queue after use
     * @throws:NA
     * @param: NA
     * @return: NA
     */
    public void clear(){
        head = null;
        end = null;
        size = 0;
    }

    /**
     * loops through the queue and add the data in each node to an array to return to the user
     * @return: a generic array containing all the data in queue
     * @param: NA
     * @throws: NA
     */
    public ArrayList<item> getAll(){
        //initialize new list
        ArrayList<item> fullPQ = new ArrayList<item>();
        Node temp = head;
        //loop through PQ and each item in the queue to list
        for(int i = 0; i < size; i ++){
            fullPQ.add(temp.data);
            temp = temp.next;
        }
        return fullPQ;
    }

    /**
     * returns the size of the queue to the user
     * @return size: and int containing the size of the queue
     * @param: NA
     * @throws: NA
     */
    public int size(){return size;}
    //loop through queue and print
    public void print(){
        Node temp = head;
        while(temp.next != null){
            System.out.println(temp.data);
        }
    }
}

