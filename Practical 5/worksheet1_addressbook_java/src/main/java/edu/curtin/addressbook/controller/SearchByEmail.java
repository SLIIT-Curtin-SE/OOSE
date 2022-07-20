package edu.curtin.addressbook.controller;

import edu.curtin.addressbook.model.*;

public class SearchByEmail implements Option
{
    @Override 
    public String doOption(String s, AddressBook addressBook)
    {
        String result = null;

        if (addressBook.isThere(s)) //do a presence check just in case.
        {
            result = addressBook.getEntry(s).display(); //We fetch the entry and then use the display() method on the entry
        }
        else 
        {
            System.out.println("No such email!");
        }

        return result;
    }

    @Override
    public boolean requiresText()
    {
        return true;
    }
}