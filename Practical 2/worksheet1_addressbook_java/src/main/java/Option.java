package edu.curtin.addressbook;

public interface Option
{
    String doOption(String s, AddressBook addressBook);
    boolean requiresText();
}