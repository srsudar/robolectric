package com.xtremelabs.droidsugar.fakes;

import android.content.IntentFilter;
import com.xtremelabs.droidsugar.util.Implementation;
import com.xtremelabs.droidsugar.util.Implements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings({"UnusedDeclaration"})
@Implements(IntentFilter.class)
public class FakeIntentFilter {
    List<String> actions = new ArrayList<String>();

    public void __constructor__(String action) {
        actions.add(action);
    }

    @Implementation
    public void addAction(String action) {
        actions.add(action);
    }

    @Implementation
    public String getAction(int index) {
        return actions.get(index);
    }

    @Implementation
    public final Iterator<String> actionsIterator() {
        return actions.iterator();
    }
}
