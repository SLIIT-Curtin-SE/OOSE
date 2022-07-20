public class Main{
    public static void main(String[] args){
        Controls newControl = new Controls();
        newControl.openInnerDoor(); //Astronaut goes in
        newControl.openInnerDoor(); //Astronaut closes the door
        newControl.openOuterDoor(); //Tries to open the outer lock door
        newControl.depressurise(); //Depressurizes cabin 
        newControl.openOuterDoor(); //Opens outerdoor and goes out
        newControl.openOuterDoor(); //Closes outerdoor
        //Does whatever that needs to be done
        newControl.openOuterDoor(); //opens outerdoor again
        newControl.openInnerDoor(); //tries to open inner door without closing outerdoor
        newControl.openOuterDoor(); //closes outerdoor
        newControl.openInnerDoor(); //tries to open the inner door without pressurizing air lock
        newControl.pressurise();
        newControl.openInnerDoor(); //Opens inner door finally
    }
}