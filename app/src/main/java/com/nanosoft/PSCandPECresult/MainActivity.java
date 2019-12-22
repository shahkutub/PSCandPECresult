package com.nanosoft.PSCandPECresult;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dataSource.commonData;
import jsonManager.ConnectionDetector;
import jsonManager.JSONParser;

public class MainActivity extends Activity {

    private final Context context = this;
    final String[] examname = {"নির্বাচন করুন", "প্রাথমিক শিক্ষা সমাপনী", "ইবতেদায়ি শিক্ষা সমাপনী"};
    // final String[] examYear = {"নির্বাচন করুন", "২০১৫", "২০১৪", "২০১৩"};
    private static String DIV_URL, DIS_URL, THA_URL, DATA_URL, YEAR_URL, div_val = null;
    public static int DIV_ID = 0, DIS_ID = 0, THA_ID = 0, typeint, type_int, year_int, div_int, dis_int;
    public String studentRoll = "", sub1, sub2, sub3, sub4, sub5, sub6, avgpoint,TOTAL,
            scl_name, scl_type, fat_name, mot_name, std_name, schol_status;
    private String SUBn1,SUBn2,SUBn3,SUBn4,SUBn5,SUBn6;

    /*SUB1": "89",
            "SUB2": "94",
            "SUB3": "98",
            "SUB4": "92",
            "SUB5": "88",
            "SUB6"*/

    public String divNa, disNa, type_val, tha_val, EXAM_TYPE_ID, yearNa;
    //	String studentRoll;
    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    Spinner ExamType, ExamYr, stuDivision, stuDistrict, stuUpozila;
    EditText stuRollNo;
    Button stuSubmit, stuResubmit;
    public ConnectionDetector cd;
    ProgressDialog pDialog;
    ArrayAdapter<String> adapter, adapter1;
    ArrayList<commonData> divisionsList, districtsList, thanalist, yearList;
    String exam_year;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DIV_URL = "http://180.211.137.51:3472/index.php/welcome/index/division";
        YEAR_URL = "http://180.211.137.51:3472/index.php/welcome/index/year";

        //	DIV_URL="http://10.0.2.2/CodeIgniterPSC/index.php/welcome/index/division";

        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView8);

        ExamType = (Spinner) findViewById(R.id.spnExamType);
        ExamYr = (Spinner) findViewById(R.id.spnExamYear);
        stuDivision = (Spinner) findViewById(R.id.spnDivision);
        stuDistrict = (Spinner) findViewById(R.id.spnDistrict);
        stuUpozila = (Spinner) findViewById(R.id.spnUpozila);
        stuRollNo = (EditText) findViewById(R.id.rollNoEtext);
        stuSubmit = (Button) findViewById(R.id.btnSubmit);
        stuResubmit = (Button) findViewById(R.id.btnReSubmit);

        adapter = new ArrayAdapter<String>
                (context, android.R.layout.simple_spinner_item, examname);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ExamType.setAdapter(adapter);
        /*
        adapter1 = new ArrayAdapter<String>
                (context, android.R.layout.simple_spinner_item, examYear);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ExamYr.setAdapter(adapter1);
        */
        if (isNetworkAvailable()) {

            ExamType.setOnItemSelectedListener(examtype_listener);
            ExamYr.setOnItemSelectedListener(year_listener);
            // new GetDivision().execute();
            stuDivision.setOnItemSelectedListener(div_listener);
            stuDistrict.setOnItemSelectedListener(dis_listener);
            stuUpozila.setOnItemSelectedListener(thana_listener);
            //		new GetData().execute();
        } else {

            Toast.makeText(context, "SORRY!No Network Available!!", Toast.LENGTH_SHORT).show();
        }
        stuSubmit.setOnClickListener(btn_submit);
        stuResubmit.setOnClickListener(btn_resubmit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    OnItemSelectedListener examtype_listener = new OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                   long Id) {

            EXAM_TYPE_ID = ExamType.getSelectedItem().toString();
            typeint = position;

            if (typeint > 0) {
                ExamType.setSelection(typeint);
                new GetYear().execute();
                //ExamType.setEnabled(false);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    };


    private String selectedYear;
    OnItemSelectedListener year_listener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                   long arg3) {


            year_int = position;
            selectedYear = ExamYr.getSelectedItem().toString();

            if (typeint > 0 && year_int > 0) {
                ExamYr.setSelection(year_int);
                year = Integer.parseInt(ExamYr.getSelectedItem().toString());
                // Toast.makeText(getApplicationContext(),year_int,Toast.LENGTH_LONG).show();
                new GetDivision().execute();
                yearNa = ExamYr.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), yearNa, Toast.LENGTH_LONG).show();

                //ExamYr.setEnabled(false);
            } else {
                ExamYr.setSelection(0);
                Toast.makeText(getApplicationContext(), "Select Exam Type First.", Toast.LENGTH_SHORT).show();
            }
            if (isNetworkAvailable()) {

            } else {
                Toast.makeText(getApplicationContext(), "তথ্য পাওয়া যায়নি", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    };

    OnItemSelectedListener div_listener = new OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                   long id) {
            div_int = position;

            if (typeint > 0 && year_int > 0 && isNetworkAvailable()) {
                stuDivision.setSelection(position);
                new GetDistrict().execute();
            } else {
                stuDivision.setSelection(0);
                Toast.makeText(getApplicationContext(), "Select Exam Type and Exam Year First.", Toast.LENGTH_SHORT).show();
            }
            if (typeint > 0 && position > 0 && year_int > 0) {

                //stuDivision.setEnabled(false);
                DIV_ID = Integer.parseInt(((commonData) stuDivision.getSelectedItem()).getId());
                divNa = stuDivision.getSelectedItem().toString();

                if (DIV_ID > 0) {
                    DIS_URL = "http://180.211.137.51:3472/index.php/welcome/index/district/" + DIV_ID;
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    };
    OnItemSelectedListener dis_listener = new OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                   long id) {
            dis_int = position;
            if (div_int > 0 && position > 0 && isNetworkAvailable()) {
                stuDistrict.setSelection(position);
                //stuDistrict.setEnabled(false);
                new GetThana().execute();

            }

            DIS_ID = Integer.parseInt(((commonData) stuDistrict.getSelectedItem()).getId());
            disNa = stuDistrict.getSelectedItem().toString();
            if (DIS_ID > 0) {

                THA_URL = "http://180.211.137.51:3472/index.php/welcome/index/thana/" + DIS_ID + "/" + DIV_ID;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    };

    OnItemSelectedListener thana_listener = new OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                   long Id) {
            if (position > 0 && dis_int > 0) {
                stuUpozila.setSelection(position);
                //stuUpozila.setEnabled(false);
            }

            THA_ID = Integer.parseInt(((commonData) stuUpozila.getSelectedItem()).getId());
//		Toast.makeText(getApplicationContext(), THA_ID, Toast.LENGTH_LONG).show();
            tha_val = stuUpozila.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    };

    //Fatching Year all data

    // Fetching Division All data
    private class GetDivision extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("অপেক্ষা করুন ...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            divisionsList = new ArrayList<commonData>();
            JSONParser jsonParser = new JSONParser();
            String json = null;
            if (DIV_URL != null) {
                json = jsonParser.makeServiceCall(DIV_URL, JSONParser.GET);
            }
            Log.e("Response: DIV", "> " + json);
            if (json != null) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    if (jsonArray != null) {
                        //	if (div_val.equals("null")) {
                        divisionsList.add(new commonData("0", "নির্বাচন করুন"));
                        //	}
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = (JSONObject) jsonArray.get(i);
                            String id = c.getString("DIV_CODE");
                            //		String  div_name = c.getString("DIV_NAME_BAN");
                            //		Toast.makeText(context, div_name, Toast.LENGTH_SHORT).show();
                            String name_eng = c.getString("DIV_NAME");
                            //   String name_eng= c.getString("DIV_CODE");
                            commonData data = new commonData(id, name_eng);
                            divisionsList.add(data);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Didn't receive any data from server!", Toast.LENGTH_SHORT).show();

                Log.e("JSON Data", "Didn't receive any data from server!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (divisionsList != null) {
                ArrayAdapter<commonData> divAdapter = new ArrayAdapter<commonData>(MainActivity.this,
                        android.R.layout.simple_spinner_item, divisionsList);
                divAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stuDivision.setAdapter(divAdapter);
            } else {
                Toast.makeText(getApplicationContext(), "তথ্য পাওয়া যায়নি", Toast.LENGTH_LONG).show();
            }
        }
    }
    // End Featvhing Division List

    // Featching year Item
    private class GetYear extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("অপেক্ষা করুন ...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            yearList = new ArrayList<commonData>();
            JSONParser jsonParser = new JSONParser();
            String json = null;
            if (YEAR_URL != null) {
                json = jsonParser.makeServiceCall(YEAR_URL, JSONParser.GET);
            }
            Log.e("Response: YEAR", "> " + json);
            if (json != null) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    if (jsonArray != null) {
                        //	if (div_val.equals("null")) {
                        yearList.add(new commonData("0", "নির্বাচন করুন"));
                        //	}
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = (JSONObject) jsonArray.get(i);
                            String id = c.getString("TYEAR_ID");
                            //		String  div_name = c.getString("DIV_NAME_BAN");
                            //		Toast.makeText(context, div_name, Toast.LENGTH_SHORT).show();
                            String name_eng = c.getString("TYEAR");

                            //   String name_eng= c.getString("DIV_CODE");
                            commonData data = new commonData(id, name_eng);
                            yearList.add(data);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
               // Toast.makeText(getApplicationContext(), "Didn't receive any data from server!", Toast.LENGTH_SHORT).show();

                Log.e("JSON Data", "Didn't receive any data from server!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (yearList != null) {
                ArrayAdapter<commonData> yearAdapter = new ArrayAdapter<commonData>(MainActivity.this,
                        android.R.layout.simple_spinner_item, yearList);
                yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ExamYr.setAdapter(yearAdapter);
            } else {
                Toast.makeText(getApplicationContext(), "তথ্য পাওয়া যায়নি", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Fetching District All data
    private class GetDistrict extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("অপেক্ষা করুন ...");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            districtsList = new ArrayList<commonData>();
            JSONParser jsonParser = new JSONParser();
            String json = null;
            if (DIS_URL != null) {
                json = jsonParser.makeServiceCall(DIS_URL, JSONParser.GET);
            }
            Log.e("Response: ", "> " + json);
            if (json != null) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    if (jsonArray != null) {
                        districtsList.add(new commonData("0", "নির্বাচন করুন"));
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = (JSONObject) jsonArray.get(i);
                        String id = c.getString("DIS_CODE");
                        //	String  dis_name = c.getString("DIS_NAME_BAN");
                        String name_eng = c.getString("DIS_NAME");
                        //	String name_eng= c.getString("DIS_CODE");
                        commonData district = new commonData(id, name_eng);
                        districtsList.add(district);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
                //		Toast.makeText(getApplicationContext(), "Connection Lost!!Try Again Later.", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (districtsList != null) {
                ArrayAdapter<commonData> disAdapter = new ArrayAdapter<commonData>(MainActivity.this,
                        android.R.layout.simple_spinner_item, districtsList);
                disAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stuDistrict.setAdapter(disAdapter);
            } else {
                Toast.makeText(getApplicationContext(), "তথ্য পাওয়া যায়নি", Toast.LENGTH_LONG).show();
            }
        }
    }

    private class GetThana extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("অপেক্ষা করুন ...");
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            thanalist = new ArrayList<commonData>();
            JSONParser jsonParser = new JSONParser();
            String json = null;
            if (THA_URL != null) {
                json = jsonParser.makeServiceCall(THA_URL, JSONParser.GET);
            }
            Log.e("Response: ", "> " + json);

            if (json != null) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    if (jsonArray != null) {
                        thanalist.add(new commonData("0", "নির্বাচন করুন"));
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = (JSONObject) jsonArray.get(i);
                        String id = c.getString("THANA");
                        //	String  dis_name = c.getString("DIS_NAME_BAN");
                        String name_eng = c.getString("THA_NAME");
                        //	String name_eng= c.getString("THANA");
                        commonData thana = new commonData(id, name_eng);
                        thanalist.add(thana);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
                //		Toast.makeText(getApplicationContext(), "Connection Lost!!Try Again Later.", Toast.LENGTH_SHORT).show();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (thanalist != null) {
                ArrayAdapter<commonData> thaAdapter = new ArrayAdapter<commonData>(MainActivity.this,
                        android.R.layout.simple_spinner_item, thanalist);
                thaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stuUpozila.setAdapter(thaAdapter);
            } else {
                Toast.makeText(getApplicationContext(), "তথ্য পাওয়া যায়নি", Toast.LENGTH_LONG).show();
            }
        }
    }

    View.OnClickListener btn_submit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isNetworkAvailable()) {
                if (year_int != 0 && dis_int != 0 && div_int != 0) {
                    studentRoll = stuRollNo.getText().toString();
                    new GetData().execute();
                } else {
                    Toast.makeText(getApplication(), "Data Field Empty!!", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
    View.OnClickListener btn_resubmit = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            // TODO Auto-generated method stub
            finish();
        }

    };

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("অপেক্ষা করুন ...");
            pDialog.setCancelable(true);
            pDialog.show();
        }
        public JSONObject f;
        @Override
        protected Void doInBackground(Void... arg0) {

            type_int = typeint + 10;
            JSONParser jsonParser = new JSONParser();
            String json = null;
            if (year_int > 0) {
                DATA_URL = "http://180.211.137.51:3472/index.php/welcome/index/data/" + type_int + "/" + THA_ID + "/" + studentRoll + "/"
                        + selectedYear;

                if (DATA_URL != null) {
                    Log.i("tt",DATA_URL);
                    json = jsonParser.makeServiceCall(DATA_URL, JSONParser.GET);
                }
            }
//            if (year_int == 1) {
//                DATA_URL = "http://180.211.137.51:3472/index.php/welcome/index/data/" + type_int + "/" + THA_ID + "/" + studentRoll + "/"
//                        + 2015;
//
//                Log.e("STUDENT DATA: ", "Entered 2015 ");
//                if (DATA_URL != null) {
//                    json = jsonParser.makeServiceCall(DATA_URL, JSONParser.GET);
//                }
//            } else if (year_int == 2) {
//                DATA_URL = "http://180.211.137.51:3472/index.php/welcome/index/data/" + type_int + "/" + THA_ID + "/" + studentRoll + "/"
//                        + 2014;
//
//                Log.e("STUDENT DATA: ", "Entered 2014 ");
//                if (DATA_URL != null) {
//                    json = jsonParser.makeServiceCall(DATA_URL, JSONParser.GET);
//                }
//            } else if (year_int == 3) {
//                Log.e("STUDENT DATA: ", "Entered 2013 ");
//                DATA_URL = "http://180.211.137.51:3472/index.php/welcome/index/data/" + type_int + "/" + THA_ID + "/" + studentRoll + "/"
//                        + 2013;
//
//
//                if (DATA_URL != null) {
//                    json = jsonParser.makeServiceCall(DATA_URL, JSONParser.GET);
//                }
//            } else if (year_int == 4) {
//                Log.e("STUDENT DATA: ", "Entered 2012 ");
//                DATA_URL = "http://180.211.137.51:3472/index.php/welcome/index/data/" + type_int + "/" + THA_ID + "/" + studentRoll + "/"
//                        + 2012;
//
//
//                if (DATA_URL != null) {
//                    json = jsonParser.makeServiceCall(DATA_URL, JSONParser.GET);
//                }
//            } else if (year_int == 5) {
//                Log.e("STUDENT DATA: ", "Entered 2011 ");
//                DATA_URL = "http://180.211.137.51:3472/index.php/welcome/index/data/" + type_int + "/" + THA_ID + "/" + studentRoll + "/"
//                        + 2011;
//
//
//                if (DATA_URL != null) {
//                    json = jsonParser.makeServiceCall(DATA_URL, JSONParser.GET);
//                }
//            }
//            Log.e("STUDENT DATA: ", "> " + json);
            if (json != null) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    if (jsonArray != null) {

                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                         f = (JSONObject) jsonArray.get(i);
                        sub1 = f.getString("SUB1G");
                        //	String  dis_name = c.getString("DIS_NAME_BAN");
                        sub2 = f.getString("SUB2G");
                        sub3 = f.getString("SUB3G");
                        sub4 = f.getString("SUB4G");
                        sub5 = f.getString("SUB5G");
                        sub6 = f.getString("SUB6G");
                        //number
                        SUBn1 = f.getString("SUB1");
                        SUBn2 = f.getString("SUB2");
                        SUBn3 = f.getString("SUB3");
                        SUBn4 = f.getString("SUB4");
                        SUBn5 = f.getString("SUB5");
                        SUBn6 = f.getString("SUB6");

                        avgpoint = f.getString("AVG_GPA");
                        TOTAL = f.getString("TOTAL");
                        std_name = f.getString("STD_NAME");
                        scl_name = f.getString("SCH_NAME");
                        scl_type = f.getString("TYPEB");
                        fat_name = f.getString("FATHER_NAME");
                        mot_name = f.getString("MOTHER_NAME");
                        schol_status = f.getString("STATUS_SCHOLAR_DETAIL");
                        if (schol_status.equals("N")) {

                            schol_status = "বৃত্তি পায়নি";
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
                //		Toast.makeText(getApplicationContext(), "Connection Lost!!Try Again Later.", Toast.LENGTH_SHORT).show();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

            //	Toast.makeText(getApplicationContext(), sub1, Toast.LENGTH_SHORT).show();
            if(f!=null) {


                if (type_int == 11) {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);

                    intent.putExtra("EXAM_TYPE", EXAM_TYPE_ID);
                    intent.putExtra("STU_NAME", std_name);
                    intent.putExtra("SCL_NAME", scl_name);
                    intent.putExtra("SCL_TYPE", scl_type);
                    intent.putExtra("FAT_NAME", fat_name);
                    intent.putExtra("MOT_NAME", mot_name);
                    intent.putExtra("SUB1", sub1);
                    intent.putExtra("SUB2", sub2);
                    intent.putExtra("SUB3", sub3);
                    intent.putExtra("SUB4", sub4);
                    intent.putExtra("SUB5", sub5);
                    intent.putExtra("SUB6", sub6);


                    intent.putExtra("SUBn1", SUBn1);
                    intent.putExtra("SUBn2", SUBn2);
                    intent.putExtra("SUBn3", SUBn3);
                    intent.putExtra("SUBn4", SUBn4);
                    intent.putExtra("SUBn5", SUBn5);
                    intent.putExtra("SUBn6", SUBn6);


                    intent.putExtra("AVGPOINT", avgpoint);
                    intent.putExtra("TOTAL", TOTAL);
                    intent.putExtra("DIVISION", divNa);
                    intent.putExtra("DISTRICT", disNa);
                    intent.putExtra("THANA", tha_val);
                    intent.putExtra("ROLL_NO", studentRoll);
                    intent.putExtra("SCHOLAR_STATUS", schol_status);

                   // intent.putExtra("TYEAR", TYEAR);
                    startActivity(intent);
                    //		Toast.makeText(getApplicationContext(), "তথ্য পাওয়া যায়নি", Toast.LENGTH_LONG).show();

                } else if (type_int == 12) {
                    Intent intent2 = new Intent(MainActivity.this, EbtedaeActivity.class);

                    intent2.putExtra("EXAM_TYPE", EXAM_TYPE_ID);
                    intent2.putExtra("STU_NAME", std_name);
                    intent2.putExtra("SCL_NAME", scl_name);
                    intent2.putExtra("SCL_TYPE", scl_type);
                    intent2.putExtra("FAT_NAME", fat_name);
                    intent2.putExtra("MOT_NAME", mot_name);
                    intent2.putExtra("SUB1", sub1);
                    intent2.putExtra("SUB2", sub2);
                    intent2.putExtra("SUB3", sub3);
                    intent2.putExtra("SUB4", sub4);
                    intent2.putExtra("SUB5", sub5);
                    intent2.putExtra("SUB6", sub6);


                    intent2.putExtra("SUBn1", SUBn1);
                    intent2.putExtra("SUBn2", SUBn2);
                    intent2.putExtra("SUBn3", SUBn3);
                    intent2.putExtra("SUBn4", SUBn4);
                    intent2.putExtra("SUBn5", SUBn5);
                    intent2.putExtra("SUBn6", SUBn6);


                    intent2.putExtra("AVGPOINT", avgpoint);
                    intent2.putExtra("TOTAL", TOTAL);
                    intent2.putExtra("DIVISION", divNa);
                    intent2.putExtra("DISTRICT", disNa);
                    intent2.putExtra("THANA", tha_val);
                    intent2.putExtra("ROLL_NO", studentRoll);
                    intent2.putExtra("SCHOLAR_STATUS", schol_status);
                    startActivity(intent2);
                    //		Toast.makeText(getApplicationContext(), "তথ্য পাওয়া যায়নি", Toast.LENGTH_LONG).show();

                }

            }else{

                Toast.makeText(context, "তথ্য পাওয়া যায়নি", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NetInfo = manager.getActiveNetworkInfo();
        if (NetInfo != null) {
            if (NetInfo.isAvailable() && NetInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }
}

