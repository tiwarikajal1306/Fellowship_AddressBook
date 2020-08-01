package com.AddressBook.services;

import com.AddressBook.model.Person;
import com.AddressBook.utility.CSVFileOperation;
import com.AddressBook.utility.GSONFileOperation;
import com.AddressBook.utility.JSONFileOperation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookOperation implements IAddressBookOperation {
    private List<Person> record = new ArrayList<>();
    List<Person> sampleAddressBook;
    JSONFileOperation jsonFileOperation = new JSONFileOperation();
    CSVFileOperation csvFileOperation = new CSVFileOperation();
    GSONFileOperation gsonFileOperation = new GSONFileOperation();
    Scanner sc = new Scanner(System.in);

    String JSON_FILE_PATH = "./src/main/resources/AddressBook.json";
    String CSV_FILE_PATH = "./src/main/resources/AddressBook.csv";
    String GSON_FILE_PATH = "./src/main/resources/AddressBookGson.json";

    @Override
    public void addPerson() {
        Person person = new Person();
        System.out.println("Enter first name");
        String firstName = sc.nextLine();
        System.out.println("Enter last name");
        String lastName = sc.nextLine();
        boolean isPresent = checkDuplicateRecord(firstName, lastName);
        if (!isPresent) {
            person.setFirstName(firstName);
            person.setLastName(lastName);
            System.out.println("Enter State");
            String state = sc.nextLine();
            person.setState(state);
            System.out.println("Enter City");
            String city = sc.nextLine();
            person.setCity(city);
            System.out.println("Enter zip");
            String zip = sc.nextLine();
            person.setZip(zip);
            System.out.println("Enter PhoneNumber");
            String phoneNumber = sc.nextLine();
            person.setPhoneNumber(phoneNumber);
        }
        record.add(person);
    }

    private boolean checkDuplicateRecord(String firstName, String lastName) {
        for (Person person : record) {
            if (firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName())) {
                System.out.println("Person already present");
                return true;
            }
        }
        return false;
    }

    @Override
    public void viewRecord() {
        record.forEach(System.out::println);
    }

    @Override
    public void editRecord() {
        Scanner sc = new Scanner(System.in);
        String firstAndLastName = sc.nextLine();
        for (Person person : record) {
            String name = person.getFirstName() + " " + person.getLastName();
            if (firstAndLastName.equals(name)) {
                System.out.println("Enter the choice \n1 State \n2 city \n3 zip \n4 phoneNumber");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
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

    @Override
    public void deleteRecord(String firstName, String lastName) {
        record.removeIf(i ->
                i.getFirstName().equals(firstName) && i.getLastName().equals(lastName)
        );
    }

    @Override
    public void sortTheRecordByName() {
        record.sort(Comparator.comparing(Person::getFirstName).
                thenComparing(Person::getLastName));
    }

    @Override
    public void sortRecordByAddress() {
        System.out.println("sort the entries in addressBook by \n1 sortByCity \n2 sortByState \n3 sortByZip");
        int choice = sc.nextInt();
        switch (choice) {
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
    }

    @Override
    public void viewRecordByCityAndState(String city, String state) {
        sampleAddressBook = record.stream().filter
                (person -> person.getState().equals(state) && person.getCity().equals(city))
                .collect(Collectors.toList());
        sampleAddressBook.forEach(System.out::println);
        if (sampleAddressBook.size() == 0)
            System.out.println("No such record exist");
    }

    @Override
    public void searchRecordByCityOrState() {
        System.out.println("Enter choice \n1 searchByCity \n2 searchByState");
        sampleAddressBook = null;
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter city: ");
                String city = sc.next();
                sampleAddressBook = record.stream().filter
                        (person -> person.getCity().equals(city))
                        .collect(Collectors.toList());
                sampleAddressBook.forEach(System.out::println);
                break;
            case 2:
                System.out.print("Enter state: ");
                String state = sc.next();
                sampleAddressBook = record.stream().filter
                        (person -> person.getState().equals(state))
                        .collect(Collectors.toList());
                sampleAddressBook.forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        if (sampleAddressBook.size() == 0)
            System.out.println("No such record exist");
    }

    public void readJsonData() {
        jsonFileOperation.readFromJSON(record, JSON_FILE_PATH);
    }

    public void writeInJson() {
        jsonFileOperation.writeInJSON(record, JSON_FILE_PATH);
    }

    public void readDataFromCSV() {
        record = csvFileOperation.readDataFromCSVFile(CSV_FILE_PATH);
    }

    public void writeDataInCSV() {
        csvFileOperation.writeIntoCSVFile(record, CSV_FILE_PATH);
    }

    public void writeInGSON() {
        gsonFileOperation.writeInGSONFile(record, GSON_FILE_PATH);
    }

    public void readDataFromGSON() {
        record = gsonFileOperation.readFromGSONFile(GSON_FILE_PATH);
    }
}