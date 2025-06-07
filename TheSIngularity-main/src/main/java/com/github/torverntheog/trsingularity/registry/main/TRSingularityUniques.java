package com.github.torverntheog.trsingularity.registry.main;

import com.github.manasmods.manascore.api.skills.ManasSkill;
import com.github.manasmods.manascore.api.skills.SkillAPI;
import com.github.torverntheog.trsingularity.main.uniques.UniqueSkill;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

// This is where u define it's an event for the mod "trsingularity".
// You'll always need this by events and registry
@Mod.EventBusSubscriber(modid = "trsingularity", bus = Mod.EventBusSubscriber.Bus.MOD)
public class TRSingularityUniques {

    // Here you just define under which mod it should be registried.
    // For example when u log in the game, the skill will show trsingularity:skill_name
    private static final DeferredRegister<ManasSkill> registry = DeferredRegister.create(SkillAPI.getSkillRegistryKey(), "trsingularity");

    // Example of registering a Unique Skill:
    public static final RegistryObject<UniqueSkill> SKILL_ID = registry.register("skill_name", UniqueSkill::new);

    // UniqueSkill is just the class where it's situated your skill.
    // SKILL_ID is what you'll use when calling the skill inside your mod, so TRSingularityUniques.SKILL_ID
    // skill_name is what will appear by the game.

    // As you can see here, Init is being used. This is the one u have to add by TRSingularityRegistry.
    // If u implement it right, init should be showing blue.
    public static void init(IEventBus modEventBus) {
        registry.register(modEventBus);
    }
}