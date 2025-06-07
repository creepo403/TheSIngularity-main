package com.github.torverntheog.trsingularity.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

// This is where you define your configs, don't touch in anything unless you know what you're doing.
public class TRSingularityConfig {
    // Here you define the class where ur configs is and the id you'll give it so you can actually use it.
    //public final TRSkillConfig trskillconfig;
    //public final TRRaceConfig trraceconfig;

    private TRSingularityConfig(ForgeConfigSpec.Builder builder) {
        // By builder.push they mean a category by configs, what u place inside it will be what will show up.
        // By builder.pop means closing that same category, in case u want to begin another.
        // Each config inside those two will appear by respective category.

        //builder.push("Skills");
        //this.trskillconfig = new TRSkillConfig(builder);
        //builder.pop();
        //builder.push("Races");
        //this.trraceconfig = new TRRaceConfig(builder);
        //builder.pop();
    }

    // No touching anything below.

    public static final TRSingularityConfig INSTANCE;
    public static final ForgeConfigSpec SPEC;

    static {
        Pair<TRSingularityConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(TRSingularityConfig::new);
        INSTANCE = pair.getKey();
        SPEC = pair.getValue();
    }
}
