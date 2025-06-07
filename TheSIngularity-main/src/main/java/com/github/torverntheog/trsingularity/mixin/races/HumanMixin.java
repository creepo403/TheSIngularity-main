package com.github.torverntheog.trsingularity.mixin.races;

import com.github.manasmods.tensura.race.Race;
import com.github.manasmods.tensura.race.human.HumanRace;
import com.github.manasmods.tensura.registry.race.TensuraRaces;
import com.github.torverntheog.trsingularity.registry.main.TRSingularityRaces;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.IForgeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

// Hello there, what you see below is a Mixin. But what's a Mixin? Well basically a Mixin is a way of modifying
// code you don't have direct access to. You'll be using these a lot by Tensura Code.

// Mixins are hard to understand at first, but you'll get the hang of it.

// Below we define this is a Mixin with @Mixin and where u want to modify the code by the ().
// In the () you'll always need to tell which class you desire to modify and include .class
// After defining it's a Mixin, you'll go to resources and open trsingularity.mixins.json
// There you'll be able to add the Mixin, so it actually works. I'll explain more there.
@Mixin (HumanRace.class)
public class HumanMixin {

    // @Inject is where u say where and when you want to modify the code.
    // method is which part of the code u want to and at is where.
    // by @AT(""), you can choose RETURN, TAIL and HEAD. I mostly recommend RETURN to avoid bugs.
    // cancellable and remap u should have by 90% of the mixins, it works to prevent crashes.
    @Inject(
            method = "getNextEvolutions",
            at = @At("RETURN"),
            cancellable = true,
            remap = false)
    // Here you create your own method with what u want to add or modify, be aware that has limits of what u can do.
    // You can name anything you like.
    public void injectNextEvolutions(Player player, CallbackInfoReturnable<List<Race>> cir) {
        List<Race> list = cir.getReturnValue();
        // By here you'll add to the Human list your race. So it'll show up with the rest of the set races.
        list.add((Race) ((IForgeRegistry<?>) TensuraRaces.RACE_REGISTRY.get()).getValue(TRSingularityRaces.ROBOT));
        cir.setReturnValue(list);
    }
}
