package io.github.linpeilie.processor.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileUtils {

    public static <T> File writeUtf8Lines(Collection<T> list, File file) {
        return writeLines(list, file, StandardCharsets.UTF_8);
    }

    public static <T> File writeLines(Collection<T> list, File file, Charset charset) {
        return writeLines(list, file, charset, false);
    }

    public static <T> File writeLines(Collection<T> list, File file, Charset charset, boolean isAppend) {
        return FileWriter.create(file, charset).writeLines(list, isAppend);
    }

    public static List<String> readUtf8Lines(File file) {
        return readLines(file, StandardCharsets.UTF_8);
    }

    public static List<String> readLines(File file, Charset charset) {
        return readLines(file, charset, new ArrayList<>());
    }

    public static <T extends Collection<String>> T readLines(File file, Charset charset, T collection) {
        return FileReader.create(file, charset).readLines(collection);
    }

    public static BufferedInputStream getInputStream(File file) throws IOException {
        return new BufferedInputStream(Files.newInputStream(file.toPath()));
    }

    public static BufferedReader getReader(File file, Charset charset) throws IOException {
        BufferedInputStream in = getInputStream(file);
        InputStreamReader reader;
        if (null == charset) {
            reader = new InputStreamReader(in);
        } else {
            reader = new InputStreamReader(in, charset);
        }

        return new BufferedReader(reader);
    }

    public static File mkParentDirs(File file) {
        if (null == file) {
            return null;
        }
        return mkdir(getParent(file, 1));
    }

    public static File getParent(File file, int level) {
        if (level < 1 || null == file) {
            return file;
        }

        File parentFile;
        try {
            parentFile = file.getCanonicalFile().getParentFile();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        if (1 == level) {
            return parentFile;
        }
        return getParent(parentFile, level - 1);
    }

    public static File mkdir(File dir) {
        if (dir == null) {
            return null;
        }
        if (false == dir.exists()) {
            mkdirsSafely(dir, 5, 1);
        }
        return dir;
    }

    public static boolean mkdirsSafely(File dir, int tryCount, long sleepMillis) {
        if (dir == null) {
            return false;
        }
        if (dir.isDirectory()) {
            return true;
        }
        for (int i = 1; i <= tryCount; i++) { // 高并发场景下，可以看到 i 处于 1 ~ 3 之间
            // 如果文件已存在，也会返回 false，所以该值不能作为是否能创建的依据，因此不对其进行处理
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
            if (dir.exists()) {
                return true;
            }
            ThreadUtils.sleep(sleepMillis);
        }
        return dir.exists();
    }

    public static File touch(File file) {
        if (null == file) {
            return null;
        }
        if (false == file.exists()) {
            mkParentDirs(file);
            try {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            } catch (Exception e) {
                throw new UncheckedIOException(new IOException(e));
            }
        }
        return file;
    }

    public static boolean isNotEmpty(File file) {
        return !isEmpty(file);
    }

    public static boolean isEmpty(File file) {
        if (null == file || !file.exists()) {
            return true;
        }

        if (file.isDirectory()) {
            String[] subFiles = file.list();
            return subFiles == null || subFiles.length == 0;
        } else if (file.isFile()) {
            return file.length() <= 0;
        }

        return false;
    }
}
