package models;

import utils.Helper;

import java.util.Objects;


public class Owner {
    //TODO The id (int id)  in the system is entered by the user.
    //     Default value is 100.
    //     When creating the Owner, must be an id between 100 and 999
    //     When updating an existing Owner, only update the  id if between 100 and 999
    private  int id = 100;// 3 digits - default 100

    //TODO The  name (String name)  in the system is entered by the user.
    //     Default value is "".
    //     When creating the Owner, truncate the name to 30 characters.
    //     When updating an existing Owner, only update the name if it is 30 characters or less.
    private String name = "";

    //TODO The  phoneNumber (String phoneNumber)  in the system is entered by the user.
    //     Default value is "UnKnown".
    //     When creating the Owner, only add numbers
    //     When updating an existing Owner, only update if String only contains numbers.
    private String phoneNumber = "087302000";
    //TODO Add the constructor, Owner(int , String , String )  that adheres to the above validation rules
    public Owner(int id, String name, String phoneNumber) {
        setId(id);
        //setName(name);
        setPhoneNumber(phoneNumber);
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules

  // Getters
    public int getId(){return id;}

    public String getName(){return name;}

    public String getPhoneNumber(){return phoneNumber;}

    // Setters
    public void setId(int id) {
        if (Helper.validRange(id, 100, 999) )
            this.id = id;
    }

    // TEST FAILING FOR THIS METHOD
    //public static String truncateString(String stringToTruncate, int length) {
        //if (stringToTruncate.length() <= length) {
          //  return stringToTruncate;
       // } else {
          //  return stringToTruncate.substring(0, length);
       // }
   // }
    //public void setName(String name) {
       // if (name != null) {
          //  this.name = truncateString(name, 30);
       // }
   // }

    public void setPhoneNumber(String phoneNumber) {if (Helper.onlyContainsNumbers(phoneNumber))
        this.phoneNumber = phoneNumber;}

    //TODO Add a generated equals method.

    @Override // this means this method is replacing a method from its parent class,
              // in this case, Object.equals() and Object.hashCode().
              // also ensures correctness in case you spell something wrong for example.

    // this checks if o is null or a different class, returning false if so.
    // casts o to owner and compares id, name and phoneNumber for equality.
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false; // getClass() = built in object in java.
                                                                   // returns the class of the current object which is Owner.
                                                                   // o.getClass() returns the class of o.
                                                                   // getClass() != o.getClass() makes sure both objects are of the same type (Owner).
        Owner owner = (Owner) o; // changes o from a general Object to an Owner, to use it's properties.
        return id == owner.id && Objects.equals(name, owner.name) && Objects.equals(phoneNumber, owner.phoneNumber);
        // this line is comparing the fields of two Owners and to see if they hold the same values.
    }

    @Override // this means this method is replacing a method from its parent class,
              // in this case, Object.equals() and Object.hashCode().
             // also ensures correctness in case you spell something wrong for example.

    public int hashCode() { // generates the hash code for an Owner,
                            // object based on it's id, name and phoneNumber.
        return Objects.hash(id, name, phoneNumber); // creates and returns the hash code for these fields.
    }


    //TODO The toString should return the string in this format:
    //      123, Micheal Taylor, 0871234567  is a dog owner

    public String toString() {
        return "Owner ID: " + id
                + ", Name: " + name
                + ", PhoneNumber: " + phoneNumber
                + " is a dog owner";
    }

}