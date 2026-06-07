package utils.Stack;

import java.util.ArrayList;

public class DynamicStack {
    private final ArrayList<String> elements;

    public DynamicStack() {
        elements = new ArrayList<>();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void add(String element) {
        elements.addLast(element);
    }

    public String delete() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.\n");
            return null;
        }
        return elements.removeLast();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Pila vacía []";

        StringBuilder sb = new StringBuilder();
        sb.append("Tope -> ");
        for (int i = elements.size() - 1; i >= 0; i--) {
            sb.append("[").append(elements.get(i)).append("] ");
        }
        return sb.toString();
    }
}
