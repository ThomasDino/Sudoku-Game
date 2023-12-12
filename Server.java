import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private List<ClientHandler> clients = new ArrayList<>();
    private Sudoku sudoku;
    private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    public Server(int port) {
        this.sudoku = new Sudoku();
        this.sudoku.fillValues();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processInput(String input, ClientHandler client) {
        if (input.equals("show")) {
            client.sendMessage(sudoku.getSudokuString());
        } else {
            String[] parts = input.split(" ");
            if (parts.length == 4 && parts[0].equals("update")) {
                int i = Integer.parseInt(parts[1]);
                int j = Integer.parseInt(parts[2]);
                int num = Integer.parseInt(parts[3]);
                boolean isValid = sudoku.enterNumber(i, j, num);
                broadcast(sudoku.getSudokuString());
                if (!isValid) {
                    clients.forEach(clientHandler -> clientHandler.sendMessage("Update failed. Please try again."));
                }
                if (sudoku.isBoardFull()) {
                    broadcast("The game is complete!");
                    shutdown();
                }
            }
        }
    }

    private void broadcast(String message) {
        clients.forEach(client -> client.sendMessage(message));
    }

    private void shutdown() {
        clients.forEach(client -> client.sendMessage("The game is over. Shutting down server."));
        System.exit(0);
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                this.writer = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String input;
                while ((input = reader.readLine()) != null) {
                    processInput(input, this);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            writer.println(message);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Server <PORT_NUMBER>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        new Server(port);
    }
}
