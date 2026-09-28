package main;

import controllers.DayCare;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Driver {
    private DayCare dayCare = new DayCare();
    private Scanner scanner = new Scanner(System.in);

    //TODO Define an object of the DayCare here.  It should be declared private.
   // private DayCare dayCare = new DayCare(); // dayCare = a variable.
    // the variable can store a reference to a DayCare object.
    // DayCare dayCare = the set-up before instantiation.
    // new DayCare() is instantiating the object.

    public static void main(String[] args) {
        new Driver(); // calls the constructor of Driver (executes System.out.println).
        Scanner scanner = new Scanner(System.in);

    }

    //TODO Refer to the tutors instructions for building this class and for the menu.  You are free to deviate in any way
    //     from the Driver menu that is in the tutors instructions, once you have these included:
    //     (with tests still compiling)
    //       - CRUD on DayCare
    //       - Search facility (for Dogs and Owners)
    //       - Reports
    //       - Persistence
    // Note:  This is the ONLY class that can talk to the user i.e. have System.out.print and Scanner reads in it.
    private int mainMenu() {
        return scanner.readNextInt("""
                |--------------Doggie Day Care--------------|
                | 1) Manage Dogs in DayCare                 |
                | 2) Manage Owners in DayCare.              | 
                | 3) Search Dogs                            |
                | 4) Search Owners                          |
                | 5) Reports Menu                           |
                | 6) Save Data                              | 
                | 7) Load Data                              | 
                |-------------------------------------------|
                8) Exit
                """);

    }
        private void runMenu() {
        int option = mainMenu();

            while (option != 0) {
            switch (option) {
                case 1 -> manageDogs();
                case 2 -> manageOwners();
                case 3 -> searchDogs();
                case 4 -> searchDogs();
                case 5 -> reportsMenu();
                case 6 -> saveData();
                case 7 -> loadData();
                default -> System.out.println("Invalid option entered: " + option);
            }
        }
        //pause the program so that the user can read what we just printed to the terminal window
           scanner.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();

            //the user chose option 0, so exit the program
            System.out.println("Exiting...bye");
            System.exit(0);
    }
        }

        //----------------------------------------------------------------------------
        // Private methods for displaying the menu and processing the selected options
        //----------------------------------------------------------------------------



        //------------------------------------
        // Private methods for CRUD on Dog
        //------------------------------------
            private void manageDogs() {
                int option = scanner.readNextInt("""
                        |--------------Doggie Day Care--------------|
                        | 1) Add Dog                              |
                        | 2) Update Dog                            |
                        | 3) Delete Dog                            |
                        | 4) List Dogs                             |
                        |-------------------------------------------|
                        0) Exit
                        """);
                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (option) {
                    case 1 -> addDog();
                    case 2 -> updateDog();
                    case 3 -> deleteDog();
                    case 4 -> listDogs();
                    case 0 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid option entered: " + option);
                }
            }

        //-----------------------------------------------------------------
        //  Private methods for Search facility
        //-----------------------------------------------------------------
                private void searchDogs() {
                    System.out.println("Enter Dog Name to Search: ");
                    scanner.nextLine(); // Clear buffer
                    String dogName = scanner.nextLine(); // read the dog name from the user
                    Dog foundDog = dayCare.findDogByName(dogName); // find the dog by name
                    if (foundDog != null) {
                        System.out.println("Dog Found: " + foundDog);
                    } else {
                        System.out.println("Dog Not Found");
                    }
                }


        //-----------------------------
        //  Private methods for Reports
        // ----------------------------
                private void reportsMenu() {
                    int option = scanner.readNextInt("""
                    |--------------Doggie Day Care--------------|
                    | 1) List Dogs                             
                    | 2) List Owners                            
                    | 3) List Dogs by Owner                      
                    | 4) List Owners by Dog                      
                    |-------------------------------------------
                    0) Exit
                    """);
                    switch (option) {
                        case 1 -> listDogs();
                        case 2 -> listOwners();
                        case 3 -> listDogsByOwner();
                        case 4 -> listOwnersByDog();
                        default -> System.out.println("Invalid option entered: " + option);
                    }
                    private void listDogsByOwner() {
                        // Implementation for listing dogs by owner
                        System.out.println("List Dogs by Owner functionality not implemented yet");
                    }

                    private void listOwnersByDog() {
                        // Implementation for listing owners by dog
                        System.out.println("List Owners by Dog functionality not implemented yet");
                    }

        //  Private methods for Persistence
        // --------------------------------
            private void saveData() {
                    System.out.println("Saving Data.....");
                  //  dayCare.save(); // save the data to the file
                    System.out.println("Data Saved");
                }

                private void loadData() {
                    System.out.println("Loading Data.....");
                  //  dayCare.load(); // load the data from the file
                    System.out.println("Data Loaded");
                }
            }
    }