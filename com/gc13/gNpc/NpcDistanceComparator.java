package com.gc13.gNpc;

import java.util.Comparator;

import net.minecraft.server.Entity;

public class NpcDistanceComparator implements Comparator<Entity> {

    private Entity ent;
    final PathfinderGoalNearestEnemy npc;

    public NpcDistanceComparator(PathfinderGoalNearestEnemy npc, Entity entity) {
    	this.npc = npc;
        this.ent = entity;
    }

    public int a(Entity entity, Entity entity1) {
        double d0 = this.ent.j(entity);
        double d1 = this.ent.j(entity1);
        return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
    }

	@Override
	public int compare(Entity arg0, Entity arg1) {
		return a(arg0, arg1);
	}

}