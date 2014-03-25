package com.chenlong.fragment_addview;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShowFragment2 extends Fragment {

	public ShowFragment2() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.show_fragment2, null);
	}
	
	@Override
	public void onPause() {
		
		super.onPause();
	}
}
