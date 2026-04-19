import java.net.*;
import java.io.*;

public class UDPServerFinal {

    private static final int PORT = 4444;
    private static final String SERVER_FOLDER = "server_files";
    private static final String ADMIN_IP = "192.168.1.111";

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

            String response = handleRequest(message, clientIP);

            byte[] sendData = response.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, clientIP, clientPort);

            serverSocket.send(sendPacket);
        }
    }

    private static String handleRequest(String msg, InetAddress clientIP) {
        try {
            String[] parts = msg.split(" ", 4);

            if (parts.length < 2) {
                return "INVALID COMMAND FORMAT";
            }

            String clientName = parts[0];
            String command = parts[1];

            boolean isAdmin = clientIP.getHostAddress().equals(ADMIN_IP);

            System.out.println("U pranua komanda: " + command);
            switch (command) {
                case "READ":
                    if (parts.length < 3)
                        return "MISSING FILE NAME";
                    System.out.println(clientName + " po lexon file: " + parts[2]);
                    return readFile(parts[2]);

                case "WRITE":
                    if (!isAdmin)
                        return "ACCESS DENIED";
                    if (parts.length < 4)
                        return "MISSING DATA";
                    System.out.println(clientName + " po shkruan në file: " + parts[2]);
                    return writeFile(parts[2], parts[3]);

                case "EXECUTE":
                    if (!isAdmin)
                        return "ACCESS DENIED";
                    System.out.println(clientName + " po ekzekuton: " + parts[2]);
                    return "EXECUTED: " + parts[2];

                default:
                    return "COMMAND NOT FOUND";
            }

        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    private static String readFile(String fileName) throws IOException {
        File file = new File(SERVER_FOLDER + "/" + fileName);
        if (!file.exists())
            return "FILE NOT FOUND";

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        br.close();

        return content.toString();
    }

    private static String writeFile(String fileName, String content) throws IOException {
        FileWriter fw = new FileWriter(SERVER_FOLDER + "/" + fileName);
        fw.write(content);
        fw.close();

        return "FILE WRITTEN SUCCESSFULLY";
    }
}