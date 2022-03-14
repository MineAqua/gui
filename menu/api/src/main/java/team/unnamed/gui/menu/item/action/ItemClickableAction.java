package team.unnamed.gui.menu.item.action;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public interface ItemClickableAction {

    ItemClickableAction CANCEL_GLOBAL_ACTION = new GlobalItemClickableAction(inventory -> true);

    @Nullable Predicate<Inventory> getAction(ClickType clickType);

    static ItemClickableActionBuilder builder() {
        return new ItemClickableActionBuilder();
    }

    static ItemClickableAction globalAction(Predicate<Inventory> action) {
        return new GlobalItemClickableAction(action);
    }

}