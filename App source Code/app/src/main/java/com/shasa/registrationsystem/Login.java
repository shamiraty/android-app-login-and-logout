package com.shasa.registrationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.shasa.registrationsystem.PHP_API.ApiResponse;
import com.shasa.registrationsystem.PHP_API.Connection;
import com.shasa.registrationsystem.PHP_API.Interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
TextInputLayout username,password;
Button login;
TextView signup;
LinearLayout error;
ProgressDialog progressDialog;

SharedPreferences USERNAME;
String prefNameUSERNAME="username";


    SharedPreferences DEPARTMENT;
    String prefNameDEPARTMENT="department";


    SharedPreferences EMAIL;
    String prefNameEMAIL="email";


    SharedPreferences ROLE;
    String prefNameROLE="role";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.btn_login);
        signup=findViewById(R.id.signup);
        error=findViewById(R.id.showError);
        progressDialog=new ProgressDialog(this);
        getSupportActionBar().hide();


        USERNAME=getSharedPreferences(prefNameUSERNAME, Context.MODE_PRIVATE);
        DEPARTMENT=getSharedPreferences(prefNameDEPARTMENT, Context.MODE_PRIVATE);
        EMAIL=getSharedPreferences(prefNameEMAIL, Context.MODE_PRIVATE);
        ROLE=getSharedPreferences(prefNameROLE, Context.MODE_PRIVATE);

        if(!USERNAME.getString("USERNAME","Not Registered").equals("Not Registered"))
        {
            Intent intent=new Intent(getApplicationContext(),Dashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }



        progressDialog.setTitle("Notification");
        progressDialog.setMessage("tafadhari subiri...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Registration.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call method validate
                validate();
            }
        });
    }
    void validate()
    {
        if(username.getEditText().getText().toString().equals(""))
        {
            username.setError("jaza username");
            displayError("jaza username");
        }
        else if(password.getEditText().getText().toString().equals(""))
        {
            password.setError("jaza password");
            displayError("password");
        }
        else
        {
            ingizaData();
            progressDialog.show();
        }
    }
    void displayError(String putError)
    {
        Snackbar.make(error,putError,Snackbar.LENGTH_SHORT).show();
    }
    void ingizaData()
    {
String username2=username.getEditText().getText().toString();
String password2=password.getEditText().getText().toString();
Call<ApiResponse>call= Connection.getRetrofit().create(Interface.class).login(username2,password2);
call.enqueue(new Callback<ApiResponse>() {
    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
        if(response.code()==200)
        {
            if(response.body().getStatus().equals("ok"))
            {
                if(response.body().getResultCode()==1)
                {
                    //user yupo
                    displayError("welcome you are login");
                    progressDialog.dismiss();

                    SharedPreferences.Editor editor=USERNAME.edit();
                    SharedPreferences.Editor editor1=DEPARTMENT.edit();
                    SharedPreferences.Editor editor2=ROLE.edit();
                    SharedPreferences.Editor editor3=EMAIL.edit();

                    editor.putString("USERNAME",response.body().getUserExist());
                    editor1.putString("DEPARTMENT",response.body().getDepartment());
                    editor2.putString("ROLE",response.body().getRole());
                    editor.putString("EMAIL",response.body().getEmail());

                    editor.apply();
                    editor1.apply();
                    editor2.apply();
                    editor3.apply();

                    Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();


                }
                else
                {
                    //user hayupo
                    displayError("invalid username or password");
                    progressDialog.dismiss();
                }

            }
            else
            {
                //database error
                displayError("database error !");
                progressDialog.dismiss();
            }
        }
        else
        {
            //connection error
            displayError("connection error");
            progressDialog.dismiss();
        }
    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {

    }
});
    }
}