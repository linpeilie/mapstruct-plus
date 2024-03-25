package io.github.linpeilie.processor.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.util.Collection;

public class FileReader extends FileWrapper {
    private static final long serialVersionUID = 1L;

    public static FileReader create(File file, Charset charset){
        return new FileReader(file, charset);
    }

    // ------------------------------------------------------- Constructor start
    public FileReader(File file, Charset charset) {
        super(file, charset);
        checkFile();
    }

    // ------------------------------------------------------- Constructor end

    public <T extends Collection<String>> T readLines(T collection) {
        BufferedReader reader = null;
        try {
            reader = FileUtils.getReader(file, charset);
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                collection.add(line);
            }
            return collection;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (Exception e) {
                    // 静默关闭
                }
            }
        }
    }

    private void checkFile() {
        if (file == null) {
            throw new UncheckedIOException(new IOException("File to write content is null !"));
        }
        if (this.file.exists() && !file.isFile()) {
            throw new UncheckedIOException(
                new IOException("File [" + this.file.getAbsoluteFile() + "] is not a file !"));
        }
    }

}
