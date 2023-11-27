import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {
    public static void main(String[] args) {
        // crear socket del servidor
        try {
            ServerSocket socket_servidor = new ServerSocket(3000);
            System.out.println("Esperando conexiones...");
            // Esperar y aceptar
            while (true) {
                Socket socket_cliente = socket_servidor.accept();
                System.out.println("Cliente conectado: " + socket_cliente.getInetAddress().getHostAddress());
                // Crear un hilo par amanejar la conexion con el cliente
                hiloCliente hilo = new hiloCliente(socket_cliente);
                hilo.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}