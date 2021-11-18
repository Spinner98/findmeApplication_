package org.techtown.findmeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_userName extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_name);
        Button continueButton = (Button) findViewById(R.id.conti_next);
        EditText name = (EditText) findViewById(R.id.editTextName);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.length()>=1){
                    String username = name.getText().toString();
                    Intent intent = new Intent(getApplicationContext(),Register_UserPhone.class);
                    intent.putExtra("name_string",username);
                    startActivity(intent);
                }else{
                    Toast.makeText(Register_userName.this, "사용할 이름을 적어주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}