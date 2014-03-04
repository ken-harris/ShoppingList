package com.goesll.shoppinglist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewActivity extends Activity{

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_list);
		displayData();
	}
	
	private void displayData(){
		int colorCode = 0;
		LinearLayout layout = (LinearLayout)findViewById(R.id.listArea);
		File fileDir = getFilesDir();
		File []files = fileDir.listFiles();
		
		// What to display when there's no data created? Should we disable the button if nothing exists?
		if(files.length == 0){
			displayNoData();
		}
		
		for(File file : files){
			LinearLayout linearLayout = new LinearLayout(this);
			linearLayout.setPadding(5, 10, 5, 10);
			
			// Alternates colors
			if(colorCode == 0){
				linearLayout.setBackgroundColor(Color.LTGRAY);
				colorCode = 1;
			} else {
				linearLayout.setBackgroundColor(Color.WHITE);
				colorCode = 0;
			}
			
			
			// Displays the File Name
			TextView fileNameTextView = new TextView(this);
			fileNameTextView.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT,1f));
			fileNameTextView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					System.out.println("Clicked!");
				}
			});
			
			fileNameTextView.setText(file.getName());			
			fileNameTextView.setTextAppearance(this, R.style.ListText);
			
			// Displays the Date
			TextView dateTextView = new TextView(this);
			dateTextView.setLayoutParams(new TableRow.LayoutParams(0,LayoutParams.WRAP_CONTENT,1f));
			dateTextView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					System.out.println("Clicked!");
				}
			});
			
			dateTextView.setTextAppearance(this, R.style.ListText);
			
			// Grab date information
			try{
				FileInputStream fis = openFileInput(file.getName());
				BufferedReader read = new BufferedReader(new InputStreamReader(fis));
				String line = read.readLine();
				if(line != null){
					// line SHOULD be the date saved.
					Date date = new Date(Long.parseLong(line));
					CharSequence dateFormatted = DateFormat.format("MM-dd-yyyy hh:mm a", date);
					dateTextView.setText(dateFormatted);
				}
				fis.close();
				
			} catch (Exception e){
				
			}
			linearLayout.addView(fileNameTextView);
			linearLayout.addView(dateTextView);
			layout.addView(linearLayout);
			System.out.println(file.getAbsolutePath());
		}
	}
	
	private void displayNoData(){
		
	}
}
