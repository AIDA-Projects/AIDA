package moe.ore.file.data;

import java.io.File;

public interface FileListener {
    // void onOpenFinished(int dirNum, int fileNum);

    void onClickFile(File file);

    void onLoadFileList(File[] files);

    void onChangePath(String path);
}
