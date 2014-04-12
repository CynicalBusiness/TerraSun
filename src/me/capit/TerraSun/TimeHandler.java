package me.capit.TerraSun;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.World;

public class TimeHandler implements Runnable {

	TerraSun plugin;
	
	DateFormat hourFormat = new SimpleDateFormat("HH");
	DateFormat minuteFormat = new SimpleDateFormat("mm");
	DateFormat zoneFormat = new SimpleDateFormat("Z");
	int hour;
	int minute;
	int zone;
	int ticks;
	
	public TimeHandler(TerraSun plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		Date date = new Date();
		hour = Integer.parseInt(hourFormat.format(date));
		minute = Integer.parseInt(minuteFormat.format(date));
		zone = Integer.parseInt(zoneFormat.format(date));
		zone = zone/100;
		hour = hour-zone; // Get GMT time.
		hour = hour+plugin.offset; // Move by offset.
		ticks = hour*1000;
		ticks += Math.floor((minute/60)*10);
		for (World w : plugin.getServer().getWorlds()){
			w.setTime(ticks);
		}
	}

}
