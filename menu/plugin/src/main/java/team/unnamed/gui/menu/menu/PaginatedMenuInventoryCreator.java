package team.unnamed.gui.menu.menu;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.menu.MenuInventoryCreator;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.type.MenuInventory;

import java.util.ArrayList;
import java.util.List;

import static team.unnamed.gui.item.util.DecorateItemUtils.newStainedPane;

public class PaginatedMenuInventoryCreator
        implements MenuInventoryCreator {

    @Override
    public Inventory create(Player player, Object... data) {
        List<ItemStack> entities = new ArrayList<>();

        for (int i = 0; i <= 90; i++) {
            entities.add(ItemBuilder.newBuilder(Material.ENDER_PEARL)
                    .setName("Item #" + i)
                    .build()
            );
        }

        ItemClickable decorationItem = ItemClickable.onlyItem(newStainedPane(DyeColor.BLACK));

        return MenuInventory.newPaginatedBuilder(ItemStack.class, "Paginated Test")
                .setEntities(entities)
                .setItemsPerRow(7)
                .setEntityParser(ItemClickable::onlyItem)
                .setSkippedSlots(10, 16, 28, 34, 37, 38, 42, 43)
                .setBounds(10, 44)
                .setItemIfNoPreviousPage(decorationItem)
                .setItemIfNoNextPage(decorationItem)
                .setNextPageItem(page -> ItemClickable.onlyItem(ItemBuilder.newBuilder(Material.ARROW)
                        .setName("Next page - " + page)
                        .build()))
                .setPreviousPageItem(page -> ItemClickable.onlyItem(ItemBuilder.newBuilder(Material.ARROW)
                        .setName("Previous page - " + page)
                        .build()))
                .setLayoutLines(
                        "xxxxxxxxx",
                        "xseeeeesx",
                        "xeeeeeeex",
                        "xseeeeesx",
                        "xsseeessx",
                        "xpxxxxxnx"
                )
                .setLayoutItem('s', ItemClickable.onlyItem(newStainedPane(DyeColor.WHITE)))
                .setLayoutItem('x', decorationItem)
                .build();
    }

}
