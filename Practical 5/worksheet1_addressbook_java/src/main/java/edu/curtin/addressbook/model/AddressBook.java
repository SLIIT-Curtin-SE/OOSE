package edu.curtin.addressbook.model;


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

    public Entry searchName(String pName)
    {
        Entry foundEntry = null;
        for (Map.Entry<String, Entry> currEntry : myBook.entrySet())
        {
            if (currEntry.getValue().getName().equals(pName))
            {
                foundEntry = currEntry.getValue();
            }
        }
        return foundEntry;
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

    public String fetchAllRecords()
    {
        String result = "";
        for (Map.Entry<String, Entry> currEntry : myBook.entrySet())
        {
            result += currEntry.getValue().display();
            result += " \n\n";
        }

        return result;
    }


}
