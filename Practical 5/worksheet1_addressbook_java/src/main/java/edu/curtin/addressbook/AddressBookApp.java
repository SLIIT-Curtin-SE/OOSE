package edu.curtin.addressbook;

import edu.curtin.addressbook.model.*;
import edu.curtin.addressbook.controller.*;

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

        Menu newMenu = new Menu();
        ReadFile newReadFile = new ReadFile();
        
        try
        {
            AddressBook addressBook = newReadFile.readAddressBook(fileName);
            newMenu.showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
}
