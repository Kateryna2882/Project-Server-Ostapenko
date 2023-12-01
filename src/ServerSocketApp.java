
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class ServerSocketApp {
    public static void main(String[] args) {
        final int PORT = 8081;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            // Очікуємо підключення клієнта
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            // Створюємо потоки для зчитування та виводу повідомлень
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Обмін привітаннями
            out.println("Hello");
            String clientGreeting = in.readLine();

            if (clientGreeting.contains("привіт")) {
                // Відправляємо питання
                out.println("Що таке паляниця?");
                String answer = in.readLine();

                if (answer.equalsIgnoreCase("Хліб")) {
                    // Відправляємо поточну дату та час
                    out.println("Current date and time: " + LocalDateTime.now());
                    System.out.println("Date and time sent. Closing connection.");
                } else {
                    // Неправильна відповідь, відключення клієнта
                    System.out.println("Incorrect answer. Closing connection.");
                    clientSocket.close();
                    return;
                }
            }

            // Закриваємо ресурси
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}