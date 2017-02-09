package rebat.shoura;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.os.Bundle;

/**
 * Created by wassanalluhaidan on 2/9/17.
 */

public class RequestConsultation extends Activity {

    EditText nameEditText,emailEditText,bodyEditText, ageEditText;
    Button send;
    RadioGroup genderRadioGroup, privacyRadioGroup;
    RadioButton selectedGender, selectedPrivacy;
    float ConsultantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Leyout
        setContentView(R.layout.request_consultation);

        // Get Consultant Id
        ConsultantId = 0;

        // Get Layout Elements
        send=(Button)findViewById(R.id.send);
        nameEditText=(EditText)findViewById(R.id.personName);
        emailEditText =(EditText)findViewById(R.id.personِEmail);
        bodyEditText =(EditText)findViewById(R.id.body);
        ageEditText =(EditText)findViewById(R.id.Age);
        genderRadioGroup =(RadioGroup)findViewById(R.id.Gender);
        privacyRadioGroup =(RadioGroup)findViewById(R.id.Privacy);

        selectedGender = (RadioButton) findViewById(genderRadioGroup.getCheckedRadioButtonId());
        selectedPrivacy = (RadioButton) findViewById(privacyRadioGroup.getCheckedRadioButtonId());

        // Set On Click for the Send Button
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Values
                final String Name= nameEditText.getText().toString();
                final String Email= emailEditText.getText().toString();
                final String Body= bodyEditText.getText().toString();
                final String Age= ageEditText.getText().toString();
                final int Gender= selectedGender.getId();
                final int Privacy= selectedPrivacy.getId();

                // Check Layout Validation
                if(ValidLayout(Name, Email, Body, Age)){
                    // Save Consultation
                    float consultationNumber = SaveConsulation(Name, Email, Body, Age, Gender, Privacy);

                    if(consultationNumber!=0){
                        // Email Applicant
                        //String.format(getString(R.string.Email_ConsultationRecieved), Name, String.valueOf(consultationNumber));

                        //Email Consultant
                        String ConsultantName = GetConsultantName(ConsultantId);
                        //String.format(getString(R.string.Email_NewConsultation), ConsultantName, String.valueOf(consultationNumber));

                        // Confirm Save By Toast
                        Toast.makeText(RequestConsultation.this,getString(R.string.ConsultationSaved),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(RequestConsultation.this,getString(R.string.ErrorOccured),Toast.LENGTH_LONG).show();
                    }
                }
            }

            private String GetConsultantName(float consultantId) {
                // Get Consultant By Id
                // Get Name
                return "";
            }

            private float SaveConsulation(String name, String email, String body, String age, int gender, int privacy) {
                // Save Consultation to Web Service or Database, return consultation number
                return 0;
            }
        });
    }

    private boolean ValidLayout(String user_name, String user_email, String user_body, String user_age) {
        boolean valid = true;

        // Check Required Fields
        if(user_name.length()==0)
        {
            valid = false;
            nameEditText.requestFocus();
            nameEditText.setError(getString(R.string.FieldRequired));
        }
        if(user_email.length()==0)
        {
            valid = false;
            emailEditText.requestFocus();
            emailEditText.setError(getString(R.string.FieldRequired));
        }
        else if (!user_email.matches("([\\w.-]+@([\\w-]+)\\.+\\w{2,})")) // Invalid Email Pattern
        {
            valid = false;
            emailEditText.requestFocus();
            emailEditText.setError(getString(R.string.InvalidEmail));
        }
        if(user_body.length()==0)
        {
            valid = false;
            bodyEditText.requestFocus();
            bodyEditText.setError(getString(R.string.FieldRequired));
        }
        if(user_age.length()==0)
        {
            valid = false;
            ageEditText.requestFocus();
            ageEditText.setError(getString(R.string.FieldRequired));
        }

        return valid;
    }
}
