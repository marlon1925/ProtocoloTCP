import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class hiloCliente extends Thread {
    private Socket socket_cliente;

    public hiloCliente(Socket socket_cliente) {
        this.socket_cliente = socket_cliente;
    }

    public void run() {
        try {
            // Crear buffer para recibir y enviar datos al cliente
            String datos_recibidos;
            while (true) {

                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket_cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socket_cliente.getOutputStream(), true);

                datos_recibidos = entrada.readLine();
                System.out.println("Mensaje recibido: " + datos_recibidos);
                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingrese el mensaje para el cliente: ");
                // Enviar datos al cliente
                String mensaje = scanner.nextLine();
                salida.println(mensaje);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
