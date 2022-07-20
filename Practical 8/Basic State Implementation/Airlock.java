public class Airlock{
    int state;
    //1, is both doors closed, 2 is outerdoor is open, 3 is innerdoor is open
    double pressure;
    Door innerdoor;
    Door outerdoor;
    Sensor airLockSensor;
    Pump airLockPump;

    public Airlock(){
        state = 1;
        pressure = 100.0;
        airLockSensor = new Sensor(this);
        innerdoor = new Door();
        outerdoor = new Door();
        airLockPump = new Pump();
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
        if (innerdoor.isOpen()){
            System.out.println("Inner Door is open!, Assuming you want to close the door so closing!");
            state = 1;
            innerdoor.open();
        } else if (state == 1 && airLockSensor.getPressure() > 90.0){
            System.out.println("Opening inner door");
            state = 3;
            innerdoor.open();
        } else if (state == 2){
            System.out.println("Outerdoor is open, do you want to kill us all?");
        } else {
            System.out.println("Tried to open inner door but Air Lock is not pressurized, will created a vacuum if opened!");
        }
    }

    public void openOuterDoor(){
        if (outerdoor.isOpen()){
            System.out.println("Outer Door is open!, Assuming you want to close the door so closing!");
            state = 1;
            outerdoor.open();
        } else if (state == 1 && airLockSensor.getPressure() < 5.0){
            System.out.println("Opening outer door");
            state = 2;
            outerdoor.open();
        } else if (state == 3){
            System.out.println("Innerdoor is open, do you want to kill us all?");
        } else {
            System.out.println("Tried to open outerdoor but Air Lock has pressure, will created a vacuum if opened!");
        }
    }
}