public class Alarm{
    private boolean armed;

    public Alarm(){
        armed = false;
    }

    public void ring(){
        System.out.println("Ringing!");
    }

    public void setArmed(){
        armed = true;
    }

    public boolean getArmed(){
        return armed;
    }
}