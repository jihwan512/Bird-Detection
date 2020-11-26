/* import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.test.birdcontrol.R;

public class ListViewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_item);
    }
}*/

package com.test.birdcontrol;
import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable;
    private String titleStr;
    private String descStr;

    public void setIcon(Drawable icon){
        iconDrawable = icon;
    }

    public void setTitle(String title){
        titleStr = title;
    }

    public void setDesc(String desc){
        descStr = desc;
    }

    public Drawable getIcon(){
        return this.iconDrawable;
    }

    public String getTitle(){
        return this.titleStr;
    }

    public String getDesc(){
        return this.descStr;
    }
}