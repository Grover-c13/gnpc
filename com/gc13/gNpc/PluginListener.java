package com.gc13.gNpc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.craftbukkit.entity.CraftEntity;

public class PluginListener implements Listener {
	private Plugin plugin;
	
	public PluginListener(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void onMobSpawn(EntityDamageEvent event) {
		if(((CraftEntity)event.getEntity()) instanceof Npc) System.out.println("true");
	}
}
