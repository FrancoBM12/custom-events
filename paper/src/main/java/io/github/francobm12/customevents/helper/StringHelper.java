package io.github.francobm12.customevents.helper;

import org.jetbrains.annotations.NotNull;

public class StringHelper {

    public static @NotNull String replace(final String message, String... objects) {
        if (message == null || message.isEmpty()) {
            return "";
        }

        final StringBuilder result = new StringBuilder(message);

        int index = 0;
        while (index < objects.length - 1) {
            final String object = objects[index];
            if (object == null) {
                break;
            }

            final String replacer = objects[index + 1];
            if (replacer == null) {
                break;
            }

            int start = 0;
            while ((start = result.indexOf(object, start)) != -1) {
                result.replace(start, start + object.length(), replacer);
                start += replacer.length();
            }

            index += 2;
        }

        return result.toString();
    }
}
