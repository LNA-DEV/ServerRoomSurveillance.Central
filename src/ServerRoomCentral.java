public class ServerRoomCentral {

    public static void main(String[] args) {
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
