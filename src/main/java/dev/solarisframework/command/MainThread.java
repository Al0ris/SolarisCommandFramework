package dev.solarisframework.command;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executor;

public final class MainThread {

    public static Executor executor(JavaPlugin plugin) {
        return runnable -> run(plugin, runnable);
    }

    public static void run(JavaPlugin plugin, Runnable task) {
        if (Bukkit.isPrimaryThread()) {
            task.run();
        } else {
            Bukkit.getScheduler().runTask(plugin, task);
        }
    }
}
