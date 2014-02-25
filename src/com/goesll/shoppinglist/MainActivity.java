package com.goesll.shoppinglist;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Setup Spinner adapter so it can grab the TextSize and other properties
		adapter = ArrayAdapter.createFromResource(this, R.array.quantity_types, R.layout.quantity_not_selected_view);
		adapter.setDropDownViewResource(R.layout.quantity_selected_view);
		Item item = new Item(this);
		setupAndAddItem(item);
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
		
		Item it = new Item(this);
		setupAndAddItem(it);
	}
	
	private void setupAndAddItem(Item i){
		// Set the Adapter for the spinner
		i.getQuantitySpinner().setAdapter(adapter);		
		
		// Grab the dataContainer from the main view
		LinearLayout dataContainer = (LinearLayout) this.findViewById(R.id.dataContainer);
		
		// Add the item to the view
		dataContainer.addView(i);
	}

}
