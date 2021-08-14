package me.zhenxin.aida.activity;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.tabbar.EasyIndicator;

import java.util.HashMap;
import java.util.Map;

import me.zhenxin.aida.R;
import me.zhenxin.aida.base.BaseFragment;
import me.zhenxin.aida.databinding.FragmentChooseApkBinding;
import me.zhenxin.aida.enums.page.EnumChooseApkType;

@Page(name = "选择APK文件作为工程")
public class ChooseApkFragment extends BaseFragment {

    /**
     * 选择APK文件试图
     */
    private FragmentChooseApkBinding binding;
    
    private EasyIndicator easyIndicator;
    
    private ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_apk;
    }

    @Override
    protected void initViews() {
        binding = FragmentChooseApkBinding.inflate(getLayoutInflater());
        easyIndicator = binding.easyIndicatorChooseApkType;
        viewPager = binding.viewPagerChooseApk;

        easyIndicator.setTabTitles(EnumChooseApkType.getPageNames());
        easyIndicator.setViewPager(viewPager, mPagerAdapter);
        viewPager.setOffscreenPageLimit(EnumChooseApkType.size() - 1);
        viewPager.setCurrentItem(0);
    }

    private Map<EnumChooseApkType, View> mPageMap = new HashMap<>();

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return EnumChooseApkType.size();
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            EnumChooseApkType page = EnumChooseApkType.getPage(position);
            View view = getPageView(page);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view, params);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    };

    private View getPageView(EnumChooseApkType page) {
        View view = mPageMap.get(page);
        if (view == null) {
            TextView textView = new TextView(getContext());
            textView.setTextAppearance(getContext(), R.style.TextStyle_Content_Match);
            textView.setGravity(Gravity.CENTER);
            textView.setText(String.format("这个是%s页面的内容", page.name()));
            view = textView;
            mPageMap.put(page, view);
        }
        return view;
    }

}