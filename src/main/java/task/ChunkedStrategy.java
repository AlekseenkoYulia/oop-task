package task;

import java.io.IOException;
import java.util.ArrayList;

public class ChunkedStrategy extends Strategy {
    public ChunkedStrategy(MyReader reader) {
        super(reader);
    }

    public byte[] getContent() throws IOException {
        int size = reader.getSize();
        ArrayList<Byte> content = new ArrayList<Byte>();

        while (size > 0) {
            for (byte b : reader.getContent(size)) {
                content.add(b);
            }
            size = reader.getSize();
        }

        byte[] buffer = new byte[content.size()];
        for (int i = 0; i < content.size(); i++) {
            buffer[i] = content.get(i);
        }

        return buffer;
    }
}
