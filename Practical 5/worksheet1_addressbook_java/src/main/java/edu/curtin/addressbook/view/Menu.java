package edu.curtin.addressbook;

import edu.curtin.addressbook.model.*;
import edu.curtin.addressbook.controller.*;
import java.util.*;

public class Menu
{
    private static Scanner input = new Scanner(System.in);
    private static Map<Integer, Option> options;

    public Menu()
    {
        options = new HashMap<>();
        options.put(1, new SearchByName());
        options.put(2, new SearchByEmail());
        options.put(3, new DisplayAll());
    }

     /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    public void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        while(!done)
        {
            int choice;
            boolean rText;
            String result;
            System.out.println("(1) Search by name, (2) Search by email, (3) Display All (4) Quit");
            choice = Integer.parseInt(input.nextLine());
            done = checkTermination(choice);
            if (done == false)
            {
                try
                {      
                    Option option = options.get(choice);
                    rText = option.requiresText();
                    if (rText == true)
                    {
                        System.out.print("Enter Search Term: ");
                        String search = input.nextLine();
                        result = option.doOption(search, addressBook);
                    }
                    else
                    {
                        result = option.doOption("", addressBook); //throws abstract errors if you try to write a new poly function with just one param
                    }
                    if (result != null)
                    {
                        System.out.println(result);
                    }
                }
                catch(NumberFormatException e)
                {
                    // The user entered something non-numerical.
                    System.out.println("Enter a number");
                }
            }
        }
    }

    private boolean checkTermination(int choice)
    {
        boolean finished = false;
        if (choice == 4)
        {
            finished = true;
        }
        return finished;
    }
}