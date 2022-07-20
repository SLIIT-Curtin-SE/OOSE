package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and Jasinge Don Sahas Renuja Gunasekara 20462075
 */
public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        String fileName;
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static AddressBook readAddressBook(String fileName) throws IOException
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
    
    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        while(!done)
        {
            int option;
            System.out.println("(1) Search by name, (2) Search by email, (3) Quit");
            option = Integer.parseInt(input.nextLine());
            try
            {
                switch(option)
                {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = input.nextLine();
                        boolean found = addressBook.searchName(name);
                        if (!found)
                        {
                            System.out.println("Looks like there aren't any names that matched!");
                        }
                        break;
                        
                    case 2:
                        System.out.print("Enter email address: ");
                        String email = input.nextLine();
                        if (addressBook.isThere(email)) //do a presence check just in case.
                        {
                            addressBook.getEntry(email).display(); //We fetch the entry and then use the display() method on the entry
                        }
                        else 
                        {
                            System.out.println("No such email!");
                        }
                        break;
                        
                    case 3:
                        done = true;
                        break;
                        
                    default:
                        System.out.println("Enter a valid number");
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }
}
