public class Airlock{
    private AirlockState state;
    double pressure;
    Door innerdoor;
    Door outerdoor;
    Sensor airLockSensor;
    Pump airLockPump;

    public Airlock(){
        state = new BothClosed();
        pressure = 100.0;
        airLockSensor = new Sensor(this);
        innerdoor = new Door();
        outerdoor = new Door();
        airLockPump = new Pump();
    }

    public void setState(AirlockState state){
        this.state = state;
    }

    public void updatePressure(double pressure){
        this.pressure = pressure;
    }

    public void pressurise(){
        airLockPump.beginReturn();
        updatePressure(100.0);
    }

    public void depressurise(){
        airLockPump.beginExtraction();
        updatePressure(0.0);
    }

    public void openInnerDoor(){
        state.openInnerDoor(this);
    }

    public void openOuterDoor(){
        state.openOuterDoor(this);
    }

}