package com.badjames.palladium.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

/**
 * Manages palladium energy powers for a player.
 * Palladium energy can be used to activate special abilities.
 */
public class PalladiumPowerData extends EnergyStorage implements INBTSerializable<CompoundTag> {
    private Player player;
    
    // Power states
    private boolean speedBoostActive = false;
    private boolean shieldActive = false;
    private int speedBoostTimer = 0;
    private int shieldTimer = 0;
    
    private static final int SPEED_BOOST_COST = 100; // Energy cost
    private static final int SHIELD_COST = 150;
    private static final int SPEED_BOOST_DURATION = 200; // 10 seconds in ticks
    private static final int SHIELD_DURATION = 300; // 15 seconds

    public PalladiumPowerData(Player player) {
        super(10000, 1000, 1000, 0); // 10k energy capacity, 1k max transfer
        this.player = player;
    }

    /**
     * Activates the Speed Boost power - increases player speed
     */
    public boolean activateSpeedBoost() {
        if (this.energy >= SPEED_BOOST_COST && !speedBoostActive) {
            this.energy -= SPEED_BOOST_COST;
            speedBoostActive = true;
            speedBoostTimer = SPEED_BOOST_DURATION;
            return true;
        }
        return false;
    }

    /**
     * Activates the Shield power - reduces incoming damage
     */
    public boolean activateShield() {
        if (this.energy >= SHIELD_COST && !shieldActive) {
            this.energy -= SHIELD_COST;
            shieldActive = true;
            shieldTimer = SHIELD_DURATION;
            return true;
        }
        return false;
    }

    public void tick() {
        if (speedBoostActive) {
            speedBoostTimer--;
            if (speedBoostTimer <= 0) {
                speedBoostActive = false;
            }
        }
        
        if (shieldActive) {
            shieldTimer--;
            if (shieldTimer <= 0) {
                shieldActive = false;
            }
        }
        
        // Regenerate small amount of energy
        if (this.energy < this.maxEnergyStored) {
            this.energy = Math.min(this.energy + 5, this.maxEnergyStored);
        }
    }

    public boolean isSpeedBoostActive() {
        return speedBoostActive;
    }

    public boolean isShieldActive() {
        return shieldActive;
    }

    public int getShieldTimer() {
        return shieldTimer;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("energy", this.energy);
        tag.putBoolean("speedBoostActive", speedBoostActive);
        tag.putBoolean("shieldActive", shieldActive);
        tag.putInt("speedBoostTimer", speedBoostTimer);
        tag.putInt("shieldTimer", shieldTimer);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.energy = nbt.getInt("energy");
        this.speedBoostActive = nbt.getBoolean("speedBoostActive");
        this.shieldActive = nbt.getBoolean("shieldActive");
        this.speedBoostTimer = nbt.getInt("speedBoostTimer");
        this.shieldTimer = nbt.getInt("shieldTimer");
    }
}
