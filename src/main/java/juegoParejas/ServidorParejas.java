package juegoParejas;
import java.io.*;
import java.net.*;

public class ServidorParejas {
    private static final int PUERTO = 54321;
    private static volatile boolean activo = true;

    public static void main(String[] args) throws IOException {
        //1. Hook de apagado para cerrar con ctrl+c
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n ---");
            activo = false;
        }));

        try(ServerSocket srv = new ServerSocket(PUERTO)){
            srv.setSoTimeout(2000);
            System.out.println("--- Inicializando servidor juego parejas (Multihilo) ---");
            System.out.println("Prueba a conectar varios clientes a la vez.");
            while(activo){
                try{
                    //2.Aceptar Cliente
                    Socket cliente = srv.accept();
                    System.out.println("Nuevo cliente conectado: "+cliente.getInetAddress());
                    //3.Delegar
                    new Thread(new HandlerParejas(cliente)).start();
                } catch (IOException e) {
                    System.out.print(".");
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: "+ e.getMessage());
        }
        System.out.println("Servidor detenido y puerto liberado.");

    }


}
