public class SensorDataModel {
    public float Temperature;
    public float Humidity;
    public String Room;
    public float TemperatureLimit;
    public float HumidityLimit;

    public void AddMessage(String message) {
        String[] data = message.split(":");
        Room = data[0];
        Temperature = Float.parseFloat(data[1]) ;
        Humidity = Float.parseFloat(data[2]);
        TemperatureLimit = Float.parseFloat(data[3]);
        HumidityLimit = Float.parseFloat(data[4]);
    }
}
