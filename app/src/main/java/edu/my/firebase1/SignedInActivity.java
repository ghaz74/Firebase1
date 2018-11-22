package edu.my.firebase1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedInActivity extends AppCompatActivity {
    TextView userEmail;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if(currentUser == null){
            startActivity(MainActivity.createIntent(this));
            finish();
            return;
        }

        userEmail = (TextView) findViewById(R.id.user_email);
        userName = (TextView) findViewById(R.id.user_display_name);
        userEmail.setText(currentUser.getEmail());
        userName.setText(currentUser.getDisplayName());
    }

    public static Intent createIntent(Context context, IdpResponse idpResponse) {
//        Intent in = IdpResponse.getIntent(idpResponse);
//        in.setClass(context, SignedInActivity.class);
//        return in;
        return new Intent().setClass(context, SignedInActivity.class)
                .putExtra(ExtraConstants.IDP_RESPONSE, idpResponse);
    }
}
