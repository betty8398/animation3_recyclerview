package com.example.recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.Toast;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerViewData;

    private List<Map<String,Object>> pictureList;  //用來放Map類型資料的List
    private RecyclerView recyclerViewPicture;
    private Button button_resize,button_resize2;
    private View view2;
    private int height;
    private String TAG;
    private View sceneView;

    private int center_x,center_y;
    private int box_r,box_l,box_t,box_b;
    private int image_r,image_l,image_t,image_b;
    int x, y;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this;
        //TODO 1 取得螢幕大小
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        //TODO END 1


        List<String> stringList = new ArrayList<>();
        for(int i = 0 ;i <30 ;i++){
            stringList.add(new String("Item : " +(i+1)));
        }
        //取得 recyclerView 元件
        recyclerViewData = findViewById(R.id.recyclerview);
        //設定 recyclerView 使用 LayoutManager

        StaggeredGridLayoutManager myLayoutManager_Staggered = new StaggeredGridLayoutManager(1,1);
        recyclerViewData.setLayoutManager(myLayoutManager_Staggered);

        //建立 recyclerView 的 Adapter 物件 傳入包含項目清單的 List 物件
        CardViewAdapter adapter = new CardViewAdapter(context,stringList);

        recyclerViewData.setAdapter(adapter);

        button_resize = findViewById(R.id.button_resize);
        button_resize2 = findViewById(R.id.button_resize2);


        button_resize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新 recycler view 的範圍
                ResizeAnimation resizeAnimation = new ResizeAnimation(recyclerViewData, recyclerViewData.getWidth(),(height-getBarHeight())/2);
                resizeAnimation.setDuration(600);
                recyclerViewData.startAnimation(resizeAnimation);
                Log.d(TAG, "window height = "+height);

                Toast.makeText(context, "螢幕半高： "+(height-getBarHeight())/2, Toast.LENGTH_SHORT).show();
            }
        });

        button_resize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //更新 recycler view 的範圍
                ResizeAnimation resizeAnimation = new ResizeAnimation(recyclerViewData, recyclerViewData.getWidth(),height-getBarHeight());
                resizeAnimation.setDuration(600);
                recyclerViewData.startAnimation(resizeAnimation);

                Toast.makeText(context, "螢幕全高： "+(height-getBarHeight()), Toast.LENGTH_SHORT).show();
            }
        });
        //TODO 2  監聽view 做出拖曳效果
        view2=findViewById(R.id.view2);




        //TODO END 2  監聽view 做出拖曳效果


    }
    private int getBarHeight() {
        //狀態列的高度
        int statusBar_Height = getWindow().getDecorView().findViewById(android.R.id.statusBarBackground).getHeight();
        //動作列的高度
        int ActionBar_Height = getSupportActionBar().getHeight();
        return statusBar_Height ;
    }
}