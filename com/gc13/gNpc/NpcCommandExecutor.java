package com.gc13.gNpc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class NpcCommandExecutor implements CommandExecutor {
        
    	private Plugin plugin;
     
    	public NpcCommandExecutor(Plugin plugin) {
    		this.plugin = plugin;
    	}
     
    	@Override
    	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    		Player player = null;
    		if (sender instanceof Player) player = (Player) sender;
    		
    		// Create npc
    		if (args.length == 1) {
    			if (args[0].equals("create")) {
    				if (player == null) return false;

    				
    				plugin.spawnNpc(player.getLocation());
    				return true;
    				
    			}
    		}
    		
    		for (Entity ent : player.getWorld().getEntities()) {
    			if (((CraftEntity)ent).getHandle() instanceof EntityNpc) System.out.println("npc");
    		}
    		
    		
    		return false;
    	}

}

