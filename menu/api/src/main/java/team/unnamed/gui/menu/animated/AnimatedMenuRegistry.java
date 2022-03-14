package team.unnamed.gui.menu.animated;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.Nullable;

import team.unnamed.gui.menu.MenuInventoryWrapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AnimatedMenuRegistry {

    private final Map<UUID, MenuInventoryWrapper> inventories;

    public AnimatedMenuRegistry() {
        this.inventories = new HashMap<>();
    }

    public void initializeTask(Plugin plugin) {
        Bukkit.getScheduler().runTaskTimer(
                plugin, new AnimatedMenuRunnable(this), 0, 1);
    }

    public Collection<MenuInventoryWrapper> getInventories() {
        return this.inventories.values();
    }

    public @Nullable MenuInventoryWrapper getWrapper(Player player) {
        return this.inventories.get(player.getUniqueId());
    }

    public void remove(Player player) {
        this.inventories.remove(player.getUniqueId());
    }

    public void register(Player player, MenuInventoryWrapper wrapper) {
        this.inventories.put(player.getUniqueId(), wrapper);
    }

}
