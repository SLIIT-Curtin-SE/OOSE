package edu.curtin.addressbook;

public class DisplayAll implements Option
{
    @Override 
    public String doOption(String s, AddressBook addressBook)
    {
        
        String result = addressBook.fetchAllRecords();

        return result;
    }

    @Override
    public boolean requiresText()
    {
        return false;
    }
}