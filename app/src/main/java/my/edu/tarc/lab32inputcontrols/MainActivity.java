package my.edu.tarc.lab32inputcontrols;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age_group,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAge.setOnItemSelectedListener(this);
        spinnerAge.setAdapter(adapter);

        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoke);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Position=" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium(View view){
        int pos;
        pos = spinnerAge.getSelectedItemPosition();
        int premium = 0;
        int idGender;
        idGender = radioGroupGender.getCheckedRadioButtonId();

        //TODO: Calculate insurance premium
        if(pos == 0)
            premium = 50;
        else if(pos == 1)
            premium = 55;
        else if(pos == 2)
            premium = 60;
        else if(pos == 3)
            premium = 70;
        else if(pos == 4)
            premium = 120;
        else if(pos == 5)
            premium = 160;
        else if(pos == 6)
            premium = 200;
        else if(pos == 7)
            premium = 250;

        if(idGender == R.id.radioButtonMale){
            if(pos == 2 || pos == 5)
                premium += 50;
            else if(pos == 3 || pos == 4)
                premium += 100;
        }

        if(checkBoxSmoker.isChecked()){
            if(pos == 3)
                premium += 100;
            else if(pos == 4 || pos == 5)
                premium += 150;
            else if(pos == 6 || pos == 7)
                premium += 250;
        }

        textViewPremium.setText(getString(R.string.premium) + "RM " + premium);
    }

    public void reset(View view)
    {
        textViewPremium.setText(getString(R.string.premium));
        radioGroupGender.clearCheck();
    }
}
