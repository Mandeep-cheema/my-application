package com.mandeep.testtwomandeep;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.logging.Logger;

public class SecondPage extends AppCompatActivity {

  ArrayList<CourseModel> courseList = new ArrayList<>();
  Spinner spinner;
  TextView tvFees;
  TextView tvHours;
  TextView tvTotalFee;
  TextView tvTotalHours;
  Button btnAdd;
  CheckBox cbAccom;
  CheckBox cbMed;
  RadioGroup radioGroup;
  String gr = "";
  int Fees = 0;
  int Hours = 0;
  int tempFees = 0;
  int tempHours = 0;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    String studentName = getIntent().getStringExtra("STUDENT_NAME");
    TextView tvHeader = findViewById(R.id.tv_header);
    tvHeader.setText("Welcome " + studentName);
    spinner = findViewById(R.id.spinner);
    tvFees = findViewById(R.id.tv_fees);
    tvHours = findViewById(R.id.tv_hours);
    btnAdd = findViewById(R.id.btn_add);
    tvTotalFee = findViewById(R.id.total_fee);
    tvTotalHours = findViewById(R.id.total_hours);
    cbAccom = findViewById(R.id.cb_accomodation);
    cbMed = findViewById(R.id.cb_medical_insurance);
    radioGroup = findViewById(R.id.radioGroup);
    radioGroup.clearCheck();
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @SuppressLint("ResourceType")
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = (RadioButton) group.findViewById(checkedId);
        if (null != rb && checkedId > -1) {
          gr = rb.getText().toString();
          Fees = 0;
          Hours = 0;
          tvTotalFee.setText(Fees+"");
          tvTotalHours.setText(Hours+"");
        }
      }
    });

    CourseModel courseModel0 = new CourseModel();
    courseModel0.cousre = "Nothing selected";
    courseModel0.fees = 0;
    courseModel0.hours = 0;

    CourseModel courseModel1 = new CourseModel();
    courseModel1.cousre = "Java";
    courseModel1.fees = 1300;
    courseModel1.hours = 6;
    CourseModel courseModel2 = new CourseModel();
    courseModel2.cousre = "Swift";
    courseModel2.fees = 1500;
    courseModel2.hours = 5;
    CourseModel courseModel3 = new CourseModel();
    courseModel3.cousre = "IOS";
    courseModel3.fees = 1350;
    courseModel3.hours = 5;
    CourseModel courseModel4 = new CourseModel();
    courseModel4.cousre = "Android";
    courseModel4.fees = 1400;
    courseModel4.hours = 7;
    CourseModel courseModel5 = new CourseModel();
    courseModel5.cousre = "Database";
    courseModel5.fees = 1000;
    courseModel5.hours = 4;

    courseList.add(courseModel1);
    courseList.add(courseModel2);
    courseList.add(courseModel3);
    courseList.add(courseModel4);
    courseList.add(courseModel5);
    ArrayList<String> course = new ArrayList<>();
    course.add("Java");
    course.add("Swift");
    course.add("IOS");
    course.add("Android");
    course.add("Database");

    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course);
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(dataAdapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        for (int i = 0; i < courseList.size(); i++) {
          if (courseList.get(i).cousre.contentEquals(item)) {
            tempFees = courseList.get(i).fees;
            tempHours = courseList.get(i).hours;
            tvFees.setText(tempFees + "");
            tvHours.setText(tempHours  + "");
          }
        }
      }
      @Override
      public void onNothingSelected(AdapterView<?> parent) { }
    });

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!gr.isEmpty()) {
          if ((gr.equals(" graduated") &&  (Hours + tempHours) <= 21) || (gr.equals(" ungraduated") && (Hours + tempHours) <= 19)) {
            Fees = Fees + tempFees;
            Hours = Hours + tempHours;
            tvTotalFee.setText(Fees+"");
            tvTotalHours.setText(Hours+"");
          } else  {
            Toast.makeText(SecondPage.this, "you can't add this course",Toast.LENGTH_SHORT).show();
          }
        } else {
          Toast.makeText(SecondPage.this, "select graduated or ungraduated",Toast.LENGTH_SHORT).show();
        }

      }
    });
    cbAccom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          Fees = Fees+1000;
        } else {
          Fees = Fees-1000;
        }
        tvTotalFee.setText(Fees+"");
        Log.d("##ggsgs##","cbAccom");
      }
    });
    cbMed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          Fees = Fees+700;
        } else {
          Fees = Fees-700;
        }
        tvTotalFee.setText(Fees+"");
        Log.d("##ggsgs##","sddj");
      }
    });
  }
}
