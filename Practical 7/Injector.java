public class Injector{
    public static void main(String[] args){
        MotionSensor newMotionSensor = new MotionSensor();
        HeatSensor newHeatSensor = new HeatSensor();
        MotionSensor unConnectedSensor = new MotionSensor();
        SensorBundle newSensorBundle = new SensorBundle(newMotionSensor, newHeatSensor);
        Hardware newHardware = new Hardware(newSensorBundle);
        Alarm newAlarm = new Alarm();
        System.out.println("Sucessfully created the Security System, Alarm should be set to true!");
        SecuritySystem newSecuritySystem = new SecuritySystem(newHardware, newAlarm);

        System.out.println();
        System.out.println();

        System.out.println("The Motion Sensor " + newMotionSensor.toString() + " and " + newHeatSensor.toString() + " are connected to the Security System");
        System.out.println("The Motion Sensor " + unConnectedSensor.toString() + " is not connected ");
        System.out.println("Let us test whether both sensors will be detected");
        System.out.println();
        System.out.println("Testing for " + newMotionSensor.toString());
        newSecuritySystem.sensorDetection(newMotionSensor);
        System.out.println();
        System.out.println("Testing for " + unConnectedSensor.toString());
        newSecuritySystem.sensorDetection(unConnectedSensor);
    }
}