package team.unnamed.gui.menu.animated;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.animated.frame.AnimatedSlotFrame;
import team.unnamed.gui.menu.animated.stack.AnimatedSlotFrameStack;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.type.AnimatedMenuInventory;
import team.unnamed.gui.menu.type.MenuInventory;
import team.unnamed.gui.menu.MenuInventoryWrapper;

import java.util.List;

public class AnimatedMenuRunnable implements Runnable {

    private final AnimatedMenuRegistry registry;

    public AnimatedMenuRunnable(AnimatedMenuRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void run() {
        for (MenuInventoryWrapper wrapper : registry.getInventories()) {
            Inventory inventory = wrapper.getRawInventory();
            MenuInventory menuInventory = wrapper.getMenuInventory();

            if (menuInventory instanceof AnimatedMenuInventory) {
                AnimatedMenuInventory animatedMenuInventory
                        = (AnimatedMenuInventory) menuInventory;

                for (AnimatedSlotFrameStack frameStack
                        : animatedMenuInventory.getFrameStacks()) {
                    if (!frameStack.hasNext()) {
                        continue;
                    }

                    List<Integer> involvedSlots = frameStack.getInvolvedSlots();
                    AnimatedSlotFrame nextFrame = frameStack.next();

                    if (nextFrame == null) {
                        continue;
                    }

                    ItemClickable itemClickable = nextFrame.getItem();
                    for (int slot : involvedSlots) {
                        if (itemClickable == null) {
                            menuInventory.removeItem(slot);
                            inventory.setItem(slot, null);
                        } else {
                            menuInventory.setItem(itemClickable.clone(slot));
                            inventory.setItem(slot, itemClickable.getItemStack());
                        }
                    }
                }
            }
        }
    }

}