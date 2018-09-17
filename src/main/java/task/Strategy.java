package task;

import java.io.IOException;

abstract public class Strategy {
    MyReader reader;

    public Strategy(MyReader reader) {
        this.reader = reader;
    }

    abstract byte[] getContent() throws IOException;
}
