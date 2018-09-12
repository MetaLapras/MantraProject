package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pasistence.mantrafingerprint.R;

public class ProjectLogn_In extends AppCompatActivity {
    EditText edt_emp_name,edt_emp_id,edt_emp_password;
    Button btn_emp_next;
    Context mcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_logn__in);
        mImit();
        onClick();
    }


    private void mImit() {
        edt_emp_name      = (EditText)findViewById(R.id.emp_project_name);
        edt_emp_id        = (EditText)findViewById(R.id.emp_employee_id);
        edt_emp_password  = (EditText)findViewById(R.id.emp_password);
        btn_emp_next      = (Button)findViewById(R.id.Emp_next);
        mcContext         = ProjectLogn_In.this;
    }

    private void onClick() {
        btn_emp_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectLogn_In.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
