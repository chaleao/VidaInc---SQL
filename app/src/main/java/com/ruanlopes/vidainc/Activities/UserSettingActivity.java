package com.ruanlopes.vidainc.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;

import com.ruanlopes.vidainc.R;

public class UserSettingActivity extends PreferenceActivity {

	int tagCircle;

	Button mBackBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.settings);
		setContentView(R.layout.activity_usersetting);

//		Intent intent = getIntent();
//		tagCircle = intent.getIntExtra("tagCircle", -1);
		tagCircle = getIntent().getExtras().getInt("tagCircle");




		mBackBtn = (Button) findViewById(R.id.back_btn);
		mBackBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent resultIntent = new Intent();
				resultIntent.putExtra("taggedCircle", tagCircle);
				setResult(Activity.RESULT_OK, resultIntent);
				finish();
			}
		});
	}
}
