package dev.solarisframework.command;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MainThread {

    public static void run(JavaPlugin plugin, Runnable task) {
        if (Bukkit.isPrimaryThread()) {
            task.run();
        } else {
            Bukkit.getScheduler().runTask(plugin, task);
        }
    }
}
