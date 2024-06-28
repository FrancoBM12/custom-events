package io.github.francobm12.customevents;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginLauncher extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Hello World pe");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
