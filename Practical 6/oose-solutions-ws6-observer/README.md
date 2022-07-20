OOSE Solutions
======
This project will have different solutions on different branches
* [Back to Main](https://github.com/shyam3001/oose-solutions)

Worksheet 6 - Observer
------

Obtain a copy of `worksheet6_reminder_java.zip`. This is a simple, but somewhat incomplete todo/reminder application. The application is designed as follows:

There is also a `ReminderApp` class, containing the `main` method.

In the design so _far_, the `AddReminderWindow` can tell the controller to add a reminder, `MainWindow` can tell the controller to remove a reminder, and the controller makes the changes to the model. What’s missing is this:

* `MainWindow` does not show the updated reminder text when a change is made (though it has a `JList remindersList` object for displaying this). The user can _technically_ add a reminder, but nothing will actually show up.

* The file is not updated when a change is made, and we would like it to be auto-saved. At the moment, all changes will be lost when the program exits.

Your task is to use the Observer Pattern to fill in these gaps. For this purpose, you should treat the `ReminderList` class as the event source (subject). If you think about it, `ReminderList` knows _when_ the above actions should happen; but actually doing them is outside its responsibility.

You will then need to consider the following:

* What other functionality does ReminderList require?

* Which classes are going to function as observers?

* How (and where) are you going to initialise the links between event source and observers?