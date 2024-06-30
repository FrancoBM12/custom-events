package io.github.francobm12.customevents.helper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringHelper {

    public static @NotNull @Unmodifiable Set<Integer> stringToIntegerSet(final @Nullable String content) {
        if (content == null || content.isEmpty()) {
            return Set.of();
        }

        final Set<Integer> result = new HashSet<>();
        final String[] parts = content.split(",");

        for (final String part : parts) {
            try {
                final int number = Integer.parseInt(part);
                result.add(number);
            } catch (NumberFormatException e) {
                // Ignore non-integer parts
            }
        }

        return result;
    }

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
