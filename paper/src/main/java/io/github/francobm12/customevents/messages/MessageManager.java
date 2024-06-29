package io.github.francobm12.customevents.messages;

import io.github.francobm12.customevents.helper.ComponentHelper;
import io.github.francobm12.customevents.helper.StringHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


// TODO: NO CRITIQUES PE CAUSA, NO QUIERO VOLVER A ESCRIBIRLOS
public class MessageManager {

    private final FileConfiguration configuration;
    public MessageManager(final @NotNull FileConfiguration configuration) {
        this.configuration = configuration;
    }

    public @NotNull String content(final @NotNull String messageKey, final @Nullable String... replacements) {
        final String input = this.configuration.getString(messageKey);
        if (input == null) {
            return "{ invalid message key }";
        }

        return replacements == null ? input : StringHelper.replace(input, replacements);
    }

    /*
     *
     * @param messageKey   so
     * @param replacements so
     *
     * */

    public @NotNull List<Component> contents(final @NotNull String messageKey, final @Nullable String... replacements) {
        final List<Component> components = new ArrayList<>();
        final List<String> input = this.configuration.getStringList(messageKey);

        for (final String replacement : input) {
            final String toReplace = StringHelper.replace(replacement, replacements);
            final Component component = ComponentHelper.asComponent(toReplace);
            components.add(component);
        }

        return components;
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
        final List<String> messages = this.configuration.getStringList(messageKey);
        for (final String message : messages) {

            final String toReplace = StringHelper.replace(message, replacements);
            final Component component = ComponentHelper.asComponent(toReplace);

            player.sendMessage(component);
        }
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

        final String content = this.content(messageKey, replacements);
        final Component component = ComponentHelper.asClickableComponent(content);

        player.sendMessage(component);
    }

    public void send(
            final @NotNull Player player,
            final @NotNull String messageKey,
            final @Nullable String... replacements
    ) {

        final String content = this.content(messageKey, replacements);
        final Component component = ComponentHelper.asComponent(content);

        player.sendMessage(component);
    }

}
