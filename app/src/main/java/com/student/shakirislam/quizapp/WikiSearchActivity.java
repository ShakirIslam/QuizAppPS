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

    // Instantiate all variables and layout widgets

    private static final String TAG = "WikiSearchActivity";
    private Button button_return;
    private Button button_detail;

    private TextView text_wiki_sum;
    private ScrollView scroll_view;

    private static String wiki_intro;
    private static String wiki_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //From the OnCreate method, find all widgets and buttons using their unique ID
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_search);

        button_return = (Button) findViewById(R.id.button_back);
        button_detail = (Button) findViewById(R.id.button_details);
        text_wiki_sum = (TextView) findViewById(R.id.text_wiki_summary);
        text_wiki_sum.setText("Loading....");

        // Call the wikipage method which takes user to specific wiki page
        getWikiPage(wiki_link);

        //Set OnClickListener to specify an action when the user clicks back button
        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Return back to quiz
                onBackPressed();
            }
        });

        //Set OnClickListener to specify an action when the user clicks a button
        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Implicit intent which opens specific Wikipedia page and scrolls down to specific
                // section of Wikipedia page using specific section of url (wiki_link)
                //Intent used with StartActivity () to launch the activity

                String wikiurl = "https://en.wikipedia.org/wiki/" + wiki_link;

                //Instantiate intent – ACTION_VIEW represents the general action to be performed

                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Uri is an immutable reference which denotes the data to be operated on

                intent.setData(Uri.parse(wikiurl));
                startActivity(intent);
            }
        });

    }

    //Methods which retrieve and set the content summary (intro) of Wikipedia page
    public static String getWiki_intro() {
        return wiki_intro;
    }

    public static void setWiki_intro(String wiki_intro) {
        WikiSearchActivity.wiki_intro = wiki_intro;
    }


    //Methods which retrieve and set the link pertaining to specific Wikipedia article
    public static String getWiki_link() {
        return wiki_link;
    }

    public static void setWiki_link(String wiki_link) {
        WikiSearchActivity.wiki_link = wiki_link;
    }



    //Method which retrieves the Wikipedia page, using the OkHTTP library for HTTP request
    // (http://square.github.io/okhttp/)
    private void getWikiPage(String title){
        //This program downloads the JSON Wikipedia file
        // – title variable represents the unique section of each distinct url for specific pages
        OkHttpClient client = new OkHttpClient();
        String url = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=" + title;

        //Creates request object for making network calls
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Use the client to create a Call object
        //Use enqueue method for asynchronous calls


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //onFailure method invoked when HTTP call to Wikipedia fails
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                final Response wikiResponse = response;
                //onResponse method invoked when HTTP call to Wikipedia is successful

                //In order to update the UI, the runOnUiThread() is used to sync with the UI thread
                WikiSearchActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = " ";

                        //Use try catch block to handle exceptions when retrieving body of Wikipedia page as a string

                        try{
                            data = wikiResponse.body().string();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        try{
                            String pagenumber = "";
                            //Iterator interface used to traverse collection of wiki pages
                            Iterator <String> iterator = new JSONObject(data).getJSONObject("query").getJSONObject("pages").keys();
                            while (iterator.hasNext()){
                                pagenumber = iterator.next();
                            }

                            //Because the content summary is in the extract object of JSON file,
                            // must use the get() method to retrieve all objects up to and
                            // including the extract object so as to not return any extra
                            // characters from JSON file

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
