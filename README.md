```markdown
# Software Design Patterns: Assignment 2

This repository contains the implementation of creational design patterns in Java (JDK 17) as part of the Software Design Patterns course.

---

## Project Structure

```text
.
├── AbstractFactory/          # Part B: Abstract Factory Pattern (UI Components)
│   ├── Button.java           # Abstract Product: Button interface
│   ├── Checkbox.java         # Abstract Product: Checkbox interface
│   ├── GUIFactory.java       # Abstract Factory interface
│   ├── MacOSButton.java      # Concrete Product for macOS
│   ├── MacOSCheckbox.java    # Concrete Product for macOS
│   ├── MacOSFactory.java     # Concrete Factory for macOS
│   ├── WindowsButton.java    # Concrete Product for Windows
│   ├── WindowsCheckbox.java  # Concrete Product for Windows
│   └── WindowsFactory.java   # Concrete Factory for Windows
├── FactoryMethod/            # Part A: Factory Method Pattern (Logistics)
│   ├── Logistics.java        # Abstract Creator defining the factory method
│   ├── RoadLogistics.java    # Concrete Creator for road transportation
│   ├── SeaLogistics.java     # Concrete Creator for sea transportation
│   ├── Ship.java             # Concrete Product implementing sea delivery
│   ├── Transport.java        # Product interface
│   └── Truck.java            # Concrete Product implementing road delivery
├── DeliveryApplication.java  # Client application binding both patterns together
├── Main.java                 # Entry point, CLI input processing, and validation
├── .gitignore
└── README.md

```

---

## Part A: Factory Method Pattern (Logistics)

### Overview

The **Factory Method** pattern decouples the creator (`Logistics`) from the concrete products (`Truck`, `Ship`). The abstract base class `Logistics` defines a core business execution workflow (`planDelivery`), while delegating object creation to specialized subclasses (`RoadLogistics` and `SeaLogistics`).

### Class Diagram

```text
                    +-------------------+
                    | <<interface>>     |
                    |     Transport     |
                    +-------------------+
                    | + deliver(...)    |
                    +-------------------+
                              ^
                              |
               +--------------+--------------+
               |                             |
     +-------------------+         +-------------------+
     |       Truck       |         |       Ship        |
     +-------------------+         +-------------------+
     | + deliver(...)    |         | + deliver(...)    |
     +-------------------+         +-------------------+
               ^                             ^
               : (creates)                   : (creates)
               :                             :
     +-------------------+         +-------------------+
     |   RoadLogistics   |         |    SeaLogistics   |
     +-------------------+         +-------------------+
     | + createTransport |         | + createTransport |
     +-------------------+         +-------------------+
               |                             |
               +--------------+--------------+
                              |
                              v
                    +-------------------+
                    |  Logistics (Abs)  |
                    +-------------------+
                    | + createTransp()  |
                    | + planDelivery()  |
                    +-------------------+

```

---

## Part B: Abstract Factory Pattern (UI Components)

### Overview

The **Abstract Factory** pattern provides an interface (`GUIFactory`) for creating families of related UI objects (`Button` and `Checkbox`) without specifying their concrete classes. This ensures consistency across different operating system themes (`Windows` and `macOS`).

### Class Diagram

```text
       +------------------+                    +------------------+
       |  <<interface>>   |                    |  <<interface>>   |
       |      Button      |                    |     Checkbox     |
       +------------------+                    +------------------+
       | + paint()        |                    | + paint()        |
       +------------------+                    +------------------+
         ^              ^                        ^              ^
         |              |                        |              |
+---------------+ +---------------+    +-----------------+ +---------------+
| WindowsButton | |  MacOSButton  |    | WindowsCheckbox | | MacOSCheckbox |
+---------------+ +---------------+    +-----------------+ +---------------+
| + paint()     | | + paint()     |    | + paint()       | | + paint()     |
+---------------+ +---------------+    +-----------------+ +---------------+
        ^                 ^                    ^                 ^
        : (creates)       : (creates)          : (creates)       : (creates)
        :                 :                    :                 :
+-------------------+   +--------------------+ |                 |
|  WindowsFactory   |   |    MacOSFactory    |-+-----------------+
+-------------------+   +--------------------+
| + createButton()  |   | + createButton()   |
| + createCheckbox()|   | + createCheckbox() |
+-------------------+   +--------------------+
          |                       |
          +-----------+-----------+
                      |
                      v
            +-------------------+
            |   <<interface>>   |
            |    GUIFactory     |
            +-------------------+
            | + createButton()  |
            | + createCheckbox()|
            +-------------------+

```

---

## Client Integration (`DeliveryApplication`)

The `DeliveryApplication` class acts as the client that operates strictly on abstract interfaces (`Logistics`, `GUIFactory`, `Button`, `Checkbox`). It receives concrete instances via constructor injection, demonstrating loose coupling:

```java
public class DeliveryApplication {
    private final Logistics logistics;
    private final Button button;
    private final Checkbox checkbox;

    public DeliveryApplication(Logistics logistics, GUIFactory guiFactory) {
        this.logistics = logistics;
        this.button = guiFactory.createButton();
        this.checkbox = guiFactory.createCheckbox();
    }

    public void run(String cargo, String destination) {
        button.paint();
        checkbox.paint();
        logistics.planDelivery(cargo, destination);
    }
}

```

---

## How to Compile and Run

### Prerequisites

* **Java Development Kit (JDK):** Version 17 or higher.

### 1. Compilation

Navigate to the root directory of the repository in your terminal and compile all Java files across packages:

```bash
javac FactoryMethod/*.java AbstractFactory/*.java *.java

```

### 2. Execution

Run the compiled `Main` entry point class:

```bash
java Main

```

---

## Input Validation & Test Cases

The application prompts the user for two inputs:

1. **Delivery Mode:** `ROAD` or `SEA`
2. **UI Platform:** `WINDOWS` or `MACOS`

### Supported Case Matrix

| Delivery Input | UI Input | Output Execution |
| --- | --- | --- |
| `ROAD` | `WINDOWS` | Renders Windows Button & Checkbox + Truck Delivery |
| `SEA` | `WINDOWS` | Renders Windows Button & Checkbox + Ship Delivery |
| `ROAD` | `MACOS` | Renders macOS Button & Checkbox + Truck Delivery |
| `SEA` | `MACOS` | Renders macOS Button & Checkbox + Ship Delivery |

### Error Handling

Invalid inputs (e.g., `AIR` or `LINUX`) trigger informative error messages (`Error: Invalid delivery mode...` / `Error: Invalid UI platform...`) and gracefully terminate without runtime exceptions.

```

```
