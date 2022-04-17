package com.zhr.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Android 中Fragment + ViewPager 的使用
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.vp1)
    ViewPager mVp1;
    @BindView(R.id.vp2)
    ViewPager2 mVp2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<String> data = new ArrayList<>(2);
        data.add("左1");
        data.add("右1");
        data.add("左2");
        data.add("右2");
        data.add("左3");
        data.add("右3");

        // ViewPager 的使用 ： https://www.jianshu.com/p/e5abbda4a71c
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(data);
        // ViewPager 建立与适配器的联系
//        mVp1.setAdapter(pagerAdapter);

        List<Fragment> fragments1 = new ArrayList<>(3);
        fragments1.add(new LeftFragment());
        fragments1.add(new CenterFragment());
        fragments1.add(new RightFragment());
        fragments1.add(new AddFragment());

        // 这里的behavior不传的话 默认是BEHAVIOR_SET_USER_VISIBLE_HINT ,也可以传 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager()
                , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT , fragments1);
        mVp1.setAdapter(fragmentPagerAdapter);

//        // 返回当前页的索引
//        mVp1.getCurrentItem();
//        // 设置当前页的索引
//        mVp1.setCurrentItem();
        // 设置ViewPager的预加载页面数量（指的是左右两边 (2x + 1)页）, 默认为1，设置为0是不起作用的
        // 一开始默认会加载页面0和1，当滑动到页面1时则会预加载012页面，滑动到页面2时会销毁页面0，预加载123页面
        // 每加载界面一次都会调用instantiateItem 这个方法一次
        mVp1.setOffscreenPageLimit(1);
        // 设置ViewPager之间的左右间距
        mVp1.setPageMargin(50);
        // 一般LayoutDirection 就是设置布局的方式是LTR还是 RTL（主流是LTR方式） 就这两种常用的布局方式
//        mVp1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // ViewPager的数据刷新  adapter的notifyDataSetChanged()方法 也会受到页面缓存的影响
        // 有两种数据刷新的方法：https://blog.csdn.net/tectrol/article/details/78766416
        // 推荐：在instantiateItem里面将view设置tag为当前的position，在view.getTag获取当前是否是我们需要刷新数据的页面
        // 如果是的话返回 POSITION_NONE ， 不是的话返回 POSITION_UNCHANGED
//        pagerAdapter.notifyDataSetChanged();

//        ViewPager 有三个adapter：PagerAdapter、FragmentPagerAdapter、FragmentStatePagerAdapter
//        使用的场景：PagerAdapter 用于视图比较简单的场景 ； FragmentPagerAdapter 用于Fragment数量少的景（因为它会缓存所有的Fragment）
//        FragmentStatePagerAdapter 用于Fragment数量多的场景（因为它会移除滑过去的pager）

        // ViewPager2 的使用   https://www.jianshu.com/p/6d46c89069f8
//        FragmentStateAdapter 替换了FragmentStatePagerAdapter
//        RecyclerView.Adapter 替换了PagerAdapter

        List<Fragment> fragments2 = new ArrayList<>(3);
        fragments2.add(new LeftFragment());
        fragments2.add(new CenterFragment());
        fragments2.add(new RightFragment());

        mVp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        /**
         * 如果每个界面的布局视图都是一样的，就不需要FragmentStateAdapter
         * 如果每个界面的布局视图是不一样的，就需要用Fragment去单独盛放 这些不同的控件和数据
         */
//        mVp2.setAdapter(new MyRvPagerAdapter(data));
        mVp2.setAdapter(new MyFragmentStateAdapter(getSupportFragmentManager() , getLifecycle() , fragments2));
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mTv1.setText("左");
                break;
            case R.id.btn2:
                mTv1.setText("右");
                break;
            default:
                break;
        }
    }
}
