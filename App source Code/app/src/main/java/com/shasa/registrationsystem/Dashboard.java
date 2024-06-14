package com.shasa.registrationsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

  SharedPreferences sharedPreferences;
    SharedPreferences USERNAME;
    String prefNameUSERNAME="username";


    SharedPreferences DEPARTMENT;
    String prefNameDEPARTMENT="department";


    SharedPreferences EMAIL;
    String prefNameEMAIL="email";


    SharedPreferences ROLE;
    String prefNameROLE="role";

    TextView username,department,email,role,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        username=findViewById(R.id.userProfile);
        department=findViewById(R.id.departmentProfile);
        email=findViewById(R.id.emailProfile);
        role=findViewById(R.id.role);
        logout=findViewById(R.id.logout);

        USERNAME=getSharedPreferences(prefNameUSERNAME, Context.MODE_PRIVATE);
        DEPARTMENT=getSharedPreferences(prefNameDEPARTMENT, Context.MODE_PRIVATE);
        EMAIL=getSharedPreferences(prefNameEMAIL, Context.MODE_PRIVATE);
        ROLE=getSharedPreferences(prefNameROLE, Context.MODE_PRIVATE);

        username.setText(USERNAME.getString("USERNAME","Not Registered"));
        department.setText(DEPARTMENT.getString("DEPARTMENT","Not Registered"));
        email.setText(EMAIL.getString("EMAIL","Not Registered"));
        role.setText(ROLE.getString("ROLE","Not Registered"));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                logout();
            }
        });
    }
    void logout()
    {

        //alert dialog after clicking signout button on nav header-------------------------------------------------------------
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Logout") .setTitle("Confirm logout ?");
        builder.setMessage(" Confirm Logout "+USERNAME.getString("USERNAME","Not Registered"))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(DialogInterface dialog, int id)
                    {
                        SharedPreferences.Editor editor=USERNAME.edit();
                        editor.clear();
                        editor.apply();
                        editor.commit();
                        Intent intent=new Intent(getApplicationContext(),Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "logout Sucessfull", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"action aborted",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Notification");
        alert.show();

        //alert dialog mwisho-------------------------------------------------------------

    }
}