package edu.kis.powp.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public final class CommandFactory {

    private CommandFactory() {
    }

    public static ComplexCommand rectangleCommand(int x, int y, int width, int height, Job2dDriver driver) {
        List<DriverCommand> commands = new ArrayList<>();

        commands.add(new SetPositionCommand(driver, x, y));
        commands.add(new OperateToCommand(driver, x + width, y));
        commands.add(new OperateToCommand(driver, x + width, y + height));
        commands.add(new OperateToCommand(driver, x, y + height));
        commands.add(new OperateToCommand(driver, x, y));

        return new ComplexCommand(commands);
    }

    public static ComplexCommand equilateralTriangleCommand(int x, int y, int side, Job2dDriver driver) {
        List<DriverCommand> commands = new ArrayList<>();

        int halfSide = side / 2;
        int triangleHeight = (int) Math.round((Math.sqrt(3) / 2) * side);

        commands.add(new SetPositionCommand(driver, x, y));
        commands.add(new OperateToCommand(driver, x + side, y));
        commands.add(new OperateToCommand(driver, x + halfSide, y + triangleHeight));
        commands.add(new OperateToCommand(driver, x, y));

        return new ComplexCommand(commands);
    }
}