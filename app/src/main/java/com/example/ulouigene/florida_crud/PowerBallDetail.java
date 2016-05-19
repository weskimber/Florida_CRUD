package com.example.ulouigene.florida_crud;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
/**
 * Created by ulouigene on 5/11/2016.
 */
public class PowerBallDetail extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextAge;
    private int _Student_Id=0;
    RestService restService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restService = new RestService();
        setContentView(R.layout.activity_powerball_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _Student_Id =0;
        Intent intent = getIntent();
        _Student_Id =intent.getIntExtra("student_Id", 0);
        if (_Student_Id>0){
            restService.getService().getPowerBallById(_Student_Id, new Callback<PowerBall>() {
                @Override
                public void success(PowerBall powerball, Response response) {

                    editTextAge.setText(String.valueOf(powerball.First));
                    editTextName.setText(powerball.Second);
                    editTextEmail.setText(powerball.X);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(PowerBallDetail.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_student_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (findViewById(R.id.btnDelete)==v){
            restService.getService().deletePowerBallById(_Student_Id, new Callback<PowerBall>() {
                @Override
                public void success(PowerBall powerball, Response response) {
                    Toast.makeText(PowerBallDetail.this, "Student Record Deleted", Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(PowerBallDetail.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();

                }

            });

            finish();
        }else if (v== findViewById(R.id.btnClose)){
            finish();
        }else if (findViewById(R.id.btnSave)==v){

            PowerBall powerball=new PowerBall();
            Integer status =0;
            powerball.X= editTextEmail.getText().toString();
            powerball.Second=Integer.parseInt(editTextName.getText().toString());
            powerball.First= Integer.parseInt(editTextAge.getText().toString());
            powerball.ID = _Student_Id;

            if (_Student_Id == 0) {
                restService.getService().addPowerBall(powerball, new Callback<PowerBall>() {
                    @Override
                    public void success(PowerBall powerball, Response response) {
                        Toast.makeText(PowerBallDetail.this, "New Student Inserted.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(PowerBallDetail.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }else{
                restService.getService().updatePowerBallById(_Student_Id,powerball , new Callback<PowerBall>() {
                    @Override
                    public void success(PowerBall powerball, Response response) {
                        Toast.makeText(PowerBallDetail.this, "Student Record updated.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(PowerBallDetail.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        }
    }
}
