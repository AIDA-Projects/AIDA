package me.zhenxin.aida.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xuexiang.xui.widget.edittext.ClearEditText;

import butterknife.BindView;
import me.zhenxin.aida.R;

public class CreateProjectActivity extends AppCompatActivity {

    @BindView(R.id.input_create_project_name)
    ClearEditText inputCreateProjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
    }
}