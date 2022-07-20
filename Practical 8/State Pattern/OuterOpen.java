public class OuterOpen implements AirlockState{
    public void openInnerDoor(Airlock context){
        System.out.println("Outer door is open, do you want to kill us all?");
    }

    public void openOuterDoor(Airlock context){
        System.out.println("Outer Door is open!, Assuming you want to close the door so closing!");
        context.outerdoor.open();
        context.setState(new BothClosed());
    }
}