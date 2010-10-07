package com.xtremelabs.droidsugar.fakes;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import com.xtremelabs.droidsugar.util.FakeHelper;
import com.xtremelabs.droidsugar.util.Implementation;
import com.xtremelabs.droidsugar.util.Implements;

@SuppressWarnings({"UnusedDeclaration"})
@Implements(Activity.class)
public class FakeActivity extends FakeContextWrapper {
    private Intent intent;
    public View contentView;

    public boolean finishWasCalled;
    public int resultCode;
    public Intent resultIntent;
    public Activity parent;
    private Activity realActivity;
    private TestWindow window;

    public FakeActivity(Activity realActivity) {
        super(realActivity);
        this.realActivity = realActivity;
    }

    @Implementation
    public final Application getApplication() {
        return FakeHelper.application;
    }

    @Implementation
    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    @Implementation
    public Intent getIntent() {
        return intent;
    }

    @Implementation
    public void setContentView(int layoutResID) {
        contentView = getLayoutInflater().inflate(layoutResID, null);
    }

    @Implementation
    public void setContentView(View view) {
        contentView = view;
    }

    @Implementation
    public final void setResult(int resultCode) {
        this.resultCode = resultCode;
    }

    @Implementation
    public final void setResult(int resultCode, Intent data) {
        this.resultCode = resultCode;
        resultIntent = data;
    }

    @Implementation
    public LayoutInflater getLayoutInflater() {
        return new RobolectricLayoutInflater(resourceLoader.viewLoader, realActivity);
    }

    @Implementation
    public View findViewById(int id) {
        if (contentView != null) {
            return contentView.findViewById(id);
        } else {
            throw new RuntimeException("you should have called setContentView() first");
        }
    }

    @Implementation
    public final Activity getParent() {
        return parent;
    }

    @Implementation
    public void finish() {
        finishWasCalled = true;
    }

    @Implementation
    public Window getWindow() {
        if(window == null) {
            window = new TestWindow(realActivity);
        }
        return window;
    }
}
