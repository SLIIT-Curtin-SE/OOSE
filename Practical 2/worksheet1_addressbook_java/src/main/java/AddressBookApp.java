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

    private static Map<Integer, Option> options;

    public AddressBookApp()
    {
        options = new HashMap<>();
        options.put(1, new SearchByName());
        options.put(2, new SearchByEmail());
        options.put(3, new DisplayAll());
    }
    
    public static void main(String[] args)
    {
        String fileName;

        AddressBookApp newBookApp = new AddressBookApp();
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = newBookApp.readAddressBook(fileName);
            newBookApp.showMenu(addressBook);
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
    private AddressBook readAddressBook(String fileName) throws IOException
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
    private void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        while(!done)
        {
            int choice;
            boolean rText;
            String result;
            System.out.println("(1) Search by name, (2) Search by email, (3) Display All (4) Quit");
            choice = Integer.parseInt(input.nextLine());
            done = checkTermination(choice);
            if (done == false)
            {
                try
                {      
                    Option option = options.get(choice);
                    rText = option.requiresText();
                    if (rText == true)
                    {
                        System.out.print("Enter Search Term: ");
                        String search = input.nextLine();
                        result = option.doOption(search, addressBook);
                    }
                    else
                    {
                        result = option.doOption("", addressBook); //throws abstract errors if you try to write a new poly function with just one param
                    }
                    if (result != null)
                    {
                        System.out.println(result);
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

    private boolean checkTermination(int choice)
    {
        boolean finished = false;
        if (choice == 4)
        {
            finished = true;
        }
        return finished;
    }
}
