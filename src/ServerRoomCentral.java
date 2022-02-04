import javax.swing.*;
import java.awt.*;

public class ServerRoomCentral implements IReceiver {

    RoomDisplayPanel rdp;
    ISender connector;

    public static void main(String[] args) {
        new ServerRoomCentral();
    }

    public  ServerRoomCentral()
    {
        JFrame mainWindow = new JFrame("Server Room Surveillance Central");
        mainWindow.setSize(1000, 800);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(null);

        rdp = new RoomDisplayPanel();
        rdp.setLocation(20, 20);
        mainWindow.add(rdp);

        mainWindow.setVisible(true);



        try {
            connector = new MqttConnector(this);
        }
        catch(Exception ex)
        {
            System.out.println("MqttConnector init failed");
            System.out.println(ex);
        }
    }

    @Override
    public void Update(String topic, String message) throws Exception {
        if (topic.equals("sensorclient/data")){
            SensorDataModel model = new SensorDataModel();
            model.AddMessage(message);
            rdp.lblHumid.setText("Humidity: " + model.Humidity + "%");
            rdp.lblTemp.setText("Temperature: " + model.Temperature + "°C");
            rdp.lblRoom.setText("Room: " + model.Room);
        }

        if (topic.equals("sensorclient/alarm")){
            Toolkit.getDefaultToolkit().beep();
        }
    }


}
