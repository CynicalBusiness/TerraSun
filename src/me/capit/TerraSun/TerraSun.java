package me.capit.TerraSun;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class TerraSun extends JavaPlugin {

	public int offset;
	public volatile int hour, minute, second;
	public volatile int ticks;
	
	public boolean enabled = false;
	
	public WeatherEventHandler weh;
	public Logger log;
	
	@Override
	public void onEnable(){
		enabled = true;
		log = this.getLogger();
		
		log.info("#=================================================#");
		log.info("            World of Crytaria - TerraSun");
		log.info("Starting plugin...");
		
		log.info("  Reading file data...");
		saveDefaultConfig();
		offset = this.getConfig().getInt("config.GMT_OFFSET");
		log.info("  File data OK!");
		
		log.info("  Registering commands...");
		CommandHandler cmdh = new CommandHandler(this);
		this.getCommand("gettime").setExecutor(cmdh);
		log.info("    Registered command /GETTIME");
		this.getCommand("setoffset").setExecutor(cmdh);
		log.info("    Registered command /SETOFFSET");
		log.info("  Commands OK!");
		
		log.info("  Registering events...");
		weh = new WeatherEventHandler(this);
		this.getServer().getPluginManager().registerEvents(weh, this);
		
		TimeHandler timer = new TimeHandler(this);
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, timer, 0L, 20L);
        log.info("  Events OK!");
		
        log.info("TerraSun ready! Plugin OK!");
        log.info("#=================================================#");
	}
	
	@Override
	public void onDisable(){
		enabled = false;
	}
}
