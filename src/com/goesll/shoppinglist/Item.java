package com.goesll.shoppinglist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class Item extends LinearLayout{

	private EditText itemText;
	private EditText quantityText;
	private Spinner quantitySpinner;
	
	public Item(Context context, AttributeSet attrs){
		super(context,attrs);		
		setOrientation(LinearLayout.HORIZONTAL);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_item, this, true);
		itemText = (EditText) getChildAt(0);
		quantityText = (EditText)getChildAt(1);
		quantitySpinner = (Spinner)getChildAt(2);
	}
	
	public Item(Context context){
		this(context,null);
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
