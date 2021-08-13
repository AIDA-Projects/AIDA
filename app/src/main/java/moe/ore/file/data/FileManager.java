package moe.ore.file.data;

import java.io.File;

public class FileManager {
    private String nowPath = "/";
    private FileListener listener;

    private void open(String path) {
        if(listener != null) {
            listener.onChangePath(path);
        }
        File basePathFile = new File(path);
        this.nowPath = basePathFile.getAbsolutePath();
        if(basePathFile.exists() && basePathFile.canRead()) {
            if(basePathFile.isDirectory()) {
                File[] files = basePathFile.listFiles();
                if (files != null) {
                    if(listener != null) {
                        listener.onLoadFileList(files);
                    }
                }
            } else if(basePathFile.isFile()) {
                setNowPath(basePathFile.getParent());
            }
        } else {
            if(listener != null) {
                listener.onLoadFileList(new File[0]);
            }
        }
    }

    public void clickFile(File file) {
        if(listener != null) {
            listener.onClickFile(file);
        }
    }

    public void back() {
        String parent = new File(nowPath).getParent();
        if(parent != null) {
            setNowPath(parent);
        }
    }

    public void refresh() {
        setNowPath(nowPath);
    }

    public void setListener(FileListener listener) {
        this.listener = listener;
    }

    public String getNowPath() {
        return nowPath;
    }

    public void setNowPath(String nowPath) {
        open(nowPath);
    }
}
