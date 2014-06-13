package me.capit.TerraSun;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class TerraSun extends JavaPlugin {

	public int offset;
	public volatile int hour, minute, second;
	public volatile int ticks;
	
	public boolean enabled = false;
	
	public WeatherEventHandler weh;
	
	@Override
	public void onEnable(){
		enabled = true;
		
		getLogger().info("#==========================================================#");
		getLogger().info("                   Starting TerraSun...");
		
		getLogger().info("Reading data...");
		saveDefaultConfig();
		offset = this.getConfig().getInt("config.GMT_OFFSET");
		
		getLogger().info("Data OK!");
		getLogger().info("Registering command and events...");
		CommandHandler cmdh = new CommandHandler(this);
		this.getCommand("gettime").setExecutor(cmdh);
		this.getCommand("setoffset").setExecutor(cmdh);
		
		weh = new WeatherEventHandler(this);
		this.getServer().getPluginManager().registerEvents(weh, this);
		
		TimeHandler timer = new TimeHandler(this);
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, timer, 0L, 20L);
		
        getLogger().info("Registry OK!");
        getLogger().info("#==========================================================#");
	}
	
	@Override
	public void onDisable(){
		enabled = false;
	}
}
