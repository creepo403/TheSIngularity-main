package com.github.torverntheog.trsingularity.registry.main;

import com.github.manasmods.tensura.registry.race.TensuraRaces;
import com.github.torverntheog.trsingularity.main.races.robot.RobotEvolvedRace;
import com.github.torverntheog.trsingularity.main.races.robot.RobotRace;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(
        modid = "trsingularity",
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class TRSingularityRaces {
    // This is where u define the race ID, so you can actually use it by code, and also the mod it is from and the name.
    public static final ResourceLocation ROBOT = new ResourceLocation("trsingularity", "robot");
    public static final ResourceLocation ROBOT_EVOLVED = new ResourceLocation("trsingularity", "robot_evolved");


    // Here is where u registry it, u just need to add the name u choose above and set the class.
    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(((IForgeRegistry) TensuraRaces.RACE_REGISTRY.get()).getRegistryKey(), (helper) -> {
            helper.register("robot", new RobotRace());
            helper.register("robot_evolved", new RobotEvolvedRace());
        });
    }
}
