package com.goesll.shoppinglist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

/*
 * TODO:
 * - Allow user to click a "DONE" button, then merge all alike rows into one.
 * - Have two separate views: Create(Edit?) and Shop
 *    - Create allows the user to create a new shopping list (if no previous ones exist then this should be default view)
 *    - Shop allows a user to view their created list. Can click on each "Item" and it will do a strikethrough.
 * - Get Search up and running to search through the rows for all the text the user types in.
 *    - Should auto filter?
 *    - Is this useful? 
 * - Save dialog shows up once. After that it uses that name every time the user hits save.
 * 
 * There will be 4 Views total:
 * 	Main View -- Contains options for User to either Create a new list, or View/Edit previous lists.
 *  Create View -- Allows user to type in their shopping list.
 *     -Allow user to select whether or not they want it to merge items together (maybe they want to preserve their sort)
 *  Shopping View -- Loads up the list and allows user to click the row when they have purchased/placed item in their cart.
 *     -Allow user to either strikethrough when selected or completely remove the row.
 *     -Sort the row if strike through? Move found items to the bottom?
 *     -Allow user to sort the list by alphabet or keep it the way they want?
 *  List View -- View that allows a user to open/delete/rename
 */

/*
 * Current issue: Create new Activity that views the saved files. Allows for a user to open/delete/rename.
 */

/*
 * Future problem?
 * Too many saved files, not enough space? What is max space for use in an app?
 */

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		addListenerToButtons();
	}
	
	public void addListenerToButtons(){
		final Context context = this;
		
		Button createButton = (Button)findViewById(R.id.createButton);
		createButton.setOnClickListener(new View.OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, CreateActivity.class);
                            startActivity(intent);   
 
			}
 
		});
		
		Button viewButton = (Button)findViewById(R.id.viewButton);
		viewButton.setOnClickListener(new View.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, ViewActivity.class);
                            startActivity(intent);   
 
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
