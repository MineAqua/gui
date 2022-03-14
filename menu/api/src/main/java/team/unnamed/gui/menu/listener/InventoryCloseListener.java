package team.unnamed.gui.menu.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import org.bukkit.plugin.Plugin;
import team.unnamed.gui.menu.animated.AnimatedMenuRegistry;
import team.unnamed.gui.menu.type.MenuInventory;
import team.unnamed.gui.menu.MenuInventoryWrapper;
import team.unnamed.gui.menu.util.MenuUtils;

import java.util.function.Predicate;

public class InventoryCloseListener
        implements Listener {

    private final Plugin plugin;
    private final AnimatedMenuRegistry animatedMenuRegistry;

    public InventoryCloseListener(Plugin plugin, AnimatedMenuRegistry animatedMenuRegistry) {
        this.plugin = plugin;
        this.animatedMenuRegistry = animatedMenuRegistry;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        if (MenuUtils.isCustomMenu(inventory)) {
            MenuInventoryWrapper wrapper = MenuUtils.getAsWrapper(inventory);
            MenuInventory menuInventory = wrapper.getMenuInventory();
            Predicate<Inventory> action = menuInventory.getCloseAction();

            boolean cancelled = false;
            if (action != null) {
                if (action.test(inventory)) {
                    cancelled = true;
                    Bukkit.getScheduler().runTaskLater(
                            plugin, () -> event.getPlayer().openInventory(inventory), 1);
                }
            }

            if (!cancelled) {
                animatedMenuRegistry.remove((Player) event.getPlayer());
            }
        }
    }

}
