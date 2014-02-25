package com.goesll.shoppinglist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Item extends LinearLayout{

	private EditText itemText;
	private EditText quantityText;
	private Spinner quantitySpinner;
	private boolean addedRow = false;
	private boolean alreadyBuilt = false;
	
	public Item(Context context, AttributeSet attrs){
		super(context,attrs);			
		setupItem(context);				
	}
	
	public Item(Context context){
		this(context,null);
	}
	
	private void setupItem(Context context){
		setOrientation(LinearLayout.HORIZONTAL);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_item, this, true);
		itemText = (EditText) getChildAt(0);
		quantityText = (EditText)getChildAt(1);
		quantitySpinner = (Spinner)getChildAt(2);
		quantityText.setOnEditorActionListener(new OnEditorActionListener() {
		    
			@Override
			public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_NEXT) {
		            textView.clearFocus();
		            quantitySpinner.requestFocus();
		            quantitySpinner.performClick(); // Opens up the Quantity Spinner
		        }
		        return true;
			}
		});
		quantitySpinner = (Spinner)getChildAt(2);
		quantitySpinner.setOnItemSelectedListener(new ItemSelectedListener(context,this));
		quantitySpinner.setFocusable(true);
		quantitySpinner.setFocusableInTouchMode(true);
	}
	
	// hasBeenBuilt is a workaround for the ItemSelectedListener for a Spinner.
	// Apparently it gets called every time the spinner is built. 
	// Need to prevent it from generating a bunch of rows every time.
	public boolean hasBeenBuilt(){
		return alreadyBuilt;
	}
	
	public void setAlreadyBuilt(boolean b){
		alreadyBuilt = b;
	}
	
	public boolean hasAddedRow(){
		return addedRow;
	}
	
	public void setAddedRow(boolean b){
		addedRow = b;
	}
	
	public void setItemEditText(EditText et){
		itemText = et;
	}
	
	public void setQuantityEditText(EditText et){
		quantityText = et;
	}
	
	public void setQuantitySpinner(Spinner sp){
		quantitySpinner = sp;
	}
	
	public EditText getItemText(){
		return itemText;
	}
	
	public EditText getQuantityText(){
		return quantityText;
	}
	
	public Spinner getQuantitySpinner(){
		return quantitySpinner;
	}
}
