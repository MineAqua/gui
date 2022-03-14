package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.animated.stack.AnimatedSlotFrameStack;
import team.unnamed.gui.menu.item.ItemClickable;

import java.util.List;
import java.util.function.Predicate;

public class AnimatedMenuInventory extends DefaultMenuInventory {

    private final List<AnimatedSlotFrameStack> frameStacks;

    protected AnimatedMenuInventory(String title, int slots,
                                    List<ItemClickable> items,
                                    Predicate<Inventory> openAction,
                                    Predicate<Inventory> closeAction,
                                    boolean canIntroduceItems,
                                    List<AnimatedSlotFrameStack> frameStacks) {
        super(title, slots, items, openAction, closeAction, canIntroduceItems);
        this.frameStacks = frameStacks;
    }

    public List<AnimatedSlotFrameStack> getFrameStacks() {
        return frameStacks;
    }

}
