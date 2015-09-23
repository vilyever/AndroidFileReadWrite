package com.vilyever.androidfilereadwrite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.vilyever.contextholder.VDContextHolder;
import com.vilyever.filereadwrite.VDFileConstant;
import com.vilyever.filereadwrite.VDFileReader;
import com.vilyever.filereadwrite.VDFileWriter;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VDContextHolder.initial(getApplicationContext());

        String text = "hello text";
        String filePath = VDFileConstant.getCacheDir("test").getAbsolutePath() + "/text";
        VDFileWriter.writeText(new File(filePath), text);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        filePath = VDFileConstant.getCacheDir("test").getAbsolutePath() + "/launcher.png";
        VDFileWriter.writeBitmap(new File(filePath), bitmap);
        bitmap.recycle();

        TextView label = (TextView) findViewById(R.id.label);
        filePath = VDFileConstant.getCacheDir("test").getAbsolutePath() + "/text";
        label.setText(VDFileReader.readText(new File(filePath)));

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        filePath = VDFileConstant.getCacheDir("test").getAbsolutePath() + "/launcher.png";
        bitmap = VDFileReader.readBitmap(new File(filePath));
        imageView.setImageBitmap(bitmap);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
