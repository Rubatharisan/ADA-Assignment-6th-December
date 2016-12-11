import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by rubatharisan on 10/12/2016.
 */
public class Heap {
    private int capacity;
    private int size;
    private Vertex[] heap;

    private HashMap<Vertex, Integer> aux = new HashMap<>();

    public boolean debug = false;

    Heap(int capacity){
        this.heap = new Vertex[capacity];

        this.size = 0;
        this.capacity = capacity;
    }


    private int getLeftChildIndex(int parentIndex){
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex){
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex){
        return (childIndex-1)/2;
    }


    private boolean hasLeftChild(int index){
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index){
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index){
        return getParentIndex(index) >= 0;
    }


    private Vertex leftChild(int index){
        return heap[getLeftChildIndex(index)];
    }

    private Vertex rightChild(int index){
        return heap[getRightChildIndex(index)];
    }

    private Vertex parent(int index){
        return heap[getParentIndex(index)];
    }

    private void swap(int indexOne, int indexTwo){

        aux.put(heap[indexOne], indexTwo);
        aux.put(heap[indexTwo], indexOne);

        Vertex tmp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = tmp;


        if(debug){
            System.out.println("[heap] Swapping vertex: " + heap[indexOne].getId() +
                    " (distance: " + heap[indexOne].distance + ") with vertex: " +
                    heap[indexTwo].getId() + " (distance " + heap[indexTwo].distance + ")");
        }
    }

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

    public Vertex peek(){
        if(size == 0){
            System.out.println("Heap is empty");
        } else {

            return heap[0];
        }

        return null;
    }

    public Vertex poll(){
        if(size == 0){
            System.out.println("Not possible to get anything out of heap - it's empty...");
        } else {

            Vertex minVertex = heap[0];

            heap[0] = heap[size - 1];

            size--;

            if(debug) {
                System.out.println("[heap] Polling vertex: " + minVertex.getId() + " (distance: " + minVertex.distance + ") out of the heap");
                System.out.println("[heap] Perculating vertex: " + heap[0].getId() + " (distance: " + heap[0].distance + ") down from the root in the heap");
            }

            perculateDown(0);

            return minVertex;
        }

        return null;
    }

    public void add(Vertex vertex){
        checkCapacity();

        aux.put(vertex, size);
        heap[size] = vertex;

        if(debug){
            System.out.println("[heap] Adding vertex: " + vertex.getId() + " (distance: " + vertex.distance + ")");
        }

        perculateUp(size);
        size++;

    }

    private void perculateUp(int index){
        while(hasParent(index) && parent(index).distance > heap[index].distance){

            if(debug){
                System.out.println("[heap] Perculating vertex: " + heap[index].getId() + " (distance: " + heap[index].distance + ") up");
            }

            swap(getParentIndex(index), index);
            index = getParentIndex((index));
        }
    }

    private void perculateDown(int index){
        while(hasLeftChild(index)){
            int smallerChildIndex = getLeftChildIndex(index);

            if(hasRightChild(index) && rightChild(index).distance < leftChild(index).distance){
                smallerChildIndex = getRightChildIndex(index);
            }

            if(heap[index].distance < heap[smallerChildIndex].distance){
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;

        }
    }

    public void decreaseKey(Vertex v){
        perculateUp(aux.get(v));

        if(debug){
            printHeap();
        }
    }

    public void printHeap(){
        System.out.println();
        System.out.println("[heap] Printing out the heap");

        int counter = 0;
        for(int i = 0; i < this.size; i++){
            System.out.println("Index: " + counter + ", Vertex: " + heap[i].getId() + ", Distance: " + heap[i].distance);
            counter++;
        }

        System.out.println();
    }

    public void findPosition(Vertex v){
        System.out.println("[heap] Vertex: " + v.getId() + " is positioned at " + aux.get(v));
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }

        return false;
    }

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
