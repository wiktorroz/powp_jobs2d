package edu.kis.powp.command;

import edu.kis.powp.jobs2d.Job2dDriver;
public class SetPositionCommand implements DriverCommand {
    private final Job2dDriver job2dDriver;
    private int x, y;

    public SetPositionCommand(Job2dDriver job2dDriver, int x, int y) {
        this.job2dDriver = job2dDriver;
        this.x = x;
        this.y = y;
    }

    public void execute() {
        job2dDriver.setPosition(this.x, this.y);
    }

}
