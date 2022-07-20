public class HeatSensor implements Sensor{

    SecuritySystem securitySystem;

    @Override
    public void addSensorObserver(SecuritySystem securitySystem){
        this.securitySystem = securitySystem;
    }
}