package me;

import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class Client {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", PORT);
        log("소켓 연결 : " + socket);

        try {
            ClientInputHandler inputHandler = new ClientInputHandler(socket);
            ClientOutputHandler outputHandler = new ClientOutputHandler(socket);
            Thread inputThread = new Thread(inputHandler);
            Thread outputThread = new Thread(outputHandler);
            inputThread.start();
            outputThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }

}
