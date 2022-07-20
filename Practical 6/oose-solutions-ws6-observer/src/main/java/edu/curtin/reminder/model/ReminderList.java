package edu.curtin.reminder.model;

import java.util.*;

/**
 * Represents a collection of Reminders.
 */
public class ReminderList
{
    private List<Reminder> reminders;
    private Set<Observers> obs = new HashSet<>();

    public ReminderList()
    {
        reminders = new ArrayList<>();
    }
    
    /** Add a single reminder to the list. */
    public void addReminder(Reminder rem)
    {
        reminders.add(rem);
        notifyObservers();
    }
    
    /** Add a complete list of reminders to the existing list. */
    public void addReminders(List<Reminder> newReminders)
    {
        reminders.addAll(newReminders);
    }
    
    /** Remove a reminder by index (i.e. 0 to #reminders-1) */
    public void removeReminder(int index)
    {
        reminders.remove(index);
        notifyObservers();
    }
    
    /** Retrieve a copy of the reminder list. */
    public List<Reminder> getReminders()
    {
        return Collections.unmodifiableList(reminders);
    }

    public void addObserver(Observers ob){
        obs.add(ob);
    }

    public void notifyObservers(){
        for (Observers ob : obs) {
            ob.update();
        }
    }
}
