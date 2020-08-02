package com.AddressBook.utility;

import com.AddressBook.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONFileOperation {

    @SuppressWarnings("unchecked")
    public void writeInJSON(List<Person> addressBook, String jsonFilePath) {
        JSONArray personList = new JSONArray();
        for (Person person : addressBook) {
            JSONObject personDetails = new JSONObject();
            personDetails.put("FirstName", person.getFirstName());
            personDetails.put("LastName", person.getLastName());
            personDetails.put("MobileNumber", person.getPhoneNumber());
            personDetails.put("State", person.getState());
            personDetails.put("City", person.getCity());
            personDetails.put("Zip", person.getZip());

            JSONObject addressBookObject = new JSONObject();
            addressBookObject.put("addressBook", personDetails);

            personList.add(addressBookObject);
        }
        try {
            FileWriter fileWriter = new FileWriter(jsonFilePath);
            fileWriter.append(personList.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromJSON(List<Person> addressBook, String jsonFilePath) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(jsonFilePath)) {
            Object obj = jsonParser.parse(reader);
            JSONArray personList = (JSONArray) obj;
            personList.forEach(person -> addressBook.add(parseAddressBookObject((JSONObject) person)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private Person parseAddressBookObject(JSONObject personDetail) {
        Person person = new Person();
        JSONObject addressBookObject = (JSONObject) personDetail.get("addressBook");
        String firstName = (String) addressBookObject.get("FirstName");
        person.setFirstName(firstName);
        String lastName = (String) addressBookObject.get("LastName");
        person.setLastName(lastName);
        String phoneNumber = (String) addressBookObject.get("MobileNumber");
        person.setPhoneNumber(phoneNumber);
        String state = (String) addressBookObject.get("State");
        person.setState(state);
        String city = (String) addressBookObject.get("City");
        person.setCity(city);
        String zip = (String) addressBookObject.get("Zip");
        person.setZip(zip);
        return person;
    }
}
