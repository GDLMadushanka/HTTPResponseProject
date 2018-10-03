import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This server will start on any given port and respond with any given content.
 */
public class SampleServer {

    private static int PORT = 6000;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.err.println("Server started on given port : " + PORT);

            // repeatedly wait for connections, and process
            while (true) {

                clientSocket = serverSocket.accept();
                System.err.println("New Client connected");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String s;
                while ((s = in.readLine()) != null) {
                    System.out.println(s);
                    if (s.isEmpty()) {
                        break;
                    }
                }

                out.write("HTTP/1.0 500 Custom 500 ERROR messsage\r\n");
                out.write("Cache-Control: no-cache\r\n");
                out.write("Server: Microsoft-IIS/8.5\r\n");
                out.write("X-AspNet-Version: 4.0.30319\r\n");
                out.write("X-Powered-By: ASP.NET\r\n");
                out.write("Date: Sat, 15 Sep 2018 16:15:36 GMT\r\n");
                out.write("Content-Length: 0\r\n");
                out.write("\r\n");
                out.close();
                in.close();
                clientSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        }
    }
}
