package com.goesll.shoppinglist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShopActivity extends Activity {
	private File listFile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_list);	
		Intent i = getIntent();
		listFile = (File)i.getSerializableExtra("ListFile");		
		setupDisplay();
	}
	
	private void setupDisplay(){
		LinearLayout mainLayout = (LinearLayout)findViewById(R.id.shoppingList);
		
		try{
			FileInputStream fis = openFileInput(listFile.getName());
			BufferedReader read = new BufferedReader(new InputStreamReader(fis));
			String line = read.readLine();
			while((line = read.readLine()) != null){
				
				LinearLayout itemRow = new LinearLayout(this);
				itemRow.setOrientation(LinearLayout.HORIZONTAL);
				
				//Comes out as: itemName,amount,quantityType
				String []item = line.split(",");
				
				ShopItem shopItem = new ShopItem(this, item[0], item[1], item[2]);
				
				mainLayout.addView(shopItem);
			}
			fis.close();
			
		} catch (Exception e){
			
		}
		
		
	}
}
