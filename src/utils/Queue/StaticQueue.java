package utils.Queue;

/**
 * Cola estática circular genérica.
 * Capacidad fija definida en el constructor.
 */
public class StaticQueue<T> {
    private final T[] elements;
    private int front;
    private int end;
    private int quantity;

    @SuppressWarnings("unchecked")
    public StaticQueue(int length) {
        elements = (T[]) new Object[length];
        front = 0;
        end = -1;
        quantity = 0;
    }

    public boolean isEmpty() {
        return quantity == 0;
    }

    public boolean isFull() {
        return quantity == elements.length;
    }

    public void add(T element) {
        if (isFull()) {
            System.out.println("La cola está llena.\n");
            return;
        }
        end = (end + 1) % elements.length;
        elements[end] = element;
        quantity++;
    }

    public T delete() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.\n");
            return null;
        }
        T tmp = elements[front];
        front = (front + 1) % elements.length;
        quantity--;
        return tmp;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.\n");
            return null;
        }
        return elements[front];
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCapacity() {
        return elements.length;
    }
}
