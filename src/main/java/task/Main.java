package task;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String host = "bash.im";
        int port = 443;
        Client c = new Client(host, port, Protocol.HTTPS);
        Request r = new Request(c);
        r.makeBashRequest(452072);
        System.out.println(r.findBashQuote());

        Scanner in = new Scanner(System.in);
        while (true) {
            r.makeBashRequest(in.nextInt());
            System.out.println(r.findBashQuote());
        }
    }
}
