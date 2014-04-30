package com.gc13.gNpc;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.MathHelper;
import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet29DestroyEntity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;

public class Plugin extends JavaPlugin {
	
	public void onEnable() {
		getCommand("gnpc").setExecutor(new NpcCommandExecutor(this));
		new PluginListener(this);
		
		// Minecraft modification
		try{
			@SuppressWarnings("rawtypes")
			Class[] args = new Class[3];
			args[0] = Class.class;
			args[1] = String.class;
			args[2] = int.class;

			Method a = net.minecraft.server.EntityTypes.class.getDeclaredMethod("a", args);
			a.setAccessible(true);

			a.invoke(a, EntityNpc.class, "gNPC", 55);
		}catch (Exception e){
			e.printStackTrace();
			this.setEnabled(false);
		}
		
		
	}
	
	public void onDisable() {

	}

	
	public Material stringToItem(String item) {
		try {
			Material material = Material.valueOf(item);
			return material;
		} catch (Exception e) {
			return null;
		}
	}
	

	
	public Npc spawnNpc(Location location) {
		// Get world
		net.minecraft.server.World mcWorld = ((CraftWorld) location.getWorld()).getHandle();
	
		// Create Minecraft entity then Bukkit NPC entity
		EntityNpc entNpc = new EntityNpc(mcWorld);
		Npc npc = new Npc(mcWorld.getServer(), entNpc);
		
		entNpc.setNpc(npc);
		entNpc.initBehaviour();
		
		// Put in world and broadcast
		//npc.teleport(location);
		entNpc.setPosition(location.getX(), location.getY(), location.getZ());
		mcWorld.addEntity(entNpc);
		broadCastNpc(npc, location);
		return npc;
	}
	
	protected void broadCastNpc(Npc npc, Location location) {
		World world = location.getWorld();
		for (Player player : world.getPlayers()) {
			double distance = player.getLocation().distance(location);
			if (distance > 50) continue;
			broadcastNpcToPlayer(npc, player);
		}
	}
	
	protected void broadcastNpcToPlayer(Npc npc, Player player) {
		// NOTCH CODE
		
		EntityPlayer ePlayer = ((CraftPlayer)player).getHandle();
		ePlayer.netServerHandler.sendPacket(new Packet29DestroyEntity(npc.getHandle().id));
		
		
		Packet20NamedEntitySpawn npcPacket = new Packet20NamedEntitySpawn();
		
		npcPacket.a = npc.getHandle().id;
		npcPacket.b = npc.getName();
		npcPacket.c = MathHelper.floor(npc.getHandle().locX * 32.0D);
		npcPacket.d = MathHelper.floor(npc.getHandle().locY * 32.0D);
		npcPacket.e = MathHelper.floor(npc.getHandle().locZ * 32.0D);
		npcPacket.f = (byte) ((int) (npc.getHandle().yaw * 256.0F / 360.0F));
		npcPacket.g = (byte) ((int) (npc.getHandle().pitch * 256.0F / 360.0F));
		npcPacket.h = 0;
		
//		npcPacket.a = npc.getHandle().id;
//		npcPacket.b = npc.getName();
//		npcPacket.c = MathHelper.floor(npc.getHandlegetLocation().getX() * 32.0D);
//		npcPacket.d = MathHelper.floor(npc.getLocation().getY() * 32.0D);
//		npcPacket.e = MathHelper.floor(npc.getLocation().getZ() * 32.0D);
//		npcPacket.f = (byte) ((int) (npc.getLocation().getYaw() * 256.0F / 360.0F));
//		npcPacket.g = (byte) ((int) (npc.getLocation().getPitch() * 256.0F / 360.0F));
//		npcPacket.h = npc.getHandItem().getId();
		
		ePlayer.netServerHandler.sendPacket(npcPacket);
	}




}
