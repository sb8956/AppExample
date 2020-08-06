package com.example.appexample;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;

        import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText TextInputEditText_eamil, TextInputEditText_password;
    RelativeLayout RelativeLayout_login;

    String emailOK="123@gmail.com";
    String passwordOK="1234";
    String  inputEmail="";
    String intputPassword="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText_eamil=findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password=findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login =findViewById(R.id.RelativeLayout_login);

        //1. 값을 가져온다. -  검사 (123@gmail.com / 1234 이 맞는지 확인)
        //2. 클릭을 감지한다. -우리가 정한값과 같지 않으면 로그인 버튼을 누를수 없도록 한다
        //3. 1번 값을 다음 액티비티로 넘긴다.
        RelativeLayout_login.setClickable(false);


        TextInputEditText_eamil.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d("SENTI",s+","+count);
                if(s!=null) {
                    inputEmail = s.toString();
                    RelativeLayout_login.setEnabled(validation());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d("SENTI",s+","+count);
                if (s != null) {
                    intputPassword = s.toString();
                    RelativeLayout_login.setEnabled(validation());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //RelativeLayout_login.setClickable(true);
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

    public boolean validation(){
        return inputEmail.equals(emailOK) && intputPassword.equals(passwordOK);
    }
}
