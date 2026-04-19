import java.net.*;

import java.util.Scanner;

public class UDPClientSender {

    public static final String SERVER_IP = "172.16.109.190";

    public static final int SERVER_PORT = 4444;

    public static void main(String[] args) throws Exception {

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName(SERVER_IP);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Shkruaj emrin e klientit: ");
        String clientName = scanner.nextLine();
        System.out.println("Klienti (Sender) i lidhur me serverin");

        while (true) {

            System.out.println("\nShkruaj njërën nga komandat: READ / WRITE / EXECUTE");
            System.out.print("Shkruaj komandën: ");
            String command = scanner.nextLine();

            String fullMessage = clientName + " " + command;

            byte[] sendData = fullMessage.getBytes();

            DatagramPacket sendPacket =

                    new DatagramPacket(sendData, sendData.length, IPAddress, SERVER_PORT);

            clientSocket.send(sendPacket);

            UDPClientReceiver.receiveResponse(clientSocket);
        }
    }

}