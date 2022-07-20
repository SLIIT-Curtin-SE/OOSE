public class BothClosed implements AirlockState{

    public void openInnerDoor(Airlock context){
        if (context.airLockSensor.getPressure() > 90.0){
            System.out.println("Opening inner door");
            context.innerdoor.open();
            context.setState(new InnerOpen());
        } else {
            System.out.println("Tried to open inner door but Air Lock is not pressurized, will created a vacuum if opened!");
        }
    }

    public void openOuterDoor(Airlock context){
        if (context.airLockSensor.getPressure() < 5.0){
            System.out.println("Opening outer door");
            context.setState(new OuterOpen());
        } else {
            System.out.println("Tried to open outerdoor but Air Lock has pressure, will created a vacuum if opened!");
        }
    }
}