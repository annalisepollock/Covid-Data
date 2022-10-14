import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Stores data sorted by the size of the key in a tree
 * @param <Key>: Generic to hold the key for each data piece being placed in the tree
 * @param <Value>: Generic to hold the value corresponding to each key placed in the tree
 */
public class BinarySearchTree <Key extends Comparable<Key>, Value>{
    private Node root;
    private String name;
    File output;
    FileWriter writer;

    /**
     * Initializes Binary Search tree name.
     * @param n String holding the name for the tree that will be used if the method is called to write the tree to a file
     * @return: NA
     * @throws: NA
     */
    //initialize output file and writer
    BinarySearchTree(String n) throws IOException {
        name = n;
        output = new File(name);
        writer = new FileWriter(name);
        output.createNewFile();
        writer.write("Inorder Traversal\n");


    }

    /**
     * Initialize Data structures used to create tree,
     */
    //stores data
    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private Node(Key k, Value v) {
            key = k;
            val = v;
        }
    }

    /**
     * Takes a key sends it into a private method get with the root to search for the given key in the tree
     * @param key Generic that connects to Node storing data
     * @return value: Generic holding the data referenced to by the key
     * @throws: NA
     */
    //Code from Textbook
    public Value get(Key key){return get(root, key);}

    /**
     * Loops through every item in the tree and compares to the given key until it finds it or reaches the end of the queue.
     * @param root: A node referencing the top of the tree
     * @param key: A generic referencing the data that the user is searching for in the tree
     * @return: Returns the data stored in the value of the node with a key matching the parameter
     * @throws:
     */
    private Value get(Node root, Key key){
        //check that key is not null
        if(key == null){
            throw new IllegalArgumentException("calls get() with null key");
        }
        //check that root of tree is not null
        if(root == null){
            return null;
        }
        //compare key with current root
        int comparison = key.compareTo(root.key);
        //if key is larger than root key search to the right
        if(comparison > 0){
            return(get(root.right, key));
        }
        //if key is smaller than root key search to the left
        else if (comparison < 0){
            return(get(root.left, key));
        }
        //if it is equal than you have found the key you're looking for
        else{
            return root.val;
        }
    }

    /**
     * Takes a key and a value and passes it in to a private put function with the root and returns the output
     * @param key: Generic holding the key that will be placed into the tree
     * @param val: Generic holding the data that will be stored in the tree
     * @return: NA
     * @throws: NA
     */
    public void put(Key key, Value val){root = put(root, key, val);}

    /**
     * Starts at the root node of the tree and moves left or right depending on the size of the key until it finds a null location where the key fits
     * Places the key and value at that place in the tree;
     * @param x: A Node representing where to start searching in the tree for a null child
     * @param key: A generic holding the value that determines the data's location in the tree
     * @param val: A generic holding the data to be stored in the tree;
     * @return: returns the node that was added to the tree
     * @throws: NA
     */
    private Node put(Node x, Key key, Value val){
        //base case for when you've reached the correct spot in tree
        if(x == null){
            return new Node(key, val);
        }
        //compare with current key, and if it's less place to the left, if its more place to the right
        int comparison = key.compareTo(x.key);
        if(comparison < 0){
            x.left =  put(x.left, key, val);// recursively move through tree until end is reached
        }
        else if(comparison > 0){
            x.right = put(x.right, key, val);
        }
        else{
            x.val = val;// if comparison is equal then key already in table, replace value.
        }
        return x;
    }

    /**
     * Calls private inorder method with the root of the tree so that the user does not have to understand the structure of the tree
     * @param: NA
     * @return: NA
     * @throws: NA
     */
    public void writeInOrder() throws IOException {writeInOrder(root);}

    /**
     * Moves all the way to the leftmost node in the tree and goes through each node in order of size of key
     * For each node write the data stored within it to a file
     * @param x: A node that represents where in the tree the program should start searching for the smallest key
     * @return: NA
     * @throws: NA
     */
    //code from Geeksforgeeks
    //https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
    private void writeInOrder(Node x) throws IOException {
        //base case when you've reached end of tree
        if(x == null){
            return;
        }
        //loop all the way to the leftmost end of subtree
        writeInOrder(x.left);

        //write data in node to file
        writer.write(x.key.toString());

        //loop through to rightmost end of subtree
        writeInOrder(x.right);
    }

}
