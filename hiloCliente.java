import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class hiloCliente extends Thread {
    private Socket socketCliente;

    public hiloCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    public void run() {
        try {
            // Crear buffer para recibir y enviar datos al cliente
            String datosRecibidos;
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);

            while (true) {
                datosRecibidos = entrada.readLine();
                System.out.println("Mensaje recibido: " + datosRecibidos);

                // Verificar si el mensaje es "chao" para cerrar el hilo
                if (datosRecibidos.equals("chao")) {
                    System.out.println("El cliente ha cerrado la conexión.");
                    break;
                }

                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingrese el mensaje para el cliente: ");
                // Enviar datos al cliente
                String mensaje = scanner.nextLine();
                salida.println(mensaje);
            }

            // Cerrar la conexión y el hilo
            socketCliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
