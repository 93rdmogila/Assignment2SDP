import FactoryMethod.*;
import AbstractFactory.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("LOGISTICS & UI SYSTEM");
        System.out.print("Enter delivery mode (ROAD / SEA): ");
        String deliveryInput = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter UI platform (WINDOWS / MAC): ");
        String uiInput = scanner.nextLine().trim().toUpperCase();

        Logistics logistics;
        if (deliveryInput.equals("ROAD")) {
            logistics = new RoadLogistics();
        } else if (deliveryInput.equals("SEA")) {
            logistics = new SeaLogistics();
        } else {
            System.err.println("Error: Invalid delivery mode '" + deliveryInput + "'. Expected 'ROAD' or 'SEA'.");
            return; 
        }

        GUIFactory guiFactory;
        if (uiInput.equals("WINDOWS")) {
            guiFactory = new WindowsFactory();
        } else if (uiInput.equals("MAC")) {
            guiFactory = new MacFactory();
        } else {
            System.err.println("Error: Invalid UI platform '" + uiInput + "'. Expected 'WINDOWS' or 'MAC'.");
            return;
        }
        System.out.println("\n--- Executing Application ---");
        DeliveryApplication app = new DeliveryApplication(logistics, guiFactory);
        app.run("Laboratory Equipment", "Aktau Warehouse");
        scanner.close();
    }
}