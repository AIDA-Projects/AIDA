package me.zhenxin.aida.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xuexiang.xui.utils.SnackbarUtils;
import com.xuexiang.xui.widget.searchview.MaterialSearchView;
import com.xuexiang.xutil.common.StringUtils;

import java.util.ArrayList;
import java.util.List;

import me.zhenxin.aida.R;
import me.zhenxin.aida.adapter.SimpleAdapter;
import me.zhenxin.aida.databinding.ActivityChooseApkBinding;
import me.zhenxin.aida.entity.SimpleItemEntity;
import me.zhenxin.aida.utils.AppUtils;
import me.zhenxin.aida.utils.GlobalConfig;

public class ChooseApkActivity extends AppCompatActivity {

    private ActivityChooseApkBinding binding;

    private ListView chooseApkListView;

    /**
     * 数据
     */
    private List<SimpleItemEntity> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseApkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
        initViews();
        loadListView();
    }



    private void loadData() {
        data = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> appInfoList = AppUtils.getAppInfoList(packageManager);
        for (PackageInfo packageInfo : appInfoList) {
            SimpleItemEntity entity = new SimpleItemEntity();
            entity.setSimpleItemImg(packageInfo.applicationInfo.loadIcon(packageManager));
            entity.setSimpleItemTitle(packageInfo.applicationInfo.loadLabel(packageManager).toString());
            entity.setSimpleItemSummary(packageInfo.packageName);
            data.add(entity);
        }
    }

    private void initViews() {
        // 设置列表视图文本过滤器
        binding.listViewChooseApk.setTextFilterEnabled(true);

        // 设置菜单
        Toolbar toolbar = findViewById(R.id.tool_bar_choose_apk);
        setSupportActionBar(toolbar);

        // 搜索
        binding.searchViewChooseApk.setVoiceSearch(false);
        binding.searchViewChooseApk.setEllipsize(true);
        binding.searchViewChooseApk.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SnackbarUtils.Long(binding.searchViewChooseApk, "Query: " + query).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (StringUtils.isEmpty(newText)) {
                    binding.listViewChooseApk.clearTextFilter();
                } else {
                    binding.listViewChooseApk.setFilterText(newText);
                }
                return true;
            }
        });
        binding.searchViewChooseApk.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
        binding.searchViewChooseApk.setSubmitOnClick(true);
        chooseApkListView = binding.listViewChooseApk;

        // 设置APK列表点击事件
        chooseApkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleItemEntity simpleItemEntity = data.get(position);
                String title = simpleItemEntity.getSimpleItemTitle();
                String summary = simpleItemEntity.getSimpleItemSummary();
                Toast.makeText(ChooseApkActivity.this, title + "  " + summary, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadListView() {
        loadData();
        SimpleAdapter simpleAdapter = new SimpleAdapter(data, this, new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                Toast.makeText(ChooseApkActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });
        chooseApkListView.setAdapter(simpleAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_choose_apk, menu);

        // 搜索菜单
        MenuItem menuItemSearchChooseApk = menu.findItem(R.id.menu_search_choose_apk);
        binding.searchViewChooseApk.setMenuItem(menuItemSearchChooseApk);

        // 过滤菜单-复选框


        return super.onCreateOptionsMenu(menu);
    }

    private void filterMenuEvent(MenuItem item) {
        boolean isChecked = !item.isChecked();
        item.setChecked(isChecked);
        switch (item.getItemId()) {
            case R.id.filter_menu_system_app:
                GlobalConfig.MenuListFilterCheck.systemAppChecked = isChecked;
                break;
        }
        loadListView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getGroupId()) {
            case R.id.group_list_filter:
                filterMenuEvent(item);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (binding.searchViewChooseApk.isSearchOpen()) {
            binding.searchViewChooseApk.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    binding.searchViewChooseApk.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}