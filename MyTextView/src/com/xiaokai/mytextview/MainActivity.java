package com.xiaokai.mytextview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.xiaokai.mytextview.ColorfulTextView.OnTextClickListener;

public class MainActivity extends Activity implements OnTextClickListener {
	ColorfulTextView tv;
	Button btn;
	int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (ColorfulTextView) findViewById(R.id.tv);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Text t = new Text("aaaaa" + count++, getResources().getColor(
						R.color.zise), 18);
				tv.appendText(t, MainActivity.this);
			}
		});

		Text t = new Text("aaaaa", getResources().getColor(R.color.zise), 18);
		tv.appendText(t, this);

		Text t1 = new Text("\nbbbbbb", Color.RED, 18);
		tv.appendText(t1, this);

		Text t3 = new Text("\r\rbbbbbb", Color.BLUE, 50);
		tv.appendText(t3, this);

	}

	@Override
	public void onTextClick(ColorfulTextView ctv, int position, String s) {
		if (position == 0) {
			ctv.clear();
		} else {
			Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
		}
	}
}
