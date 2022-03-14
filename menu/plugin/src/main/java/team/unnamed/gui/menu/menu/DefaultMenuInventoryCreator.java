package team.unnamed.gui.menu.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import team.unnamed.gui.menu.MenuInventoryCreator;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.type.MenuInventory;

public class DefaultMenuInventoryCreator
        implements MenuInventoryCreator {

    @Override
    public Inventory create(Player player, Object... data) {
        return MenuInventory.newBuilder("Test")
                .fillBorders(ItemClickable.onlyItem(new ItemStack(Material.STAINED_GLASS_PANE)))
                .addItem(ItemClickable.builder(22)
                        .setItem(new ItemStack(Material.ENDER_PEARL))
                        .setAction(inventory -> {
                            player.sendMessage("Testing");
                            player.closeInventory();
                            return true;
                        })
                        .build())
                .setOpenAction(inventory -> {
                    player.sendMessage("Opening...");
                    return false;
                })
                .setCloseAction(inventory -> {
                    player.sendMessage("Closing...");

                    return false;
                })
                .build();
    }

}
