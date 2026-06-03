package utils;

import entities.Ticket;

public class LinkedList {
    private NodeList<Ticket> head;

    public boolean isEmpty() {
        return head == null;
    }

    public NodeList<Ticket> getHead() {
        return head;
    }

    public void setHead(NodeList<Ticket> head) {
        this.head = head;
    }

    public void insertFirst(String description, String fullName, int priority) {
        NodeList<Ticket> newNode = new NodeList<Ticket>(new Ticket(description, fullName, priority));
        newNode.setNext(head);
        head = newNode;
    }

    public void insertAtEnd(String description, String fullName, int priority) {
        NodeList<Ticket> newNode = new NodeList<>(new Ticket(description, fullName, priority));

        if(isEmpty()){
            head = newNode;
            return;
        }

        NodeList<Ticket> tmp = head;
        while(tmp.getNext() != null){
            tmp = tmp.getNext();
        }

        tmp.setNext(newNode);
    }

    public void add(Ticket ticket) {
        NodeList<Ticket> newNode = new NodeList<>(ticket);

        if(isEmpty()){
            head = newNode;
            return;
        }

        NodeList<Ticket> tmp = head;
        while(tmp.getNext() != null){
            tmp = tmp.getNext();
        }

        tmp.setNext(newNode);
    }

    public void deleteById(int id) {
        if(isEmpty()){
            System.out.println("Error: Node list not initialized");
            return;
        }

        if (head.getData().getId() == id) {
            head = head.getNext(); // The second node becomes the new head
            return;
        }

        NodeList<Ticket> currentTmp = head.getNext();
        NodeList<Ticket> previousTmp = head;

        while(currentTmp != null){
            if(currentTmp.getData().getId() == id) {
                previousTmp.setNext(currentTmp.getNext());
                return;
            }

            previousTmp = currentTmp;
            currentTmp = currentTmp.getNext();
        }
        
        System.out.println("Error: Ticket with ID " + id + " not found");
    }

    public Ticket findById(int id) {
        if (id <= 0) {
            System.out.println("Error: El ID debe ser un número entero positivo.");
            return null;
        }

        if (isEmpty()) {
            System.out.println("Error: No hay tickets resueltos registrados para buscar.");
            return null;
        }

        NodeList<Ticket> tmp = head;
        while (tmp != null) {
            if (tmp.getData().getId() == id) {
                return tmp.getData();
            }

            tmp = tmp.getNext();
        }

        System.out.println("No se encontró un ticket resuelto con el ID " + id + ".");
        return null;
    }
}
