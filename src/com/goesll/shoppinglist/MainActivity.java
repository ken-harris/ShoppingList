package com.goesll.shoppinglist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

/*
 * TODO:
 * - Allow user to click a "DONE" button, then merge all alike rows into one.
 * - Have two separate views: Create(Edit?) and Shop
 *    - Create allows the user to create a new shopping list (if no previous ones exist then this should be default view)
 *    - Shop allows a user to view their created list. Can click on each "Item" and it will do a strikethrough.
 * - Get Search up and running to search through the rows for all the text the user types in.
 *    - Should auto filter?
 *    - Is this useful? 
 */

public class MainActivity extends Activity {

	private ArrayAdapter<?> adapter;
	private String fileName;
	
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
	 * addRow()
	 * 
	 * Adds another row of Items to the view.
	 */
	public void addRow(){
		
		Item it = new Item(this);
		setupAndAddItem(it);
	}
	
	/*
	 * Action that happens when the user is done adding all their items to a the list.
	 *  - Merge all of the items into similar rows
	 *  - Export as a Text File(?)
	 *  - Allow user to name or use default (but include date)
	 */
	
	public void doneButtonClick(View v){
		
	}
	
	/*
	 * Action that happens when the user wants to save the list they are working on.
	 *  - Allow user to name or use default (but include date)	
	 */
	
	/*
	 * To load the data:
	 * 
	 * StringBuilder sb = new StringBuilder();
				
				try{
					FileInputStream fis = openFileInput(fileName);
					BufferedReader read = new BufferedReader(new InputStreamReader(fis));
					String line = null;
					while((line = read.readLine()) != null){
						sb.append(line).append("\n");
					}
					fis.close();
					
				} catch (Exception e){
					
				}
	 */
	public void saveButtonClick(View v){
		
		// Make a simple alert dialog
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Saving Shopping List");
		alert.setMessage("Enter name for list (No Special Chars):");
		
		final EditText input = new EditText(this);
		Calendar time = Calendar.getInstance();
		fileName = "List-" + (time.get(Calendar.MONTH)+1) + "-" + time.get(Calendar.DAY_OF_MONTH) + "-" + time.get(Calendar.YEAR);
		input.setText(fileName);	
		
		// Custom TextWatcher to disallow special characters
		input.addTextChangedListener(new NoSpecialCharsTextWatcher());
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		alert.setView(input);
		
		// If they click Save this happens
		alert.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  String value = input.getText().toString();
			  fileName = value;
			  
			  FileOutputStream outputStream;
				try {
					
					outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
					// Save time at the top of the file, for easy access in the future.
					String time = Long.toString(Calendar.getInstance().getTimeInMillis());
					outputStream.write(time.getBytes());
					outputStream.write("\n".getBytes());
					for(Object s:getListItems()){
						outputStream.write(((String)s).getBytes());
						outputStream.write("\n".getBytes());
					}
					outputStream.close();
					
				} catch(Exception e){
					e.printStackTrace();
				}
			  }
			});

		// Clicking Cancel does nothing right now
		alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
			  }
			});

		// Show dialog
		alert.show();
	}
	
	private Object[] getListItems(){
		LinearLayout layout = (LinearLayout)this.findViewById(R.id.dataContainer);
		System.out.println("number of items: " + layout.getChildCount());
		ArrayList<String> strArray = new ArrayList<String>();
		for(int i = 0; i < layout.getChildCount(); i++){
			Item item = (Item)layout.getChildAt(i);
			
			if(item.getItemText().getText().toString().equals("") && item.getQuantityText().getText().toString().equals("") && item.getQuantitySpinner().getSelectedItem().equals("Select Qty:")){
				System.out.println("Empty row found!");
			} else {
				System.out.println(item);
				strArray.add(item.toString());
			}		
		}
		return strArray.toArray();
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
