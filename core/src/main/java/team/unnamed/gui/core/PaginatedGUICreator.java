package team.unnamed.gui.core;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.gui.type.PaginatedGUIData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PaginatedGUICreator {

  public static <E> Inventory createPage(Inventory delegate, PaginatedGUIData<E> guiData) {
    int currentPage = guiData.getCurrentPage();
    List<E> entities = guiData.getEntities();
    Function<E, ItemClickable> itemParser = guiData.getItemParser();
    int spaces = guiData.getSpaces();

    List<ItemClickable> items = guiData.getItems();
    List<ItemClickable> copyItems = new ArrayList<>(guiData.getBaseItems());

    List<Integer> availableSlots = guiData.getAvailableSlots();

    int entityIndexStart = currentPage == 1 ? 0 : spaces * (currentPage - 1);
    int entityIndexEnd = spaces * currentPage;
    int slotIndex = 0;
    int entitiesSize = entities.size();

    for (int i = entityIndexStart; i < entityIndexEnd; i++) {
      if (i >= entitiesSize) {
        break;
      }

      E entity = entities.get(i);
      ItemClickable itemClickable = itemParser.apply(entity);

      int slot = availableSlots.get(slotIndex);

      copyItems.set(
          slot,
          itemClickable.cloneInSlot(slot)
      );

      slotIndex++;
    }

    ItemClickable nextPageItem = guiData.getNextPageItem();
    ItemClickable previousPageItem = guiData.getPreviousPageItem();

    if (currentPage > 1) {
      copyItems.set(previousPageItem.getSlot(), previousPageItem);
    }

    if (currentPage < guiData.getMaxPages()) {
      copyItems.set(nextPageItem.getSlot(), nextPageItem);
    }

    for (ItemClickable itemClickable : copyItems) {
      items.add(itemClickable);

      if (itemClickable == null) {
        continue;
      }

      delegate.setItem(
          itemClickable.getSlot(),
          itemClickable.getItemStack()
      );
    }

    return delegate;
  }

}