package me.capit.TerraSun;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEventHandler implements Listener {
	
	TerraSun plugin;
	
	public WeatherEventHandler(TerraSun plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onWheatherChange(WeatherChangeEvent e){
		double chance = Math.random();
		double rainChance = plugin.getConfig().getDouble("config.RAIN_CHANCE");
		boolean doweather = plugin.getConfig().getBoolean("config.DO_WEATHER");
		if (chance>rainChance && doweather){
			e.setCancelled(true);
		}
	}
	
}
