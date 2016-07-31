package com.yang.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.yang.card.R;

public class ImageUtil {


	public static Bitmap decodeImageIgnorOom(Context context, Options opts,boolean isFont) {
		if (opts == null) {
			opts = new Options();
		}

		opts.inJustDecodeBounds = false;
		Bitmap bm = null;
		int count = 1;
		boolean isOutOfMemory = false;
		do {
			if (count != 1) {
				opts.inSampleSize = opts.inSampleSize * 2;
			}

			isOutOfMemory = false;

			try {
//				bm = BitmapFactory.decodeFile(path, opts);
				if (isFont){
					bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.preview);
				}else {
					bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
				}

			} catch (OutOfMemoryError e) {
				e.printStackTrace();
				isOutOfMemory = true;
			}

			count++;
		} while (isOutOfMemory);

		return bm;
	}
}
