package com.example.suzuki.datadetailview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // 市区町村名を入れる
        /*
            我期待以下
            String title_str = dataAnalyzer.getlocationName();
         */
        String title_str = "神戸";
        toolbar.setTitle(title_str);

        setSupportActionBar(toolbar);

        getWindow().setExitTransition(new Slide());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.test);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
            }
        });

        /*
          Data detail_data
         */
        String data_title[] = {"平均気温", "人口", "人口密度", "NO DATA", "NO DATA", "NO DATA", "NO DATA", "NO DATA", "NO DATA", "NO DATA"};

        String pre_data[] = new String[10];

        pre_data[0] = String.valueOf(16.7) + "℃";
        pre_data[1] = String.valueOf(1527481) + "人";
        pre_data[2] = String.valueOf(2742) + "人/㎢";
        for (int i=3; i < pre_data.length; i++){
            pre_data[i] = "NO DATA";
        }


        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear);

        int margin_size = 10;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(margin_size, margin_size, margin_size, 0);

        int padding_size = 20;

        for(int i = 0; i < data_title.length; i++){
            if (i == data_title.length - 1){
                layoutParams.setMargins(margin_size, margin_size, margin_size, margin_size);
            }
            TextView data_text = new TextView(this);
            LinearLayout vert_linear = new LinearLayout(this);
            vert_linear.setOrientation(LinearLayout.HORIZONTAL);
            vert_linear.setBackground(getDrawable(R.drawable.fradme_style));
            vert_linear.setPadding(padding_size, padding_size, padding_size, padding_size);
            vert_linear.setLayoutParams(layoutParams);
            vert_linear.setGravity(Gravity.CENTER);

            data_text.setText(data_title[i] +" : " + pre_data[i]);
            data_text.setTextSize(32);
            data_text.setGravity(Gravity.CENTER);

            vert_linear.addView(data_text);
            linearLayout.addView(vert_linear);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
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
}
