package com.shasa.registrationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.shasa.registrationsystem.PHP_API.ApiResponse;
import com.shasa.registrationsystem.PHP_API.Connection;
import com.shasa.registrationsystem.PHP_API.Interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
TextInputLayout username,password,confirmPassword,email;
Spinner department;
Button register;
LinearLayout error;
ProgressDialog progressDialog;
TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username=findViewById(R.id.username_reg);
        password=findViewById(R.id.password_reg);
        confirmPassword=findViewById(R.id.confirm_password);
        email=findViewById(R.id.email_reg);
        department=findViewById(R.id.department);
        register=findViewById(R.id.btn_reg);
        error=findViewById(R.id.error_reg);
        login=findViewById(R.id.btnLOGIN);
        progressDialog=new ProgressDialog(this);
        getSupportActionBar().hide();

        progressDialog.setTitle("Notification");
        progressDialog.setMessage("tafadhari subiri...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }


    void displayError(String putError)
    {
        Snackbar.make(error,putError,Snackbar.LENGTH_SHORT).show();

    }

    void validate(){
        if(username.getEditText().getText().toString().equals(""))
        {
            username.setError("ingiza username");
            displayError("andika username");
        }
        else if(password.getEditText().getText().toString().equals(""))
        {
            password.setError("ingiza password");
            displayError("andika password");
        }
        else if(!confirmPassword.getEditText().getText().toString().equals(password.getEditText().getText().toString()))
            {

               confirmPassword.setError("passwords did not match");
                displayError("match the passwords");
            }
        else if(department.getSelectedItem().toString().equals("Select Department"))
        {
            displayError("Select department");
        }
        else
        {
            ingizaData();
            progressDialog.show();
        }

    }
    void ingizaData()
    {
        String username2=username.getEditText().getText().toString();
        String password2=password.getEditText().getText().toString();
        String department2=department.getSelectedItem().toString();
        String email2=email.getEditText().getText().toString();

        Call<ApiResponse> call= Connection.getRetrofit().create(Interface.class).registration_users(username2,password2,email2,department2);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {


              if(response.code()==200)
              {
                  if(response.body().getStatus().equals("ok"))
                  {
                     //endelea
                      if(response.body().getResultCode()==0)
                      {
                          //user yupo
                          displayError("The user has already exist");
                          progressDialog.dismiss();
                          showPOPUP();
                      }
                      else if(response.body().getResultCode()==1)
                      {
                          //user hayupo na umefanikiwa kusajiri
                          displayError("you have successfull register the user");
                          progressDialog.dismiss();
                          Intent intent=new Intent(getApplicationContext(),Login.class);
                          intent.putExtra("username",username2);
                          intent.putExtra("password",password2);
                          startActivity(intent);
                      }
                  }
                  else if(response.body().getStatus().equals("failed"))
                  {
                      //query of inserting data into table has failed
                      displayError("please try again later..");
                      progressDialog.dismiss();
                  }
                  else if(response.body().getStatus().equals("dberror"))
                  {
                      //database error
                      displayError("database error");
                      progressDialog.dismiss();
                  }


              }
                else
              {
                  //connection error
              }





            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });


    }
    void showPOPUP()
        {

            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(this).inflate(R.layout.userexist, viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogView);
            //find views
             TextView tittle=dialogView.findViewById(R.id.tittle);
            TextView tittle2=dialogView.findViewById(R.id.tittle2);
            ImageView canvas=dialogView.findViewById(R.id.imageDisplay);
            tittle.setTextColor(getApplicationContext().getResources().getColor(R.color.red));
            tittle.setText("Registration Failed !");
            tittle2.setText("user Exist!");

            canvas.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.warning_48px));
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            Button button=dialogView.findViewById(R.id.buttonOk);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

        }
    }
