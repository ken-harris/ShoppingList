package com.goesll.shoppinglist;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private ArrayAdapter<?> adapter;
	
	// Current problem: Cannot focus to the Spinner.
	// http://stackoverflow.com/questions/6443212/spinner-did-not-got-focus for info.
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		((Item)findViewById(R.id.item1)).getItemText().setFocusable(true);
		// Setup Spinner adapter so it can grab the TextSize and other properties
		adapter = ArrayAdapter.createFromResource(this, R.array.quantity_types, R.layout.quantity_not_selected_view);
		adapter.setDropDownViewResource(R.layout.quantity_selected_view);
		Spinner spin = ((Item)findViewById(R.id.item1)).getQuantitySpinner();
		spin.setAdapter(adapter);
		spin.setFocusable(true);
		spin.setFocusableInTouchMode(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*
	 * addButtonClick(View v)
	 * 
	 * Adds another row of Items to the view.
	 * 
	 * v -- The Button
	 */
	public void addButtonClick(View v){
		
		// Grab the dataContainer from the main view
		LinearLayout dataContainer = (LinearLayout) this.findViewById(R.id.dataContainer);
		Item it = new Item(this);
		// Set the Adapter for the spinner
		it.getQuantitySpinner().setAdapter(adapter);
		// Add the item to the view
		dataContainer.addView(it);
	}

}
