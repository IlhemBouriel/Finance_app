package tn.gl4.finance;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonPlayVideo2 = (Button)findViewById(R.id.button1);
        getWindow().setFormat(PixelFormat.UNKNOWN);
//displays a video file
        VideoView mVideoView2 = (VideoView)findViewById(R.id.videoView1);
        String uriPath2 = "android.resource://tn.gl4.finance/"+R.raw.movie;
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();
        mVideoView2.start();
        buttonPlayVideo2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView mVideoView2 = (VideoView) findViewById(R.id.videoView1);
// VideoView mVideoView = new VideoView(this);
                String uriPath = "android.resource://tn.gl4.finance/" + R.raw.movie;
                Uri uri2 = Uri.parse(uriPath);
                mVideoView2.setVideoURI(uri2);
                mVideoView2.requestFocus();
                mVideoView2.start();
            }
        });

       /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MarcheTitresActivity.class);
                startActivity(i);
            }
        });

        Button buttonCurConv = (Button) findViewById(R.id.buttonCurrencyConverter);
        buttonCurConv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ConvertisseurActivity.class);
                startActivity(i);
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.button) {
            Intent i = new Intent(MainActivity.this, MarcheTitresActivity.class);
            startActivity(i);
        }
        if (id == R.id.buttonCurrencyConverter) {
            Intent i = new Intent(MainActivity.this, ConvertisseurActivity.class);
            startActivity(i);
        }
        if(id == R.id.buttonBroker)
        {
            Intent i = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(i);
        }
        if (id == R.id.exit) {
            finish();
            System.exit(0);
        }


        return super.onOptionsItemSelected(item);
    }
}
