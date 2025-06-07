package com.github.torverntheog.trsingularity;

import com.github.torverntheog.trsingularity.config.TRSingularityConfig;
import com.github.torverntheog.trsingularity.registry.TRSingularityRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;

import java.io.*;

@Mod(TRSingularity.MODID)
public class TRSingularity {
    public static final String MODID = "trsingularity";
    public static final String MODNAME = "TR: Singularity";
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger("TR: Singularity");

    @SuppressWarnings("removal")
    public TRSingularity() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);
        TRSingularityRegistry.register(modEventBus);
        LOGGER.info("TR:Singularity has been loaded!");
        FileUtils.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve("tensura-reincarnated"), "tensura-reincarnated");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TRSingularityConfig.SPEC, getConfigFileName("trsingularity-config"));
    }

    private String getConfigFileName(String name) {
        return String.format("%s/%s.toml", "tensura-reincarnated", name);
    }

    public static Logger getLogger() {
        return (Logger) LOGGER;
    }

    @SubscribeEvent
    public void onCommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common Setup");
        if (isFirstLaunch()) {
            editTOMLFile();
            markAsEdited();
            LOGGER.info("Common setup works and TOML file was edited.");
        } else {
            LOGGER.info("Common setup works. TOML file already edited.");
        }
    }

    private boolean isFirstLaunch() {
        File markerFile = new File("defaultconfigs/tensura-reincarnated/trsingularity_footprint");
        return !markerFile.exists();
    }

    /**
     * Marks the launch as completed by creating a marker file.
     */
    private void markAsEdited() {
        File markerFile = new File("defaultconfigs/tensura-reincarnated/trsingularity_footprint");

        try {
            if (markerFile.createNewFile()) {
                System.out.println("Marker file created: " + markerFile.getAbsolutePath());
            } else {
                System.out.println("Marker file already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating marker file: " + e.getMessage());
        }
    }

    /**
     * Edits the TOML file to add required entries.
     */
    public void editTOMLFile() {
        File tomlFile = new File("defaultconfigs/tensura-reincarnated/common.toml");

        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tomlFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading the TOML file: " + e.getMessage());
            return;
        }

        String content = contentBuilder.toString();
        String[] newStarting = {""};
        String[] newRandom = {""};
        String[] newSkills = new String[]{""};

        // Add here starting stuff, for example reincarnation skills or new races by the menu choice/random
        String startingRacesKey = "startingRaces = [";
        String randomRacesKey = "possibleRandomRaces = [";
        String reincarnationSkillsKey = "reincarnationSkills = [";
        content = addItemsToTOMLList(content, startingRacesKey, newStarting);
        content = addItemsToTOMLList(content, randomRacesKey, newRandom);
        content = addItemsToTOMLList(content, reincarnationSkillsKey, newSkills);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tomlFile))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to the TOML file: " + e.getMessage());
        }

        System.out.println("Items added to TOML lists successfully.");
    }

    /**
     * Adds new items to a TOML list if they are not already present.
     *
     * @param content  The TOML file content as a string.
     * @param listKey  The key of the list to update.
     * @param newItems The items to add to the list.
     * @return The updated TOML content.
     */
    private String addItemsToTOMLList(String content, String listKey, String[] newItems) {
        int index = content.indexOf(listKey);
        if (index == -1) {
            System.out.println("List identifier '" + listKey + "' not found.");
            return content;
        }
        int endIndex = content.indexOf("]", index) + 1;
        if (endIndex == 0) {
            System.out.println("Closing bracket not found for list: " + listKey);
            return content;
        }
        String listContent = content.substring(index, endIndex);
        for (String newItem : newItems) {
            if (!listContent.contains(newItem)) {
                listContent = listContent.replace("]", ", \"" + newItem + "\"]");
            }
        }
        return content.replace(content.substring(index, endIndex), listContent);
    }

}
