package com.test.birdcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class BirdInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_info);

        ListView listview;
        ListViewAdapter adapter;

        //Adapter생성
        adapter = new ListViewAdapter();

        //리스트뷰 참조 및 Adapter닫기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img_bdthum01),
"여름철새 summer visitor", "겨울을 따뜻한 열대, 아열대지역에서 보내고, 봄이 되면 우리나라에서 번식을 하고 여름이지나면 다시 월동지로 이동하는 새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.summer1),"제비", "여름철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.summer2),"꾀꼬리", "여름철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.summer3),"뻐꾸기", "여름철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.summer4),"개개비", "여름철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.summer5),"백로류", "여름철새") ;

        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img_bdthum02),
                "겨울철새 winter visitor", "우리나라 보다 고위도 지역에서 번식, 늦가을부터 우리나라를 찾아와 겨울을 보내고 이른 봄에 다시 번식지로 올라가는 철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.winter1),"두루미", "겨울철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.winter2),"큰기러기", "겨울철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.winter3),"쇠기러기", "겨울철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.winter4),"가창오리", "겨울철새") ;


        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.img_bdthum03),
                "통과철새 passage migrant", "우리나라보다 고위도 지역에서 번식하고 저위도 지역에서 월동, 우리나라에서 봄과 가을에 관찰되는 종") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.passage1),"벌매", "통과철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.passage2),"비둘기조롱이", "통과철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.passage3),"노랑눈썹솔새", "통과철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.passage4),"제비딱새", "통과철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.passage5),"긴발톱할미새", "통과철새") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.passage6),"붉은가슴밭종다리", "통과철새") ;


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                String titleStr = item.getTitle();
                String descStr = item.getDesc();
                Drawable iconDrawable = item.getIcon();

                // TODO : use item data.
                
            }
        });
    }
}