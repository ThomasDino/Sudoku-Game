import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Client <PORT_NUMBER>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        try (Socket socket = new Socket(SERVER_HOST, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server. Type 'show' to see the current state or 'update i j num' to make a move.");

            new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = reader.readLine()) != null) {
                        System.out.println("Server: " + serverResponse);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            String userInput;
            while ((userInput = userInputReader.readLine()) != null) {
                writer.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
