package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.AbstractDriver;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;

public class AbstractDriverAdapter extends AbstractDriver {
    public AbstractDriverAdapter() {
        super(0, 0); //zaczynamy od (0,0)
    }
    @Override
    public void operateTo(int x, int y) {
        // pobieramy aktualnie wybrany sterownik
        Job2dDriver driver = DriverFeature.getDriverManager().getCurrentDriver();

        driver.setPosition(getX(), getY());
        driver.operateTo(x, y);
        //aktualizujemy pozycję w AbstractDriver (final)
        setPosition(x, y);
    }
    @Override
    public String toString() {
        return "Adapter for FiguresJane";
    }
}
