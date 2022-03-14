package team.unnamed.gui.menu.animated.stack;

import team.unnamed.gui.menu.animated.frame.AnimatedSlotFrame;

import java.util.List;

public class SingleAnimatedSlotFrameStack
        implements AnimatedSlotFrameStack {

    private final List<Integer> involvedSlots;
    private final AnimatedSlotFrame frame;

    protected SingleAnimatedSlotFrameStack(List<Integer> involvedSlots,
                                           AnimatedSlotFrame frame) {
        this.involvedSlots = involvedSlots;
        this.frame = frame;
    }

    @Override
    public AnimatedSlotFrame next() {
        return frame;
    }

    @Override
    public AnimatedSlotFrame current() {
        return frame;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public List<Integer> getInvolvedSlots() {
        return involvedSlots;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

}
