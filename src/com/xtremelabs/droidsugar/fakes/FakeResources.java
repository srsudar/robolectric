package com.xtremelabs.droidsugar.fakes;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import com.xtremelabs.droidsugar.util.Implementation;
import com.xtremelabs.droidsugar.util.Implements;

import java.util.Locale;

@SuppressWarnings({"UnusedDeclaration"})
@Implements(Resources.class)
public class FakeResources {
    @Implementation
    public int getColor(int id) throws Resources.NotFoundException {
        return FakeContextWrapper.resourceLoader.colorResourceLoader.getValue(id);
    }

    @Implementation
    public String getString(int id) throws Resources.NotFoundException {
        return FakeContextWrapper.resourceLoader.stringResourceLoader.getValue(id);
    }

    @Implementation
    public String getString(int id, Object... formatArgs) throws Resources.NotFoundException {
        String raw = getString(id);
        return String.format(Locale.ENGLISH, raw, formatArgs);
    }

    @Implementation
    public String[] getStringArray(int id) throws Resources.NotFoundException {
        String[] arrayValue = FakeContextWrapper.resourceLoader.stringResourceLoader.getArrayValue(id);
        if (arrayValue == null) {
            throw new Resources.NotFoundException();
        }
        return arrayValue;
    }

    @Implementation
    public CharSequence getText(int id) throws Resources.NotFoundException {
        return getString(id);
    }

    @Implementation
    public DisplayMetrics getDisplayMetrics() {
        return new DisplayMetrics();
    }

    @Implementation
    public Drawable getDrawable(int id) throws Resources.NotFoundException {
        return new BitmapDrawable();
    }

    @Implementation
    public int getDimensionPixelSize(int id) throws Resources.NotFoundException {

        // The int value returned from here is probably going to be handed to TextView.setTextSize(),
        // which takes a float. Avoid int-to-float conversion errors by returning a value generated from this
        // resource ID but which isn't too big (resource values in R.java are all greater than 0x7f000000).

        return id - 0x7f000000;
    }
}
