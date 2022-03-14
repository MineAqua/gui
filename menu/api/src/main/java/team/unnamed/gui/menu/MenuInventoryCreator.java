package team.unnamed.gui.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface MenuInventoryCreator {

    Inventory create(Player player, Object... data);

}
