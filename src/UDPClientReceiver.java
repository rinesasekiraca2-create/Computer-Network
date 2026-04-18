import java.net.*;

public class UDPClientReceiver {

    public static void receiveResponse(DatagramSocket clientSocket) throws Exception {

        byte[] receiveData = new byte[1024];

        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String response = new String(
                receivePacket.getData(),
                0,
                receivePacket.getLength()
        );

        System.out.println("Përgjigje nga serveri: " + response);
    }
}