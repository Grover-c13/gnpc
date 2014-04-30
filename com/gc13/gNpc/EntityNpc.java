package com.gc13.gNpc;



import net.minecraft.server.DamageSource;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityMonster;
import net.minecraft.server.EntityTracker;
import net.minecraft.server.MonsterType;
import net.minecraft.server.Packet18ArmAnimation;
import net.minecraft.server.PathfinderGoalFloat;
import net.minecraft.server.PathfinderGoalHurtByTarget;
import net.minecraft.server.PathfinderGoalLookAtPlayer;
import net.minecraft.server.PathfinderGoalMeleeAttack;
import net.minecraft.server.PathfinderGoalMoveIndoors;
import net.minecraft.server.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.PathfinderGoalMoveTowardsTarget;
import net.minecraft.server.PathfinderGoalRandomLookaround;
import net.minecraft.server.PathfinderGoalRandomStroll;
import net.minecraft.server.World;

public class EntityNpc extends EntityMonster {

	private Npc npc;
	public EntityNpc(World world) {
		super(world);
//		this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.22F, 0.1F));
//		this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
//		this.goalSelector.a(3, new PathfinderGoalMoveThroughVillage(this, 0.16F, true));
//		this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 0.16F));
//		this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.32F));
//		this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 0.2F));
//		this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
	}
	
	public void initBehaviour() {
		
		if (npc == null) return;
		int bCount = 0;
		
		this.goalSelector.a(bCount++, new PathfinderGoalFloat(this));
		this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 0.32F, false));
		this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.22F, 0.1F));
		this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
		this.goalSelector.a(3, new PathfinderGoalMoveThroughVillage(this, 0.16F, true));
		this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 0.16F));
		this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.32F));
		this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 0.2F));
		this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));;
	}

	public void setNpc(Npc npc) {
		this.npc = npc;
	}

	public Npc getNpc() {
		return npc;
	}

	public void setSpeed(float speed) {
		this.bb = speed;
	}

	public float getSpeed() {
		return bb;
	}

	@Override
	public boolean c_() {
		return true;
	}


	@Override
	protected Entity findTarget() {
		return null;
	}

	@Override
	public MonsterType getMonsterType() {
		return MonsterType.UNDEFINED;
	}

	@Override
	public int getMaxHealth() {
		return 100000;
	}

	@Override
	public boolean a(Entity entity) {
		EntityTracker entitytracker = this.world.getServer().getServer().getTracker(0);
		entitytracker.a(this, new Packet18ArmAnimation(this, 1));
		boolean damage = entity.damageEntity(DamageSource.mobAttack(this), this.damage);
		// 1 = arm swing
		// 2 = damage
		// 3 = none (sleep i think)
		// 4 = none
		// 5 = none
		// 6 = critical
		// 7 = potion

		if(this.target != null) if (!this.target.isAlive()) this.target = null;
		return damage;


	}



}


