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
	double minute;
	int zone;
	int ticks;
	long mticks;
	
	public TimeHandler(TerraSun plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		Date date = new Date();
		hour = Integer.parseInt(hourFormat.format(date));
		minute = (double)Integer.parseInt(minuteFormat.format(date));
		zone = Integer.parseInt(zoneFormat.format(date));
		zone = zone/100;
		hour = hour-zone; // Get GMT time.
		hour = hour+plugin.offset; // Move by offset.
		hour--; // Seems to overshoot by one.
		ticks = hour*1000;
		long rminute = Math.round((minute/60)*100);
		ticks += rminute;
		plugin.hour = hour;
		plugin.minute = Math.round(minute);
		plugin.ticks = ticks;
		for (World w : plugin.getServer().getWorlds()){
			w.setGameRuleValue("doDaylightCycle", "false");
			w.setTime(ticks);
		}
	}

}
