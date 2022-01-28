import org.eclipse.paho.client.mqttv3.*;

public class MqttConnector implements MqttCallback {

    String broker       = "tcp://127.0.0.1:1883";
    String clientId     = "SeverRoomCentral";
    MqttClient client;

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

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
