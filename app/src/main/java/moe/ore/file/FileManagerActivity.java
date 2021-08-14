package moe.ore.file;

import static moe.ore.file.util.FileToolBarHelper.setToolbarMoreIconCustomColor;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import me.zhenxin.aida.R;
import moe.ore.file.data.FileListener;
import moe.ore.file.data.FileManager;
import moe.ore.file.data.FileObject;
import moe.ore.file.util.FileToolBarHelper;

public class FileManagerActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static final Comparator<FileObject> comparator = (file1, file2) -> {
        if (file1.isDir() && file2.isFile() ){
            return -1;
        } else if ( file1.isFile() && file2.isDir() ){
            return 1;
        } else {
            return file1.getName().compareTo( file2.getName() ) ;
        }
    };

    private ListView mListView;
    private Toolbar mToolbar;

    private final FileManager fileManager = new FileManager();
    private FileObjectAdapter adapter;

    /**
     * 中央接口
     */
    private FileChooseInterface fileChooseInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_choose);
        mToolbar = findViewById(R.id.fc_toolbar);
        setToolbarMoreIconCustomColor(mToolbar, R.color.xui_config_color_white); // 设置toolbar更多按钮颜色
        setSupportActionBar(mToolbar);
        this.mListView = findViewById(R.id.fc_file_list);

        init();
    }

    private void init() {
        fileManager.setListener(new FileListener() {
            final ArrayList<FileObject> fileObjects = new ArrayList<>();
            
            @Override
            public void onClickFile(File file) {
                if(fileChooseInterface != null) {
                    fileChooseInterface.onClickFile(file);
                }
            }

            @Override
            public void onLoadFileList(File[] files) {
                fileObjects.clear();
                int dirNum = 0;
                int fileNum = 0;
                for (File file : files) {
                    if(file.isDirectory()) {
                        dirNum++;
                    } else if (file.isFile()) {
                        fileNum++;
                    }
                    fileObjects.add(new FileObject(file));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        fileObjects.sort(comparator);
                } else {
                        Collections.sort(fileObjects, comparator);
                }
                FileToolBarHelper.setToolbarDirInfo(mToolbar, dirNum, fileNum, 0, 0);
                if(adapter == null) {
                    adapter = new FileObjectAdapter(FileManagerActivity.this, fileObjects, fileManager);
                    mListView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChangePath(String path) {
                mToolbar.setTitle(path);
            }

        });
        findViewById(R.id.fc_file_item).setOnClickListener(v -> fileManager.back());
        fileManager.setNowPath(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    @Override
    @SuppressLint("RestrictedApi")
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh) { // 刷新
            fileManager.refresh();
        }
        return super.onOptionsItemSelected(item);
    }

    public FileChooseInterface getFileChooseInterface() {
        return fileChooseInterface;
    }

    public void setFileChooseInterface(FileChooseInterface fileChooseInterface) {
        this.fileChooseInterface = fileChooseInterface;
    }
}
