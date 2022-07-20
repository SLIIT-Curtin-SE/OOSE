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

    public void display()
    {
        System.out.println("The name is " + name);
        System.out.println("The emails are....");

        for (String email : emails)
        {
            System.out.println(email);
        }
    }
}
