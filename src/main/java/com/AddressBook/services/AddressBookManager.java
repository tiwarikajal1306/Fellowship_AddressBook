package com.AddressBook.services;

import com.AddressBook.model.Person;

import java.util.Scanner;

public class AddressBookManager implements IAddressBookManager {
    Scanner sc = new Scanner(System.in);
    @Override
    public Person addPerson() {
        Person p = new Person();
        System.out.println("Enter first name");
        String firstName = sc.nextLine();
        p.setFirstName(firstName);
        System.out.println("Enter last name");
        String lastName = sc.nextLine();
        p.setLastName(lastName);
        System.out.println("Enter State");
        String state = sc.nextLine();
        p.setState(state);
        System.out.println("Enter City");
        String city = sc.nextLine();
        p.setCity(city);
        System.out.println("Enter zip");
        String zip = sc.nextLine();
        p.setZip(zip);
        System.out.println("Enter PhoneNumber");
        String phoneNumber = sc.nextLine();
        p.setPhoneNumber(phoneNumber);
        return p;
    }

    public void viewRecord() {
        System.out.println("record view");
        AddressBook.record.forEach(System.out::println);
    }
    public void editRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Edit the information");
        System.out.println("Enter first and last Name");
        String name1 = sc.nextLine();
        for (Person person : AddressBook.record) {
            String name = person.getFirstName() + " " + person.getLastName();
            if (name1.equals(name)) {
                System.out.println("Enter the choice \n1 State \n2 city \n3 zip \n4 phoneNumber");
                int choice3 = Integer.parseInt(sc.nextLine());
                switch (choice3) {
                    case 1:
                        System.out.println("Enter the State");
                        String state = sc.nextLine();
                        person.setState(state);
                        break;
                    case 2:
                        System.out.println("Enter the City");
                        String city = sc.nextLine();
                        person.setCity(city);
                        break;
                    case 3:
                        System.out.println("Enter the Zip");
                        String zip = sc.nextLine();
                        person.setZip(zip);
                        break;
                    case 4:
                        System.out.println("Enter the phoneNumber");
                        String phoneNumber = sc.nextLine();
                        person.setPhoneNumber(phoneNumber);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            }
        }
    }
    public void deleteRecord() {
        System.out.println("Enter the Person first and last Name");
        System.out.print("\n Enter first Name: ");
        String firstName = sc.next();
        System.out.print("\n Enter last Name: ");
        String lastName = sc.next();
        AddressBook.record.removeIf(i ->
                i.getFirstName().equals(firstName) && i.getLastName().equals(lastName)
        );
    }
}
