package io.github.francobm12.customevents;


import io.github.francobm12.api.builder.Buildable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// TODO:
public record CacheData(
        int countdownAmount,
        Set<Integer> countdownNotifications
) {

    @Contract(value = " -> new", pure = true)
    public static @NotNull Builder builder() {
        return new Builder();
    }

    public static class Builder implements Buildable<CacheData> {

        private int countdownAmount;
        private Set<Integer> countdownNotifications;

        public Builder countdownAmount(int countdownAmount) {
            this.countdownAmount = countdownAmount;
            return this;
        }

        public Builder countdownNotifications(Set<Integer> countdownNotifications) {
            this.countdownNotifications = countdownNotifications;
            return this;
        }

        @Override
        public CacheData build() {
            return new CacheData(
                    this.countdownAmount,
                    this.countdownNotifications
            );
        }
    }


}
