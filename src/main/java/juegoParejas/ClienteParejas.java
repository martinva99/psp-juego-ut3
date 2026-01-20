package juegoParejas;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteParejas {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 54321;

        try (Socket socket = new Socket(host, puerto);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

        } catch (ConnectException e) {
            System.out.println("Error: No se pudo conectar al servidor.");
        } catch (IOException e) {
            System.out.println("Error de comunicación.");
        }
    }

}


