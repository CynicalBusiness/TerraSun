package me.capit.TerraSun;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor{
	
	TerraSun plugin = null;
	List<String> helpList = Arrays.asList(
			"&e/org create [name] &r- &7Founds a new organization.");
	
	public CommandHandler(TerraSun p){
		plugin=p;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("gettime")){
			if (args.length==0){
				if (s.hasPermission("terrasun.gettime")){
					char spacer = plugin.offset<0 ? null : '+';
					s.sendMessage("The time is currently "+plugin.hour+":"+plugin.minute+". (GMT"+spacer
							+plugin.offset+")");
				} else {
					s.sendMessage(ChatColor.RED + "You don't have permission for that!");
					return true;
				}
			} else {
				s.sendMessage(ChatColor.RED + "Too many arguments!");
				return true;
			}
		} else if (cmd.getName().equalsIgnoreCase("setoffset")){
			if (s.hasPermission("terrasun.setoffset")){
				if (args.length==1){
					int loffset = Integer.parseInt(args[0]);
					if (loffset<14 && loffset>-12){
						plugin.offset = loffset;
						plugin.getConfig().set("config.GMT_OFFSET", loffset);
						s.sendMessage("Successfully set offset to "+loffset+".");
					} else {
						s.sendMessage(ChatColor.RED + "Not a valid offset! (Must be between -12 and 14!");
					}
				} else {
					s.sendMessage(ChatColor.RED + "Bad argument count!");
					return true;
				}
			} else {
				s.sendMessage(ChatColor.RED + "You don't have permission for that!");
				return true;
			}
		}
		return false;
	}
	
}
