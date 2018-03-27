package com.example.wenkun.finalproject;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.wenkun.finalproject.service.FloatWindowService;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String STATICACTION = "UpdateList";
    private boolean hasPermission;
    public EssayAdapter myAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity","onResume");
        //myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button addItem=(Button)findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this, EssayDetailsActivity.class);
                intent.setAction("addItem");
                startActivity(intent);
            }
        });

        AccountLib al = (AccountLib) getApplicationContext();

        final List<Essay> data = new ArrayList<Essay>();

        try {
            List<Essay> es = al.getEssay();
            data.addAll(es);
        } catch (Exception e) {
            data.add(new Essay("dummy","11/11","dummy"));
        }

        myAdapter=new EssayAdapter(this,data);
        myAdapter.notifyDataSetChanged();

        myAdapter.setOnItemClickListener(new EssayAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                String t="点击"+(position+1)+"个";
                Essay essay=(Essay) myAdapter.getItemData(position);

                Intent intent = new Intent(MenuActivity.this, EssayDetailsActivity.class);
                intent.putExtra("essay", essay);
                intent.putExtra("position", position);
                intent.setAction("updateItem");
                startActivityForResult(intent, 10001);
                //Toast.makeText(MainActivity.this,t,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                try {
                    AccountLib al = (AccountLib) getApplicationContext();
                    Essay essay = (Essay) myAdapter.getItemData(position);
                    Boolean result = al.delete(essay.id);
                    myAdapter.removeData(position);
                    myAdapter.notifyDataSetChanged();
                    String t="移除第"+(position+1)+"行";
                    Toast.makeText(MenuActivity.this,t,Toast.LENGTH_SHORT).show();
                } catch(Exception e) {
                    Toast.makeText(MenuActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myAdapter);

        verifyPermission();
        checkUsagePermission();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (10001) : {
                if (resultCode == Activity.RESULT_OK) {
                    int pos = data.getIntExtra("position", 0);
                    Essay e = new Essay(
                            data.getStringExtra("title"),
                            data.getStringExtra("datetime"),
                            data.getStringExtra("data"),
                            data.getIntExtra("id", 0)
                    );
                    Log.wtf("pos", "" + pos);
                    if (pos >= 0) {
                        myAdapter.setItemData(pos, e);
                    } else {
                        myAdapter.addData(e);
                    }
                    myAdapter.notifyDataSetChanged();
                }
                break;
            }
            case (1) : {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Settings.canDrawOverlays(this)) {
                        Toast.makeText(this, "权限授予失败，将无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "权限授予成功", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
            case (2) : {
                AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
                int mode = 0;
                mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), getPackageName());
                boolean granted = mode == AppOpsManager.MODE_ALLOWED;
                if (!granted) {
                    Toast.makeText(this, "请开启该权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void verifyPermission(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(this)) {
                Log.e("msg", "already has permission");
                hasPermission = true;
            } else {
                Log.e("msg", "ask for permission");
                try{
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    startActivityForResult(intent, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //  申请查看使用情况的权限
    private void checkUsagePermission() {
        try{
            AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            int mode = 0;
            mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(), getPackageName());
            boolean granted = mode == AppOpsManager.MODE_ALLOWED;
            if (!granted) {
                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                startActivityForResult(intent, 2);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.essay) {
            Intent intent=new Intent(MenuActivity.this,MenuActivity.class);
            startActivity(intent);
        } else if (id == R.id.profile) {
            Intent intent=new Intent(MenuActivity.this,ProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.ball) {
            Intent intent = new Intent(MenuActivity.this, AppManagement.class);
            Log.e("MenuActivity","activate ball");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
