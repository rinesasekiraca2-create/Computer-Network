import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientFinal {

    private static final String SERVER_IP = "192.168.1.111";
    private static final int SERVER_PORT = 4444;

    public static void main(String[] args) throws Exception {

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(SERVER_IP);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Shkruaj emrin e klientit: ");
        String clientName = scanner.nextLine();

        System.out.println("Klienti [" + clientName + "] u lidh me serverin " + SERVER_IP + ":" + SERVER_PORT);

        while (true) {
            System.out.println("\nShkruaj njeren nga komandat: READ/WRITE/EXECUTE");

            System.out.print("Shkruaj komandë: ");
            String command = scanner.nextLine();

            String fullMessage = clientName + " " + command;

            byte[] sendData = fullMessage.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, SERVER_PORT);

            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Përgjigje nga serveri: " + response);
        }
    }
}