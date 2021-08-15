package me.zhenxin.aida.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.github.clans.fab.FloatingActionMenu;
import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xpage.core.CoreSwitchBean;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.SnackbarUtils;
import com.xuexiang.xui.widget.searchview.MaterialSearchView;
import com.xuexiang.xutil.app.ActivityUtils;
import com.xuexiang.xutil.common.StringUtils;

import java.util.ArrayList;
import java.util.List;

import me.zhenxin.aida.R;
import me.zhenxin.aida.adapter.HistoryProjectAdapter;
import me.zhenxin.aida.databinding.ActivityHistoryProjectBinding;
import me.zhenxin.aida.databinding.MenuProjectManagerFabBinding;
import me.zhenxin.aida.entity.HistoryProjectEntity;

public class HistoryProjectActivity extends BaseActivity {

    /**
     * 历史项目视图布局
     */
    private ActivityHistoryProjectBinding binding;

    /**
     * 项目管理菜单
     */
    private FloatingActionMenu projectManagerMenu;


    /**
     * 历史项目数据
     */
    private List<HistoryProjectEntity> historyProjectEntities = null;
    /**
     * 历史项目适配器
     */
    private HistoryProjectAdapter historyProjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryProjectBinding.inflate(getLayoutInflater());
        projectManagerMenu = binding.menuProjectManagerFab.projectManagerFabMenu;
        setContentView(binding.getRoot());
        initViews();
        initData();
        loadListView();
        initEvent();
        MenuProjectManagerFabBinding pm = binding.menuProjectManagerFab;
        ArrayList<View> views = new ArrayList<>();
        views.add(pm.pmFabCreateProject);
        views.add(pm.pmFabImport);
        views.add(pm.pmFabFile);
        views.add(pm.pmFabDir);
        setOnClick(views, this::onClickEvent);
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        // 设置列表视图文本过滤器
        binding.historyProjectListView.setTextFilterEnabled(true);

        // 设置菜单
        //historyProjectToolBar.inflateMenu(R.menu.menu_history_project);
        Toolbar toolbar = findViewById(R.id.history_project_tool_bar);
        setSupportActionBar(toolbar);

        // 搜索
        binding.historyProjectSearchView.setVoiceSearch(false);
        binding.historyProjectSearchView.setEllipsize(true);
        // historyProjectSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        binding.historyProjectSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SnackbarUtils.Long(binding.historyProjectSearchView, "Query: " + query).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (StringUtils.isEmpty(newText)) {
                    binding.historyProjectListView.clearTextFilter();
                } else {
                    binding.historyProjectListView.setFilterText(newText);
                }
                return true;
            }
        });
        binding.historyProjectSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
        binding.historyProjectSearchView.setSubmitOnClick(true);
    }

    /**
     * +
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 加载列表视图组件
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private void loadListView() {
        HistoryProjectEntity t1 = new HistoryProjectEntity();
        t1.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        t1.setName("测试项目工程文件测试项目工程文件测试项目工程文件");
        t1.setPkg("com.test.demo");
        t1.setId(1L);
        t1.setLastTime("2021-8-10 14:09:52");
        HistoryProjectEntity t2 = new HistoryProjectEntity();
        t2.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        t2.setName("测试项目工程文件2");
        t2.setPkg("com.test.demo2");
        t2.setId(2L);
        t2.setLastTime("2021-8-11 14:09:52");
        HistoryProjectEntity t3 = new HistoryProjectEntity();
        t3.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        t3.setName("破解RE文件管理器");
        t3.setPkg("com.test.re");
        t3.setId(3L);
        t3.setLastTime("2021-8-11 14:09:52");
        historyProjectEntities = new ArrayList<>();
        historyProjectEntities.add(t1);
        historyProjectEntities.add(t2);
        historyProjectEntities.add(t3);
        historyProjectAdapter = new HistoryProjectAdapter(historyProjectEntities, this, new HistoryProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                Toast.makeText(HistoryProjectActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });
        // 设置适配器
        binding.historyProjectListView.setAdapter(historyProjectAdapter);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        binding.historyProjectToolBar.setOnMenuItemClickListener(menuItemClickListener);
    }

    Toolbar.OnMenuItemClickListener menuItemClickListener = item -> {
        Toast.makeText(HistoryProjectActivity.this, "点击了:" + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.create_project:
                //点击设置
                Toast.makeText(HistoryProjectActivity.this, "创建项目", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        binding.historyProjectSearchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.historyProjectSearchView.isSearchOpen()) {
            binding.historyProjectSearchView.closeSearch();
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
                    binding.historyProjectSearchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.pm_fab_create_project:
                ActivityUtils.startActivity(ChooseApkActivity.class);
                break;
            case R.id.pm_fab_import:
                break;
            case R.id.pm_fab_file:
                break;
            case R.id.pm_fab_dir:
                break;
        }
        projectManagerMenu.toggle(false);
    }
}