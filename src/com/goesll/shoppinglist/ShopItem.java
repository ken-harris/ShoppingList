package com.goesll.shoppinglist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShopItem extends LinearLayout {

	private TextView itemNameTextView;
	private TextView amountTextView;
	private TextView quantityTextView;
	
	public ShopItem(Context context, AttributeSet attrs, String in, String am, String qt){
		super(context,attrs);
		
		itemNameTextView = new TextView(context);
		itemNameTextView.setText(in);
		itemNameTextView.setTextAppearance(context, R.style.ListText);
		
		amountTextView = new TextView(context);
		amountTextView.setText(am);
		amountTextView.setTextAppearance(context, R.style.ListText);
		
		quantityTextView = new TextView(context);
		quantityTextView.setText(qt);
		quantityTextView.setTextAppearance(context, R.style.ListText);
		
		addItemViews();
	}
	
	public ShopItem(Context context, String in, String am, String qt){
		this(context,null,in,am,qt);
	}
	
	private void addItemViews(){
		
		itemNameTextView.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT,1f));
		amountTextView.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT,1f));
		quantityTextView.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT,1f));
		
		
		this.addView(itemNameTextView);
		this.addView(amountTextView);
		this.addView(quantityTextView);
	}
	
}
