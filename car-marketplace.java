/*
Name: Aryaman Sharma
Date: June 5, 2024
Purpose: To program a car marketplace which has the options to buy, sell, and quit the program.
Run Instructions: Copy and paste the code to the Eclipse IDE and run.
*/

import java.util.Scanner;

public class Main {
    // Main method where the program execution starts
    public static void main(String[] args) {
        // Initialize arrays to store car information
        int[] year = {2015, 2020, 2019, 2025, 1995};
        String[] make = {"Honda", "Toyota", "Ford", "Tesla", "Honda"};
        String[] model = {"Civic", "Prius", "Explorer", "Roadster", "CRV"};
        int[] mileage = {75000, 60000, 100000, 10, 250674};
        int[] price = {15000, 23000, 50000, 150755, 4200};
        String[] typeOfCar = {"Sedan", "Hatchback", "SUV", "Coupe", "SUV"};
        boolean[] soldOrNot = {false, false, false, false, false};

        // Create a scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Ask for user's name and greet
        System.out.print("Enter your name: ");
        String name = input.next();
        intro(name);

        // Main loop to interact with the user until they choose to quit
        String choice;
        do {
            choice = buyOrSell(name);
            if (choice.equalsIgnoreCase("b")) {
                // If user chooses to buy, call buyNum method, which returns an index of the car user bought
                int carNumBuy = buyNum(year, make, model, mileage, price, typeOfCar, soldOrNot);
                // case where user chooses to buy a car
                if (carNumBuy != year.length + 1) {
                    // Calculate total price including taxes and display
                    double totalPriceWithTaxes = getTotalPriceWithTaxes(price, carNumBuy);
                    System.out.println("Total price of the car including taxes and marketplace tax: $" + totalPriceWithTaxes);
                    System.out.println();
                }

            } else if (choice.equalsIgnoreCase("s")) {
                // If user chooses to sell, do the following
                // getting the number of listings to add
                int numListing = getNumListing(input);
                int prevListLen = year.length;
                int listLen = prevListLen + numListing;

                // creating new array for the new listings to be added
                int[] yearNew = new int[listLen];
                String[] makeNew = new String[listLen];
                String[] modelNew = new String[listLen];
                int[] mileageNew = new int[listLen];
                int[] priceNew = new int[listLen];
                String[] typeOfCarNew = new String[listLen];
                boolean[] soldOrNotNew = new boolean[listLen];

                // copying old listings to new ones
                for (int i = 0; i < prevListLen; i++) {
                    yearNew[i] = year[i];
                    makeNew[i] = make[i];
                    modelNew[i] = model[i];
                    mileageNew[i] = mileage[i];
                    priceNew[i] = price[i];
                    typeOfCarNew[i] = typeOfCar[i];
                    soldOrNotNew[i] = soldOrNot[i];
                }

                // reassigning the old arrays to hold space for extra listings
                year = yearNew;
                make = makeNew;
                model = modelNew;
                mileage = mileageNew;
                price = priceNew;
                typeOfCar = typeOfCarNew;
                soldOrNot = soldOrNotNew;
                // calling sell method to add new listings
                sell(year, make, model, mileage, price, typeOfCar, soldOrNot, numListing, prevListLen);
            }
        } while (!choice.equalsIgnoreCase("q"));
    }

    // Method to greet the user
    public static void intro(String name) {
        System.out.println("Welcome To The Car Marketplace, " + name + "!");
    }

    // Method to ask the user whether they want to buy, sell, or quit
    public static String buyOrSell(String name) {
        Scanner input = new Scanner(System.in);
        String choice;
        // ask the user their choice of action
        do {
            System.out.println("Would you like to BUY(B) or SELL(S) or QUIT(Q): ");
            choice = input.next();
        } while (!choice.equalsIgnoreCase("b") && !choice.equalsIgnoreCase("s") && !choice.equalsIgnoreCase("q"));
        // If the choice is quit (q), call the quit method
        if (choice.equalsIgnoreCase("q")) {
            quit(name);
        }
        return choice.toLowerCase();
    }

    // Method to get the number of cars the user wants to sell
    public static int getNumListing(Scanner input) {
        int numListing;
        // asking the user how many cars they want to list
        do {
            System.out.println("How Many Cars Would You Like To List?: ");
            numListing = input.nextInt();
            // checking if user input is invalid
            if (numListing < 0) {
                System.out.println("Invalid input, Cannot Have Listings Less Than 0");
            }
        } while (numListing < 0);
        return numListing;
    }

    // Method to calculate total price including taxes
    public static double getTotalPriceWithTaxes(int[] price, int carNumBuy) {
        double totalPrice = price[carNumBuy - 1]; // Base price
        double taxRate = 0.13; // tax rate of 13%
        double marketplaceTaxRate = 0.05; // marketplace tax rate of 5%
        double taxAmount = totalPrice * taxRate;
        double marketplaceTaxAmount = totalPrice * marketplaceTaxRate;
        // the price including taxes
        double totalPriceWithTaxes = totalPrice + taxAmount + marketplaceTaxAmount;

        // Round the total price to two decimal places
        totalPriceWithTaxes = Math.round(totalPriceWithTaxes * 100.0) / 100.0;
        return totalPriceWithTaxes;
    }

    // Method to handle selling cars
    public static void sell(int[] year, String[] make, String[] model, int[] mileage, int[] price, String[] typeOfCar, boolean[] soldOrNot, int numListing, int prevListLen) {
        Scanner input = new Scanner(System.in);

        // Get information for new listings by calling different methods
        for (int i = 1; i <= numListing; i++) {
            System.out.println("Enter details for Vehicle #" + i);
            year[prevListLen + i - 1] = inputYear(input, i);
            make[prevListLen + i - 1] = inputMake(input, i);
            model[prevListLen + i - 1] = inputModel(input, i);
            mileage[prevListLen + i - 1] = inputMileage(input, i);
            price[prevListLen + i - 1] = inputPrice(input, i);
            typeOfCar[prevListLen + i - 1] = inputTypeOfCar(input, i);
            soldOrNot[prevListLen + i - 1] = false;
        }

        System.out.println("Listings updated successfully!");
    }

    // Method to input year of the vehicle
    public static int inputYear(Scanner input, int i) {
        int year;
        // asking the user what year is the car made in
        do {
            System.out.println("Enter the Year Of Vehicle #" + i);
            year = input.nextInt();
            if (year < 0) {
                System.out.println("Invalid input!, Year cannot be negative");
            }
        } while (year < 0);
        return year;
    }

    // Method to input make of the vehicle
    public static String inputMake(Scanner input, int i) {
        String make;
        // asking the user what make is the car
        do {
            System.out.println("Enter the Make (Only allowed Makes: Honda, Toyota, Ford, Tesla) Of Vehicle #" + i);
            make = input.next();
            // checking make, so that it is between the possible makes on the marketplace
            if (!make.equalsIgnoreCase("Honda") && !make.equalsIgnoreCase("Toyota") && !make.equalsIgnoreCase("Ford") && !make.equalsIgnoreCase("Tesla")) {
                System.out.println("Make Not Acceptable, Try Again! (Only allowed Makes: Honda, Toyota, Ford, Tesla)");
            }
        } while (!make.equalsIgnoreCase("Honda") && !make.equalsIgnoreCase("Toyota") && !make.equalsIgnoreCase("Ford") && !make.equalsIgnoreCase("Tesla"));
        return make;
    }

    // Method to input model of the vehicle
    public static String inputModel(Scanner input, int i) {
        // asking the user what model is the car, any model is acceptable
        System.out.println("Enter the Model Of Vehicle #" + i);
        return input.next();
    }

    // Method to input mileage of the vehicle
    public static int inputMileage(Scanner input, int i) {
        int mileage;
        // asking the user how much mileage on the car listing
        do {
            System.out.println("Enter the Mileage Of Vehicle #" + i);
            mileage = input.nextInt();
            if (mileage < 0) {
                System.out.println("Invalid input!, Mileage cannot be negative");
            }
        } while (mileage < 0);
        return mileage;
    }

    // Method to input price of the vehicle
    public static int inputPrice(Scanner input, int i) {
        int price;
        // asking the user the selling price of the car
        do {
            System.out.println("Enter the Price Of Vehicle #" + i);
            price = input.nextInt();
            if (price < 0) {
                System.out.println("Invalid input!, Price cannot be negative");
            }
        } while (price < 0);
        return price;
    }

    // Method to input type of the vehicle
    public static String inputTypeOfCar(Scanner input, int i) {
        String typeOfCar;
        // asking the user what type is the car
        do {
            System.out.println("Enter the Body Type (Only allowed Car Types: Sedan, Hatchback, SUV, Coupe) Of Vehicle #" + i);
            typeOfCar = input.next();
            // checking type of car, so that it is between the possible types on the marketplace
            if (!typeOfCar.equalsIgnoreCase("Sedan") && !typeOfCar.equalsIgnoreCase("Hatchback") && !typeOfCar.equalsIgnoreCase("SUV") && !typeOfCar.equalsIgnoreCase("Coupe")) {
                System.out.println("Type Of Car Not Acceptable, Try Again! (Only allowed Car Types: Sedan, Hatchback, SUV, Coupe)");
            }
        } while (!typeOfCar.equalsIgnoreCase("Sedan") && !typeOfCar.equalsIgnoreCase("Hatchback") && !typeOfCar.equalsIgnoreCase("SUV") && !typeOfCar.equalsIgnoreCase("Coupe"));
        return typeOfCar;
    }

    // Method to display farewell message
    public static void quit(String name) {
        System.out.println("Thanks For Using The Car Marketplace, " + name + "!");
        System.out.println("Have An Amazing Day!");
    }

    // Method to send back the index of the car, the user wants to purchase
    public static int buyNum(int[] year, String[] make, String[] model, int[] mileage, int[] price, String[] typeOfCar, boolean[] soldOrNot) {
        Scanner input = new Scanner(System.in);

        // Prompt the user to select filters for car search
        System.out.println("Select Your Filters");
        System.out.println("Minimum Year: ");
        int minYear = input.nextInt();
        System.out.println("Maximum Year: ");
        int maxYear = input.nextInt();

        // Display available car makes
        System.out.println("Available Makes;");
        System.out.println("1) All");
        System.out.println("2) Honda");
        System.out.println("3) Toyota");
        System.out.println("4) Ford");
        System.out.println("5) Tesla");
        System.out.println("Enter Number For Its Respective Make: ");
        int makeInt = input.nextInt();

        // Prompt the user to input mileage and price filters
        System.out.println("Minimum Mileage: ");
        int minMileage = input.nextInt();
        System.out.println("Maximum Mileage: ");
        int maxMileage = input.nextInt();
        System.out.println("Minimum Price: ");
        int minPrice = input.nextInt();
        System.out.println("Maximum Price: ");
        int maxPrice = input.nextInt();

        // Display available body types
        System.out.println("Available Body Types;");
        System.out.println("1) All");
        System.out.println("2) Sedan");
        System.out.println("3) Hatchback");
        System.out.println("4) SUV");
        System.out.println("5) Coupe");
        System.out.println("Enter Number For Its Respective Body Type: ");
        int bodyTypeInt = input.nextInt();

        // Convert the body type number to its respective string
        String bodyType = getBodyType(bodyTypeInt);

        // Convert the make number to its respective string
        String makeCar = getCarMake(makeInt);

        // Find matching cars based on user filters
        int numCar = year.length;
        boolean carFound = false;
        System.out.println("Matching Cars:");
        for (int i = 0; i < numCar; i++) {
            if (year[i] >= minYear && year[i] <= maxYear) {
                // the select statement to check if the car matches all user criteria
                if ((makeCar.equalsIgnoreCase("All") || makeCar.equalsIgnoreCase(make[i])) &&
                        mileage[i] >= minMileage && mileage[i] <= maxMileage &&
                        price[i] >= minPrice && price[i] <= maxPrice &&
                        (bodyType.equalsIgnoreCase("All") || bodyType.equalsIgnoreCase(typeOfCar[i])) &&
                        !soldOrNot[i]) {
                    carFound = true;
                    // Display the matching car details
                    displayCarDetails(i, year, make, model, mileage, price, typeOfCar);
                }
            }
        }

        // If no matching cars found, inform the user
        if (!carFound) {
            System.out.println("No cars matching the criteria found. Please Enter " + (numCar + 1) + " to continue.");
        }
        // if there are cars found but user doesn't want to buy the listed cars case handled
        else {
            System.out.println("To buy no Car, choose number " + (numCar + 1));
            System.out.println();
        }

        // Prompt the user to select a car to buy
        int carIndex;
        do {
            System.out.println("Enter the number of the car you want to buy: ");
            carIndex = input.nextInt();
            // if the index is out of range or isn't one of the possible actions, print the message and ask again
            if ((0 >= carIndex) || (carIndex > numCar + 1)) {
                System.out.println("Invalid input!, Try Again!");
            }
        } while ((0 >= carIndex) || (carIndex > numCar + 1));

        // Mark the selected car as sold
        if (carIndex != numCar + 1) {
            soldOrNot[carIndex - 1] = true;
        }
        return carIndex;
    }

    // Method to convert the body type number to its respective string using switch
    public static String getBodyType(int bodyTypeInt) {
        String bodyType = null;
        switch (bodyTypeInt) {
            case 1:
                bodyType = "All";
                break;
            case 2:
                bodyType = "Sedan";
                break;
            case 3:
                bodyType = "Hatchback";
                break;
            case 4:
                bodyType = "SUV";
                break;
            case 5:
                bodyType = "Coupe";
                break;
        }
        return bodyType;
    }

    // Method to convert the car make number to its respective string using switch
    public static String getCarMake(int makeInt) {
        String makeCar = null;
        switch (makeInt) {
            case 1:
                makeCar = "All";
                break;
            case 2:
                makeCar = "Honda";
                break;
            case 3:
                makeCar = "Toyota";
                break;
            case 4:
                makeCar = "Ford";
                break;
            case 5:
                makeCar = "Tesla";
                break;
        }
        return makeCar;
    }

    // Method to display the details of a car
    public static void displayCarDetails(int index, int[] year, String[] make, String[] model, int[] mileage, int[] price, String[] typeOfCar) {
        System.out.println("Car #" + (index + 1) + ":");
        System.out.println("Year: " + year[index]);
        System.out.println("Make: " + make[index]);
        System.out.println("Model: " + model[index]);
        System.out.println("Mileage: " + mileage[index]);
        System.out.println("Price: " + price[index]);
        System.out.println("Body Type: " + typeOfCar[index]);
        System.out.println();
    }

}