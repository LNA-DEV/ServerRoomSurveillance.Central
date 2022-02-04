import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomDisplayPanel extends JPanel implements ActionListener {

    JLabel lblTemp, lblHumid, lblRoom, lblTempLimit, lblHumidLimit;
    JButton btnAlarm;

    public RoomDisplayPanel(){
        super();
        setSize(400, 500);

        lblRoom = new JLabel("Room: NOT SET");
        add(lblRoom);

        lblTemp = new JLabel("Temperature: NOT SET");
        add(lblTemp);

        lblHumid = new JLabel("Humidity: NOT SET");
        add(lblHumid);

        lblTempLimit = new JLabel("Temperature Limit: NOT SET");
        add(lblTempLimit);

        lblHumidLimit = new JLabel("Humidity Limit: NOT SET");
        add(lblHumidLimit);

        btnAlarm = new JButton("Confirm");
        btnAlarm.addActionListener(this);
        add(btnAlarm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAlarm) {

        }
    }
}
