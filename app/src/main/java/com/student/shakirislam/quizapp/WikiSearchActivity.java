package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

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
}
