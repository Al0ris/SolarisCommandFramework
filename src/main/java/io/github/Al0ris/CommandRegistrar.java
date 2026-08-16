package io.github.Al0ris;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.CommandManager;

public final class CommandRegistrar {

    private final CommandManager<CommandSourceStack> manager;

    public CommandRegistrar(JavaPlugin plugin, CommandManager<CommandSourceStack> manager) {
        this.manager = manager;
        ExceptionHandler.register(plugin, manager);
    }

    public void register(SolarisCommand command) {
        command.register(manager);
    }
}
