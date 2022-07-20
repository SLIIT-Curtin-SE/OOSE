public class InnerOpen implements AirlockState
{
    public void openInnerDoor(Airlock context){
        System.out.println("Inner Door is open!, Assuming you want to close the door so closing!");
        context.innerdoor.open();
        context.setState(new BothClosed());
    }

    public void openOuterDoor(Airlock context){
        System.out.println("Inner door is open, do you want to kill us all?");
    }
}