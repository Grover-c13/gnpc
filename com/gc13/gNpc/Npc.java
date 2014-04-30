package com.gc13.gNpc;


import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftCreature;
import org.bukkit.entity.EntityType;

public class Npc extends CraftCreature {
	
	private EntityNpc handle;
	private Material handItem;
	private String name;
	private int damage;
	private boolean ranged;
	
	public Npc(CraftServer server, EntityNpc entity) {
			super(server, entity);
			// main
			this.handle = entity;
			
			// Settings
			setName("test");
			setRanged(false);
	}
	

	public EntityNpc getHandle() {
		return this.handle;
	}
	
	
	// Speed & Travel
	public void setSpeed(float speed) {
		handle.setSpeed(speed);
	}
	
	public float getSpeed() {
		return handle.getSpeed();
	}
	
	// Items
	public Material getHandItem() {
		return handItem;
	}

	public void setHandItem(Material handItem) {
		this.handItem = handItem;
	}

	// Name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// Attack & Damage
	public int getDamage() {
		return this.damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public boolean isRanged() {
		return this.ranged;
	}
	
	public void setRanged(boolean ranged) {
		this.ranged = ranged;
	}
	

	// Craft Bukkit
//	@Override
//	public EntityType getType() {
//		return null;
//	}

	
	
	
}
