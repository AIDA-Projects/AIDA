package moe.ore.file.data;

import android.icu.text.SimpleDateFormat;

import java.io.File;
import java.util.Date;
import java.util.Locale;

import moe.ore.manager.helper.FileSizeConverter;

public class FileObject {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private final File file;

    public FileObject(File file) {
        this.file = file;
    }

    public String getName() {
        return file.getName();
    }

    public boolean isDir() {
        return file.isDirectory();
    }

    public boolean isFile() {
        return file.isFile();
    }

    public File getFile() {
        return file;
    }

    public FileType getType() {
        String name = getName();
        if(name.endsWith(".txt")) {
            return FileType.TXT;
        } else if (name.endsWith(".lua")) {
            return FileType.LUA;
        }

        return FileType.OTHER;
    }

    public String getLastModified() {
        long last = file.lastModified();
        Date date = new Date(last);
        return format.format(date);
    }

    public String getSizeUnit() {
        return FileSizeConverter.intSizeToSizeUnit(file.length());
    }
}
