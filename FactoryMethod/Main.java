package FactoryMethod;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== LOGISTICS SYSTEM ===");
        System.out.print("Enter delivery mode (ROAD / SEA): ");
        String deliveryMode = scanner.nextLine().trim().toUpperCase();
        Logistics logistics = null;
        if (deliveryMode.equals("ROAD")) {
            logistics = new RoadLogistics();
        } else if (deliveryMode.equals("SEA")) {
            logistics = new SeaLogistics();
        } else {
            System.err.println("Error: Invalid delivery mode '" + deliveryMode + "'. Expected 'ROAD' or 'SEA'.");
            return;
        }

        System.out.println("\n--- Processing Order ---");
        logistics.planDelivery("Laboratory Equipment", "Aktau Warehouse");
        scanner.close();
    }
}