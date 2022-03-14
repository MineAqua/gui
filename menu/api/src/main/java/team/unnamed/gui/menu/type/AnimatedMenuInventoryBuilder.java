package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.animated.frame.AnimatedSlotFrame;
import team.unnamed.gui.menu.animated.stack.AnimatedSlotFrameStack;

import java.util.ArrayList;
import java.util.List;

import static team.unnamed.validate.Validate.isNotNull;

public class AnimatedMenuInventoryBuilder
        extends MenuInventoryBuilderLayout<AnimatedMenuInventoryBuilder> {

    private final List<AnimatedSlotFrameStack> frameStacks;

    protected AnimatedMenuInventoryBuilder(String title) {
        this(title, 6);
    }

    protected AnimatedMenuInventoryBuilder(String title, int rows) {
        super(title, rows);
        this.frameStacks = new ArrayList<>();
    }

    public AnimatedMenuInventoryBuilder addFrameStack(AnimatedSlotFrameStack frameStack) {
        frameStacks.add(isNotNull(frameStack, "Frame stack cannot be null."));
        return this;
    }

    @Override
    public Inventory build() {
        for (AnimatedSlotFrameStack frameStack : frameStacks) {
            List<Integer> involvedSlots = frameStack.getInvolvedSlots();
            AnimatedSlotFrame nextFrame = frameStack.next();

            for (int slot : involvedSlots) {
                addItem(nextFrame.getItem().clone(slot));
            }
        }

        return super.internalBuild(new AnimatedMenuInventory(
                title, slots, items,
                openAction, closeAction, canIntroduceItems,
                frameStacks
        ));
    }

    @Override
    protected AnimatedMenuInventoryBuilder back() {
        return this;
    }

}
