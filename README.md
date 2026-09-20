# Software Design Patterns: Assignment 2

This repository contains the implementation of creational design patterns in Java (JDK 17) as part of the Software Design Patterns course.

---

## Project Structure

```text
.
├── FactoryMethod/          # Part A: Factory Method Pattern (Logistics)
│   ├── Logistics.java      # Abstract Creator defining the factory method
│   ├── RoadLogistics.java  # Concrete Creator for road transportation
│   ├── SeaLogistics.java   # Concrete Creator for sea transportation
│   ├── Transport.java      # Product interface
│   ├── Truck.java          # Concrete Product implementing road delivery
│   ├── Ship.java           # Concrete Product implementing sea delivery
│   └── Main.java           # Entry point and CLI validation for Factory Method
├── AbstractFactory/        # Part B: Abstract Factory Pattern (UI Components)
└── README.md
