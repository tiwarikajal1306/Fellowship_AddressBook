package com.AddressBook.services;

import com.AddressBook.model.Person;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {
    static List<Person> record = new LinkedList<Person>();

    public static void main(String[] args) {
        System.out.println("welcome to the address book");
        AddressBookManager addressBookManager = new AddressBookManager();
        int repeat = 1;
        Scanner choice = new Scanner(System.in);
        while (repeat == 1) {
            System.out.println("Enter the choice \n1 AddPerson \n2 ViewRecords \n3 EditInformation \n4 deleteRecord " +
                    "\n5 sortTheEntriesByName \n6 sortTheEntriesByAddress \n7 viewByCityAndState" +
                    " \n8 searchRecordByCityOrState");
            int choice2 = choice.nextInt();

            switch (choice2) {
                case 1:
                    Person p = addressBookManager.addPerson();
                    boolean isPresent = false;
                    for (Person p1 : record) {
                        if ((p.getFirstName() + " " + p.getLastName()).equals(p1.getFirstName()
                                + " " + p1.getLastName())) {
                            System.out.println("name already present");
                            isPresent = true;
                            break;
                        }
                    }
                    if (!isPresent)
                        record.add(p);
                    break;
                case 2:
                    addressBookManager.viewRecord();
                    break;
                case 3:
                    addressBookManager.editRecord();
                    break;
                case 4:
                    addressBookManager.deleteRecord();
                    break;
                case 5:
                    record.sort(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName));
                    addressBookManager.viewRecord();
                    break;

                case 6:
                    System.out.println("sort the entries in addressBook by \n1 sortByCity \n2 sortByState \n3 sortByZip");
                    int choice4 = choice.nextInt();
                    switch (choice4) {
                        case 1:
                            record.sort(Comparator.comparing(Person::getCity));
                            break;
                        case 2:
                            record.sort(Comparator.comparing(Person::getState));
                            break;
                        case 3:
                            record.sort(Comparator.comparing(Person::getZip));
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;

                case 7:
                    System.out.println("Enter the Person city and state Name");
                    System.out.print("\n Enter city Name: ");
                    String city = choice.next();
                    System.out.print("\n Enter state Name: ");
                    String state = choice.next();
                    List<Person> sampleAddressBook;
                    sampleAddressBook = record.stream().filter
                            (person -> person.getState().equals(state) && person.getCity().equals(city))
                            .collect(Collectors.toList());
                    sampleAddressBook.forEach(System.out::println);
                    if (sampleAddressBook.size() == 0)
                        System.out.println("No such record exist");
                    break;
                case 8:
                    sampleAddressBook = null;
                    System.out.println("Enter choice \n1 searchByCity \n2 searchByState");
                    int choice6 = choice.nextInt();
                    switch (choice6) {
                        case 1:
                            System.out.print("Enter city: ");
                            city = choice.next();
                            sampleAddressBook = record.stream().filter
                                    (person -> person.getCity().equals(city))
                                    .collect(Collectors.toList());
                            sampleAddressBook.forEach(System.out::println);
                            break;
                        case 2:
                            System.out.print("Enter state: ");
                            state = choice.next();
                            sampleAddressBook = record.stream().filter
                                    (person -> person.getState().equals(state))
                                    .collect(Collectors.toList());
                            sampleAddressBook.forEach(System.out::println);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    if (sampleAddressBook != null && sampleAddressBook.size() == 0)
                        System.out.println("No such record exist");

                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("To continue press 1 \n and for exit press any number ");
            repeat = choice.nextInt();
        }
        System.out.println("Exit");

    }
}

