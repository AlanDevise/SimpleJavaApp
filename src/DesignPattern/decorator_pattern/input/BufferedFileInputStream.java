package DesignPattern.decorator_pattern.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Filename: BufferedFileInputStream.java
 * @Package: DesignPattern.decorator_pattern.input
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2025年08月03日 15:51
 */

public class BufferedFileInputStream extends InputStream {

    /**
     * 缓冲区
     * <p>
     * 为什么大小是8192？<p/>
     * 1. 8192是2的13次方，是8KB<p/>
     * 2. 8KB是一个比较大的缓存大小，能够减少IO次数
     */
    private final byte[] buffer = new byte[8192];

    /**
     * 标记读到哪儿
     */
    private int position = -1;

    /**
     * 标记缓存的有效读取长度
     */
    private int capacity = -1;

    private final FileInputStream fileInputStream;

    public BufferedFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    @Override
    public int read() throws IOException {
        if (buffCanRead()) {
            return readFromBuffer();
        }
        refreshBuffer();
        if (!buffCanRead()) {
            return -1;
        }
        return readFromBuffer();
    }

    private int readFromBuffer() {
        /**
         * 1. 为什么要&0xFF？<p/>
         * 2. 因为byte是8位的，而int是32位的，所以需要将byte的高24位都置为0
         */
        return buffer[position++] & 0xFF;
    }

    private void refreshBuffer() throws IOException {
        capacity = this.fileInputStream.read(buffer);
        position = 0;
    }

    private boolean buffCanRead() {
        if (capacity == -1) {
            return false;
        }
        if (position == capacity) {
            return false;
        }
        return true;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
