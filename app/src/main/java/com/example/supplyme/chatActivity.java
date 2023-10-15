package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class chatActivity extends AppCompatActivity {

    private FirebaseListAdapter<ChatFirebaseModel> adapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        displayChatMessages();

        System.out.println("HELLO THERE1");

        Objects.requireNonNull(getSupportActionBar()).hide();

        FloatingActionButton fabChat = (FloatingActionButton)findViewById(R.id.sendButtonFAB);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // ***NAVIGATION BAR

        // Set current selected item
        bottomNavigationView.setSelectedItemId(R.id.chat);

        // Set up select listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.chat:

                        return true;
                    case R.id.map:
                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfilePageActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.requests:
                        startActivity(new Intent(getApplicationContext(), CurrentCaseActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
        // NAVIGATION BAR***




        fabChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText input = (EditText)findViewById(R.id.input);

                FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
                String name = appUser.getDisplayName();

                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue(new ChatFirebaseModel(input.getText().toString(),name)
                        );

                // Clear the input
                input.setText("");

            }
        });


        
    }

    private void displayChatMessages() {

        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        FirebaseListOptions<ChatFirebaseModel> options = new FirebaseListOptions.Builder<ChatFirebaseModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference(),ChatFirebaseModel.class)
                .setLayout(R.layout.message)
                .build();

        adapter = new FirebaseListAdapter<ChatFirebaseModel>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull ChatFirebaseModel model, int position) {

                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
                String name = appUser.getDisplayName();

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());



                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));

            }
        };

        listOfMessages.setAdapter(adapter);





    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}