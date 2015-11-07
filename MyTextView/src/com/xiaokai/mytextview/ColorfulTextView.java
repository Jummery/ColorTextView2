package com.xiaokai.mytextview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class ColorfulTextView extends TextView {
	private Context mContext;
	private int pos = 0;
	private List<String> mTexts = new ArrayList<String>();

	interface OnTextClickListener {
		void onTextClick(ColorfulTextView ctv, int position, String s);
	}

	public ColorfulTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		setHighlightColor(Color.TRANSPARENT);
		setMovementMethod(LinkMovementMethod.getInstance());
	}

	public ColorfulTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ColorfulTextView(Context context) {
		this(context, null);
	}

	public void appendText(Text text, OnTextClickListener onTextClickListener) {
		if (text != null) {
			mTexts.add(text.text);
			SpannableString ss = new SpannableString(text.text);
			ForegroundColorSpan fcs = new ForegroundColorSpan(text.color);
			AbsoluteSizeSpan ass1 = new AbsoluteSizeSpan(sp2px(text.textSize),
					false);
			ss.setSpan(fcs, 0, text.text.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			ss.setSpan(ass1, 0, text.text.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			ss.setSpan(
					new Clickable(onTextClickListener, pos, mTexts.get(pos)),
					0, text.text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			append(ss);
			pos++;
		}
	}

	public void clear() {
		setText("");
		mTexts.clear();
		pos = 0;
	}

	public int sp2px(int sp) {
		final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
		return (int) (sp * fontScale + 0.5f);
	}

	class Clickable extends ClickableSpan implements OnClickListener {
		private final OnTextClickListener textClickListener;
		private int position;
		private String s;

		public Clickable(OnTextClickListener clickListener, int position,
				String s) {
			this.textClickListener = clickListener;
			this.position = position;
			this.s = s;
		}

		@Override
		public void onClick(View v) {
			textClickListener.onTextClick(ColorfulTextView.this, position, s);
		}

		@Override
		public void updateDrawState(TextPaint ds) {
		}
	}
}

class Text {
	String text;
	int color;
	int textSize;

	public Text(String text, int color, int textSize) {
		this.text = text;
		this.color = color;
		this.textSize = textSize;
	}
}
