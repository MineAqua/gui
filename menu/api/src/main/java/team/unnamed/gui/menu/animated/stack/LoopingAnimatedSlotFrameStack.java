package team.unnamed.gui.menu.animated.stack;

import team.unnamed.gui.menu.animated.frame.AnimatedSlotFrame;

import java.util.List;

public class LoopingAnimatedSlotFrameStack
        extends SimpleAnimatedSlotFrameStack {

    private final boolean infiniteLoop;
    private final int totalLoops;

    private int currentLoop;

    protected LoopingAnimatedSlotFrameStack(List<AnimatedSlotFrame> frames,
                                            List<Integer> involvedSlots, int totalLoops) {
        super(frames, involvedSlots);
        this.totalLoops = totalLoops;
        this.infiniteLoop = totalLoops == -1;
    }

    @Override
    public boolean hasNext() {
        return super.hasNext() && (infiniteLoop || currentLoop <= totalLoops);
    }

    @Override
    public AnimatedSlotFrame next() {
        AnimatedSlotFrame nextFrame = super.internalNext();

        if (nextFrame == null) {
            if (currentLoop++ >= totalLoops && !infiniteLoop) {
                return null;
            }

            cursor = 0;
            return super.internalNext();
        } else {
            return nextFrame;
        }
    }

}
