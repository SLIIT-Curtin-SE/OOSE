package edu.curtin.addressbook.controller;

import edu.curtin.addressbook.model.AddressBook;

public interface Option
{
    String doOption(String s, AddressBook addressBook);
    boolean requiresText();
}