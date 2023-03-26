import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Client {

    private  Socket clientSocket;
    private  BufferedReader consoleReader;

    private  BufferedReader in;
    private  BufferedWriter out;

    private String address;
    private int port;
    private String nick;
    private Date date;
    private String formatDate;
    private SimpleDateFormat simpleFormatDate;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
        try {
           this.clientSocket = new Socket(address,port);
        } catch (IOException e) {

        }
        try {
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            this.nick = pressNickname();
            new ReadMessage().start();
            new WriteMessage().start();
        }catch (IOException e) {}
    }

    private String pressNickname() {
        String nick = "Noname";
        System.out.println("Press your Nickname: ");
        try {
            nick = consoleReader.readLine();
            out.write("Hello " + nick + "\n");
            out.flush();
        } catch (IOException e) {}
        return nick;
    }


    private class ReadMessage extends Thread {
        @Override
        public void run() {

            String str;

            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("exit")) {
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {}
        }
    }

    private class WriteMessage extends Thread {
        @Override
        public void run() {
            while (true) {
                String word;
                try {
                    date = new Date();
                    simpleFormatDate = new SimpleDateFormat("HH:mm:ss");
                    formatDate = simpleFormatDate.format(date);
                    word = consoleReader.readLine();

                    if (word.equals("exit")) {
                        out.write("exit" + "\n");
                        break;
                    } else {
                        out.write("(" + formatDate + ") " + nick + ": " + word + "\n");
                    }
                    out.flush();
                } catch (IOException e) {

                }
            }
        }
    }

}
