package edu.curtin.addressbook;

import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Jasinge Don Sahas Renuja Gunasekara 20462075
 */
public class Entry 
{
    private List<String> emails;
    private String name;

    public Entry(String pName, List<String> pEmails)
    {
        emails = pEmails;
        name = pName;
    }

    public List<String> getEmails()
    {
        return emails;
    }

    public String getName()
    {
        return name;
    }

    public String display()
    {
        String result = "The name is " + name + "\nThe emails are....\n";

        for (String email : emails)
        {
            result += "" + email + "\n"; 
        }

        return result;
    }
}
