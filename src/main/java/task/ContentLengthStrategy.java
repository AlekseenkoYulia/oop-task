package task;

import java.io.IOException;

public class ContentLengthStrategy extends Strategy {
    int size;

    public ContentLengthStrategy(MyReader reader, int size) {
        super(reader);
        this.size = size;
    }

    public byte[] getContent() throws IOException {
        return reader.getContent(size);
    }

}
