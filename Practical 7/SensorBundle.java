public class SensorBundle{
    MotionSensor motionSensor;
    HeatSensor heatSensor;

    public SensorBundle(MotionSensor newMotionSensor, HeatSensor newHeatSensor){
        motionSensor = newMotionSensor;
        heatSensor = newHeatSensor;
    }

    public MotionSensor getMotionSensor(){
        return motionSensor;
    }

    public HeatSensor getHeatSensor(){
        return heatSensor;
    }
}