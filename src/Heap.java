import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by rubatharisan on 10/12/2016.
 */
public class Heap {
    // How big is our Heap array?
    private int capacity;

    // How many elements are in our Heap array?
    private int size;

    // Our heap array
    private Vertex[] heap;

    // Our position of every vertex in our heap hashmap.
    private HashMap<Vertex, Integer> aux = new HashMap<>();

    // Is debugging on?
    public boolean debug = false;

    // Let's construct our heap
    Heap(int capacity){

        // Set the heap capacity
        this.heap = new Vertex[capacity];

        // We have 0 elements in our heap
        this.size = 0;

        // Let's define our capacity
        this.capacity = capacity;
    }


    // Get our left child of the index
    private int getLeftChildIndex(int parentIndex){
        return 2 * parentIndex + 1;
    }

    // Get our right child of the index
    private int getRightChildIndex(int parentIndex){
        return 2 * parentIndex + 2;
    }

    // Get our parent of the index
    private int getParentIndex(int childIndex){
        return (childIndex-1)/2;
    }

    // Check if the index has a left child
    private boolean hasLeftChild(int index){
        return getLeftChildIndex(index) < size;
    }

    // Check if the index has a right child
    private boolean hasRightChild(int index){
        return getRightChildIndex(index) < size;
    }

    // Check if the index has a parent
    private boolean hasParent(int index){
        return getParentIndex(index) >= 0;
    }

    // Get the left child vertex of the index
    private Vertex leftChild(int index){
        return heap[getLeftChildIndex(index)];
    }

    // Get the right child vertex of the index
    private Vertex rightChild(int index){
        return heap[getRightChildIndex(index)];
    }

    // Get the parent vertex of the index
    private Vertex parent(int index){
        return heap[getParentIndex(index)];
    }

    // Swap two indexes with each other.
    private void swap(int indexOne, int indexTwo){

        // Let's make sure that our hashmap is updated too
        aux.put(heap[indexOne], indexTwo);
        aux.put(heap[indexTwo], indexOne);

        // Update our heap array
        Vertex tmp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = tmp;


        if(debug){
            System.out.println("[heap] Swapping vertex: " + heap[indexOne].getId() +
                    " (distance: " + heap[indexOne].getDistance() + ") with vertex: " +
                    heap[indexTwo].getId() + " (distance " + heap[indexTwo].getDistance() + ")");
        }
    }

    // Check if capacity is OK in our heap, else double up the heap.
    private void checkCapacity(){

        if(size == capacity){
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity *= 2;

            if(debug){
                System.out.println("[heap] Resizing our heap capacity from " + (capacity/2) + " to " + capacity);
            }
        }

        /* if(size < (capacity/4)){
            heap = Arrays.copyOf(heap, capacity/4);
            capacity /= 4;
        } */


    }

    // Let's peek into the root element of our heap.
    public Vertex peek(){
        if(size == 0){
            System.out.println("Heap is empty");
        } else {

            return heap[0];
        }

        return null;
    }

    // Let's poll out the root of our heap
    public Vertex poll(){
        if(size == 0){
            System.out.println("Not possible to get anything out of heap - it's empty...");
        } else {
            // Get the root element
            Vertex minVertex = heap[0];

            // Get the latest element in our heap
            heap[0] = heap[size - 1];

            // Decrease the size of our heap
            size--;

            if(debug) {
                System.out.println("[heap] Polling vertex: " + minVertex.getId() + " (distance: " + minVertex.getDistance() + ") out of the heap");
                System.out.println("[heap] Perculating vertex: " + heap[0].getId() + " (distance: " + heap[0].getDistance() + ") down from the root in the heap");
            }

            // Let's try to move down our root element (root have to be the smallest element always!)
            perculateDown(0);

            // Return our pulled out vertex
            return minVertex;
        }

        return null;
    }

    // Let's add a element to our heap
    public void add(Vertex vertex){

        // Let's check if our heap is big enough? else make it bigger!
        checkCapacity();

        // Let's put the vertex into the hashmap
        aux.put(vertex, size);

        // Let's put the vertex into our heap array
        heap[size] = vertex;

        if(debug){
            System.out.println("[heap] Adding vertex: " + vertex.getId() + " (distance: " + vertex.getDistance() + ")");
        }

        // Let's try to see if the inserted vertex is smaller then it's parent.
        perculateUp(size);

        // Let's increase the size of the heap.
        size++;

    }

    // Let's try to check if a vertex priority (distance) is smaller than it's parent, if it is then swap it with it's parent.
    private void perculateUp(int index){
        while(hasParent(index) && parent(index).getDistance() > heap[index].getDistance()){

            if(debug){
                System.out.println("[heap] Perculating vertex: " + heap[index].getId() + " (distance: " + heap[index].getDistance() + ") up");
            }

            // Swap the parent with the child - because the parent is of higher priority than the child
            swap(getParentIndex(index), index);

            index = getParentIndex((index));
        }
    }

    // Let's try to check if a vertex priority (distance) is bigger than it's child, if it is then swap it with it's child .
    private void perculateDown(int index){
        while(hasLeftChild(index)){
            int smallerChildIndex = getLeftChildIndex(index);

            if(hasRightChild(index) && rightChild(index).getDistance() < leftChild(index).getDistance()){
                smallerChildIndex = getRightChildIndex(index);
            }

            if(heap[index].getDistance() < heap[smallerChildIndex].getDistance()){
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;

        }
    }

    // Decrease the key, the priority, the distance.
    public void decreaseKey(Vertex v){
        // Let's try to see if the parent has a higher priority than itself (We are fetching the element from the hashmap)
        perculateUp(aux.get(v));

        if(debug){
            printHeap();
        }
    }

    // Let's print our heap
    public void printHeap(){
        System.out.println();
        System.out.println("[heap] Printing out the heap");

        int counter = 0;
        for(int i = 0; i < this.size; i++){
            System.out.println("Index: " + counter + ", Vertex: " + heap[i].getId() + ", Distance: " + heap[i].getDistance());
            counter++;
        }

        System.out.println();
    }

    // Let's find a position of a vertex
    public void findPosition(Vertex v){
        System.out.println("[heap] Vertex: " + v.getId() + " is positioned at " + aux.get(v));
    }

    // Is our heap empty?
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }

        return false;
    }

    // Does our heap contain a certain vertex?
    public boolean contains(Vertex v){
        return aux.containsKey(v);
    }


    // Test heap method
    /* public static void main(String args[]){
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        v1.distance = 5;
        v2.distance = 4;
        v3.distance = 3;
        v4.distance = 2;
        v5.distance = 7;

        Heap heap = new Heap(5);
        heap.add(v5);
        heap.add(v1);
        heap.add(v2);
        heap.add(v4);
        heap.add(v3);
        heap.printHeap();

        v5.distance = 2;
        heap.decreaseKey(v5);

        heap.printHeap();


    } */






}
