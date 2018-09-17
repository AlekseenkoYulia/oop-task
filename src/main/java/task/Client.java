package task;

import javax.net.SocketFactory;
import java.io.*;
import java.net.*;
import java.util.List;

public class Client {
    private String host;
    private int port;
    private Protocol protocol;
    private MyReader reader;
    private Strategy strategy;

    public Client(String host, int port, Protocol protocol) {
        this.host = host;
        this.port = port;
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public Socket openSocket() throws IOException {
        SocketFactory factory = protocol.getSocketFactory();
        return factory.createSocket(host, port);
    }

    public void makeRequest(Request r) {
        try {
            Socket s = openSocket();
            OutputStream out = s.getOutputStream();
            DataInputStream is = new DataInputStream(s.getInputStream());
            out.write(r.request.getBytes(r.charset));
            reader = new MyReader(is);

            List<String> headers = reader.getHeaders();
            if (!(headers.get(0).equals("HTTP/1.1 200 OK"))) {
                System.out.println("Ошибка");
                return;
            }

            for (int i = 1; i < headers.size(); i++) {
                if (headers.get(i).contains("Transfer-Encoding: chunked")) {
                    strategy = new ChunkedStrategy(reader);
                    break;
                }
                if (headers.get(i).contains("Content-Length")) {
                    int size = Integer.parseInt(headers.get(i).substring(15));
                    strategy = new ContentLengthStrategy(reader, size);
                    break;
                }
            }

            r.headers = headers;
            r.content = strategy.getContent();

            s.close();
            out.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

