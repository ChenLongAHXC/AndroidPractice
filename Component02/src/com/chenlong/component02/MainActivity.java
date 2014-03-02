package com.chenlong.component02;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	private RadioGroup radioGroup;
	private RadioButton male;
	private RadioButton female;
	private CheckBox box1;
	private CheckBox box2;
	private CheckBox box3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		radioGroup=(RadioGroup)findViewById(R.id.myRadioGroup);
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);
        box1=(CheckBox)findViewById(R.id.run);
        box2=(CheckBox)findViewById(R.id.swim);
        box3=(CheckBox)findViewById(R.id.read);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==male.getId()){
					Toast.makeText(MainActivity.this, "You choose male", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(MainActivity.this, "You choose female", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
        box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toast.makeText(MainActivity.this, "You choose run", Toast.LENGTH_SHORT).show();
			}
		});
        box2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toast.makeText(MainActivity.this, "You choose swim", Toast.LENGTH_SHORT).show();				
			}
		});
        box3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toast.makeText(MainActivity.this, "You choose read", Toast.LENGTH_SHORT).show();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
