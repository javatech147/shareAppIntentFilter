package com.example.manishhp.sharedappreceiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvData;
    String[] blacklist = new String[]{"com.example.manishhp.sharedappreceiver"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData = findViewById(R.id.tv_data);


        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // Handle multiple images being sent
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }

    }

    private void handleSendMultipleImages(Intent intent) {

    }

    private void handleSendImage(Intent intent) {
    }

    private void handleSendText(Intent intent) {
        String extraText = intent.getStringExtra(Intent.EXTRA_TEXT);
        tvData.setText("" + extraText);
    }

    public void btnShareText(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, tvData.getText().toString().trim());
        startActivity(SharedAppUtils.generateCustomChooserIntent(shareIntent, blacklist, this));
    }
}