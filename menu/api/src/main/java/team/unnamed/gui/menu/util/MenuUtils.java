package team.unnamed.gui.menu.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.menu.MenuInventoryWrapper;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.type.MenuInventory;

import java.util.List;

public final class MenuUtils {

    private MenuUtils() {
        // the class shouldn't be instantiated
        throw new UnsupportedOperationException();
    }

    public static void fillItemList(List<ItemClickable> items, int slots) {
        for (int i = 0; i < slots; i++) {
            items.add(null);
        }
    }

    public static @NotNull Inventory parseToInventory(MenuInventory menuInventory) {
        return new DefaultMenuInventoryWrapper(null, menuInventory).getRawInventory();
    }

    public static boolean isCustomMenu(Inventory inventory) {
        if (inventory == null) {
            return false;
        }

        InventoryHolder holder = inventory.getHolder();

        return holder instanceof MenuInventoryWrapper
                || inventory instanceof MenuInventoryWrapper;
    }

    public static @NotNull MenuInventoryWrapper getAsWrapper(@NotNull Inventory inventory) {
        InventoryHolder holder = inventory.getHolder();

        return holder == null ?
                (MenuInventoryWrapper) inventory :
                (MenuInventoryWrapper) holder;
    }

}