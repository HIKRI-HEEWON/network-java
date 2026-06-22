package network.tcp.v6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientV6 {
    public static final int PORT = 12345;

    public static void main(String[] args) {
        log("클라이언트 시작");

        try (Socket socket = new Socket("localhost", PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            log("소캣 연결: " + socket);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("전송 문자 : ");
                String toSend = scanner.nextLine(); // 블로킹

                output.writeUTF(toSend);
                log("Client -> Server : " + toSend);

                if (toSend.equals("exit")) {
                    break;
                }

                // 서버로 부터 문자 받기
                String received = input.readUTF();
                log("Client <- Server : " +  received);
            }

        } catch (IOException e) {
            log(e);
        }
    }
}
