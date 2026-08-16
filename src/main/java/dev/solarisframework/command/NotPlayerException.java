package dev.solarisframework.command;

import io.papermc.paper.command.brigadier.CommandSourceStack;

public class NotPlayerException extends RuntimeException {

    private final CommandSourceStack source;

    public NotPlayerException(CommandSourceStack source) {
        this.source = source;
    }

    public CommandSourceStack source() {
        return source;
    }
}
