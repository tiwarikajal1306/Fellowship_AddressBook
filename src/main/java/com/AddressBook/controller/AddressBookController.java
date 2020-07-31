package com.AddressBook.controller;

import com.AddressBook.services.AddressBookOperation;

import java.util.Scanner;

public class AddressBookController {

    public static void main(String[] args) {
        System.out.println("welcome to the address book");
        AddressBookOperation addressBookOperation = new AddressBookOperation();
        Scanner scanner = new Scanner(System.in);
        int repeat = 1;
        while (repeat == 1) {
            System.out.println("Enter the choice \n1 AddPerson \n2 ViewRecords \n3 EditInformation \n4 deleteRecord " +
                    "\n5 sortTheEntriesByName \n6 sort TheEntriesByAddress \n7 viewByCityAndState" +
                    " \n8 searchRecordByCityOrState");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    addressBookOperation.addPerson();
                    break;
                case 2:
                    System.out.println("record view");
                    addressBookOperation.viewRecord();
                    break;
                case 3:
                    System.out.println("Edit the information");
                    System.out.println("Enter first and last Name");
                    addressBookOperation.editRecord();
                    break;
                case 4:
                    System.out.println("Enter the Person first and last Name");
                    System.out.print("\n Enter first Name: ");
                    String firstName = scanner.next();
                    System.out.print("\n Enter last Name: ");
                    String lastName = scanner.next();
                    addressBookOperation.deleteRecord(firstName, lastName);
                    break;
                case 5:
                    addressBookOperation.sortTheRecordByName();
                    addressBookOperation.viewRecord();
                    break;

                case 6:
                    addressBookOperation.sortRecordByAddress();
                    break;

                case 7:
                    System.out.println("Enter the Person city and state Name");
                    System.out.print("\n Enter city Name: ");
                    String city = scanner.next();
                    System.out.print("\n Enter state Name: ");
                    String state = scanner.next();
                    addressBookOperation.viewRecordByCityAndState(city, state);
                    break;

                case 8:
                    addressBookOperation.searchRecordByCityOrState();
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("To continue press 1 \n and for exit press any number ");
            repeat = scanner.nextInt();
        }
        System.out.println("Exit");
    }
}

