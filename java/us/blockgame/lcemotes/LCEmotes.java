package us.blockgame.lcemotes;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import us.blockgame.lcemotes.command.EmoteCommand;
import us.blockgame.lcemotes.emote.Emote;
import us.blockgame.lcemotes.util.UUIDUtil;
import java.util.stream.IntStream;


public class LCEmotes extends JavaPlugin implements PluginMessageListener {

    @Getter
    private static LCEmotes instance;

    public void onEnable() {
        instance = this;
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "Lunar-Client", this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "Lunar-Client");
        getCommand("emote").setExecutor(new EmoteCommand(this));
    }

    public static void sendEmote(Emote emote, Player player) {
        Stream.of(instance.getServer().getOnlinePlayers()).forEach(all -> 
            all.sendPluginMessage(instance, "Lunar-Client", getEmoteData(emote, player));
        );
    }

    private static byte[] getEmoteData(Emote emote, Player player) {
        byte[] data = new byte[256];
        data[0] = (byte) 26;
        data[20] = (byte) emote.getId();
        byte[] uuid = UUIDUtil.asBytes(player.getUniqueId());
        IntStream.range(1, uuid.length).forEach(i -> data[i] = uuid[i-1]);
        return data;
    }

    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {}
}
