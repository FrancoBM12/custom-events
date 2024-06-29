package io.github.francobm12.customevents.utils;

import io.github.francobm12.customevents.helper.ComponentHelper;
import io.github.francobm12.customevents.helper.StringHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

public class FileCreator extends YamlConfiguration {

    private final String fileName;
    private final Plugin plugin;
    private final File file;

    public FileCreator(Plugin plugin, String filename, String fileExtension, File folder){
        this.plugin = plugin;
        this.fileName = filename + (filename.endsWith(fileExtension) ? "" : fileExtension);
        this.file = new File(folder, this.fileName);
        this.createFile();
    }

    public FileCreator(Plugin plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }

    public FileCreator(Plugin plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    private void createFile() {
        try {
            if(!fileName.endsWith(".yml")){
                if(!file.exists()) {
                    if (this.plugin.getResource(this.fileName) != null) {
                        this.plugin.saveResource(this.fileName, false);
                    }
                    return;
                }
            }
            if (!file.exists()) {
                if (this.plugin.getResource(this.fileName) != null) {
                    this.plugin.saveResource(this.fileName, false);
                } else {
                    this.save(file);
                }
                this.load(file);
                return;
            }
            this.load(file);

            this.save(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
    public void saveDefault(){
        this.plugin.saveResource(this.fileName, false);
    }
    public void save() {
        try {
            this.save(file);
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Save of the file '" + this.fileName + "' failed.", e);
        }
    }

    public void reload() {
        try {
            load(file);
        } catch(IOException | InvalidConfigurationException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Reload of the file '" + this.fileName + "' failed.", e);
        }
    }

    public Component getComponent(String path) {
        String s = super.getString(path);
        if(s == null) return ComponentHelper.asComponent("{ invalid message key }");
        return ComponentHelper.asComponent(s);
    }

    public Component getComponent(String path, String... replacements) {
        if(replacements == null) return getComponent(path);
        String s = super.getString(path);
        if(s == null) return ComponentHelper.asComponent("{ invalid message key }");
        return ComponentHelper.asComponent(StringHelper.replace(s, replacements));
    }

    public List<Component> getComponentList(String path) {
        List<String> list = super.getStringList(path);
        List<Component> components = new ArrayList<>();

        list.forEach(line -> components.add(ComponentHelper.asComponent(line)));

        return components;
    }

    public List<Component> getComponentList(String path, String... replacements) {
        if(replacements == null) return getComponentList(path);

        List<String> list = super.getStringList(path);
        List<Component> components = new ArrayList<>();

        list.forEach(line -> components.add(ComponentHelper.asComponent(StringHelper.replace(line, replacements))));

        return components;
    }

    public boolean exists(){
        return file.exists();
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String getName() {
        String name = fileName.replace(".yml", "");
        return name.substring(name.lastIndexOf("/") + 1);
    }
}