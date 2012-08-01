package com.SiNReL.Hides;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Hides extends JavaPlugin implements Listener{
	
	private Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		log.info("["+this.getDescription().getName()+"] Hides version "+this.getDescription().getVersion()+" is enabled");
		
		if(!new File(this.getDataFolder(),"config.yml").exists()){
			createConfig();
		}
		
		Bukkit.getPluginManager().registerEvents(this,this);
	}
	
	private void createConfig(){
		this.getConfig().options().header("Copyright (c) 2012, SiNReL Company");
		
		this.getConfig().set("enabled", true);
		
		this.saveConfig();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		log.info(" "+this.getConfig().getBoolean("enabled"));
		
		if(this.getConfig().getBoolean("enabled")){
			e.setJoinMessage(null);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		if(this.getConfig().getBoolean("enabled")){
			e.setQuitMessage(null);
		}
	}
	
	public boolean HasPermission(Player player){
		return player.isOp();
	}
	
	public void onDisable(){
		log.info("["+this.getDescription().getName()+"] Hides version "+this.getDescription().getVersion()+" disabled");
	}
}