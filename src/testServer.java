import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class testServer {
    public static void main(String[] args) throws Exception {

        int port = 1989;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Serveur lancé sur le port : " + port);

        // repeatedly wait for connections, and process
        while (true) {
            // on reste bloqué sur l'attente d'une demande client
            Socket clientSocket = serverSocket.accept();
            System.err.println("Nouveau client connecté");

            // on ouvre un flux de converation

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                if (s.isEmpty()) {
                    break;
                }
            }


            out.write("HTTP/1.0 500 Something went wrong. Contact Tranman support, log reference SCZV1\r\n");
            out.write("HTTP/1.0 500 Something went wrong. Contact Tranman support, log reference SCZV1\r\n");
            out.write("Cache-Control: no-cache\r\n");
            out.write("Pragma: no-cache\r\n");
            out.write("Expires: -1\r\n");
            out.write("Server: Microsoft-IIS/8.5\r\n");
            out.write("X-AspNet-Version: 4.0.30319\r\n");
            out.write("Persistent-Auth: true\r\n");
            out.write("X-Powered-By: ASP.NET\r\n");
            out.write("Date: Sat, 15 Sep 2018 16:15:36 GMT\r\n");
            out.write("Content-Length: 0\r\n");
            out.write("\r\n");

            System.err.println("Connexion avec le client terminée");
            out.close();
            in.close();
            clientSocket.close();
        }
    }
}


