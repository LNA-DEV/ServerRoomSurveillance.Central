import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class ServerRoomCentral implements IReceiver {

    RoomDisplayPanel rdp;

    ISender connector;
    Vector<RoomDisplayPanel> rp = new Vector<RoomDisplayPanel>();

    JFrame hf;

    public static void main(String[] args) {
        new ServerRoomCentral();
    }

    public  ServerRoomCentral()
    {
        hf = new JFrame("Serverraumüberwachung - Zentrale");
        hf = new JFrame("Serverraumüberwachung - Zentrale");
        hf.setSize(1000, 800);
        hf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hf.setLayout(null);
        hf.getContentPane().setBackground(Color.BLACK);

        try {
            connector = new MqttConnector(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        hf.setVisible(true);
    }

    private void updatePanels(int room, double temp, double humid, double tlimit, double hlimit)
    {
        for(RoomDisplayPanel panel : rp) {   // if panel for the room exists -> update
            if (room == panel.room) {
                try {
                    panel.updateDisplay(room, temp, humid, tlimit, hlimit);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        // no panel available -> new panel
        RoomDisplayPanel p = new RoomDisplayPanel(connector, room);
        p.setLocation(panelx, panely);
        panelx += 250;
        if(panelx > hf.getWidth() - 250)
        {
            panelx = 20;
            panely = 400;
        }
        hf.add(p);
        hf.repaint();
        rp.add(p);
    }

    @Override
    public void update(String topic, String message) throws Exception {
        if(topic.equals("sensorclient/data"))
        {   String[] teil = message.split(":");
            int room = Integer.parseInt(teil[0]);
            double temp = Double.parseDouble(teil[1]);
            double humid = Double.parseDouble(teil[2]);
            double templ = Double.parseDouble(teil[3]);
            double huml = Double.parseDouble(teil[4]);
            updatePanels(room, temp, humid, templ, huml);
        }
        if(topic.equals("sensorclient/alarm"))
        {
            // Alarm
            //rp.setBackground(Color.red);
        }
    }


}
