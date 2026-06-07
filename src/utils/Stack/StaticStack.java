package utils.Stack;

public class StaticStack {
    private final String[] elements;
    private int top;
    private int quantity;

    public StaticStack(int length) {
        this.elements = new String[length];
        top = this.elements.length;
        quantity = 0;
    }

    public boolean isEmpty() {
        return quantity == 0;
    }

    public boolean isFull() {
        return quantity == elements.length;
    }

    public void add(String element) {
        if (isFull()) {
            System.out.println("La pila está llena.\n");
            return;
        }
        quantity++;
        elements[--top] = element;
    }

    public String delete() {
        if (isEmpty()) {
            System.out.println("La pila está vacía.\n");
            return null;
        }
        String tmp = elements[top];
        quantity--;
        top++;
        return tmp;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Pila vacía []";

        StringBuilder sb = new StringBuilder();
        sb.append("Tope -> ");
        for (int i = top; i < elements.length; i++) {
            sb.append("[").append(elements[i]).append("] ");
        }
        return sb.toString();
    }
}
