import org.eclipse.paho.client.mqttv3.*;

public class MqttConnector implements MqttCallback {

    String broker       = "tcp://127.0.0.1:1883";
    String clientId     = "SeverRoomCentral";
    MqttClient client;

    //Constructor
    public MqttConnector() throws MqttException {
        client = new MqttClient(broker, clientId);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        client.connect(connOpts);
        client.setCallback(this);
        // subscribe to data messages
        client.subscribe("sensorclient/data");
    }


    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message arrived: " + mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("delivery complete");
    }
}
