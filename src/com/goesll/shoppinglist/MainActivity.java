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
		//saveButtonClick(v);
		ArrayList<Item> itemArray = getListItems();
		if(itemArray.size() > 0){
			int arrayLength = itemArray.size();
			for(int j = 0; j < itemArray.size(); j++){
				Item firstItem = itemArray.get(j);
				for(int i = j+1; i < arrayLength; i++){
					if(firstItem.equals(itemArray.get(i))){
						// This means the item names are equal to each other
						System.out.println(firstItem + " equals " + itemArray.get(i));
						firstItem = firstItem.merge(itemArray.get(i));
						System.out.println("After merge: " + firstItem);
						itemArray.remove(i);
					}
				}
			}
			
			System.out.println(itemArray);
			Collections.sort(itemArray, new Comparator<Item>(){

				@Override
				public int compare(Item item1, Item item2) {
					return item1.getItemText().getText().toString().compareTo(item2.getItemText().getText().toString());
				}});
			System.out.println(itemArray);
		} else {
			// No items entered???
		}
		
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
					for(Object s:getListItems().toArray()){
						outputStream.write(((Item)s).toString().getBytes());
						outputStream.write("\n".getBytes());
					}
					outputStream.close();
					Toast.makeText(getApplicationContext(), "Saved " + fileName + ".", Toast.LENGTH_SHORT).show();
					
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
	
	private ArrayList<Item> getListItems(){
		LinearLayout layout = (LinearLayout)this.findViewById(R.id.dataContainer);
		System.out.println("number of items: " + layout.getChildCount());
		ArrayList<Item> strArray = new ArrayList<Item>();
		for(int i = 0; i < layout.getChildCount(); i++){
			Item item = (Item)layout.getChildAt(i);
			
			if(item.getItemText().getText().toString().equals("") && item.getQuantityText().getText().toString().equals("") && item.getQuantitySpinner().getSelectedItem().equals("Select Qty:")){
				System.out.println("Empty row found!");
			} else {
				System.out.println(item);
				strArray.add(item);
			}		
		}
		return strArray;
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
