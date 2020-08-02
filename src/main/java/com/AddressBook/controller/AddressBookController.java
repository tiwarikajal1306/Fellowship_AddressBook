package com.AddressBook.controller;

import com.AddressBook.services.AddressBookOperation;

import java.util.Scanner;

public class AddressBookController {

    public static void main(String[] args) {
        System.out.println("********Welcome to the address book********");
        AddressBookOperation addressBookOperation = new AddressBookOperation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter The Choice \n1 JSON File \n2 CSV File \n3 JSON File Using GSON Library");
        int choice = scanner.nextInt();
        switch(choice) {
            case 1 :
                addressBookOperation.readJsonData();
                break;
            case 2 :
                addressBookOperation.readDataFromCSV();
                break;
            case 3 :
                addressBookOperation.readDataFromJSONUSingGSONLibrary();
                break;
        }
        int repeat = 1;
        while (repeat == 1) {
            System.out.println("Enter The Choice \n1 Add Person Detail \n2 View Person Detail \n3 Edit Person Detail" +
                    " \n4 Delete Person Detail " +
                    "\n5 Sort The Person Detail By Name \n6 Sort The Person Detail By Address" +
                    " \n7 View Person Detail By City And State" +
                    "\n8 Search Person Detail By City Or State" + "\n9 Write Person Detail In Json" +
                    "\n10 Write Person Detail In Csv" + "\n11 Write Person Detail In JSON Using Gson Library");
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

                case 9:
                    addressBookOperation.writeInJson();
                    break;

                case 10:
                    addressBookOperation.writeDataInCSV();
                    break;

                case 11 :
                    addressBookOperation.writeInJSONUSingGSONLibrary();
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

