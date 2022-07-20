public class Sensor{
    Airlock currAirlock;

    public Sensor(Airlock currAirlock){
        this.currAirlock = currAirlock;
    }

    public double getPressure(){
        return currAirlock.pressure;
    }
}