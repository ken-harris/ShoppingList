package com.goesll.shoppinglist;

import java.util.regex.Pattern;

import android.text.Editable;
import android.text.TextWatcher;

public class NoSpecialCharsTextWatcher implements TextWatcher {

	@Override
	public void afterTextChanged(Editable e) {
		String s = e.toString();
		int len = s.length();
		
		if(!Pattern.matches("([a-zA-Z0-9]+-*)*", s)){
			e.delete(len-1,len);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

}
