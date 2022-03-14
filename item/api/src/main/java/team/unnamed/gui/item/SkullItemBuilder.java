package team.unnamed.gui.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

import static team.unnamed.bukkit.ServerVersionUtils.SERVER_VERSION;
import static team.unnamed.bukkit.ServerVersionUtils.SERVER_VERSION_INT;

public class SkullItemBuilder
        extends ItemBuilderLayout<SkullItemBuilder> {

    private static final Field PROFILE_FIELD;
    private static final Material MATERIAL;
    private static final byte DATA;

    static {
        try {
            Class<?> metaClass = Class.forName(
                    "org.bukkit.craftbukkit.v"
                            + SERVER_VERSION +
                            ".inventory.CraftMetaSkull"
            );

            PROFILE_FIELD = metaClass.getDeclaredField("profile");

            if (SERVER_VERSION_INT < 13) {
                MATERIAL = Material.SKULL_ITEM;
                DATA = 3;
            } else {
                MATERIAL = Material.getMaterial("PLAYER_HEAD");
                DATA = 0;
            }
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new IllegalStateException("Cannot get the SkullMeta profile field!", e);
        }
    }

    private String owner;
    private String url;

    protected SkullItemBuilder(int amount) {
        super(MATERIAL, amount, DATA);
    }

    public SkullItemBuilder setOwner(String owner) {
        this.owner = owner;

        return this;
    }

    public SkullItemBuilder setUrl(String url) {
        this.url = url;

        return this;
    }

    @Override
    public ItemStack build() {
        ItemStack itemStack = super.build();
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        if (owner != null) {
            skullMeta.setOwner(owner);
        } else if (url != null) {
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
            byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());

            gameProfile.getProperties().put("textures", new Property("textures", new String(encodedData)));
            boolean accessible = PROFILE_FIELD.isAccessible();
            PROFILE_FIELD.setAccessible(true);

            try {
                PROFILE_FIELD.set(skullMeta, gameProfile);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                PROFILE_FIELD.setAccessible(accessible);
            }

        }

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    @Override
    protected SkullItemBuilder back() {
        return this;
    }

}
