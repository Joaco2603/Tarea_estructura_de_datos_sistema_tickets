package utils.Queue;

import java.util.ArrayList;

/**
 * Cola dinámica genérica.
 * Sin límite de capacidad, crece según necesidad.
 */
public class DynamicQueue<T> {
    private final ArrayList<T> elements;

    public DynamicQueue() {
        elements = new ArrayList<>();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void add(T element) {
        elements.addLast(element);
    }

    public T delete() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.\n");
            return null;
        }
        return elements.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.\n");
            return null;
        }
        return elements.getFirst();
    }

    public int getQuantity() {
        return elements.size();
    }
}
