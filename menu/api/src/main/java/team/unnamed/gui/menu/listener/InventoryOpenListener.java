package team.unnamed.gui.menu.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.animated.AnimatedMenuRegistry;
import team.unnamed.gui.menu.type.AnimatedMenuInventory;
import team.unnamed.gui.menu.type.MenuInventory;
import team.unnamed.gui.menu.MenuInventoryWrapper;
import team.unnamed.gui.menu.util.MenuUtils;

import java.util.function.Predicate;

public class InventoryOpenListener
        implements Listener {

    private final AnimatedMenuRegistry animatedMenuRegistry;

    public InventoryOpenListener(AnimatedMenuRegistry animatedMenuRegistry) {
        this.animatedMenuRegistry = animatedMenuRegistry;
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();

        if (MenuUtils.isCustomMenu(inventory)) {
            MenuInventoryWrapper wrapper = MenuUtils.getAsWrapper(inventory);
            MenuInventory menuInventory = wrapper.getMenuInventory();
            Predicate<Inventory> action = menuInventory.getOpenAction();

            boolean cancelled = false;
            if (action != null) {
                if (action.test(inventory)) {
                    cancelled = true;
                    event.setCancelled(true);
                }
            }

            if (!cancelled && menuInventory instanceof AnimatedMenuInventory) {
                animatedMenuRegistry.register((Player) event.getPlayer(), wrapper);
            }
        }
    }

}