package io.github.francobm12.customevents.helper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ComponentHelper {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static @NotNull Component asComponent(final @Nullable String content) {
        return content == null || content.isEmpty() ? Component.empty() : MINI_MESSAGE.deserialize(content);
    }

    // TODO: implement this method
    public static @NotNull Component asClickableComponent(final @Nullable String content) {
        return Component.empty();
    }


}
