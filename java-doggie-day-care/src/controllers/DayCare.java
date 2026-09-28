package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Dog;
import models.Owner;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class DayCare {
    private ArrayList<Dog> dogsArray;
    private String name;  // 10 chars
    private int maxNumberOfDogs;  // must be >= 10 <= 100
//    private int daysStayed; // created for the listAllDogsThatStayMoreThanDays(int) method.

    //-------------------------------------
    //  Constructor
    //-------------------------------------
    //TODO array list of dogs, should be empty at the start.

    //TODO constructor (String name, int numDogs).
    //     Default name is "".
    //     When creating the DayCare, truncate the name to 10 characters.
    //     When updating an existing DayCare, only update the name if it is 10 characters or less.
    //     number of dogs must be must be >= 10 <= 100 default to 10
    public DayCare(String name, int numDogs) {
        dogsArray = new ArrayList<>(); // array list of dogs, should be empty at the start.

        // Truncating name to 10 characters.
        if (name.length() > 10) {
            name = name.substring(0, 10);
        }
        this.name = name;

        // Validate number of Dogs
        if (numDogs >= 10 && numDogs <= 100) {
            this.maxNumberOfDogs = numDogs;
        } else {
            this.maxNumberOfDogs = 10; // default to 10 if invalid.
        }

//        this.daysStayed = daysStayed; //created for the listAllDogsThatStayMoreThanDays(int) method
    }

    //-------------------------------------
    //  Setters/Getters
    //-------------------------------------
    //TODO Add a getter and setter for each field, that adheres to the above validation rules
    public ArrayList<Dog> getDogsArray() {
        return dogsArray;
    }

    public String getName() {
        return name;
    }

    public int getMaxNumberOfDogs() {
        return maxNumberOfDogs;
    }

    public void setDogsArray(ArrayList<Dog> dogsArray) {
        this.dogsArray = dogsArray;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxNumberOfDogs(int maxNumberOfDogs) {
        this.maxNumberOfDogs = maxNumberOfDogs;
    }

//    public void getDaysStayed() {
//        this.daysStayed = daysStayed;
//    }
//
//    public void setDaysStayed(int daysStayed) {
//        this.daysStayed = daysStayed;
//    }

    //-------------------------------------
    //  ARRAYLIST CRUD
    //-------------------------------------
    //TODO Add a method, addDog(Dog). The return type is boolean.
    //     This method will add the dog object, passed as a parameter to the arraylist of dogs.
    //     If the add was successful, return true, otherwise, return false.
    public boolean addDog(Dog dog) {
        if (dog != null) // checks if the dog is not empty.
            return dogsArray.add(dog); // add() returns true if successful.
        else return false; // return false if dog is empty.
    }

    //TODO Add a method, updateDog(int, Dog).  The return type is boolean.
    //     This method takes in, as the first parameter, the index of the dogs object that you want to update.
    //     If the index is invalid (i.e. there is no dog object at that location), return false.
    //     The other parameter is a  Dog object - that is being updated
    //     i.e. it holds the new values of  dog object
    //     If the update was successful, then return true.
    public boolean updateDog(int index, Dog dog) { // dog = the dog object.
        if (dogsArray.size() > index && index >= 0) { // checks if the index is valid
                                        // dogsArray.size() = returns the total number of dogs in the array.
                                    // index >= 0 = checks if the index is greater than or equal to 0.
            dogsArray.set(index, dog); // set replaces the dog object at the specific index with the new dog object.
            return true; // return true if the update was successful.
        } else return false; // return false if the index is invalid.
    }


    //TODO Add a method, deleteDog(int).  The return type is Dog.
    //     This method takes in the index of the dog object that you want to delete.
    //     If the index is invalid (i.e. there is no dog object at that location), return null.
    //     If the index is valid, remove the object at that index location.  Return the object you just deleted.
    public Dog deleteDog(int index) {
        if (dogsArray.size() > index && index >= 0) { // checks if the index is valid.
            return dogsArray.remove(index); // remove() returns the dog object that was removed.
        } else return null; // meaning the index is invalid.
    }


    //-------------------------------------
    //  ARRAYLIST - Utility methods
    //-------------------------------------
//TODO Add a method isValidIndex(int) which returns an boolean -
    //      - returns true if the index is valid for the dogs arrayList (in range)
    //      - returns false otherwise
    //      As this method is used inside this class, it should be private
    private boolean isValidIndex(int index) {
        if (dogsArray.size() > index && index >= 0) {
            return true; // indicating the index is valid.
        } else {
            return false; // indicating the index is invalid.
        }
    }


    //TODO  Add a method  getDog(int) which returns a Dog object:
    //       - if the supplied index is valid, the Dog object at that location is returned
    //       - if the supplied index is invalid, null is returned
    public Dog getDog(int index) {
        if (dogsArray.size() > index && index >= 0) {
            return dogsArray.get(index); // get() returns the dog object at the specific index.
        } else {
            return null; // meaning the index is invalid.
        }
    }

    //TODO  Add a method  getDog(String) which returns a Dog object:
    //       - if the supplied name is found, the first Dog object with that name is returned
    //       - if the supplied name is not found, null is returned
    public Dog getDog(String name) {
        for (Dog dog : dogsArray) { // for each dog in the dogsArray
            if (dog.getName().equals(name)) { // if the dog's name is equal to the name passed in
                return dog; // return the dog object
            }
        }
        return null; // meaning the name was not found.
    }


//TODO  Add a method  getDogById(int) which returns a Dog object:
    //       - if the supplied id is found, the Dog object with that id is returned
    //       - if the supplied id is not found, null is returned
    public Dog getDogById(int id) {
        for (Dog dog : dogsArray) { // for each dog in the dogsArray
            if (dog.getId() == id) { // if the dog's id is equal to the id passed in
                return dog; // return the dog object
            }
        }
        return null; // meaning the id was not found.
    }




    //------------------------------------
    // LISTING METHODS - Basic and ADvanced
    //------------------------------------

    //TODO Add a method, listAllDogs().  The return type is String.
    //     This method returns a list of the dogs stored in the array list.
    //     Each dog should be on a new line and should be preceded by the index number e.g.
    //        0: dog 1 Details
    //        1: dog 2 Details
    //    If there are no dogs stored in the array list, return a string that contains "There are no dogs registered at the moment".
    public String listAllDogs() {
        if (dogsArray.isEmpty()) {
            return "There are no dogs registered at the moment";
        }
        String result = ""; // create an empty string to store the result
        for (int i = 0; i < dogsArray.size(); i++) {
            result += i + ": " + dogsArray.get(i).toString() + "\n"; // add the index number and the dog's details to the result string
        }
        return result; // return the result string
    }

    //TODO Add a method, listAllDangerousDogs().  The return type is String.
    //     This method returns a list of the dogs stored in the array list that are dangerous.
    //     Each dog should be on a new line and should be preceded by the index number e.g.
    //        0: dog 1 Details
    //        1: dog 2 Details
    //    If there are no dogs stored in the array list, return a string that contains "There are no dogs registered at the moment".
    //    If there are  dogs stored in the array list, but no dangerous then
    //    return a string that contains "No Dangerous Breeds at the moment".
    public String listAllDnagerousDogs() {
        if (dogsArray.isEmpty()) {
            return "There are no dogs registered at the moment";
        }
        String result = ""; // create an empty string to store the result
        for (int i = 0; i < dogsArray.size(); i++) {
            if (dogsArray.get(i).isDangerousBreed()) { // if the dog is dangerous
                result += i + ": " + dogsArray.get(i).toString() + "\n"; // add the index number and the dog's details to the result string
            }
        }
        if (result.isEmpty()) { // if the result string is empty
            return "No Dangerous Breeds at the moment"; // return the result string
        }
        return result; // return the result string
    }

    //TODO Add a method, listAllDogsByOwner(Owner).  The return type is String.
    //     This method returns a list of the dogs stored in the array with that Owner.
    //     Each dog should be on a new line and should be preceded by the index number e.g.
    //        0: dog 1 Details
    //        1: dog 2 Details
    //    If there are no dogs stored in the array list, return a string that contains "There are no dogs registered at the moment".
    //    If there are  dogs stored in the array list, but none have that owner
    //    return a string that contains "No Dogs have that owner";
    public String listAllDogsByOwner(Owner owner) {
        if (dogsArray.isEmpty()) {
            return "There are no dogs registered at the moment";
        }
        String result = ""; // create an empty string to store the result
        for (int i = 0; i < dogsArray.size(); i++) {
            if (dogsArray.get(i).getOwners().contains(owner)) { // if the dog's owners contains the owner passed in
                result += i + ": " + dogsArray.get(i).toString() + "\n"; // add the index number and the dog's details to the result string
            }
        }
        if (result.isEmpty()) { // if the result string is empty
            return "No Dogs have that owner"; // return the result string
        }
        return result; // return the result string
    }

    //TODO Add a method, listAllDogsThatStayMoreThanDays(int).  The return type is String.
    //     This method returns a list of the dogs stored in the array that stay the inputed day or longer.
    //     Each dog should be on a new line and should be preceded by the index number e.g.
    //        0: dog 1 Details
    //        1: dog 2 Details
    //    If there are no dogs stored in the array list, return a string that contains "There are no dogs registered at the moment".
    //    If there are  dogs stored in the array list, but none for more than the input number of days
    //    return a string that contains "No Dogs stay longer than 2 days at the moment";
//    public String listAllDogsThatStayMoreThanDays(int days) {
//        if (dogsArray.isEmpty()) {
//            return "There are no dogs registered at the moment";
//        }
//        String result = ""; // create an empty string to store the result
//        for (int i = 0; i < dogsArray.size(); i++) {
//            if (dogsArray.get(i).getDaysStaying() > days) { // if the dog stays more than the input number of days
//                result += i + ": " + dogsArray.get(i).toString() + "\n"; // add the index number and the dog's details to the result string
//            }
//        }
//        if (result.isEmpty()) { // if the result string is empty
//            return "No Dogs stay longer than " + days + " days at the moment"; // return the result string
//        }
//        return result; // return the result string
//    }
    //METHOD NOT WORKING.




    //-------------------------------------
    //  Counting Methods
    //-------------------------------------

    //TODO Add a method, numberOfDogs().  The return type is int.
    //     This method returns the number of dogs objects currently stored in the array list.
    public int numberOfDogs() {
        return dogsArray.size();
    }

    //TODO Add a method, numberOfDangerousDogs().  The return type is int.
    //     This method returns the number of dangerous dogs objects currently stored in the array list.
    public int numberOfDangerousDogs() {
        int count = 0;
        for (Dog dog : dogsArray) { // for each dog in the array list
            if (dog.isDangerousBreed()) {
                count++;
            }
        }
        return count;
    }

    //TODO Add a method, getWeeklyIncome().  The return type is double.
    //     This method returns the amount received from all the dogs per week.
//    public double getWeeklyIncome() {
//        double total = 0;
//        for (Dog dog : dogsArray) { // for each dog in the array list
//            total += dog.getDaysStaying() * dog.getCostPerDay(); // add the cost of the dog's stay to the total.
//            // getDaysStaying and getCostPerday are made up variable names for understanding of what the code is doing,
//            // line not working.
//        }
//        return total;
//    }

    //TODO Add a method, getAverageNumDaysPerWeek().  The return type is int.
    //     This method returns the average number of days dogs stay in the Day Care.
    public int geAverageNumDaysPerWeek() {
        int total = 0;
        for (Dog dog : dogsArray) { // for each dog in the array list
           // total += dog.getDaysStaying(); // add the number of days the dog stays to the total
        }
        return total / dogsArray.size(); // return the average number of days the dogs stay
    }


    //------------------------------
    //  FINDING METHODS
    //-------------------------------

    //TODO Add a method, findDogByName(String).  The return type is Dog.
    //    This method returns the first dog with the name that was passed as a parameter.
    //    However, if the name is not found, null is returned.
    public Dog findDogByName(String name) {
        for (Dog dog : dogsArray) { // for each dog in the array list
            if (dog.getName().equals(name)) { // if the dog's name is equal to the name passed in
                return dog; // return the dog
            }
        }
        return null; // if the dog is not found, return null
    }

    //TODO Add a method, findDogByOwnerAndBreedAndAge(String , String , int ).  The return type is Dog.
    //    This method returns the first dog with the name, breeed and age that was passed as a parameter.
    //    However, if the name is not found, null is returned.
//    public Dog findDogByOwnerAndBreedAndAge(String owner, String breed, int age) {
//        for (Dog dog : dogsArray) { // for each dog in the array list
//           if (dog.getOwner().equals(owner) && dog.getBreed().equals(breed) && dog.getAge() == age) { // if the dog's owner, breed and age are equal to the parameters
//                return dog; // return the dog
//            }
//       // }
//       return null; // if the dog is not found, return null
//    }



    //------------------------------
    //  SEARCHING METHODS
    //-------------------------------

    //TODO Add a method, searchDogsByName(String).  The return type is String.
    //    This method returns a list of the dogs whose name contains the string passed as a parameter.
    //    Each matching dog should be on a new line and should be preceded by the index number e.g.
    //        1: dog 2 Details
    //        4: dog 5 Details
    //    If there are no dogs stored in the array list, return a string that contains "No dogs".
    //    If there are no dogs whose name contains the supplied string, the return string should
    //    have "No dogs found with that name".
    public String searchDogsByName(String) {
        String result = ""; // create a string to store the result
        int index = 1; // create an index to store the index of the dog
        for (Dog dog : dogsArray) { // for each dog in the array list
            if (dog.getName().contains(name)) { // if the dog's name contains the name passed in
                result += index + ": " + dog.getName() + " " + dog.getBreed() + "\n"; // add the dog's name and breed to the result string
                index++; // increment the index
            }
        }
        if (result.isEmpty()) { // if the result string is empty
            return "No dogs found with that name"; // return the result string
        }
        return result; // return the result string
    }


    //TODO Add a method, searchDogsByOwnersName(String).  The return type is String.
    //    This method returns a list of dogs whose owner name contains the string passed
    //    as a parameter.
    //    Each dog should be on a new line and should contain the dog name and breed e.g.
    //        Buddy (Golden Retriver)
    //        Jack (Jack Russel)
    //    If there are no dogs stored in the array list, return a string that contains "No dogs".
    //    If there are no dogs whose owner name contains the supplied string, the return string should
    //    have "No dogs found for this owner.
    public String searchDogsByOwnersName(String) {
        String result = ""; // create a string to store the result
        for (Dog dog : dogsArray) { // for each dog in the array list
            if (dog.getOwner().contains(name)) { // if the dog's owner contains the name passed in
                result += dog.getName() + " (" + dog.getBreed() + ")\n"; // add the dog's name and breed to the result string
            }
        }
        if (result.isEmpty()) { // if the result string is empty
            return "No dogs found for this owner"; // return the result string
        }
        return result; // return the result string
    }



    //---------------------------------
    //  Methods for Persistence
    // --------------------------------

    //TODO Add a method, load().  The return type is void.
    //    This method uses the XStream component to deserialise the playList object and their associated owners from
    //    an XML file into the Dogs array list.


    //TODO Add a method, save().  The return type is void.
    //    This method uses the XStream component to serialise the playList object and their associated owners to
    //    an XML file.

}