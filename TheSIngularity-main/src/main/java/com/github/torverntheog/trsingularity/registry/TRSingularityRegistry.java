package com.github.torverntheog.trsingularity.registry;

import com.github.torverntheog.trsingularity.registry.main.TRSingularityUniques;
import net.minecraftforge.eventbus.api.IEventBus;


// Hello there, this is where u registry your registers. Basically if u create a registry for uniques for example
// You'll have to add it here so the class actually works, the only one that bypasses this is races.

public class TRSingularityRegistry {

    public static void register(IEventBus modEventBus) {

        // Example of how to register the Uniques registry class:
        TRSingularityUniques.init(modEventBus);

        // You can either use init or registry, mostly it doesn't really matter.
        // But it has to match the one by the class. Now go to main and click by TRSingularityUniques

    }
}
