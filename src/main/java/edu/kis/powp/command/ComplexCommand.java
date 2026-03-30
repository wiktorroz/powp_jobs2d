package edu.kis.powp.command;

import java.util.ArrayList;
import java.util.List;

public class ComplexCommand implements DriverCommand {

    private List<DriverCommand> commandList = new ArrayList<>();

    public ComplexCommand() {
    }

    public ComplexCommand(List<DriverCommand> commands) {
        commandList = commands;
    }

    @Override
    public void execute() {
        for (DriverCommand command : commandList) {
            command.execute();
        }
    }
}