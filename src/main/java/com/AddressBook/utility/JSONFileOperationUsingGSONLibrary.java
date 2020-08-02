package com.AddressBook.utility;

import com.AddressBook.model.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONFileOperationUsingGSONLibrary {
    public void writeInJSONFileUsingGSONLibrary(List<Person> addressBook, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(addressBook);
        try (FileWriter fileWriter = new FileWriter(filePath)){
            fileWriter.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Person> readFromGSONFile(String filePath) {
        List<Person> personDetail;
        try{
            Person[] personDetails = new Gson().fromJson(new FileReader(filePath), Person[].class);
            personDetail = new ArrayList<>(Arrays.asList(personDetails));
            return personDetail;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
