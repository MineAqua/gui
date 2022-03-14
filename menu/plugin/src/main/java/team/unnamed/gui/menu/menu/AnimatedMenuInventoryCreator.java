package team.unnamed.gui.menu.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import team.unnamed.gui.menu.MenuInventoryCreator;
import team.unnamed.gui.menu.animated.frame.AnimatedSlotFrame;
import team.unnamed.gui.menu.animated.stack.AnimatedSlotFrameStack;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.item.action.ItemClickableAction;
import team.unnamed.gui.menu.type.MenuInventory;
import team.unnamed.gui.menu.util.Slots;

public class AnimatedMenuInventoryCreator
        implements MenuInventoryCreator {

    @Override
    public Inventory create(Player player, Object... data) {
        return MenuInventory.newAnimatedBuilder("Testing")
                .addFrameStack(AnimatedSlotFrameStack.newBuilder(22)
                        .setLoops(-1)
                        .addFrame(AnimatedSlotFrame.create(
                                ItemClickable.onlyItem(
                                        new ItemStack(Material.ENDER_PEARL),
                                        ItemClickableAction.globalAction(inventory -> {
                                            player.closeInventory();
                                            player.sendMessage("Ender pearl!");
                                            return true;
                                        })),
                                20
                        ))
                        .addFrame(AnimatedSlotFrame.create(
                                ItemClickable.onlyItem(
                                        new ItemStack(Material.DIAMOND),
                                        ItemClickableAction.globalAction(inventory -> {
                                            player.closeInventory();
                                            player.sendMessage("Diamond!");
                                            return true;
                                        })),
                                20
                        ))
                        .build())
                .addFrameStack(AnimatedSlotFrameStack.newBuilder(Slots.getBorderSlots(6))
                        .setLoops(-1)
                        .addFrame(AnimatedSlotFrame.create(
                                ItemClickable.onlyItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14)),
                                20
                        ))
                        .addFrame(AnimatedSlotFrame.create(
                                ItemClickable.onlyItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 1)),
                                20
                        ))
                        .addFrame(AnimatedSlotFrame.create(
                                ItemClickable.onlyItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 4)),
                                20
                        ))
                        .addFrame(AnimatedSlotFrame.create(
                                ItemClickable.onlyItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5)),
                                20
                        ))
                        .addFrame(AnimatedSlotFrame.create(
                                ItemClickable.onlyItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)),
                                20
                        ))
                        .addFrame(AnimatedSlotFrame.create(
                                ItemClickable.onlyItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11)),
                                20
                        ))
                        .addFrame(AnimatedSlotFrame.create(
                                ItemClickable.onlyItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 6)),
                                20
                        ))
                        .build())
                .build();
    }

}
