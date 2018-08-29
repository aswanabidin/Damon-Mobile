package emerio.danamon.features.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import emerio.danamon.R;

public class LoginActivity extends AppCompatActivity {

    private ImageButton btnViewDrawerNavigation;
    private ProgressDialog progressDialog;
    private Context context;

    private String username;
    private String password;

    // Email and Password Variable
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private EditText edtEmail, edtPassword;

    // Button Login
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final LinearLayout content = (LinearLayout) findViewById(R.id.content);

        // Progress Dialog Initializing
        progressDialog = new ProgressDialog(this);

        // Email and Password Initializing and new object
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_login_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_login_password);

        edtEmail = (EditText) findViewById(R.id.et_login_email);
        edtPassword = (EditText) findViewById(R.id.et_login_password);

        edtEmail.addTextChangedListener(new LoginActivity.MyTextWatcher(edtEmail));

        // Button Login Initializing
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = edtEmail.getText().toString().toLowerCase().trim();
                password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    submitForm();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    submitForm();
                    return;
                }

            }
        });


        // View Drawer Navigation
        btnViewDrawerNavigation = (ImageButton) findViewById(R.id.btn_view_drawer_navigation);
        btnViewDrawerNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                content.setTranslationX(slideX);
            }
        };

        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    /**
     * Launch Dashboard Activity on Successful Login
     */
    private void loadDashboard() {
        Intent i = new Intent(getApplicationContext(), Welcome.class);
        startActivity(i);
        finish();

    }

    /* Validate Email */

    private void submitForm() {
        if (!validateEmail()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
    }

    private boolean validateEmail() {

        String email = edtEmail.getText().toString().trim();

        if (email.isEmpty()) {
            inputLayoutEmail.setError("Email atau User ID tidak boleh kosong");
            requestFocus(edtEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {

        String password = edtPassword.getText().toString().trim();

        if (password.isEmpty()) {
            inputLayoutPassword.setError("Password tidak boleh kosong");
            requestFocus(edtPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    // If edittext isEmpty then view error (enable error)

    private class MyTextWatcher implements TextWatcher {

        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.input_layout_login_email:
                    validateEmail();
                    finish();
                    break;
                case R.id.input_layout_login_password:
                    validatePassword();
                    break;
            }

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
