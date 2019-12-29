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


public class EbtedaeActivity extends Activity {

	
	
	TextView div_name,dis_name,tha_name,sch_name,sch_type,stu_roll,fat_name,mot_name,stu_name,scholar_status;
	TextView textNumber1,textGrade1,textNumber2,textGrade2,textNumber3,textGrade3,textNumber4,textGrade4,textNumber5,textGrade5,
			textNumber6,textGrade6,textTotalGrade,textTotalNumber;
	Intent intent;
	public String div_val,tha_val,dis_val,roll_val,sub1_val,sub2_val,sub3_val,
	sub4_val,sub5_val,sub6_val,avgpoint_val,stuName,sclName,sclType,fatherName,motherName,scholarStatus;
//private int roll_val;
	private String exam_type;
	private String number1,number2,number3,number4,number5,number6,TOTAL;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ebtedae);
			
		intent = getIntent();
		//exam_type=intent.getIntExtra("EXAM_TYPE",0);
		exam_type=intent.getStringExtra("EXAM_TYPE");
		div_val=intent.getStringExtra("DIVISION");
		dis_val=intent.getStringExtra("DISTRICT");
		tha_val=intent.getStringExtra("THANA");
		roll_val=intent.getStringExtra("ROLL_NO");
		
		stuName=intent.getStringExtra("STU_NAME");
		sclName=intent.getStringExtra("SCL_NAME");
		sclType=intent.getStringExtra("SCL_TYPE");
		fatherName=intent.getStringExtra("FAT_NAME");
		motherName=intent.getStringExtra("MOT_NAME");
		scholarStatus=intent.getStringExtra("SCHOLAR_STATUS");
		
		sub1_val=intent.getStringExtra("SUB1");
		sub2_val=intent.getStringExtra("SUB2");
		sub3_val=intent.getStringExtra("SUB3");
		sub4_val=intent.getStringExtra("SUB4");
		sub5_val=intent.getStringExtra("SUB5");
		sub6_val=intent.getStringExtra("SUB6");
		//avgpoint_val=intent.getStringExtra("AVGPOINT");
/*		sub1_val=intent.getStringExtra("SUB1");
		sub1_val=intent.getStringExtra("SUB1");*/



		number1 = intent.getStringExtra("SUBn1");
		number2 = intent.getStringExtra("SUBn2");
		number3 = intent.getStringExtra("SUBn3");
		number4 = intent.getStringExtra("SUBn4");
		number5 = intent.getStringExtra("SUBn5");
		number6 = intent.getStringExtra("SUBn6");

		avgpoint_val = intent.getStringExtra("AVGPOINT");
		TOTAL = intent.getStringExtra("TOTAL");
		
		
		div_name=(TextView) findViewById(R.id.textDivision);
		dis_name=(TextView) findViewById(R.id.textDistrict);
		tha_name=(TextView) findViewById(R.id.textThana);
		sch_name=(TextView) findViewById(R.id.textScName);
		sch_type=(TextView) findViewById(R.id.textScType);
		stu_name=(TextView) findViewById(R.id.textStdName);
		stu_roll=(TextView) findViewById(R.id.textRollNo);
		fat_name=(TextView) findViewById(R.id.textFatName);
		mot_name=(TextView) findViewById(R.id.textMotName);
	//	scholar_status=(TextView) findViewById(R.id.tvScholarship);

		textNumber1=(TextView) findViewById(R.id.textNumber1);
		textGrade1=(TextView) findViewById(R.id.textGrade1);

		textNumber2=(TextView) findViewById(R.id.textNumber2);
		textGrade2=(TextView) findViewById(R.id.textGrade2);

		textNumber3=(TextView) findViewById(R.id.textNumber3);
		textGrade3=(TextView) findViewById(R.id.textGrade3);

		textNumber4=(TextView) findViewById(R.id.textNumber4);
		textGrade4=(TextView) findViewById(R.id.textGrade4);

		textNumber5=(TextView) findViewById(R.id.textNumber5);
		textGrade5=(TextView) findViewById(R.id.textGrade5);

		textNumber6=(TextView) findViewById(R.id.textNumber6);
		textGrade6=(TextView) findViewById(R.id.textGrade6);


		textTotalGrade=(TextView) findViewById(R.id.textTotalGrade);
		textTotalNumber=(TextView) findViewById(R.id.textTotalNumber);




		
		if(div_val!=null){
			div_name.setText(div_val);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(dis_val!=null){
			dis_name.setText(dis_val);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(tha_val!=null){
			tha_name.setText(tha_val);
		}
		else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		
		if(stuName!=null){
			stu_name.setText(stuName);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(sclName!=null){
			sch_name.setText(sclName);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(sclType!=null){

			sch_type.setText(sclType);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		
		
		if(fatherName!=null){
			fat_name.setText(fatherName);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(motherName!=null){
			mot_name.setText(motherName);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
	/*	if(scholarStatus!=null){
			scholar_status.setText(scholarStatus);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}*/
		
		
		if(roll_val!=null){
			stu_roll.setText(roll_val);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(sub1_val!=null){
			textGrade1.setText(sub1_val);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(sub2_val!=null){
			textGrade2.setText(sub2_val);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(sub3_val!=null){
			textGrade3.setText(sub3_val);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(sub4_val!=null){
			textGrade4.setText(sub4_val);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(sub5_val!=null){
			textGrade5.setText(sub5_val);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
		if(sub6_val!=null){
			textGrade6.setText(sub6_val);
		}else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}




		if (number1 != null) {
			textNumber1.setText(number1);
		} else {
			//Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}

		if (number2 != null) {
			textNumber2.setText(number2);
		} else {
			//Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}

		if (number3 != null) {
			textNumber3.setText(number3);
		} else {
			//Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}

		if (number4 != null) {
			textNumber4.setText(number4);
		} else {
			//Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}


		if (number5 != null) {
			textNumber5.setText(number5);
		} else {
			//Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}

		if (number6 != null) {
			textNumber6.setText(number6);
		} else {
			//Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}


		if (avgpoint_val != null) {
			textTotalGrade.setText("জি পি এ: " + avgpoint_val);
		} else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}


		if (TOTAL != null) {

			textTotalNumber.setText("মোট : " + TOTAL);

		} else {
			Toast.makeText(getApplicationContext(), "EMPTY DATA!!", Toast.LENGTH_LONG).show();
		}
	
//		Toast.makeText(getApplicationContext(),"SUB1 VALUE :"+ sub1_val, Toast.LENGTH_LONG).show();
		/*		Toast.makeText(getApplicationContext(), div_val, Toast.LENGTH_LONG).show();
		Toast.makeText(getApplicationContext(), dis_val, Toast.LENGTH_LONG).show();
		
		Toast.makeText(getApplicationContext(), tha_val, Toast.LENGTH_LONG).show();
		Toast.makeText(getApplicationContext(), roll_val, Toast.LENGTH_LONG).show();*/
/*		if(exam_type.contentEquals("Primary")){			
			Toast.makeText(getApplicationContext(), "SUCCESS!!", Toast.LENGTH_LONG).show();
		}else if(exam_type.contentEquals("Ebtedaye")) {
			Toast.makeText(getApplicationContext(), "ANOTHER SUCCESS!!", Toast.LENGTH_LONG).show();
		}*/
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}
	
	public boolean isNetworkAvailable(){
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
