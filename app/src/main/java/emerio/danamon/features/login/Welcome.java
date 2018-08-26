package emerio.danamon.features.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import emerio.danamon.R;
import emerio.danamon.features.login.example.SessionHandler;
import emerio.danamon.features.login.example.UserModel;

public class Welcome extends AppCompatActivity {

    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        session = new SessionHandler(getApplicationContext());
        UserModel user = session.getUserDetails();
        TextView welcomeText = findViewById(R.id.welcomeText);

        welcomeText.setText("Welcome "+user.getFullName()+", your session will expire on "+user.getSessionExpiryDate());

    }
}
