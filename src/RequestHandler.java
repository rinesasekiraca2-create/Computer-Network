import java.net.*;
import java.io.*;


public class RequestHandler {
    private static final String SERVER_FOLDER = "server_files";
    private static final String ADMIN_IP = "192.168.1.105";

    public static String handleRequest(String msg, InetAddress clientIP) {
        try {
            String[] parts = msg.split(" ", 3);
            String command = parts[0];

            boolean isAdmin = clientIP.getHostAddress().equals(ADMIN_IP);

            switch (command) {
                case "READ":
                    return readFile(parts[1]);

                case "WRITE":
                    if (!isAdmin) return "ACCESS DENIED";
                    return writeFile(parts[1], parts[2]);

                case "EXECUTE":
                    if (!isAdmin) return "ACCESS DENIED";
                    return "EXECUTED: " + parts[1];

                default:
                    return "COMMAND NOT FOUND";
            }
        }
        catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
    private static String readFile(String fileName) throws IOException {
        File file = new File(SERVER_FOLDER + "/" + fileName);
        if (!file.exists()) return "FILE NOT FOUND";


        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        br.close();

        return content.toString();
    

    }
}