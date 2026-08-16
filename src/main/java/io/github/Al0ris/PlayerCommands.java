package io.github.Al0ris;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.execution.CommandExecutionHandler;

import java.util.function.BiConsumer;

public final class PlayerCommands {

    public static CommandExecutionHandler<CommandSourceStack> playerOnly(
            BiConsumer<Player, CommandContext<CommandSourceStack>> handler) {
        return ctx -> {
            CommandSender sender = ctx.sender().getSender();
            if (!(sender instanceof Player player)) {
                throw new NotPlayerException(ctx.sender());
            }
            handler.accept(player, ctx);
        };
    }
}
