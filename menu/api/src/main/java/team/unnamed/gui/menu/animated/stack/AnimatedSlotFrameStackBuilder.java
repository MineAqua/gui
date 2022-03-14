package team.unnamed.gui.menu.animated.stack;

import team.unnamed.gui.menu.animated.frame.AnimatedSlotFrame;

import java.util.ArrayList;
import java.util.List;

import static team.unnamed.validate.Validate.isNotNull;

public class AnimatedSlotFrameStackBuilder {

    private final List<AnimatedSlotFrame> frames;
    private final List<Integer> involvedSlots;

    private int loops;

    protected AnimatedSlotFrameStackBuilder(List<Integer> involvedSlots) {
        this.involvedSlots = involvedSlots;
        this.frames = new ArrayList<>();
    }

    public AnimatedSlotFrameStackBuilder addFrame(AnimatedSlotFrame frame) {
        this.frames.add(isNotNull(frame, "Frame cannot be null."));
        return this;
    }

    public AnimatedSlotFrameStackBuilder setLoops(int loops) {
        this.loops = loops;
        return this;
    }

    public AnimatedSlotFrameStack build() {
        if (loops == 0) {
            return new SimpleAnimatedSlotFrameStack(this.frames, this.involvedSlots);
        } else {
            return new LoopingAnimatedSlotFrameStack(this.frames, this.involvedSlots, this.loops);
        }
    }

}
