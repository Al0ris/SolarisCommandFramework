package io.github.Al0ris;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.CommandManager;

public final class SolarisCommandFramework {

    private static CommandRegistrar registrar;

    public static void init(JavaPlugin plugin, CommandManager<CommandSourceStack> manager) {
        SolarisCommandFramework.registrar = new CommandRegistrar(plugin, manager);
    }

    public static void registerCommand(SolarisCommand command) {
        registrar.register(command);
    }
}
