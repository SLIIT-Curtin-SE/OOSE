public class Controls{
    Airlock newAirLock;
    public Controls(){
        newAirLock = new Airlock();
    }

    public void pressurise(){
        newAirLock.pressurise();
    }

    public void depressurise(){
        newAirLock.depressurise();
    }

    public void openInnerDoor(){
        newAirLock.openInnerDoor();
    }

    public void openOuterDoor(){
        newAirLock.openOuterDoor();
    }
}
