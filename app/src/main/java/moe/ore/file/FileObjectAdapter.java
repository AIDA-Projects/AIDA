package moe.ore.file;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.io.File;
import java.util.List;

import me.zhenxin.aida.R;
import moe.ore.file.data.FileManager;
import moe.ore.file.data.FileObject;

public class FileObjectAdapter extends ArrayAdapter<FileObject> {
    private final FileManager manager;
    private final int resourceId;

    public FileObjectAdapter(@NonNull Context context, @NonNull List<FileObject> objects, FileManager manager) {
        super(context, R.layout.list_sub_view, objects);
        resourceId = R.layout.list_sub_view;
        this.manager = manager;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FileObject fileObject = getItem(position);

        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.fileIcon = view.findViewById(R.id.fc_file_icon);
            viewHolder.fileEditTime = view.findViewById(R.id.fc_file_edit_time);
            viewHolder.fileName = view.findViewById(R.id.fc_file_name);
            viewHolder.fileSize = view.findViewById(R.id.fc_file_unit);
            viewHolder.fileIconBackground = view.findViewById(R.id.fc_file_icon_background);

            view.findViewById(R.id.fc_file_item).setOnClickListener(v -> {
                String fileName = manager.getNowPath() + "/" + viewHolder.fileName.getText();
                if(fileObject.isDir()) {
                    manager.setNowPath(fileName);
                } else {
                    manager.clickFile(new File(fileName));
                }
            });

            view.setTag(viewHolder);
        } else{
            view = convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.fileName.setText(fileObject.getName());
        if(fileObject.isFile()) {
            switch (fileObject.getType()) {
                case LUA:
                    viewHolder.fileIconBackground.setCardBackgroundColor(Color.parseColor("#3960af"));
                    viewHolder.fileIcon.setImageDrawable(getContext().getDrawable(R.drawable.ic_baseline_script));
                    break;
                case TXT:
                    viewHolder.fileIconBackground.setCardBackgroundColor(Color.parseColor("#3960af"));
                    viewHolder.fileIcon.setImageDrawable(getContext().getDrawable(R.drawable.ic_baseline_txt));
                    break;
                case OTHER:
                    viewHolder.fileIconBackground.setCardBackgroundColor(Color.parseColor("#607d8b"));
                    viewHolder.fileIcon.setImageDrawable(getContext().getDrawable(R.drawable.ic_baseline_file));
                    break;
            }
            viewHolder.fileSize.setVisibility(View.VISIBLE);
            viewHolder.fileSize.setText(fileObject.getSizeUnit());

            viewHolder.fileEditTime.setVisibility(View.VISIBLE);
            viewHolder.fileEditTime.setText(fileObject.getLastModified());
        } else if(fileObject.isDir()) {
            viewHolder.fileIcon.setImageDrawable(getContext().getDrawable(R.drawable.ic_baseline_folder));
            viewHolder.fileIconBackground.setCardBackgroundColor(Color.BLACK);

            viewHolder.fileEditTime.setVisibility(View.VISIBLE);
            viewHolder.fileEditTime.setText(fileObject.getLastModified());
        }

        return view;
    }

    static class ViewHolder {
        CardView fileIconBackground;

        ImageView fileIcon;

        TextView fileName;

        TextView fileEditTime;

        TextView fileSize;
    }
}
