package it.is.simpl.neverforget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

public class SignInActivity extends AppCompatActivity
{
    private GoogleSignInOptions signInOptions;
    private GoogleSignInClient signInClient;
    private GoogleSignInAccount signInAccount;
    private SignInButton signInButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);

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

    @Override
    protected void onStart()
    {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        if( (signInAccount = GoogleSignIn.getLastSignedInAccount(this)) != null)
        {
            updateUI();
        }
    }

    public void updateUI()
    {

    }

    public void signIn()
        {
        Intent signInIntent = signInClient.getSignInIntent();
        startActivityForResult(signInIntent, 9001);
    }
}
