public class ClientController {
    public static String address = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        new Client(address,port);
    }
}
