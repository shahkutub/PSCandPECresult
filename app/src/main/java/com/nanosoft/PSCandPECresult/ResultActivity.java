package com.nanosoft.PSCandPECresult;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity {


    TextView div_name, dis_name, tha_name, sch_name, sch_type, stu_roll, fat_name, mot_name, stu_name, scholar_status;
    TextView banGrade, engGrade, mathGrade, bdIntGrade, sciGrade, relGrade, avgGrade, textTotalNumber, textBangNumber, textEngNumber, textMathNumber, textBanIntNumber, textSciNumber, textRelNumber;
    Intent intent;
    public String div_val, tha_val, dis_val, roll_val, sub1_val, sub2_val, sub3_val, sub4_val, bngNumer, engNumber, mathNumber, relNumber, baInNumber, sciNumber,
            sub5_val, sub6_val, avgpoint_val, TOTAL, stuName, sclName, sclType, fatherName, motherName, scholarStatus, TYEAR;
    //private int roll_val;
    private String exam_type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        intent = getIntent();
        //exam_type=intent.getIntExtra("EXAM_TYPE",0);
        exam_type = intent.getStringExtra("EXAM_TYPE");
        div_val = intent.getStringExtra("DIVISION");
        dis_val = intent.getStringExtra("DISTRICT");
        tha_val = intent.getStringExtra("THANA");
        roll_val = intent.getStringExtra("ROLL_NO");

        stuName = intent.getStringExtra("STU_NAME");
        sclName = intent.getStringExtra("SCL_NAME");
        sclType = intent.getStringExtra("SCL_TYPE");
        fatherName = intent.getStringExtra("FAT_NAME");
        motherName = intent.getStringExtra("MOT_NAME");
        scholarStatus = intent.getStringExtra("SCHOLAR_STATUS");

        sub1_val = intent.getStringExtra("SUB1");
        sub2_val = intent.getStringExtra("SUB2");
        sub3_val = intent.getStringExtra("SUB3");
        sub4_val = intent.getStringExtra("SUB4");
        sub5_val = intent.getStringExtra("SUB5");
        sub6_val = intent.getStringExtra("SUB6");

        bngNumer = intent.getStringExtra("SUBn1");
        engNumber = intent.getStringExtra("SUBn2");
        mathNumber = intent.getStringExtra("SUBn3");
        baInNumber = intent.getStringExtra("SUBn4");
        sciNumber = intent.getStringExtra("SUBn5");
        relNumber = intent.getStringExtra("SUBn6");

        TYEAR = intent.getStringExtra("TYEAR");

        avgpoint_val = intent.getStringExtra("AVGPOINT");
        TOTAL = intent.getStringExtra("TOTAL");
/*		sub1_val=intent.getStringExtra("SUB1");
        sub1_val=intent.getStringExtra("SUB1");*/


        div_name = (TextView) findViewById(R.id.textDivision);
        dis_name = (TextView) findViewById(R.id.textDistrict);
        tha_name = (TextView) findViewById(R.id.textThana);
        sch_name = (TextView) findViewById(R.id.textScName);
        sch_type = (TextView) findViewById(R.id.textScType);
        stu_name = (TextView) findViewById(R.id.textStdName);
        stu_roll = (TextView) findViewById(R.id.textRollNo);
        fat_name = (TextView) findViewById(R.id.textFatName);
        mot_name = (TextView) findViewById(R.id.textMotName);
        scholar_status = (TextView) findViewById(R.id.tvScholarship);

        banGrade = (TextView) findViewById(R.id.textBanGrade);
        engGrade = (TextView) findViewById(R.id.textEngGrade);
        mathGrade = (TextView) findViewById(R.id.textMathGrade);
        bdIntGrade = (TextView) findViewById(R.id.textBanIntGrade);
        sciGrade = (TextView) findViewById(R.id.textSciGrade);
        relGrade = (TextView) findViewById(R.id.textRelGrade);
        avgGrade = (TextView) findViewById(R.id.textTotalGrade);
        textTotalNumber = (TextView) findViewById(R.id.textTotalNumber);

        textBangNumber = (TextView) findViewById(R.id.textBangNumber);
        textEngNumber = (TextView) findViewById(R.id.textEngNumber);
        textMathNumber = (TextView) findViewById(R.id.textMathNumber);
        textBanIntNumber = (TextView) findViewById(R.id.textBanIntNumber);
        textSciNumber = (TextView) findViewById(R.id.textSciNumber);
        textRelNumber = (TextView) findViewById(R.id.textRelNumber);

        //seeing if datafiels is empty
		/*
		if( div_val!=null && tha_val!=null && stuName!=null && sclType!=null && fatherName!=null &&motherName!=null
			&& scholarStatus!=null && roll_val!=null && sub1_val!=null && sub2_val!=null && sub3_val!=null && sub4_val!=null
				&& sub5_val!=null && sub6_val!=null && avgpoint_val!=null){
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}else{
			div_name.setText(div_val);
			tha_name.setText(tha_val);
			stu_name.setText(stuName);
			sch_name.setText(sclName);
			sch_type.setText(sclType);
			fat_name.setText(fatherName);
			mot_name.setText(motherName);
			scholar_status.setText("");
			stu_roll.setText(roll_val);
			banGrade.setText(sub1_val);
			engGrade.setText(sub2_val);
			mathGrade.setText(sub3_val);
			bdIntGrade.setText(sub4_val);
			sciGrade.setText(sub5_val);
			relGrade.setText(sub6_val);
			avgGrade.setText(avgpoint_val);
		}

		*/

        if (div_val != null) {
            div_name.setText(div_val);
        } else {
            Toast.makeText(getApplicationContext(), "বিভাগ পাওয়া যায়নি !!", Toast.LENGTH_LONG).show();
        }
        if (dis_val != null) {
            dis_name.setText(dis_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (tha_val != null) {
            tha_name.setText(tha_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }

        if (stuName != null) {
            stu_name.setText(stuName);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (sclName != null) {
            sch_name.setText(sclName);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (sclType != null) {
            sch_type.setText(sclType);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }


        if (fatherName != null) {
            fat_name.setText(fatherName);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (motherName != null) {
            mot_name.setText(motherName);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (scholarStatus != null) {
            //scholar_status.setText(scholarStatus); //Commented by Kamrul 30/12/2015
            scholar_status.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }


        if (roll_val != null) {
            stu_roll.setText(roll_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (sub1_val != null) {
            banGrade.setText(sub1_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (sub2_val != null) {
            engGrade.setText(sub2_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (sub3_val != null) {
            mathGrade.setText(sub3_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (sub4_val != null) {
            bdIntGrade.setText(sub4_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
        if (sub5_val != null) {
            sciGrade.setText(sub5_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }


        if (sub6_val != null) {
            relGrade.setText(sub6_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }


        if (bngNumer != null) {
            textBangNumber.setText(bngNumer);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }

        if (engNumber != null) {
            textEngNumber.setText(engNumber);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }

        if (mathNumber != null) {
            textMathNumber.setText(mathNumber);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }

        if (baInNumber != null) {
            textBanIntNumber.setText(baInNumber);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }


        if (sciNumber != null) {
            textSciNumber.setText(sciNumber);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }

        if (relNumber != null) {
            textRelNumber.setText(relNumber);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }


        if (avgpoint_val != null) {
            avgGrade.setText("জি পি এ: " + avgpoint_val);
        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }


        if (TOTAL != null) {

            textTotalNumber.setText("মোট : " + TOTAL);

        } else {
            Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
        }
		
		/*
		Toast.makeText(getApplicationContext(),"SUB1 VALUE :"+ sub1_val, Toast.LENGTH_LONG).show();
				Toast.makeText(getApplicationContext(), div_val, Toast.LENGTH_LONG).show();
		Toast.makeText(getApplicationContext(), dis_val, Toast.LENGTH_LONG).show();
		
		Toast.makeText(getApplicationContext(), tha_val, Toast.LENGTH_LONG).show();
		Toast.makeText(getApplicationContext(), roll_val, Toast.LENGTH_LONG).show();*/
/*		if(exam_type.contentEquals("Primary")){			
			Toast.makeText(getApplicationContext(), "SUCCESS!!", Toast.LENGTH_LONG).show();
		}else if(exam_type.contentEquals("Ebtedaye")) {
			Toast.makeText(getApplicationContext(), "ANOTHER SUCCESS!!", Toast.LENGTH_LONG).show();
		}
		*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result, menu);
        return true;
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
