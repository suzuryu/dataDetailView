package com.example.suzuki.datadetailview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    private DataManager dataManager;
    private SQLiteManager DBManager;

    // ひとつひとつのテーブルデータをレイアウトに追加していく
    private void addData2View(String[] data){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear);

        int margin_size = 10;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(margin_size, margin_size, margin_size, 0);

        int padding_size = 20;

        TextView data_text = new TextView(this);
        LinearLayout vert_linear = new LinearLayout(this);
        vert_linear.setOrientation(LinearLayout.HORIZONTAL);
        vert_linear.setBackground(getDrawable(R.drawable.fradme_style));
        vert_linear.setPadding(padding_size, padding_size, padding_size, padding_size);
        vert_linear.setLayoutParams(layoutParams);
        vert_linear.setGravity(Gravity.CENTER);

        data_text.setText(data[0] + " : " + data[1]);
        data_text.setTextSize(32);
        data_text.setGravity(Gravity.CENTER);

        vert_linear.addView(data_text);
        linearLayout.addView(vert_linear);
    }

    //　テーブルデータの内容を設定
    private void createDataView(String city_str){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TownData townData = DBManager.queryByCityName(city_str);
        toolbar.setTitle(townData.getPrefecture() + townData.getCityName());

        getWindow().setExitTransition(new Slide());

        /*
          Data setting
         */
        String datas[][] = new String[4][2];
        for(int i=0; i < datas.length; i++){
            datas[i][0] = "";
            datas[i][1] = "";
        }

        datas[0][0] = "学校の数";
        datas[1][0] = "駅の数";
        datas[2][0] = "犯罪発生率";
        datas[3][0] = "人口";

        datas[0][1] = String.valueOf(townData.getSchoolCount()) + "つ";
        datas[1][1] = String.valueOf(townData.getStationCount()) + "つ";
        datas[2][1] = String.valueOf(townData.getCrimePer()) + "%";
        datas[3][1] = String.valueOf(townData.getPopulation()) + "人";

        // add data to view list
        for(String[] data: datas){
            addData2View(data);
        }
    }

    private void downloadData() {
        try {
            JsonHelper jsonHelper = new JsonHelper(this.DBManager);
            jsonHelper.readJson(this.getAssets().open("cityData.json"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.dataManager = new DataManager();
        this.DBManager = new SQLiteManager(this);

        downloadData();
        setScreenMain();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    private void setScreenMain() {
        setContentView(R.layout.activity_scrolling);
        createDataView("八王子市");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setScreenSub();
            }
        });
    }

    private void setScreenSub(){
        setContentView(R.layout.test);

        Button senniButton = findViewById(R.id.seni);

        senniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setScreenMain();
            }
        });
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
