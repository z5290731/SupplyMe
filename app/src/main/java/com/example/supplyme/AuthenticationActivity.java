package com.example.supplyme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class AuthenticationActivity extends AppCompatActivity {

    private static final int AUTHENTICATION = 1;
    private FirebaseAuth mAuth;
    private static final String TAG = "AuthenticationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        List<AuthUI.IdpConfig> signIn = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        //.setTheme(R.style.AuthenticationTheme)
                        .setAvailableProviders(signIn)
                        .build(),
                AUTHENTICATION);
    }

    @Override
    protected void onActivityResult(int requestLoginCode, int resultLoginCode, @Nullable Intent data) {
        super.onActivityResult(requestLoginCode, resultLoginCode, data);

        FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = appUser.getEmail();

        if(requestLoginCode == AUTHENTICATION) {
            IdpResponse authenticationResponse = IdpResponse.fromResultIntent(data);



            if(resultLoginCode == RESULT_OK && email.equals("corey.rfs@gmail.com") )  {

                loginSuccess();
            } else if (resultLoginCode == RESULT_OK && email.equals("isabel.procureme@gmail.com")) {

                loginSuccessRequester();

            } else {

                loginSuccess();

            }
        }
    }

    public void loginSuccess() {


        Intent intent = new Intent(this, CurrentCaseActivity.class);
        startActivity(intent);

    }

    public void loginSuccessRequester() {


        Intent intent = new Intent(this, CurrentCaseActivity.class);
        startActivity(intent);

    }
}