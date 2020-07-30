package com.AddressBook.services;

import com.AddressBook.model.Person;

public interface IAddressBookManager {
      Person addPerson();
      void viewRecord();
      void editRecord();
      void deleteRecord();

}
