package edu.curtin.addressbook;

public class SearchByName implements Option
{
    @Override 
    public String doOption(String s, AddressBook addressBook)
    {
        String result = null;
        Entry foundEntry = addressBook.searchName(s);
        if (foundEntry == null)
        {
            System.out.println("Looks like there aren't any names that matched!");
        }
        else
        {   
            result = foundEntry.display();
        }

        return result;
    }

    @Override
    public boolean requiresText()
    {
        return true;
    }
}