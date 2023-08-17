import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader consoleReader;

    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        clientSocket = new Socket("localhost", 4004);
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        System.out.println("Что бы Вы хотели сказать?");
        String word;
        while (!(word = consoleReader.readLine()).equals("exit")) {
            out.write(word + "\n");
            out.flush();
            String respond = in.readLine();
            System.out.println("Ответ от сервера: " + respond);
            System.out.println("Что бы Вы хотели сказать?");
        }

        in.close();
        out.close();
        clientSocket.close();
    }
}
