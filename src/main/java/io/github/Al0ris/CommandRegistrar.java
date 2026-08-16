package io.github.Al0ris;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.incendo.cloud.CommandManager;

public final class CommandRegistrar {

    private final CommandManager<CommandSourceStack> manager;

    public CommandRegistrar(CommandManager<CommandSourceStack> manager) {
        this.manager = manager;
    }

    public void register(SolarisCommand command) {
        command.register(manager);
    }
}
