import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class RoomDisplayPanel extends JPanel implements ActionListener
{
    int room;
    JButton confirm, send;
    JTextField tftemp, tfhum;
    JLabel l, lt, lh;
    Sender sender;
    DecimalFormat df = new DecimalFormat("#0.00");
    DecimalFormat df1 = new DecimalFormat("#0.0");

    public RoomDisplayPanel(Sender sender, int room)
    {
        super();
        this.sender = sender;
        setLayout(null);
        this.room = room;
        Font f = new Font("Arial", 0 , 20);
        setFont(f);
        l = new JLabel("" + room);
        l.setBackground(Color.black);
        l.setForeground(Color.white);
        l.setFont(f);
        l.setBounds(10,5,240,20);
        add(l);
        lt = new JLabel("Temperature: " + df.format( 0) + "°C") ;
        lt.setBackground(Color.LIGHT_GRAY);
        lt.setFont(f);
        lt.setBounds(10,50,240,30);
        add(lt);
        lh = new JLabel("Humidity: " + df.format(0) + "%");
        lh.setFont(f);
        lh.setBounds(10,80,240,30);
        add(lh);
        JLabel ltemp = new JLabel("Temp. Limit : ");
        ltemp.setFont(f);
        ltemp.setBounds(10,120,240,30);
        add(ltemp);
        tftemp = new JTextField(""+df1.format(0));
        tftemp.setFont(f);
        tftemp.setBounds(150,120,60,30);
        add(tftemp);
        JLabel lhum = new JLabel("Humid. Limit: ");
        lhum.setFont(f);
        lhum.setBounds(10,160,240,30);
        add(lhum);
        tfhum = new JTextField(""+df1.format(0));
        tfhum.setFont(f);
        tfhum.setBounds(150,160,60,30);
        add(tfhum);
        setSize(240,290);
        setPreferredSize(new Dimension(200,290));
        send = new JButton("Update");
        send.setFont(f);
        send.setBounds(10,200, 180, 30);
        send.addActionListener(this);
        add(send);
        confirm = new JButton("Confirm A");
        confirm.setFont(f);
        confirm.setEnabled(false);
        confirm.setBounds(10,240, 180, 30);
        confirm.addActionListener(this);
        add(confirm);
        setBackground(Color.GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirm)
        {   // Confirm alarm
            l.setForeground(Color.GREEN);
            setBackground(Color.GRAY);
            confirm.setEnabled(false);
            try {
                sender.send("ServerRoomCentral/confirm/" + room,"");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Alarm confirmation failed");
            }
        }
        if(e.getSource() == send)
        {   // Update limits
            try {
                sender.send("ServerRoomCentral/limits/" + room, tftemp.getText() + ":" + tfhum.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Setting new limits failed");
            }
        }
    }

    public void updateDisplay(int room, double temp, double humid, double tlimit, double hlimit) throws Exception
    {   this.room = room;
        l.setForeground(Color.green);
        l.setText("" + room);
        lh.setText("Humidity: " + df.format(humid) + "%");
        lt.setText("Temperature: " + df.format(temp) + "°C");
        // Set limits if empty
        if(tftemp.getText().isEmpty())
            tftemp.setText("" + df1.format(tlimit));
        if(tfhum.getText().isEmpty())
            tfhum.setText("" + df1.format(hlimit));
    }

    public void setAlarm()
    {   l.setForeground(Color.WHITE);
        setBackground(Color.RED);
        confirm.setEnabled(true);
    }

}
