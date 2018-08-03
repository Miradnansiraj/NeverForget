package it.is.simpl.neverforget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity
{
    private GoogleSignInOptions signInOptions;
    private GoogleSignInClient signInClient;
    private GoogleSignInAccount signInAccount;
    private SignInButton signInButton;
    private long BACK_PRESSED = 0;
    private Toast eT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eT = Toast.makeText(this,"Press again to exit", Toast.LENGTH_LONG);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by signInOptions.
        signInClient = GoogleSignIn.getClient(this, signInOptions);

        // Set the dimensions of the sign-in button and onClick
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    public void signIn()
    {
        Intent signInIntent = signInClient.getSignInIntent();
        startActivityForResult(signInIntent, 9001);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 9001)
        {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask)
    {
        try
        {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
        }
        catch (ApiException e)
        {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            e.printStackTrace();
        }
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
