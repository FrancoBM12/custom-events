package io.github.francobm12.customevents.messages;

import io.github.francobm12.customevents.helper.ComponentHelper;
import io.github.francobm12.customevents.helper.StringHelper;
import io.github.francobm12.customevents.utils.FileCreator;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private final FileCreator configuration;

    public MessageManager(final @NotNull FileCreator configuration) {
        this.configuration = configuration;
    }

    public @NotNull Component content(final @NotNull String messageKey, final @Nullable String... replacements) {
        return configuration.asComponent(messageKey, replacements);
    }

    /*
     *
     * @param messageKey   so
     * @param replacements so
     *
     * */

    public @NotNull List<Component> contents(final @NotNull String messageKey, final @Nullable String... replacements) {
        return configuration.asComponentList(messageKey, replacements);
    }

    /*
    *
    * @param player       so
    * @param messageKey   so
    * @param replacements so
    *
    * */

    public void sendMany(
            final @NotNull Player player,
            final @NotNull String messageKey,
            final @Nullable String... replacements
    ) {
        this.configuration.asComponentList(messageKey, replacements).forEach(player::sendMessage);
    }

    /*
     *
     * @param player       so
     * @param messageKey   so
     * @param replacements so
     *
     * */

    public void sendClickable(
            final @NotNull Player player,
            final @NotNull String messageKey,
            final @Nullable String... replacements
    ) {

        final Component component = this.content(messageKey, replacements);
        player.sendMessage(component);
    }

    public void send(
            final @NotNull Player player,
            final @NotNull String messageKey,
            final @Nullable String... replacements
    ) {

        final Component component = this.content(messageKey, replacements);
        player.sendMessage(component);
    }

}
