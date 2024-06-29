package io.github.francobm12.api.events;

import io.github.francobm12.api.numerics.Numeric;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface EventSettings {

    List<UUID> participants();

    Numeric currentPlayers();
    Numeric minPlayers();
    Numeric maxPlayers();

    // TODO: rename method name
    default List<Player> participantsAsBukkitPlayers() {
        return this.participants().stream().map(Bukkit::getPlayer).toList();
    }


}
