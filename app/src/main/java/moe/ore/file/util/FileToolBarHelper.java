package moe.ore.file.util;

import static moe.ore.file.util.FileSizeConverter.intSizeToSizeUnit;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import me.zhenxin.aida.R;

public class FileToolBarHelper {
    public static void setToolbarDirInfo(Toolbar toolbar, int dirNum, int fileNum, long useStorage, long totalStorage) {
        toolbar.setSubtitle(String.format("文件夹：%s  文件：%s  储存：%s/%s", dirNum, fileNum, intSizeToSizeUnit(useStorage), intSizeToSizeUnit(totalStorage)));
    }

    public static void setToolbarMoreIconCustomColor(Toolbar toolbar, int colorId) {
        if(toolbar == null) {
            return;
        }
        Drawable moreIcon = ContextCompat.getDrawable(toolbar.getContext(), R.drawable.abc_ic_menu_overflow_material);
        if(moreIcon != null) {
            moreIcon.setColorFilter(ContextCompat.getColor(toolbar.getContext(), colorId), PorterDuff.Mode.SRC_ATOP);
            toolbar.setOverflowIcon(moreIcon);
        }
    }
}
