package entities;

import java.time.LocalDateTime;

public class Ticket implements Comparable<Ticket> {
    // Static attribute to generate unique consecutive IDs
    private static int counter = 1;
    private int id;
    private String description;
    private String fullName;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Ticket.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(LocalDateTime resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    private LocalDateTime creationDate;
    private LocalDateTime resolutionDate;
    private int priority; // 1: High, 2: Medium, 3: Low

    /**
     * Ticket Constructor.
     * Resolution date starts as null by default.
     */
    public Ticket(String description, String fullName, int priority) {
        this.id = counter++; // Assigns the current value and then increments it
        this.description = description;
        this.fullName = fullName;
        this.priority = priority;
        this.creationDate = LocalDateTime.now();
        this.resolutionDate = null;
    }

    @Override
    public int compareTo(Ticket other) {
        // Sort by priority ascending (1 is more priority than 2)
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }
        // If same priority, sort by ID ascending (FIFO)
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        String priorityText;
        switch (priority) {
            case 1: priorityText = "Alta"; break;
            case 2: priorityText = "Media"; break;
            case 3: priorityText = "Baja"; break;
            default: priorityText = "No especificada"; break;
        }
        
        return String.format("--- Ticket #%d ---\n" +
                "Usuario: %s\n" +
                "Descripción: %s\n" +
                "Prioridad: %s (%d)\n" +
                "Fecha Creación: %s\n" +
                "Fecha Resolución: %s",
                id, fullName, description, priorityText, priority, creationDate,
                resolutionDate != null ? resolutionDate.toString() : "Pendiente");
    }
}