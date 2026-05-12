package io.github.linpeilie.processor.utils;

import io.github.linpeilie.processor.ContextConstants;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class IncrementMarkUtils {

    public static Integer incrementAndGet() {
        int mark = 0;
        RandomAccessFile randomAccessFile = null;
        FileChannel channel = null;
        FileLock lock = null;
        try {
            File file = FileUtils.touch(new File(ContextConstants.AutoIncrementFile.file));

            randomAccessFile = new RandomAccessFile(file, "rw");

            channel = randomAccessFile.getChannel();

            // 获取文件锁
            lock = channel.lock();

            String line = randomAccessFile.readLine();
            if (line != null && !line.isEmpty()) {
                try {
                    mark = Integer.parseInt(line);
                    mark ++;
                } catch (Exception e) {
                    if (!file.delete() && file.exists()) {
                        throw new UncheckedIOException(new IOException("Failed to delete invalid increment mark file."));
                    }
                    FileUtils.touch(file);
                    randomAccessFile.close();
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    channel.close();
                    channel = randomAccessFile.getChannel();
                    lock.release();
                    lock = channel.lock();
                    mark = 0;
                }
            }

            randomAccessFile.setLength(0);
            randomAccessFile.seek(0);
            randomAccessFile.writeBytes(String.valueOf(mark));

            return mark;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            if (lock != null && lock.isValid()) {
                try {
                    lock.release();
                } catch (IOException e) {
                    // ignore
                }
            }
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    // ignore
                }
            }
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

}
