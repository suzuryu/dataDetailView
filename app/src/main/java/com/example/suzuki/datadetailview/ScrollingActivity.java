package com.example.suzuki.datadetailview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    private void addData2View(TownData townData){
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

        data_text.setText(townData.getData_name_() + " : " + townData.getData_val_());
        data_text.setTextSize(32);
        data_text.setGravity(Gravity.CENTER);

        vert_linear.addView(data_text);
        linearLayout.addView(vert_linear);
    }

    private void createDataView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // 市区町村名を入れる
        /*
            我期待以下
            String title_str = dataAnalyzer.getlocationName();
         */
        String title_str = "神戸";
        toolbar.setTitle(title_str);

        //setSupportActionBar(toolbar);

        getWindow().setExitTransition(new Slide());

        /*
          Data setting
         */
        TownData datas[] = new TownData[10];
        for(int i=0; i < datas.length; i++){
            datas[i] = new TownData();
        }
        datas[0].setData_name_("平均気温");
        datas[1].setData_name_("人口");
        datas[2].setData_name_("人口密度");

        datas[0].setData_val_(String.valueOf(16.7) + "℃");
        datas[1].setData_val_(String.valueOf(1527481) + "人");
        datas[2].setData_val_(String.valueOf(2742) + "人/㎢");

        // add data to view list
        for(TownData townData: datas){
            addData2View(townData);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        createDataView();

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
        DataManager textDataManager = new DataManager();
        textDataManager.writeToFile("test.txt", textDataManager.getTextData());
        ArrayList<String> fileData = textDataManager.readFromFile("test.txt");
        String data = "";
        for(String fileD: fileData){
            data += fileD;
        }
        Log.d("data",data);
        TextView textView = findViewById(R.id.textView);
        textView.setText(data);
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
