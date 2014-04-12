package me.capit.TerraSun;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class TerraSun extends JavaPlugin {

	public int offset = this.getConfig().getInt("config.GMT_OFFSET");
	public volatile int hour;
	public volatile int minute;
	
	public boolean enabled = false;
	
	@Override
	public void onEnable(){
		enabled = true;
		CommandHandler cmdh = new CommandHandler(this);
		this.getCommand("gettime").setExecutor(cmdh);
		this.getCommand("setoffset").setExecutor(cmdh);
		
		TimeHandler timer = new TimeHandler(this);
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, timer, 0L, 20L);
		
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable(){
		enabled = false;
	}
}
