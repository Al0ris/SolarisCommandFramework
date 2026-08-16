package io.github.Al0ris;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.exception.ArgumentParseException;
import org.incendo.cloud.exception.CommandExecutionException;
import org.incendo.cloud.exception.InvalidSyntaxException;
import org.incendo.cloud.exception.NoPermissionException;

public final class ExceptionHandler {

    public static void register(JavaPlugin plugin, CommandManager<CommandSourceStack> manager) {
        manager.exceptionController()
                .registerHandler(NotPlayerException.class, ctx ->
                        sendError(ctx.context().sender(), "This command can only be used by players."))
                .registerHandler(NoPermissionException.class, ctx ->
                        sendError(ctx.context().sender(), "You don't have permission to do that."))
                .registerHandler(InvalidSyntaxException.class, ctx ->
                        sendError(ctx.context().sender(), "Usage: /" + ctx.exception().correctSyntax()))
                .registerHandler(ArgumentParseException.class, ctx ->
                        sendError(ctx.context().sender(), "Invalid argument: " + ctx.exception().getCause().getMessage()))
                .registerHandler(CommandExecutionException.class, ctx -> {
                    sendError(ctx.context().sender(), "An internal error occurred.");
                    plugin.getLogger().warning(ctx.exception().getMessage());
                });
    }

    private static void sendError(CommandSourceStack source, String message) {
        source.getSender().sendMessage(Component.text(message, NamedTextColor.RED));
    }
}
