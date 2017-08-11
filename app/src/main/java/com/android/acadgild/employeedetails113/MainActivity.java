package com.android.acadgild.employeedetails113;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.acadgild.employeedetails113.database.DBHelper;
import com.android.acadgild.employeedetails113.model.EmployeeData;
import com.android.acadgild.employeedetails113.utils.CommonUtilities;
import com.android.acadgild.employeedetails113.utils.Constants;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {


    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper= CommonUtilities.getDBObject(this);
        //To Insert record of Employee into DB
        //insertEmployeeRecord();

        new LoadImageFromDatabaseTask().execute(0);
    }


    // To insert Employee Record
    private void insertEmployeeRecord(){

        Drawable dbDrawable = getResources().getDrawable(R.drawable.empphoto);
        Bitmap bitmap = ((BitmapDrawable)dbDrawable).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            //Inserting values to Employee table.
            ContentValues vals = new ContentValues();
            vals.put(Constants.EMP_ID, "1");
            vals.put(Constants.EMP_NAME, "Jaldip Gadhiya");
            vals.put(Constants.EMP_AGE, "30");
            vals.put(Constants.EMP_PHOTO_IMAGE, stream.toByteArray());
            dbHelper.insertContentVals(Constants.EMPLOYEE_RECORD, vals);


    }

    //Class to load image from database
    private class LoadImageFromDatabaseTask extends AsyncTask<Integer, Integer, EmployeeData> {

        private final ProgressDialog LoadImageProgressDialog =  new ProgressDialog(MainActivity.this);

        protected void onPreExecute() {
            this.LoadImageProgressDialog.setMessage("Loading Image from Db...");
            this.LoadImageProgressDialog.show();
        }

        @Override
        protected EmployeeData doInBackground(Integer... integers) {
            ///Get EmployeeData
            return dbHelper.getAllEmployee();
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(EmployeeData imageHelper) {


            if (this.LoadImageProgressDialog.isShowing()) {
                this.LoadImageProgressDialog.dismiss();
            }
            TextView eName = (TextView) findViewById(R.id.etName);
            TextView eAge = (TextView) findViewById(R.id.etAge);
            //To set retrived detail from DB to UI
            eName.setText(imageHelper.getEmpName().toString());
            eAge.setText(imageHelper.getEmpAge().toString());
            setUpImage(imageHelper.getEmpPhotoImage());
        }

    }

    //To set image retrived from DB to Imageview
    private void setUpImage(byte[] bytes) {

        ImageView imageview = (ImageView)findViewById(R.id.imgEmpPhoto);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imageview.setImageBitmap(bitmap);
    }

}
