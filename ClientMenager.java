import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//klas koito vkl "runnable" za upravlenie na klientite
public final class ClientMenager implements Runnable {
private final Socket clientSocket;
private final int number;
private final String name;
//konstruktor
    public ClientMenager(Socket clientSocket, int number, String name){
        this.clientSocket = clientSocket;
        this.number = number;
        this.name = name;
    }
    @Override
    public void run() {
//        startira kom.s klienta
        try{
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            boolean gameover = false;
            while(!gameover){
                int guess = in.readInt();
                System.out.println("Клиент" + name + "предположи: "+ guess);
                if(guess == number){
                    out.writeUTF(name +"позна! Числото е "+ number);
                    out.writeBoolean(true);
                    gameover = true;
                } else if (guess < number) {
                    out.writeUTF("Малко е, опитай с по-голямо.");
                    out.writeBoolean(false);
                }
            }
            in.close();
            out.close();
            clientSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
