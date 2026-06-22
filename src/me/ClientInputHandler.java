package me;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.close;
import static util.MyLogger.log;

public class ClientInputHandler implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private boolean closed = false;

    public ClientInputHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            log("ClientInputHandler started");
            while (true) {
                String received = input.readUTF();
                log("client <- server : " + received);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            log("ClientInputHandler stopped");
            if (closed) {
                return;
            }
            closed = true;
            close(input);
            close(socket);
        }
    }
}
