import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApp {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1"; // або "localhost" для локального тесту
        final int SERVER_PORT = 8081;

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Отримуємо привітання від сервера
            String serverGreeting = in.readLine();
            System.out.println("Server: " + serverGreeting);

            // Відправляємо привітання на сервер
            out.println("привіт");

            // Отримуємо відповідь від сервера
            String response = in.readLine();
            System.out.println("Server: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}