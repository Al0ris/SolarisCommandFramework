package dev.solarisframework.command;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.incendo.cloud.CommandManager;

public interface SolarisCommand {
    void register(CommandManager<CommandSourceStack> manager);
}
