package com.xtremelabs.droidsugar.fakes;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.xtremelabs.droidsugar.util.Implementation;
import com.xtremelabs.droidsugar.util.Implements;

@SuppressWarnings({"UnusedDeclaration"})
@Implements(MapController.class)
public class FakeMapController {
    public FakeMapView fakeMapView;
    public GeoPoint geoPointAnimatedTo;

    @Implementation
    public void animateTo(com.google.android.maps.GeoPoint geoPoint) {
        setCenter(geoPoint);
        geoPointAnimatedTo = geoPoint;
    }

    @Implementation
    public void animateTo(com.google.android.maps.GeoPoint geoPoint, java.lang.Runnable runnable) {
        animateTo(geoPoint);
        runnable.run();
    }

    @Implementation
    public void setCenter(com.google.android.maps.GeoPoint geoPoint) {
        fakeMapView.mapCenter = geoPoint;
    }

    @Implementation
    public void zoomToSpan(int latSpan, int lngSpan) {
        fakeMapView.latitudeSpan = latSpan;
        fakeMapView.longitudeSpan = lngSpan;
    }

    @Implementation
    public boolean zoomIn() {
        fakeMapView.zoomLevel++;
        return true;
    }

    @Implementation
    public boolean zoomOut() {
        fakeMapView.zoomLevel--;
        return true;
    }

    @Implementation
    public int setZoom(int i) {
        fakeMapView.zoomLevel = i;
        return i;
    }
}
