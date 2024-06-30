package io.github.francobm12.customevents;

import io.github.francobm12.customevents.utils.FileCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PluginLauncher extends JavaPlugin {

    private CacheData cacheData;
    private FileCreator messageFile;

    @Override
    public void onEnable() {
        this.getLogger().info("Enabling plugin...");

        this.messageFile = FileCreator.createFile(this, "messages");
        this.saveDefaultConfig();

        this.cacheData = CacheData.builder()
                .countdownAmount(this.messageFile.getInt("settings.countdown.amount"))
                .countdownNotifications(this.messageFile.getString("settings.countdown.notify"))
                .build();

    }

    @Override
    public void onDisable() {
        this.getLogger().info("Disabling plugin...");
    }

    public YamlConfiguration messageFile() {
        return this.messageFile;
    }

    private void registerListener(final Listener @NotNull ... listeners) {
        for (final Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
