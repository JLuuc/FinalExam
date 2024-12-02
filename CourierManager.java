
// CourierManager.java

import java.util.ArrayList;
import java.util.Scanner;

public class CourierManager {
    private ArrayList<Package> packages = new ArrayList<>();

    public static void main(String[] args) {
        CourierManager manager = new CourierManager();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===============================");
            System.out.println(" Welcome to Delivery Dilemmas!");
            System.out.println("===============================");
            System.out.println("Please select an option:");
            System.out.println("1. Add a new package");
            System.out.println("2. Display all packages and shipping costs");
            System.out.println("3. Sort packages by weight");
            System.out.println("4. Search for a package by Tracking ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manager.addPackage(scanner);
                    break;
                case 2:
                    manager.displayPackages();
                    break;
                case 3:
                    manager.sortPackagesByWeight();
                    break;
                case 4:
                    manager.searchPackageByID(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using Delivery Dilemmas!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    private void addPackage(Scanner scanner) {
        System.out.print("Enter package type (Standard/Express): ");
        String type = scanner.nextLine();
        System.out.print("Enter tracking ID: ");
        String trackingID = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter weight: ");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Package pkg;
        if ("Standard".equalsIgnoreCase(type)) {
            pkg = new StandardPackage(trackingID, destination, weight);
        } else if ("Express".equalsIgnoreCase(type)) {
            pkg = new ExpressPackage(trackingID, destination, weight);
        } else {
            System.out.println("Invalid package type!");
            return;
        }

        if (pkg.validateTrackingID() && pkg.validateDestination() && weight > 0) {
            packages.add(pkg);
            System.out.println("Package added successfully!");
        } else {
            System.out.println("Invalid package details! Please try again.");
        }
    }

    private void displayPackages() {
        System.out.println("Package List:");
        for (Package pkg : packages) {
            System.out.println(pkg);
        }
    }

    private void sortPackagesByWeight() {
        for (int i = 0; i < packages.size() - 1; i++) {
            for (int j = 0; j < packages.size() - i - 1; j++) {
                if (packages.get(j).getWeight() > packages.get(j + 1).getWeight()) {
                    Package temp = packages.get(j);
                    packages.set(j, packages.get(j + 1));
                    packages.set(j + 1, temp);
                }
            }
        }
        System.out.println("Packages sorted by weight!");
        displayPackages();
    }

    private void searchPackageByID(Scanner scanner) {
        System.out.print("Enter Tracking ID: ");
        String trackingID = scanner.nextLine();
        int left = 0, right = packages.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Package midPackage = packages.get(mid);
            if (midPackage.getTrackingID().compareTo(trackingID) == 0) {
                System.out.println("Package Found:");
                System.out.println(midPackage);
                return;
            } else if (midPackage.getTrackingID().compareTo(trackingID) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("Package not found!");
    }
}
