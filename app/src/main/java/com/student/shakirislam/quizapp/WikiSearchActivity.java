package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WikiSearchActivity extends AppCompatActivity {

    private static final String TAG = "WikiSearchActivity";
    private Button button_return;
    private Button button_detail;

    private TextView text_wiki_sum;
    private ScrollView scroll_view;

    private static String wiki_intro;
    private static String wiki_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_search);

        button_return = (Button) findViewById(R.id.button_back);
        button_detail = (Button) findViewById(R.id.button_details);
        text_wiki_sum = (TextView) findViewById(R.id.text_wiki_summary);
        text_wiki_sum.setText("Loading....");
        getWikiPage(wiki_link);

        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Return back to quiz
                onBackPressed();
            }
        });

        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent to view outside link.

                String wikiurl = "https://en.wikipedia.org/wiki/" + wiki_link;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(wikiurl));
                startActivity(intent);
            }
        });

    }

    public static String getWiki_intro() {
        return wiki_intro;
    }

    public static void setWiki_intro(String wiki_intro) {
        WikiSearchActivity.wiki_intro = wiki_intro;
    }

    public static String getWiki_link() {
        return wiki_link;
    }

    public static void setWiki_link(String wiki_link) {
        WikiSearchActivity.wiki_link = wiki_link;
    }

    private void getWikiPage(String title){
        //Gets the wikipedia introduction
        OkHttpClient client = new OkHttpClient();
        String url = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=" + title;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                final Response wikiResponse = response;

                WikiSearchActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = " ";
                        try{
                            data = wikiResponse.body().string();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        try{
                            String pagenumber = "";
                            Iterator <String> iterator = new JSONObject(data).getJSONObject("query").getJSONObject("pages").keys();
                            while (iterator.hasNext()){
                                pagenumber = iterator.next();
                            }

                            data = new JSONObject(data).getJSONObject("query").getJSONObject("pages").getJSONObject(pagenumber).getString("extract");
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        //Change the textview here
                        text_wiki_sum.setText(data);
                    }
                });

            }
        });
    }
}
