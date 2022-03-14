package team.unnamed.gui.menu.animated.stack;

import team.unnamed.gui.menu.animated.frame.AnimatedSlotFrame;

import java.util.List;

public class SimpleAnimatedSlotFrameStack
        implements AnimatedSlotFrameStack {

    private final List<AnimatedSlotFrame> frames;
    private final List<Integer> involvedSlots;

    protected int cursor;
    protected AnimatedSlotFrame currentFrame;
    protected int currentDelay;

    protected SimpleAnimatedSlotFrameStack(List<AnimatedSlotFrame> frames,
                                           List<Integer> involvedSlots) {
        this.frames = frames;
        this.involvedSlots = involvedSlots;
    }

    @Override
    public AnimatedSlotFrame next() {
        return internalNext();
    }

    @Override
    public AnimatedSlotFrame current() {
        return currentFrame;
    }

    @Override
    public int getSize() {
        return frames.size();
    }

    @Override
    public List<Integer> getInvolvedSlots() {
        return involvedSlots;
    }

    @Override
    public boolean hasNext() {
        if (currentDelay > 0 && currentFrame != null) {
            currentDelay--;
            return false;
        }

        return cursor >= 0 && cursor <= getSize();
    }

    protected AnimatedSlotFrame internalNext() {
        if (cursor >= getSize()) {
            return null;
        }

        this.currentFrame = frames.get(cursor++);
        this.currentDelay = currentFrame.getDelay();

        return currentFrame;
    }

}
