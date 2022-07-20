package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Jasinge Don Sahas Renuja Gunasekara 20462075
 */
public class AddressBook
{
    private Map<String, Entry> myBook = new HashMap<>();

    public void add(String pEmailAddress, Entry pEntry)
    {
        myBook.put(pEmailAddress, pEntry);
    }

    public Entry getEntry(String pEmailAddress)
    {
        return myBook.get(pEmailAddress);
    }

    public boolean searchName(String pName)
    {
        boolean found = false;
        for (Map.Entry<String, Entry> currEntry : myBook.entrySet())
        {
            if (currEntry.getValue().getName().equals(pName))
            {
                found = true;
                currEntry.getValue().display();
            }
        }
        return found;
    }

    public boolean isThere(String pEmailAddress)
    {
        boolean result = false;
        if (myBook.containsKey(pEmailAddress))
        {
            result = true;
        }
        
        return result;
    }
}
