package com.gc13.gNpc;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.server.Entity;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityMonster;
import net.minecraft.server.EntityPig;
import net.minecraft.server.EntitySkeleton;
import net.minecraft.server.EntityZombie;
import net.minecraft.server.PathfinderGoalTarget;

public class PathfinderGoalNearestEnemy extends PathfinderGoalTarget {
	String type;
	private NpcDistanceComparator g;
	EntityLiving a;
	int num;
	private List<String> enemies;
	private List<String> friends;
	
	public PathfinderGoalNearestEnemy(EntityLiving entityliving, float grow, List<String> enemies, List<String> friends, int num, boolean flag) {
		super(entityliving, grow, flag);
		this.enemies = enemies;
        this.d = grow;
        this.num = num;
        this.g = new NpcDistanceComparator(this, entityliving);
        this.a(1);
        
	}

	@Override
	public boolean a() {
		List list = this.c.world.a(EntityLiving.class, this.c.boundingBox.grow((double) this.d, 4.0D, (double) this.d));
        Collections.sort(list, this.g);
		Iterator iterator = list.iterator();
		
        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();
  
            EntityLiving entityliving = (EntityLiving) entity;
            String searchEnemy = null;
            
			if ((entity instanceof EntityZombie)) searchEnemy = "monster:zombie";
			if ((entity instanceof EntitySkeleton)) searchEnemy = "monster:skeleton";
			if ((entity instanceof EntityPig)) searchEnemy = "animal:pig";
			
			if (searchEnemy == null) continue;
			if (friends != null) if (friends.contains(searchEnemy)) continue;
			if (!enemies.contains(searchEnemy)) continue;
			
			
            if (this.a(entityliving, false)) {
            	System.out.println("found");
                this.a = entityliving;
                return true;
            }
        }

		return false;
	}
	
	 public void c() {
        this.c.b(this.a);
        super.c();
    }

}
