package com.goesll.shoppinglist;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class ItemSelectedListener implements OnItemSelectedListener {

	private Context context;
	private Item item;
	
	public ItemSelectedListener(Context c, Item i){
		context = c;
		item = i;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		if(!item.hasBeenBuilt()){
			item.setAlreadyBuilt(true);
		} else {
			if(!item.hasAddedRow()){
				((MainActivity)context).addRow();
				item.setAddedRow(true);
			}
		}		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {}

}
