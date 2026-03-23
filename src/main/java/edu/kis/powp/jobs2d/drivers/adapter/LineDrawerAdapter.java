package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DrawerFeature;

// sterownik umoliwiający rysowanie innych typów linii np. przerywanych
public class LineDrawerAdapter extends DrawPanelController implements Job2dDriver {
    private int startX = 0, startY = 0;
    private ILine typLinii;
    public LineDrawerAdapter(ILine typLinii) {
        this.typLinii = typLinii;
    }

    @Override
    public void setPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    @Override
    public void operateTo(int x, int y) {

        typLinii.setStartCoordinates(this.startX, this.startY);
        typLinii.setEndCoordinates(x, y);
        setPosition(x, y);

        DrawerFeature.getDrawerController().drawLine(typLinii);
    }

    @Override
    public String toString() {
        return "@Q!$!@$!#@$(*#@&Q(%^*#@";
    }

}