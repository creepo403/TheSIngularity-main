package com.github.torverntheog.trsingularity.main.races.robot;

import com.github.manasmods.tensura.ability.TensuraSkill;
import com.github.manasmods.tensura.capability.race.TensuraPlayerCapability;
import com.github.manasmods.tensura.race.Race;
import com.github.manasmods.tensura.registry.items.TensuraMobDropItems;
import com.github.manasmods.tensura.registry.race.TensuraRaces;
import com.github.manasmods.tensura.util.JumpPowerHelper;
import com.github.torverntheog.trsingularity.registry.main.TRSingularityRaces;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RobotRace extends Race {
    // This is just like the Skills, you define the type/difficulty of the race.
    public RobotRace() {
        super(Difficulty.HARD);
    }


    // Below is where u define the stats the player will acquire, I'll just place the default and u modify it.
    public double getBaseHealth() {
        return 100F;
    }

    public double getSpiritualHealthMultiplier() {
        return 1.0F;
    }

    public float getPlayerSize() {
        return 2.0F;
    }

    public double getBaseAttackDamage() {
        return 1.0F;
    }

    public double getBaseAttackSpeed() {
        return 1.0;
    }

    public double getKnockbackResistance() {
        return 0.0F;
    }

    public double getJumpHeight() {
        return JumpPowerHelper.defaultPlayer(0.0F);
    }

    public double getMovementSpeed() {
        return 0.1F;
    }

    public double getSprintSpeed() {
        return 1.3F;
    }

    // Here u define what the race is (None, Majin, Divine) Remove all if u don't want the 2.
    //public boolean isDivine() {return true;}
    //public boolean isMajin() {return true;}

    // This is where u define Aura/MP range, I don't recommend setting the same as needed to evolve.
    // I also recommend adding a lil more by max to get that tasty off set number by EP.
    public @NotNull Pair<Double, Double> getBaseAuraRange() {
        return Pair.of((double) 1000.0F, (double) 1100.0F);
    }

    public @NotNull Pair<Double, Double> getBaseMagiculeRange() {
        return Pair.of((double) 1000.0F, (double) 1100.0F);
    }

    // Here you add the list of skills they get by acquiring the race.
    public List<TensuraSkill> getIntrinsicSkills(Player player) {
        List<TensuraSkill> list = super.getIntrinsicSkills(player);
        return list;
    }

    // This is where u add the next evolution by the evolution menu.
    public @NotNull List<Race> getNextEvolutions(@NotNull Player player) {
        List<Race> list = new ArrayList<>();
        list.add((Race) ((IForgeRegistry<?>) TensuraRaces.RACE_REGISTRY.get()).getValue(TRSingularityRaces.ROBOT_EVOLVED));
        return list;
    }

    // This is where u add the next evolution by for example forceevo command.
    public @NotNull Race getDefaultEvolution(@NotNull Player player) {
        return ((Race) ((IForgeRegistry<?>) TensuraRaces.RACE_REGISTRY.get()).getValue(TRSingularityRaces.ROBOT_EVOLVED));
    }

    // This is where u add the next evolution by awakening.
    public Race getAwakeningEvolution(@NotNull Player player) {
        return ((Race) ((IForgeRegistry<?>) TensuraRaces.RACE_REGISTRY.get()).getValue(TRSingularityRaces.ROBOT_EVOLVED));
    }

    // This is where u add the next evolution by when your owner awakens.
    public Race getHarvestFestivalEvolution(@NotNull Player player) {
        return ((Race) ((IForgeRegistry<?>) TensuraRaces.RACE_REGISTRY.get()).getValue(TRSingularityRaces.ROBOT_EVOLVED));
    }

    // You can just place return null; by the evolutions if u want none.
    // If it is the last evolution, you can just remove the methods as well.


    // This is where u get the previous evolution, since the race is from Tensura,
    public @NotNull List<Race> getPreviousEvolutions(@NotNull Player player) {
        List<Race> list = new ArrayList<>();
        list.add((Race) ((IForgeRegistry<?>) TensuraRaces.RACE_REGISTRY.get()).getValue(TensuraRaces.HUMAN.getId()));
        return list;
    }

    // Now it may be a bit harder to understand to I need you to focus.

    public double getEvolutionPercentage(@NotNull Player player) {
        // A int is a way to add a value to something, here it's 0 because it'll always start at 0 by default.
        // Do not increase it.
        int essence = 0;

        // Here it's checking for the player in both client and server. It's saying that essence
        // equals to getting the stats and value of the player, then it searches for the item used Royal Blood.
        // This will simply gather the value of the item used from the player stats.

        if (player instanceof LocalPlayer localPlayer) {
            essence = localPlayer.getStats().getValue(Stats.ITEM_USED.get(TensuraMobDropItems.ROYAL_BLOOD.get()));
        } else if (player instanceof ServerPlayer serverPlayer) {
            essence = serverPlayer.getStats().getValue(Stats.ITEM_USED.get(TensuraMobDropItems.ROYAL_BLOOD.get()));
        }

        // Here u define essences, that is how many essences u want it to be required. You can also change the number for a config.
        // This simply says that essence should be 10. And the 30.0F defines how much % it is by evolution
        // In this case, 10 essence = 30% of the evolution.
        double essences = Math.min(essence * 10, 30.0F);

        // Here is like the int, you're also defining a value but now for ep.
        double ep = 0.0F;
        // This gathers how much EP the player has and checks if it's more than 5.000.
        // If it is more than 5.000, it says that ep equals to 70% of the evolution.
        if (TensuraPlayerCapability.getBaseEP(player) > 5.000F) {
            ep = 70.0F;
        }

        // Here you simply return both values so both will be used to evolve.
        return ep + essences;

        // If u don't want essences, just remove all code related to it and change ep from 70.0F to 100.0F
    }


    // Here is where you add what's required by the evolution menu.
    public @NotNull List<Component> getRequirementsForRendering(@NotNull Player player) {
        List<Component> list = new ArrayList<>();
        // This will add a lane where it'll say u need experience points.
        list.add(Component.translatable("tensura.evolution_menu.ep_requirement"));
        // This will add a lane where it'll say you need an item, which is Royal Blood.
        list.add(Component.translatable("tensura.evolution_menu.consume_requirement", TensuraMobDropItems.ROYAL_BLOOD.get().getDefaultInstance().getDisplayName()));
        return list;
    }
}

