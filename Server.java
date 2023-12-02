import java.io.IOException;
import java.net.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    public static void main(String[] args)throws IOException {
        final Random random = new Random();
        int number = random.nextInt(10)+1;

        final ServerSocket serverSocket = new ServerSocket(1222);
        System.out.println("Сървъра работи и очаква клиенти...  ");
//        tread pool menajirane na nqkolko klienta

        final ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i=0; i<3;  i++) {
            Socket clientSocket = serverSocket.accept();

            String name = "Клиент" + (i+1);//dava ime na klient
            System.out.println(name + " е свързан:" + clientSocket.getInetAddress());
            executor.execute(new ClientMenager(clientSocket, number,name ));
        }
//        spirane na baseina i syrvyra
        executor.shutdown();
        serverSocket.close();
    }
}


