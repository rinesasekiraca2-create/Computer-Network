import java.net.*;

import java.util.Scanner;

public class UDPClientSender {

    public static final String SERVER_IP = "192.168.1.105";

    public static final int SERVER_PORT = 9876;

    public static void main(String[] args) throws Exception {

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName(SERVER_IP);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Klienti (Sender) i lidhur me serverin");

        while (true) {

            System.out.print("Shkruaj komandë: ");

            String command = scanner.nextLine();

            byte[] sendData = command.getBytes();

            DatagramPacket sendPacket =

                    new DatagramPacket(sendData, sendData.length, IPAddress, SERVER_PORT);

            clientSocket.send(sendPacket);

            UDPClientReceiver.receiveResponse(clientSocket);
        }
    }

}