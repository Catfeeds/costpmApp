package com.fudan.cosmosapp.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.VoiceRecognition.VoiceRecgnitionActivity;
import com.fudan.cosmosapp.adapter.TabPageAdapter;
import com.fudan.cosmosapp.app.Constant;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.camare.TakePhoteActivity;
import com.fudan.cosmosapp.fragment.ClassifyFragment;
import com.fudan.cosmosapp.fragment.DiscoverFragment;
import com.fudan.cosmosapp.fragment.HomeFragment;
import com.fudan.cosmosapp.fragment.MeFragment;
import com.fudan.cosmosapp.utils.SPUtils;
import com.fudan.cosmosapp.utils.ToastUtils;
import com.fudan.cosmosapp.utils.permission.PermissionReq;
import com.fudan.cosmosapp.utils.permission.PermissionResult;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private static final int SELECT_PIC = 0;

    List<Fragment> fragments=new ArrayList<>();
    ViewPager   mViewPager;
    /**
     * 按钮的没选中显示的图标
     */
    private int[] unselectedIconIds = { R.drawable.ic_tab_home_gray,
            R.drawable.ic_tab_classify_gray, R.drawable.ic_tab_discover_gray,
            R.drawable.ic_tab_me_gray };
    /**
     * 按钮的选中显示的图标
     */
    private int[] selectedIconIds = { R.drawable.ic_tab_home_yellow,
            R.drawable.ic_tab_classify_yellow, R.drawable.ic_tab_discover_yellow,
            R.drawable.ic_tab_me_yellow };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            initPermission();
        }

        Constant.BASEURL = SPUtils.getSharedStringData(this,Constant.IP,Constant.BASEURL);

        init();
//        NewsModel.getNews(null,null);
//        NewsModel.getSubject(9);
//        NewsModel.getquestionListByESJson("请问一加一");
        initView();
    }

    private void initView() {
        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(unselectedIconIds[0]),
                        Color.parseColor(colors[0])
                ).title("首页")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(unselectedIconIds[1]),
                        Color.parseColor(colors[1])
                ).title("分类")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(unselectedIconIds[2]),
                        Color.parseColor(colors[2])
                ).title("发现")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(unselectedIconIds[3]),
                        Color.parseColor(colors[3])
                ).title("用户")
                        .badgeTitle("icon")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(mViewPager, 0);

        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
//        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
//        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
//        navigationTabBar.setTypeface("fonts/custom_font.ttf");
        navigationTabBar.setIsTitled(true);
        navigationTabBar.setIsTinted(false);
//        navigationTabBar.setIsBadgeUseTypeface(true);
//        navigationTabBar.setBadgeBgColor(Color.RED);
//        navigationTabBar.setBadgeTitleColor(Color.WHITE);
    }

    private void initPermission() {
        //请求读写权限
        PermissionReq.with(this).permissions(
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .result(new PermissionResult() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied() {
                        //弹出对话框 提示用户
                        ToastUtils.showToast(MainActivity.this,"内存读写权限被拒绝");
                    }
                }).request();

        //请求内存修改权限
        PermissionReq.with(this).permissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .result(new PermissionResult() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied() {
                        //弹出对话框 提示用户

                        ToastUtils.showToast(MainActivity.this,"内存修改权限被拒绝");
                    }
                }).request();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionReq.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    protected void init() {

        Fragment homeFragment = new HomeFragment();
        // TODO: 2017/8/14  
//        Fragment classifyFragment = new ClassifyFragment();
        Fragment discoverFragment = new DiscoverFragment();
        Fragment meFragment = new MeFragment();
        fragments.add(homeFragment);
//        fragments.add(classifyFragment);
        fragments.add(ClassifyFragment.newInstance());
        fragments.add(discoverFragment);
        fragments.add(meFragment);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        TabPageAdapter tabPageAdapter = new TabPageAdapter(
                getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(tabPageAdapter);
        mViewPager.setOnPageChangeListener(this);


//        GradeModel  gradeModel=GradeModel.getInstance(getApplicationContext());
//        Call<Grade> temp=gradeModel.getGrade();
//        temp.enqueue(new Callback<Grade>() {
//            @Override
//            public void onResponse(Call<Grade> call, Response<Grade> response) {
//                if (response.isSuccess()){
//                    Grade   resultGrade=response.body();
//                    if(resultGrade!=null){
//                        List<Grade.GradeListBean>   entity=resultGrade.getGradeList();
//                        //entity.get(1).getGradeName();
//                        Log.i("yinxiaofei",entity.get(1).getGradeName());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Grade> call, Throwable t) {
//
//            }
//        });

    }

//    /**
//     * 选择某页
//     * @param position 页面的位置
//     */
//    private void selectPage(int position) {
//        // 将所有的tab的icon变成灰色的
//        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
//            Drawable gray = getResources().getDrawable(unselectedIconIds[i]);
//            // 不能少，少了不会显示图片
//            gray.setBounds(0, 0, gray.getMinimumWidth(),
//                    gray.getMinimumHeight());
//            RadioButton child = (RadioButton) mRadioGroup.getChildAt(i);
//            child.setCompoundDrawables(null, gray, null, null);
//            child.setTextColor(getResources().getColor(
//                    R.color.dark_gray));
//        }
//        // 切换页面
//        mViewPager.setCurrentItem(position, false);
//        // 改变图标
//        Drawable yellow = getResources().getDrawable(selectedIconIds[position]);
//        yellow.setBounds(0, 0, yellow.getMinimumWidth(),
//                yellow.getMinimumHeight());
//        RadioButton select = (RadioButton) mRadioGroup.getChildAt(position);
//        select.setCompoundDrawables(null, yellow, null, null);
//        select.setTextColor(getResources().getColor(
//                R.color.black));
//    }

//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (checkedId) {
//            case R.id.btn_home: // 首页选中
//                selectPage(0);
//                break;
//            case R.id.btn_classify: // 分类选中
//                selectPage(1);
//                break;
//            case R.id.btn_discover: // 发现选中
//                selectPage(2);
//                break;
//            case R.id.btn_me: // 个人中心选中
//                selectPage(3);
//                break;
//        }
//    }

    /**
     * 调用相册
     */
    private void goAlbums() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PIC);
    }

    public void takePhote(View view) {
        //弹出对话框
        //相册还是本地
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("选择图片");
        dialog.setItems(R.array.dialog_photo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0 :
                        startActivity(new Intent(MainActivity.this, TakePhoteActivity.class));
                        break;
                    case 1 :
                        //获取图片
                        goAlbums();
                        break;
                }
            }
        });
        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case SELECT_PIC:
                //获取图片后裁剪图片
                Intent intent = new Intent(this, TakePhoteActivity.class);
                intent.setData(data.getData());
                Log.e("data",data.getData().getPath());
                startActivity(intent);

                break;
        }
    }

    public void takeVoice(View view) {

        startActivity(new Intent(this, VoiceRecgnitionActivity.class));
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
