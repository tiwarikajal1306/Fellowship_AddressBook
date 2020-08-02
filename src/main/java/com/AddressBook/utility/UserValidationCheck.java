package com.AddressBook.utility;

import java.util.Scanner;

public class UserValidationCheck {
    Scanner scanner = new Scanner(System.in);
    public String validFirstName() {
        System.out.println("Enter first name");
        String firstName = scanner.nextLine();
        if(firstName.matches("^[A-Z]{1}[a-zA-Z]{2,}"))
        return firstName;
        else {
            System.out.println("Entered First Name Is Not Valid..Enter First Name Again");
            this.validFirstName();
        }
        return null;
    }
    public String validLastName() {
        System.out.println("Enter Last name");
        String lastName = scanner.nextLine();
        if(lastName.matches("^[A-Z]{1}[a-zA-Z]{2,}"))
            return lastName;
        else {
            System.out.println("Entered Last Name Is Not Valid..Enter Last Name Again");
            this.validLastName();
        }
        return null;
    }
    public String validStateName() {
        System.out.println("Enter State name");
        String stateName = scanner.nextLine();
        if(stateName.matches("^[A-Z]{1}[a-zA-Z]{2,}"))
            return stateName;
        else {
            System.out.println("Entered State Name Is Not Valid..Enter State Name Again");
            this.validStateName();
        }
        return null;
    }
    public String validCityName() {
        System.out.println("Enter City name");
        String cityName = scanner.nextLine();
        if(cityName.matches("^[A-Z]{1}[a-zA-Z]{2,}"))
            return cityName;
        else {
            System.out.println("Entered City Name Is Not Valid..Enter City Name Again");
            this.validCityName();
        }
        return null;
    }
    public String validZipCode() {
        System.out.println("Enter Zip Code");
        String zipCode = scanner.nextLine();
        if(zipCode.matches("^[1-9][0-9]{5}$"))
            return zipCode;
        else {
            System.out.println("Entered Zip Code Is Not Valid..Enter Zip Code Again");
            this.validZipCode();
        }
        return null;
    }
    public String validPhoneNumber() {
        System.out.println("Enter Phone Number");
        String phoneNumber = scanner.nextLine();
        if(phoneNumber.matches("^[1-9]+[0-9]+[\\ ]?+[1-9]{1}[0-9]{9}$"))
            return phoneNumber;
        else {
            System.out.println("Entered phone Number Is Not Valid..Enter Phone Number Again");
            this.validPhoneNumber();
        }
        return null;
    }
}