package com.goesll.shoppinglist;

import com.goesll.shoppinglist.helper.Conversion;

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
	
	/*
	 * merge(Item item2)
	 * 
	 * Merges the passed in Item to the first Item
	 */
	
	public Item merge(Item item2){
		// Easiest problem is: Quantity Type is the same, so just combine the Quantity Amount.
		if(this.quantitySpinner.getSelectedItem().toString().equalsIgnoreCase(item2.getQuantitySpinner().getSelectedItem().toString())){
			double quantity = Double.parseDouble(this.getQuantityText().getText().toString());
			quantity += Double.parseDouble(item2.getQuantityText().getText().toString());
			this.getQuantityText().setText(Double.toString(quantity));
			
		} else {
			// This means the quantities are separate. Grab both and figure out the largest, convert the smallest to largest, and then merge them.
			String quantity1 = this.getQuantitySpinner().getSelectedItem().toString();
			int firstSpinnerPosition = this.getQuantitySpinner().getSelectedItemPosition();
			String quantity2 = item2.getQuantitySpinner().getSelectedItem().toString();
			int secondSpinnerPosition = item2.getQuantitySpinner().getSelectedItemPosition();
			String largest = Conversion.largestQuantity(quantity1, quantity2);
			if(largest.equals(quantity1)){
				double quantity = (Double.parseDouble(this.getQuantityText().getText().toString()));
				quantity += Conversion.convertTo(quantity2, item2.getQuantityText().getText().toString(), quantity1);
				this.getQuantityText().setText(Double.toString(quantity));
				this.getQuantitySpinner().setSelection(firstSpinnerPosition); // Not needed?
				
			} else if(largest.equals(quantity2)){
				double quantity = (Double.parseDouble(this.getQuantityText().getText().toString()));
				quantity += Conversion.convertTo(quantity1, this.getQuantityText().getText().toString(), quantity2);
				this.getQuantityText().setText(Double.toString(quantity));
				this.getQuantitySpinner().setSelection(secondSpinnerPosition);
				
			} else {
				// Either no conversion, no quantity amount selected, or Eggs/Cartons
				if((quantity1.contains("Egg") && quantity2.contains("Carton")) | (quantity1.contains("Carton") && quantity2.contains("Egg"))){
					// If one is Egg and the other is Carton then convert both to Carton, and round up.(?)					
				}
			}

		}
		
		return this;		
	}
	
	@Override
	public int hashCode(){
		int hash = 1;
		hash = hash * 17 + itemText.getText().toString().hashCode();
		hash = hash * 31 + quantityText.getText().toString().hashCode();
		hash = hash * 7 + quantitySpinner.getSelectedItem().toString().hashCode();
		return hash;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Item))
			return false;
		
		Item it = (Item)obj;
		if(it.getItemText().getText().toString().equalsIgnoreCase(itemText.getText().toString())){
			return true;
		}
		
		return false;
	}
	
	public String toString(){
		return getItemText().getText().toString()+ "," + getQuantityText().getText().toString() + "," + getQuantitySpinner().getSelectedItem().toString();
	}
}
