import org.eclipse.paho.client.mqttv3.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
        client.subscribe("sensorclient/alarm");
    }


    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        if (!mqttMessage.toString().contains(":"))
        {
            System.out.println(GetDateTimeNow() + " ALARM IN ROOM: " + mqttMessage);
            java.awt.Toolkit.getDefaultToolkit().beep();java.awt.Toolkit.getDefaultToolkit().beep();
        }else
        {
            System.out.println(GetDateTimeNow() + ": Message arrived: " + mqttMessage);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("delivery complete");
    }

    private String GetDateTimeNow(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
