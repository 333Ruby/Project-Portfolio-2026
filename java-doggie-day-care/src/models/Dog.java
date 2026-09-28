package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Dog {
    //TODO add a constant DANGEROUS_DAILY_RATE, make it equal to 40.0
    private static float DANGEROUS_DAILY_RATE = 40; // static as the return type because it is a constant.

    //TODO add a constant NONDANGEROUS_DAILY_RATE, make it equal to 30.0
    private static float NONDANGEROUS_DAILY_RATE = 30; // static as the return type because it is a constant.

    //TODO The id (int id)  in the system is entered by the user.
    //     Default value is 1000.
    //     When creating the Dog, must be an id between 1000 and 9990
    //     When updating an existing Dog, only update the  id if between 1000 and 9999
    private int id = 1000;

    //TODO The  name (String name)  in the system is entered by the user.
    //     Default value is "".
    //     When creating the Dog, truncate the name to 20 characters.
    //     When updating an existing Dog, only update the name if it is 20 characters or less.
    private String name = "";

    //TODO boolean dangerousBreed defaults to false
    private boolean dangerousBreed = false;

    //TODO The age (int age)  in the system is entered by the user.
    //     Default value is 5.
    //     When creating the Dog, must be an id between 0 and 20
    //     When updating an existing Dog, only update the  id if between 1000 and 9999
    private int age = 5;

    //TODO char sex -  MUST BE M OR F / default to 'F'
    private char sex = 'F';

    //TODO boolean neutered defaults to false
    private boolean neutered = false;

    //TODO ArrayList of owners
    private ArrayList<Owner> owners = new ArrayList<>();

    //TODO boolean Array called daysInKennel, defaults to false, stores 5 days
    //   leave at 5 day (so easy to remember starts Monday)
    private boolean[] daysInKennel = new boolean[5];

    //TODO add constructor Dog(int,String , String , boolean , int , char , boolean , Owner ) {
    public Dog(int id, String name, boolean dangerousBreed, int age, char sex, boolean neutered, ArrayList<Owner> owners) {
        // validating the id as per the instructions.
        if (id >= 1000 && id <= 9999) {
            this.id = id;
        }
        // validating the name as per the instructions.
        if (name.length() <= 20) {
            this.name = name;
        }

        this.dangerousBreed = dangerousBreed;

        this.owners = owners;
        // validating the age as per the instructions.
        if (age >= 0 && age <= 20) {
            this.age = age;
        }
        // validating the sex as per the instructions.
        if (sex == 'M' || sex == 'F') {
            this.sex = sex;
        }

        this.neutered = neutered;
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Owner> getOwners() {
        return owners;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public boolean[] getDaysInKennel() {
        return daysInKennel;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }

    public void setOwners(ArrayList<Owner> owners) {
        if (owners != null) {
            this.owners = owners;
        }
    }

    //TODO Add a generated equals method.

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false; // getClass() = built in object in java.
        // returns the class of the current object which is Dog.
        // o.getClass() returns the class of o.
        // getClass() != o.getClass() makes sure both objects are of the same type (Dog).
        Dog dog = (Dog) o; // changes o from a general Object to a Dog object
        return id == dog.id && dangerousBreed == dog.dangerousBreed && age == dog.age && sex == dog.sex && neutered == dog.neutered && daysInKennel == dog.daysInKennel && Objects.equals(name, dog.name) && Objects.equals(owners, dog.owners);
        // this line is comparing fields to see if they hold the same value.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dangerousBreed, age, sex, neutered, owners, daysInKennel);
    }


    //TODO create a method called numOfDaysInKennel that returns an int
    public int numOfDaysInKennel() {
        int count = 0; // default value is 0 (initially no days are booked)
        for (boolean day : daysInKennel) { // interacting with the daysInKennel array.
            if (day) { // if the day is booked, increase count by 1.
                count++;
            }
        }
        return count; // returning the number of days that have been booked.
    }

    //TODO create a method called listOwners that returns a String
    private String listOwners() {
        String allOwnerNames = ""; // default value is an empty string.
        for (Owner owner : owners) { // interacting with the owners array.
            allOwnerNames += owner.getName() + ", ";// adding the name of the owner to the string.
        }
        return allOwnerNames; // returning the string of all the owners.
    }

    //TODO create a method called getweeklyBill that returns a float with the weekly cost of the dog (num of days * cost)
    public float getWeeklyBill() {
        int numOfDays = 0; // initial value is 0.
        for (boolean day : daysInKennel) { // interacting with the daysInKennel field.
            if (day) { // if the day is booked, increase by 1.
                numOfDays++;
            }
        }

        // the ? = a conditional.
        // DANGEROUS_DAILY_RATE and NONDANGEROUS_DAILY_RATE are coming from the constants,
        // and the values stored in them are being used here.
        float dailyRate = dangerousBreed ? DANGEROUS_DAILY_RATE : NONDANGEROUS_DAILY_RATE;

        float weeklyBill = numOfDays * dailyRate; // this is where the calcuations are happening.

        return weeklyBill;
    }


    //TODO The toString should return the string containing each of the field values including the use of the listOwners()
    //  should print male neutered or female not neutered
    //    should print days that the dog is booked into kennels

    @Override
    public String toString() {
        // formatting the gender and neutered status.
        String genderStatus = (sex == 'M') ? "Male" : "Female";
        String neuteredStatus = neutered ? "Neutered" : "Not neutered";

        //getting a list of owners.
        String allOwnersNames = listOwners();

        //counting the days the dog is in the kennel.
        int numOfDays = numOfDaysInKennel();

        return "Dog ID: " + id
                + "name: " + name
                + "dangerousBreed: " + dangerousBreed
                + "age: " + age
                + "sex: " + genderStatus
                + "neutered: " + neuteredStatus
                + "owners: " + allOwnersNames
                + "daysInKennel: " + numOfDays;
    }
}