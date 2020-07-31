package com.AddressBook.services;

public interface IAddressBookOperation {
    void addPerson();
    void viewRecord();
    void editRecord();
    void deleteRecord(String firstName, String lastName);
    void sortTheRecordByName();
    void sortRecordByAddress();
    void viewRecordByCityAndState(String city, String state);
    void searchRecordByCityOrState();
}
