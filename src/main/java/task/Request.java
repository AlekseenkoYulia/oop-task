package task;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Request {
    private Client client;
    String request;
    List<String> headers;
    byte[] content;
    String charset;

    Request(Client c) {
        this.client = c;
    }

    public void makeBashRequest(int n) {
        charset = "windows-1251";
        request = "GET /quote/" + n + " HTTP/1.1\nHost:" + client.getHost() + "\n\n";
        client.makeRequest(this);
    }

    public String findBashQuote() throws UnsupportedEncodingException {
        String answer = new String(content, charset);
        String start = "<div class=\"text\">";
        int startIndex = answer.indexOf(start) + start.length();
        int endIndex = answer.indexOf("</div>", startIndex);
        String quote = answer.substring(startIndex, endIndex);
        return quote;
    }
}
