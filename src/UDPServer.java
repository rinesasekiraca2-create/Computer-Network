import java.net.*;


public class UDPServer {



    private static final int PORT = 9876;

    private static final String SERVER_FOLDER = "server_files";

    private static final String ADMIN_IP = "192.168.1.105";



    public static void main(String[] args) throws Exception {



        DatagramSocket serverSocket = new DatagramSocket(PORT);

        byte[] receiveData = new byte[1024];



        System.out.println("Serveri është duke dëgjuar në portin " + PORT);
    }
}