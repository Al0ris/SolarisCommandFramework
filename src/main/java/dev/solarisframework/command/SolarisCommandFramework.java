package dev.solarisframework.command;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.CommandManager;

public final class SolarisCommandFramework {

    private static CommandRegistrar registrar;

    public static void init(JavaPlugin plugin, CommandManager<CommandSourceStack> manager) {
        ExceptionHandler.register(plugin, manager);
        SolarisCommandFramework.registrar = new CommandRegistrar(manager);
    }

    public static void registerCommand(SolarisCommand command) {
        if (registrar == null) {
            throw new IllegalStateException(
                    "SolarisCommandFramework.init() must be called before registerCommand()");
        }
        registrar.register(command);
    }
}
