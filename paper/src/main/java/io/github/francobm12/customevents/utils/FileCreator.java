package io.github.francobm12.customevents.utils;

import io.github.francobm12.customevents.helper.ComponentHelper;
import io.github.francobm12.customevents.helper.StringHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

public class FileCreator extends YamlConfiguration {

    private static final String INVALID_PATH = "{ invalid message key }";

    private final String fileName;
    private final Plugin plugin;
    private final File file;


    public FileCreator(Plugin plugin, @NotNull String filename, String fileExtension, File folder){
        this.plugin = plugin;
        this.fileName = filename + (filename.endsWith(fileExtension) ? "" : fileExtension);
        this.file = new File(folder, this.fileName);
        this.createFile();
    }

    @Contract("_, _ -> new")
    public static @NotNull FileCreator createFile(final @NotNull Plugin plugin, final @NotNull String filename) {
        return new FileCreator(plugin, filename);
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

    public void saveDefault() {
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

    public Component asComponent(final @Nullable  String path, final @Nullable String... replacements) {
        if (path == null || path.isEmpty()) {
            return ComponentHelper.asComponent(INVALID_PATH);
        }

        final String result = super.getString(path);
        if (result == null) {
            return ComponentHelper.asComponent(INVALID_PATH);
        }

        final String contentResult = replacements == null ? result : StringHelper.replace(result, replacements);
        return ComponentHelper.asComponent(contentResult);
    }

    public List<Component> asComponentList(final @Nullable String path, final int replaceAtIndex, final @Nullable String... replacements) {
        final List<Component> components = new ArrayList<>();
        if (path == null || path.isEmpty()) {
            return components;
        }

        final List<String> arrayContents = this.getStringList(path);
        for (int index = 0; index < arrayContents.size(); index++) {
            final String content = arrayContents.get(index);
            if (index != replaceAtIndex) {
                components.add(ComponentHelper.asComponent(content));
                continue;
            }

            final String componentContent = replacements == null ? content : StringHelper.replace(content, replacements);
            components.add(ComponentHelper.asComponent(componentContent));

        }

        return components;
    }

    public List<Component> asComponentList(final @Nullable String path, final @Nullable String... replacements) {
        final List<Component> components = new ArrayList<>();
        if (path == null || path.isEmpty()) {
            return components;
        }

        final List<String> arrayContents = this.getStringList(path);
        for (final String inputLine : arrayContents) {
            final String componentContent = replacements == null ? inputLine : StringHelper.replace(inputLine, replacements);
            components.add(ComponentHelper.asComponent(componentContent));
        }

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