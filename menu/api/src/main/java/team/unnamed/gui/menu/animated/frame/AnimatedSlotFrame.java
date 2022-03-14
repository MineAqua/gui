package team.unnamed.gui.menu.animated.frame;

import team.unnamed.gui.menu.item.ItemClickable;

public class AnimatedSlotFrame {

    private final ItemClickable item;
    private final int delay;

    private AnimatedSlotFrame(ItemClickable item, int delay) {
        this.item = item;
        this.delay = delay;
    }

    public ItemClickable getItem() {
        return item;
    }

    public int getDelay() {
        return delay;
    }

    public static AnimatedSlotFrame create(ItemClickable item) {
        return create(item, 20);
    }

    public static AnimatedSlotFrame create(ItemClickable item, int delay) {
        return new AnimatedSlotFrame(item, delay);
    }

}
