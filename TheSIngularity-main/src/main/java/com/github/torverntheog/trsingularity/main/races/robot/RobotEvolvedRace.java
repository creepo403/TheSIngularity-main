package com.github.torverntheog.trsingularity.main.races.robot;

import com.github.manasmods.tensura.race.Race;
import com.github.manasmods.tensura.registry.race.TensuraRaces;
import com.github.manasmods.tensura.util.JumpPowerHelper;
import com.github.torverntheog.trsingularity.registry.main.TRSingularityRaces;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// Add the rest if u want.
public class RobotEvolvedRace extends Race {
    public RobotEvolvedRace() {
        super(Difficulty.INTERMEDIATE);
    }

    public double getBaseHealth() {return 100F;}
    public double getSpiritualHealthMultiplier() {
        return 1.0F;
    }
    public float getPlayerSize() {return 2.0F;}
    public double getBaseAttackDamage() {return 1.0F;}
    public double getBaseAttackSpeed() {return 1.0;}
    public double getKnockbackResistance() {return 0.0F;}
    public double getJumpHeight() {
        return JumpPowerHelper.defaultPlayer(0.0F);
    }
    public double getMovementSpeed() {return 0.1F;}
    public double getSprintSpeed() {
        return 1.3F;
    }

    public @NotNull Pair<Double, Double> getBaseAuraRange() {
        return Pair.of((double)1000.0F, (double)1100.0F);
    }
    public @NotNull Pair<Double, Double> getBaseMagiculeRange() {return Pair.of((double)1000.0F, (double)1100.0F);}

    // This time you're defining that the previous evolution is one of your races.
    // So instead now you won't need a mixin, you'll simply state the race.
    public @NotNull List<Race> getPreviousEvolutions(@NotNull Player player) {
        List<Race> list = new ArrayList<>();
        list.add((Race)((IForgeRegistry<?>) TensuraRaces.RACE_REGISTRY.get()).getValue(TRSingularityRaces.ROBOT));
        return list;
    }
}
