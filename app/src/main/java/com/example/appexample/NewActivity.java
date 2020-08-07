package com.example.appexample;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset = {"1", "2"};
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        queue=Volley.newRequestQueue(this);
        getNews();

        //1. 화면이 로딩 -> 뉴스 정보를 받아온다. ----- end
        //2. 정보 -> 어댑터에 넘겨준다.
        //3. 어댑터 ->  받아온 정보를 세팅해서 화면에 표시
    }

    public void getNews(){
// Instantiate the RequestQueue.

        String url ="http://newsapi.org/v2/top-headlines?country=kr&apiKey=b7bf2b2512584076ac59eca81a8d4e1c";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NEWS", response);

                        try {
                            JSONObject jsonObj = new JSONObject(response);

                            JSONArray arrayArticles = jsonObj.getJSONArray("articles");

                            //response -> NewsData.class 에 분류
                            List<NewsData> news= new ArrayList<>();
                            for(int i=0, j=arrayArticles.length();i<j;i++) {
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                Log.d("News",obj.toString());

                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setUrlToImage(obj.getString("urlToImage"));
                                newsData.setConetent(obj.getString("content"));
                                news.add(newsData);

                            }

                            // specify an adapter (see also next example)
                            mAdapter = new MyAdapter(news);
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}