package me.capit.TerraSun;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class TerraSun extends JavaPlugin {

	public int offset;
	public volatile int hour;
	public volatile long minute;
	public volatile int ticks;
	
	public boolean enabled = false;
	
	@Override
	public void onEnable(){
		enabled = true;
		
		offset = this.getConfig().getInt("config.GMT_OFFSET");
		
		CommandHandler cmdh = new CommandHandler(this);
		this.getCommand("gettime").setExecutor(cmdh);
		this.getCommand("setoffset").setExecutor(cmdh);
		
		TimeHandler timer = new TimeHandler(this);
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, timer, 0L, 200L);
		
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable(){
		enabled = false;
	}
}
