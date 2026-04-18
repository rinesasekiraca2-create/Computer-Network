import java.io.File;
import java.net.*;


public class UDPServer {



    private static final int PORT = 9876;

    private static final String SERVER_FOLDER = "server_files";

    private static final String ADMIN_IP = "192.168.1.105";



    public static void main(String[] args) throws Exception {



        DatagramSocket serverSocket = new DatagramSocket(PORT);

        byte[] receiveData = new byte[1024];



        System.out.println("Serveri është duke dëgjuar në portin " + PORT);

        File folder = new File(SERVER_FOLDER);

        if (!folder.exists()) folder.mkdir();



        while (true) {

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            serverSocket.receive(receivePacket);



            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

            InetAddress clientIP = receivePacket.getAddress();

            int clientPort = receivePacket.getPort();



            System.out.println("Mesazh nga klienti: " + message);



            String response = RequestHandler.handleRequest(message, clientIP);



            byte[] sendData = response.getBytes();

            DatagramPacket sendPacket =

                    new DatagramPacket(sendData, sendData.length, clientIP, clientPort);



            serverSocket.send(sendPacket);

        }
    }
}