package edu.curtin.addressbook.controller;

import edu.curtin.addressbook.model.*;

import java.io.*;
import java.util.*;

public class ReadFile 
{
    public ReadFile(){}
/**
    * Read the address book file, containing all the names and email addresses.
    *
    * @param fileName The name of the address book file.
    * @return A new AddressBook object containing all the information.
    * @throws IOException If the file cannot be read.
    */
    public AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                String[] parts = line.split(":");
                List <String> emails = new ArrayList<>();
                String name = parts[0]; //The name part
                for (int i = 1; i < parts.length; i++) //Cant use a for each since we need the 2nd element onwards
                {
                    emails.add(parts[i]); //Adds the email into a list of emails.
                }
                Entry newEntry = new Entry(name, emails); //Create a new entry

                for (String email : emails)
                {
                    addressBook.add(email, newEntry); //We add the email as the key since emails are unique while names are not.
                    //There can be some repition but having a name as a key is just asking for trouble.
                }

                line = reader.readLine();
            }
        }
        
        return addressBook;
    }
}