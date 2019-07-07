package us.blockgame.lcemotes.command;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.blockgame.lcemotes.LCEmotes;
import us.blockgame.lcemotes.emote.Emote;

@AllArgsConstructor
public class EmoteCommand implements CommandExecutor {

    private LCEmotes plugin;

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!sender.hasPermission("lcemotes.command")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /" + commandLabel + " <emote|player> [emote]");
            return true;
        }
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You cannot do this as console.");
                return true;
            }
            if (!Emote.exists(args[0])) {
                sender.sendMessage(ChatColor.RED + "That emote doesn't exist.");
                sender.sendMessage(ChatColor.RED + "All emotes: WAVE, HANDS_UP, FLOSS, DAB, T_POSE, SHRUG, FACEPALM");
                return true;
            }
            Player player = (Player) sender;
            Emote emote = Emote.getByName(args[0]);
            LCEmotes.sendEmote(emote, player);
            sender.sendMessage(ChatColor.WHITE + "Sent " + ChatColor.AQUA + emote.toString() + ChatColor.WHITE + " emote to " + ChatColor.AQUA + sender.getName() + ChatColor.WHITE + ".");
            return true;
        }
        if (args.length == 2) {
            if (!Emote.exists(args[1])) {
                sender.sendMessage(ChatColor.RED + "That emote doesn't exist.");
                sender.sendMessage(ChatColor.RED + "All emotes: WAVE, HANDS_UP, FLOSS, DAB, T_POSE, SHRUG, FACEPALM");
                return true;
            }
            Emote emote = Emote.getByName(args[1]);
            if (args[0].equalsIgnoreCase("all")) {
                for (Player all : this.plugin.getServer().getOnlinePlayers()) {
                    LCEmotes.sendEmote(emote, all);
                    sender.sendMessage(ChatColor.WHITE + "Sent " + ChatColor.AQUA + emote.toString() + ChatColor.WHITE + " emote to " + ChatColor.AQUA + all.getName() + ChatColor.WHITE + ".");
                }
                return true;
            }
            Player target = this.plugin.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Could not find player " + target.getName() + ".");
                return true;
            }
            LCEmotes.sendEmote(emote, target);
            sender.sendMessage(ChatColor.WHITE + "Sent " + ChatColor.AQUA + emote.toString() + ChatColor.WHITE + " emote to " + ChatColor.AQUA + target.getName() + ChatColor.WHITE + ".");
            return true;
        }
        return true;
    }
}
