package net.tm30.assesment.tobi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;

    private void toLogin() {
        Snackbar.make(loginButton, "Not in current scope", Snackbar.LENGTH_SHORT).show();
    }

    private void toRegister() {
        var intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        var registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);

        // Assign listeners
        loginButton.setOnClickListener(view -> toLogin());
        registerButton.setOnClickListener(view -> toRegister());
    }
}
