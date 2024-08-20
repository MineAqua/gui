package team.unnamed.gui.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import team.unnamed.gui.item.skull.SkullSkin;

import java.lang.reflect.Field;
import java.util.UUID;

public class SkullItemBuilder
        extends ItemBuilderLayout<SkullItemBuilder> {

    private static final Field PROFILE_FIELD;
    private static final Material SKULL_MATERIAL;
    private static final byte DATA;

    static {
        try {
            final Class<?> metaClass;
            if (ServerVersionConstants.MINOR_VERSION >= 21) {
                metaClass = Class.forName("org.bukkit.craftbukkit.inventory.CraftMetaSkull");
                SKULL_MATERIAL = Material.getMaterial("PLAYER_HEAD");
                DATA = 0;
            } else {
                metaClass = Class.forName("org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaSkull");
                SKULL_MATERIAL = Material.SKULL_ITEM;
                DATA = 3;
            }

            PROFILE_FIELD = metaClass.getDeclaredField("profile");
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new IllegalStateException("Cannot get the SkullMeta profile field!", e);
        }
    }

    private final SkullSkin skin;

    private SkullItemBuilder(int amount, SkullSkin skin) {
        super(SKULL_MATERIAL, amount, DATA);
        this.skin = skin;
    }

    public static SkullItemBuilder create(SkullSkin skin) {
        return new SkullItemBuilder(1, skin);
    }

    public static SkullItemBuilder create(int amount, SkullSkin skin) {
        return new SkullItemBuilder(amount, skin);
    }

    @Override
    public ItemStack build() {
        ItemStack itemStack = super.build();
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "unnamed");

        gameProfile.getProperties().put("textures", new Property(
                "textures",
                skin.getValue(),
                skin.getSignature()
        ));

        boolean accessible = PROFILE_FIELD.isAccessible();
        PROFILE_FIELD.setAccessible(true);

        try {
            PROFILE_FIELD.set(skullMeta, gameProfile);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            PROFILE_FIELD.setAccessible(accessible);
        }

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    @Override
    protected SkullItemBuilder back() {
        return this;
    }
}
