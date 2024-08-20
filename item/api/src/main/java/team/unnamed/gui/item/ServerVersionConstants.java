package team.unnamed.gui.item;

import org.bukkit.Bukkit;

public final class ServerVersionConstants {
    public static final byte MINOR_VERSION;

    static {
        final String[] versions = Bukkit.getBukkitVersion().split("\\.");
        MINOR_VERSION = Byte.parseByte(versions[1]);
    }

    private ServerVersionConstants() {
        throw new UnsupportedOperationException();
    }
}
