package com.AddressBook.services;

import com.AddressBook.config.MySqlOperation;
import com.AddressBook.model.Person;
import com.AddressBook.utility.CSVFileOperation;
import com.AddressBook.utility.JSONFileOperation;
import com.AddressBook.utility.JSONFileOperationUsingGSONLibrary;
import com.AddressBook.utility.UserValidationCheck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookOperation implements IAddressBookOperation {
    List<Person> sampleAddressBook;
    JSONFileOperation jsonFileOperation = new JSONFileOperation();
    CSVFileOperation csvFileOperation = new CSVFileOperation();
    JSONFileOperationUsingGSONLibrary JSONFileOperationUsingGSONLibrary = new JSONFileOperationUsingGSONLibrary();
    UserValidationCheck userValidationCheck = new UserValidationCheck();
    MySqlOperation mySqlOperation = new MySqlOperation();
    Scanner sc = new Scanner(System.in);
    String JSON_FILE_PATH = "./src/main/resources/AddressBook.json";
    String CSV_FILE_PATH = "./src/main/resources/AddressBook.csv";
    String JSON_FILE_PATH2 = "./src/main/resources/AddressBookGson.json";
    private List<Person> record = new ArrayList<>();

    @Override
    public void addPerson() {
        Person person = new Person();
        userValidationCheck.validFirstName();
        userValidationCheck.validLastName();
        boolean isPresent = checkDuplicateRecord(userValidationCheck.firstName, userValidationCheck.lastName);
        if (!isPresent) {
            person.setFirstName(userValidationCheck.firstName);
            person.setLastName(userValidationCheck.lastName);
            userValidationCheck.validStateName();
            person.setState(userValidationCheck.state);
            userValidationCheck.validCityName();
            person.setCity(userValidationCheck.city);
            userValidationCheck.validZipCode();
            person.setZip(userValidationCheck.zipCode);
            userValidationCheck.validPhoneNumber();
            person.setPhoneNumber(userValidationCheck.phoneNumber);
        }
        record.add(person);
        mySqlOperation.insertData(person.getFirstName(), person.getLastName(), person.getCity(), person.getPhoneNumber(),
                person.getState(), person.getZip());
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
        mySqlOperation.selectRecords();
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

    public void editDatabaseDetails() {
        System.out.println("Enter mobileNumber of the person for updating details");
        String number = sc.nextLine();
        System.out.println("Choose option: \n" +
                " 1 = Edit mobile number\n" +
                " 2 = Edit State\n" +
                " 3 = Edit city\n" +
                " 4 = Edit zip");
        int input = Integer.parseInt(sc.nextLine());
        switch (input) {
            case 1:
                System.out.println("Enter mobile number");
                String mobileNumber = sc.nextLine();
                mySqlOperation.updateData(number, 1, mobileNumber);
                break;
            case 2:
                System.out.println("Enter State");
                String state = sc.nextLine();
                mySqlOperation.updateData(number, 2, state);
                break;
            case 3:
                System.out.println("Enter city");
                String city = sc.nextLine();
                mySqlOperation.updateData(number, 3, city);
                break;
            case 4:
                System.out.println("Enter zip");
                String zip = sc.nextLine();
                mySqlOperation.updateData(number, 4, zip);
                break;
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

    @Override
    public void readJsonData() {
        jsonFileOperation.readFromJSON(record, JSON_FILE_PATH);
    }

    @Override
    public void writeInJson() {
        jsonFileOperation.writeInJSON(record, JSON_FILE_PATH);
    }

    @Override
    public void readDataFromCSV() {
        record = csvFileOperation.readDataFromCSVFile(CSV_FILE_PATH);
    }

    @Override
    public void writeDataInCSV() {
        csvFileOperation.writeIntoCSVFile(record, CSV_FILE_PATH);
    }

    @Override
    public void writeInJSONUSingGSONLibrary() {
        JSONFileOperationUsingGSONLibrary.writeInJSONFileUsingGSONLibrary(record, JSON_FILE_PATH2);
    }

    @Override
    public void readDataFromJSONUSingGSONLibrary() {
        record = JSONFileOperationUsingGSONLibrary.readFromGSONFile(JSON_FILE_PATH2);
    }
}