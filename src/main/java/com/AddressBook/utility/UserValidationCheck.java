package com.AddressBook.utility;

import java.util.Scanner;

public class UserValidationCheck {
    public String phoneNumber;
    public String zipCode;
    public String city;
    public String state;
    public String lastName;
    public String firstName;
    Scanner scanner = new Scanner(System.in);

    public void validFirstName() {
        System.out.println("Enter first name");
        this.firstName = scanner.nextLine();
        boolean check = firstName.matches("^[A-Z]{1}[a-zA-Z]{2,}");
        if (check == false) {
            System.out.println("Entered First Name Is Not Valid..Enter First Name Again");
            validFirstName();
        }
    }

    public void validLastName() {
        System.out.println("Enter Last name");
        this.lastName = scanner.nextLine();
        boolean check = lastName.matches("^[A-Z]{1}[a-zA-Z]{2,}");
        if (check == false) {
            System.out.println("Entered Last Name Is Not Valid..Enter Last Name Again");
            validLastName();
        }
    }

    public void validStateName() {
        System.out.println("Enter State name");
        this.state = scanner.nextLine();
        boolean check = state.matches("^[A-Z]{1}[a-zA-Z]{2,}");
        if (check == false) {
            System.out.println("Entered State Name Is Not Valid..Enter State Name Again");
            validStateName();
        }
    }

    public void validCityName() {
        System.out.println("Enter City name");
        this.city = scanner.nextLine();
        boolean check = city.matches("^[A-Z]{1}[a-zA-Z]{2,}");
        if (check == false) {
            System.out.println("Entered City Is Not Valid..Enter City Name Again");
            validCityName();
        }
    }

    public void validZipCode() {
        System.out.println("Enter Zip Code");
        this.zipCode = scanner.nextLine();
        boolean check = zipCode.matches("^[1-9][0-9]{5}$");
        if (check == false) {
            System.out.println("Entered Zip Code Is Not Valid..Enter Zip Code Again");
            validZipCode();
        }
    }

    public void validPhoneNumber() {
        System.out.println("Enter Phone Number");
        this.phoneNumber = scanner.nextLine();
        boolean check = phoneNumber.matches("^[1-9]+[0-9]+[\\ ]?+[1-9]{1}[0-9]{9}$");
        if (check == false) {
            System.out.println("Entered Phone Number Is Not Valid..Enter Phone Number Again");
            validPhoneNumber();
        }
    }
}