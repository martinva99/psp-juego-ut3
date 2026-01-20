package juegoParejas;
import java.io.*;
import java.net.*;
class HandlerParejas implements Runnable {
    private final Socket socket;

    public HandlerParejas(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

        } catch (IOException e) {
            System.out.println("Cliente desconectado");
        } finally {
            System.out.println("Cliente finalizado");
        }
    }
}
