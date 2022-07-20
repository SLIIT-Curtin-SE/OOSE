public class SecuritySystem implements SensorObserver
{
    private MotionSensor motionSensor;
    private HeatSensor heatSensor;
    private Alarm alarm;

    public SecuritySystem(Hardware hw, Alarm newAlarm)
    {
        SensorBundle sens = hw.getSensors();
        motionSensor = sens.getMotionSensor();
        heatSensor = sens.getHeatSensor();
        motionSensor.addSensorObserver(this);
        heatSensor.addSensorObserver(this);
        alarm = newAlarm;
        setArmed();
    }

    public void setArmed()
    {
        alarm.setArmed();
        EmailSystem.sendMessage("Armed: " + alarm.getArmed());
    }

    @Override
    public void sensorDetection(Sensor s)
    {
        if(alarm.getArmed() && (motionSensor.equals(s) || heatSensor.equals(s)))
        {
            alarm.ring();
            EmailSystem.sendMessage("Sensor detection for " +
            s.toString());
        } else if (alarm.getArmed()) {
            EmailSystem.sendMessage("Sensor " + s.toString() + " is not connected to this Security System!");
        }
    }
}
