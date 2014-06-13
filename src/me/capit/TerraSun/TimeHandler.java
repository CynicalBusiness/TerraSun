package me.capit.TerraSun;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.World;

public class TimeHandler implements Runnable {

	TerraSun plugin;
	
	DateFormat hourFormat = new SimpleDateFormat("HH");
	DateFormat minuteFormat = new SimpleDateFormat("mm");
	DateFormat secondFormat = new SimpleDateFormat("ss");
	DateFormat zoneFormat = new SimpleDateFormat("Z");
	int hour, minute, second;
	int zone;
	int ticks;
	long mticks;
	
	public TimeHandler(TerraSun plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		if (plugin.getConfig().getBoolean("config.DO_TIME")){
			Date date = new Date();
			hour = Integer.parseInt(hourFormat.format(date));
			minute = Integer.parseInt(minuteFormat.format(date));
			second = Integer.parseInt(secondFormat.format(date));
			zone = Integer.parseInt(zoneFormat.format(date));
			zone = zone/100;
			hour = hour-zone; // Get GMT time.
			hour = hour+plugin.offset; // Move by offset.
			hour = hour>23 ? hour-=24 : hour;
			hour = hour<0 ? hour+=24 : hour;
			ticks = hour*1000;
			ticks = ticks - 6000;
			ticks += (minute/0.6)*10;
			plugin.hour = hour;
			plugin.minute = minute;
			plugin.second = second;
			plugin.ticks = ticks;
			for (World w : plugin.getServer().getWorlds()){
				w.setGameRuleValue("doDaylightCycle", "false");
				w.setTime(ticks);
			}
		}
	}

}
