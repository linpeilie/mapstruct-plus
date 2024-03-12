package io.github.linpeilie.processor.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

public class FileWriter extends FileWrapper {

    private static final long serialVersionUID = 1L;

    public static FileWriter create(File file, Charset charset) {
        return new FileWriter(file, charset);
    }

    // ------------------------------------------------------- Constructor start

    public FileWriter(File file, Charset charset) {
        super(file, charset);
        checkFile();
    }

    // ------------------------------------------------------- Constructor end

    public <T> File writeLines(Iterable<T> list, boolean isAppend) {
        return writeLines(list, null, isAppend);
    }

    public BufferedWriter getWriter(boolean isAppend) {
        try {
            return new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(FileUtils.touch(file), isAppend), charset));
        } catch (Exception e) {
            throw new UncheckedIOException(new IOException(e));
        }
    }

    public PrintWriter getPrintWriter(boolean isAppend) {
        return new PrintWriter(getWriter(isAppend));
    }

    public <T> File writeLines(Iterable<T> list, LineSeparator lineSeparator, boolean isAppend) {
        try (PrintWriter writer = getPrintWriter(isAppend)) {
            boolean isFirst = true;
            for (T t : list) {
                if (null != t) {
                    if (isFirst) {
                        isFirst = false;
                        if (isAppend && FileUtils.isNotEmpty(this.file)) {
                            // 追加模式下且文件非空，补充换行符
                            printNewLine(writer, lineSeparator);
                        }
                    } else {
                        printNewLine(writer, lineSeparator);
                    }
                    writer.print(t);

                    writer.flush();
                }
            }
        }
        return this.file;
    }

    private void printNewLine(PrintWriter writer, LineSeparator lineSeparator) {
        if (null == lineSeparator) {
            //默认换行符
            writer.println();
        } else {
            //自定义换行符
            writer.print(lineSeparator.getValue());
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
