import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket serverSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(4004);
        System.out.println("Сервер запузен");
        clientSocket = serverSocket.accept();

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        while (clientSocket.isBound()) {
            String word = in.readLine();
            System.out.println("Клиент отправил слово " + word);
            out.write("Привет, это сервер, подтверждаю получение слова " + word + "\n");
            out.flush();
        }
        in.close();
        out.close();
        serverSocket.close();

    }
}
