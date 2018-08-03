package it.is.simpl.neverforget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private long BACK_PRESSED = 0;
    private Toast eT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eT = Toast.makeText(this,"Press again to exit", Toast.LENGTH_LONG);
    }

    @Override
    public void onBackPressed()
    {
        if(BACK_PRESSED + 2000 > System.currentTimeMillis())
        {
            eT.cancel();
            super.onBackPressed();
        }
        BACK_PRESSED = System.currentTimeMillis();
        eT.show();
    }
}
