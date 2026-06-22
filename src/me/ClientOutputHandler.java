package me;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.close;
import static util.MyLogger.log;

public class ClientOutputHandler implements Runnable {

    private final Socket socket;
    private final DataOutputStream output;
    private boolean closed = false;

    public ClientOutputHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            String clientName = scanner.nextLine();
            output.writeUTF(clientName); // 사용자 입장 최초?!
            while (true) {
                System.out.println("message | ");
                String toSend = scanner.nextLine();
                log("client -> server " + toSend);
                // change name
                if (toSend.equals("change")) {
                    System.out.println("change name | ");
                    clientName = scanner.nextLine();
                    output.writeUTF("/change" +  clientName);
                }
                // exit
                if (toSend.equals("exit")) {
                    break;
                }
                // send message
                output.writeUTF(toSend);
            }
        } catch (Exception e) {
            log(e);
        } finally {
            if (closed) {return;}
            closed = true;
            close(output);
            close(socket);
        }
    }
}
