package org.zzy.lib.largeimage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.mmkv.MMKV;

import org.zzy.lib.largeimage.LargeImage;
import org.zzy.lib.largeimage.LargeImageInfo;
import org.zzy.lib.largeimage.LargeImageManager;
import org.zzy.lib.largeimage.R;
import org.zzy.lib.largeimage.adapter.LargeImageListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2020/4/10 21:33
 * 描    述：用列表的形式显示超标图片，只显示本次APP启动以
 * 来超标图片，不会显示历史的。
 * 修订历史：
 * ================================================
 */
public class LargeImageListActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private LargeImageListAdapter mLargeImageAdapter;
    private List<LargeImageInfo> mLargeImageList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_largeimage_list);
        initView();
        initData();
    }

    private void initView(){
        mToolBar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.rv_largeImage);
        mToolBar.inflateMenu(R.menu.toolbar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_setting) {
                    Intent intent = new Intent(LargeImageListActivity.this,SettingActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    private void initData(){
        mLargeImageList = new ArrayList<>();
        Map<String, LargeImageInfo> stringLargeImageInfoMap = LargeImageManager.getInstance().getmInfoCache();
        for (Map.Entry<String, LargeImageInfo> entry :  stringLargeImageInfoMap.entrySet()) {
            mLargeImageList.add(entry.getValue());
        }
        mLargeImageAdapter = new LargeImageListAdapter(this,mLargeImageList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mLargeImageAdapter);
    }
}