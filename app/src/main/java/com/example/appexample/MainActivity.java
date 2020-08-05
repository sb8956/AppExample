package com.example.appexample;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;

        import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText TextInputEditText_eamil, TextInputEditText_password;
    RelativeLayout RelativeLayout_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText_eamil=findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password=findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login =findViewById(R.id.RelativeLayout_login);

        //1. 값을 가져온다.
        //2. 클릭을 감지한다.
        //3. 1번 값을 다음 액티비티로 넘긴다.


        RelativeLayout_login.setClickable(true);
        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = TextInputEditText_eamil.getText().toString();
                String password = TextInputEditText_password.getText().toString();

                Intent intent = new Intent(MainActivity.this , LoginResultActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }
}
