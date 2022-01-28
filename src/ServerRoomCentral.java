import org.eclipse.paho.client.mqttv3.MqttException;

public class ServerRoomCentral {

    public static void main(String[] args) throws MqttException {
        try {
            MqttConnector connector = new MqttConnector();
        }
        catch(Exception ex)
        {
            System.out.println("MqttConnector init failed");
            System.out.println(ex);
        }
    }
}
