import org.eclipse.paho.client.mqttv3.*;

public class MqttConnector implements MqttCallback, ISender {

    String broker       = "tcp://127.0.0.1:1883";
    String clientId     = "SeverRoomCentral";
    MqttClient client;
    IReceiver receiver;

    public MqttConnector(IReceiver receiver) throws MqttException {
        this.receiver = receiver;
        client = new MqttClient(broker, clientId);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        client.connect(connOpts);
        client.setCallback(this);
        client.subscribe("sensorclient/data");
        client.subscribe("sensorclient/alarm");
    }


    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        receiver.Update(topic, mqttMessage.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("delivery complete");
    }

    @Override
    public void Send(String topic, String message) throws Exception {

    }
}
