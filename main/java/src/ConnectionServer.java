import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionServer extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;

    public ConnectionServer(Socket socket) throws IOException {
        this.clientSocket = socket;
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    start();
    }

    @Override
    public void run() {
        String word;
        try {
            while (true) {
                word = in.readLine();
                if (word.equals("exit")) {
                    break;
                }
                for (ConnectionServer s: Server.servers) {
                    if (!s.equals(this)) {
                        s.send(word);
                    }
                }
            }
        } catch (IOException e) {}
    }
    private void send (String word) {
        try {
            out.write(word+ "\n");
            out.flush();
        } catch (IOException e) {}
    }
}

