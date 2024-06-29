package io.github.francobm12.customevents;

import io.github.francobm12.customevents.builder.Buildable;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PluginLauncher extends JavaPlugin {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    private void registerListener(final Listener @NotNull ... listeners) {
        for (final Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
