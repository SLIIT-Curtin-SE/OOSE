public class Door{
    boolean open;
    public Door(){
        open = false;
    }

    public boolean isOpen(){
        return open;
    }

    public void open(){
        if (open){
            System.out.println("Door closing!");
            open = false;
        } else {
            System.out.println("Opening Door!");
            open = true;
        }
    }
}