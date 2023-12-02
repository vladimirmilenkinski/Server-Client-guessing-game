import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
//            syzdavane na klientski soket i vryzka sys syrvyrniq
            Socket clientSocket = new Socket("localhost", 1222);
            System.out.println("Клиента е свързан със сървъра");
//            syzd.na vh/izh potoci
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            boolean gameover = false;
            while (!gameover) {
                System.out.println("Въведи предположение за число: ");
                int guess = scanner.nextInt();
                out.writeInt(guess);
                out.flush();

                String response = in.readUTF();
                System.out.println(response);

                gameover = in.readBoolean();
            }
//            zatv. na potocite i soketa
            in.close();
            out.close();
            scanner.close();
            clientSocket.close();
            System.out.println("Играта приключи.Презареди за нова игра");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}





