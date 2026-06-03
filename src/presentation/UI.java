package presentation;

import entities.Ticket;
import utils.LinkedList;

import java.time.LocalDateTime;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Main class that manages the CLI menu and interaction with data structures.
 */
public class UI {

    // Required data structures
    private static PriorityQueue<Ticket> pendingQueue = new PriorityQueue<>();
    private static LinkedList resolvedList = new LinkedList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        System.out.println("==================================================");
        System.out.println("   SISTEMA DE GESTIÓN DE TICKETS - CENFOTEC");
        System.out.println("==================================================");

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Menú de Usuario");
            System.out.println("2. Menú de Administrador");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    menuUsuario();
                    break;
                case "2":
                    menuAdministrador();
                    break;
                case "3":
                    salir = true;
                    System.out.println("Saliendo del sistema... ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Error: Opción inválida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    /**
     * Interactive logic for User Menu.
     */
    private static void menuUsuario() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENÚ DE USUARIO ---");
            System.out.println("1. Crear un nuevo ticket");
            System.out.println("2. Buscar el estado de un ticket por ID");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    String fullName = solicitarTextoNoVacio("Ingrese su nombre completo: ");
                    String description = solicitarTextoNoVacio("Ingrese la descripción del problema: ");
                    System.out.print("Ingrese la prioridad (1=Alta, 2=Media, 3=Baja): ");

                    int priority;
                    try {
                        priority = Integer.parseInt(scanner.nextLine());
                        if(priority < 1 || priority > 3) priority = 3; // Default
                    } catch (NumberFormatException e) {
                        System.out.println("Prioridad inválida. Se asignará prioridad Baja (3).");
                        priority = 3;
                    }

                    Ticket newTicket = new Ticket(description, fullName, priority);
                    pendingQueue.add(newTicket);
                    System.out.println("\n¡Ticket creado exitosamente! Su número de seguimiento es el ID: " + newTicket.getId());
                    break;

                case "2":
                    System.out.print("Ingrese el ID del ticket que desea buscar: ");
                    try {
                        int idToSearch = Integer.parseInt(scanner.nextLine());
                        if (idToSearch <= 0) {
                            System.out.println("Error: Debe ingresar un número de ID válido.");
                            break;
                        }

                        Ticket foundTicket = resolvedList.findById(idToSearch);

                        if (foundTicket != null) {
                            System.out.println("\n--- Ticket Encontrado (RESUELTO) ---");
                            System.out.println(foundTicket);
                        }

                        Ticket pendingTicket = null;

                        for (Ticket ticket : pendingQueue) {
                            if (ticket.getId() == idToSearch) { // Ajusta .getId() según el método de tu clase Ticket
                                pendingTicket = ticket;
                                break; // Ya lo encontramos, salimos del bucle
                            }
                        }

                        // 3. Evaluar el resultado de la búsqueda en pendientes
                        if (pendingTicket != null) {
                            System.out.println("\n--- Ticket Encontrado (PENDIENTE) ---");
                            System.out.println("El ticket aún no ha sido resuelto.");
                            System.out.println(pendingTicket);
                        } else {
                            // 4. Si no está en ningún lado
                            System.out.println("Error: El registro con ID " + idToSearch + " no existe.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un número de ID válido.");
                    }
                    break;

                case "3":
                    volver = true;
                    break;

                default:
                    System.out.println("Error: Opción inválida.");
            }
        }
    }

    private static String solicitarTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }

            System.out.println("Error: El campo no puede estar vacío.");
        }
    }

    /**
     * Interactive logic for Administrator Menu.
     */
    private static void menuAdministrador() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENÚ DE ADMINISTRADOR ---");
            System.out.println("1. Ver ticket al frente de la cola (próximo a atender)");
            System.out.println("2. Resolver ticket al frente de la cola");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    Ticket nextTicket = pendingQueue.peek();
                    if (nextTicket != null) {
                        System.out.println("\n--- Próximo Ticket en Cola ---");
                        System.out.println(nextTicket);
                    } else {
                        System.out.println("\nNo hay tickets pendientes por resolver.");
                    }
                    break;

                case "2":
                    Ticket ticketToResolve = pendingQueue.poll(); // Extracts ticket from front
                    if (ticketToResolve != null) {
                        ticketToResolve.setResolutionDate(LocalDateTime.now());
                        resolvedList.add(ticketToResolve);
                        System.out.println("\n¡El Ticket #" + ticketToResolve.getId() + " ha sido marcado como RESUELTO!");
                    } else {
                        System.out.println("\nNo hay tickets pendientes en la cola.");
                    }
                    break;

                case "3":
                    volver = true;
                    break;

                default:
                    System.out.println("Error: Opción inválida.");
            }
        }
    }
}
