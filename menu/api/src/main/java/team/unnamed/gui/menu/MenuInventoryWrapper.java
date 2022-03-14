package team.unnamed.gui.menu;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.menu.type.MenuInventory;

public interface MenuInventoryWrapper {

    @NotNull MenuInventory getMenuInventory();

    @NotNull Inventory getRawInventory();

}