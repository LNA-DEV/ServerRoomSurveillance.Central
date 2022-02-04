import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomDisplayPanel extends JPanel implements ActionListener {

    JLabel lblTemp, lblHumid, lblRoom;
    JButton btnAlarm;

    public RoomDisplayPanel(){
        super();
        setSize(400, 500);
        lblTemp = new JLabel("Temperature: NOT SET");
        lblTemp.setLocation(5, 10);
        add(lblTemp);
        lblHumid = new JLabel("Humidity: NOT SET");
        lblHumid.setLocation(5, 50);
        add(lblHumid);
        lblRoom = new JLabel("Room: NOT SET");
        lblRoom.setLocation(5, 90);
        add(lblRoom);
        btnAlarm = new JButton("Confirm");
        btnAlarm.setBounds(10, 100, 200, 40);
        btnAlarm.addActionListener(this);
        add(btnAlarm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAlarm) {

        }
    }
}
