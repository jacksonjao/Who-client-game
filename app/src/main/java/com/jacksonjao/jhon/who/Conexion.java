package com.jacksonjao.jhon.who;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by Jhon on 6/05/16.
 */
public class Conexion extends Observable implements Runnable {

    public static Conexion ref;
    private Socket socket;
    private String ip;

    private Conexion() {
        socket = null;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (socket == null) {
                    socket = new Socket(InetAddress.getByName(ip), 8080);
                    System.out.println("inicia socket");
                } else {
                    setChanged();
                    notifyObservers(recibir());
                    Thread.sleep(42);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enviar(Object obj) throws IOException {
        if (socket != null) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(obj);
            oos.flush();
            System.out.println("Envía");
        }
    }

    
    public Object recibir() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Object object = ois.readObject();
        return object;
    }

    public static Conexion getInstance() {
        if (ref == null) {
            ref = new Conexion();
            Thread hilo = new Thread(ref);
            hilo.start();
        }
        return ref;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}