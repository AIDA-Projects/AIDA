package moe.ore.file.util;

public class FileSizeConverter {
    static final long MAX_KB_SIZE = 1024;
    static final long MAX_MB_SIZE = MAX_KB_SIZE * 1024;
    static final long MAX_GB_SIZE = MAX_MB_SIZE * 1024;
    static final long MAX_TB_SIZE = MAX_GB_SIZE * 1024;

    public static String intSizeToSizeUnit(long size) {
        if(size >= MAX_TB_SIZE) {
            return ((size / MAX_TB_SIZE) * 1.00d) + "TB";
        } else if(size >= MAX_GB_SIZE) {
            return ((size / MAX_GB_SIZE) * 1.00d) + "GB";
        } else if(size >= MAX_MB_SIZE) {
            return ((size / MAX_MB_SIZE) * 1.00d) + "MB";
        } else if(size >= MAX_KB_SIZE) {
            return ((size / MAX_KB_SIZE) * 1.00d) + "KB";
        }
        return size + "B";
    }
}
