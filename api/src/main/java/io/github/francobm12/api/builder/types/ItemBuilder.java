package io.github.francobm12.api.builder.types;

import io.github.francobm12.api.builder.Buildable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemBuilder {


    @Contract(value = " -> new", pure = true)
    public static @NotNull Builder builder() {
        return new Builder();
    }

    public static class Builder implements Buildable<ItemStack> {

        // private accessors for internal use
        private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

        private String displayName;
        private List<String> description;

        private Material material = Material.STONE;
        private int amount = 0;

        public Builder name(final @Nullable String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder description(final @NotNull List<String> description) {
            this.description = description;
            return this;
        }

        public Builder material(final Material material) {
            this.material = material;
            return this;
        }

        public Builder amount(final int amount) {
            this.amount = amount;
            return this;
        }

        @Override
        public ItemStack build() {
            final ItemStack itemStack = new ItemStack(this.material, this.amount);
            final ItemMeta itemMeta = Bukkit.getItemFactory().getItemMeta(itemStack.getType());

            if (this.displayName != null) {
                itemMeta.displayName(MINI_MESSAGE.deserialize(this.displayName));
            }

            if (!this.description.isEmpty()) {
                final List<Component> description = this.description.stream()
                        .map(MINI_MESSAGE::deserialize)
                        .toList();

                itemMeta.lore(description);
            }

            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
    }

}
