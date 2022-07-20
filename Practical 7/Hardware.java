public class Hardware{
    SensorBundle sensorBundle;

    public Hardware(SensorBundle newSensorBundle){
        sensorBundle = newSensorBundle;
    }

    public SensorBundle getSensors(){
        return sensorBundle;
    }
}