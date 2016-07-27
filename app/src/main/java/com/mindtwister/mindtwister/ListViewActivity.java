package com.mindtwister.mindtwister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mindtwister.mindtwister.managers.DBHandler;
import com.mindtwister.mindtwister.managers.listClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    DBHandler db;
    ListView list, list_head;
    ArrayList<HashMap<String, String>> mylist, mylist_title;
    ListAdapter adapter_title, adapter;
    HashMap<String, String> map1, map2;
    String[] result_data;
    String[] nickname;
    String[] level;
    int[] scores;
    List<listClass> sites_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        db = new DBHandler(this);

        list = (ListView) findViewById(R.id.listView2);
        list_head = (ListView) findViewById(R.id.listView1);

        sites_data = db.viewMemoryMatrixScore();

        result_data = new String[sites_data.size()];
        nickname = new String[sites_data.size()];
        level = new String[sites_data.size()];
        scores = new int[sites_data.size()];
        int i = 0;
        for (listClass s : sites_data) {
            result_data[i] = s.getGamename();
            nickname[i] = s.getNickname();
            level[i] = s.getLevel();
            scores[i] = s.getScore();
            // get the name of the category and add it to array
            i++;
        }
        showActivity();
        //  ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.activity_display, result_data);
        // ListView listView = (ListView) findViewById(R.id.listView);
        //listView.setAdapter(listAdapter);
    }

    public void showActivity() {

        mylist = new ArrayList<HashMap<String, String>>();
        mylist_title = new ArrayList<HashMap<String, String>>();

        /**********Display the headings************/


        map1 = new HashMap<String, String>();

        map1.put("Nickname", "Nickname");
        map1.put("playlevel", "playlevel");
        map1.put("score", "score");
        map1.put("gamename", "gamename");
        mylist_title.add(map1);

        try {
            adapter_title = new SimpleAdapter(this, mylist_title, R.layout.activity_display,
                    new String[]{"Nickname", "playlevel", "score", "gamename"}, new int[]{
                    R.id.Nickname, R.id.playlevel, R.id.score, R.id.gamename});
            list_head.setAdapter(adapter_title);
        } catch (Exception e) {

        }
        /****************display the contents*********/
        for (int i = 0; i < sites_data.size(); i++) {
            map2 = new HashMap<String, String>();

            map2.put("Nickname", nickname[i]);
            map2.put("playlevel", level[i]);
            map2.put("score", String.valueOf(scores[i]));
            map2.put("gamename", result_data[i]);
            mylist.add(map2);
        }


        try {
            adapter = new SimpleAdapter(this, mylist, R.layout.activity_display,
                    new String[]{
                            "Nickname", "playlevel", "score", "gamename"}, new int[]{
                    R.id.Nickname, R.id.playlevel, R.id.score, R.id.gamename});

            list.setAdapter(adapter);
        } catch (Exception e) {

        }

        /********************************************************/

    }


}
