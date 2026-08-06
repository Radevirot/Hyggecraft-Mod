package com.hyggecraft;

import com.hyggecraft.config.HyggeConfig;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Hyggecraft.MODID)
public class Hyggecraft {

    public static final String MODID = "hyggecraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Hyggecraft(IEventBus modEventBus, ModContainer modContainer) {

        // Register our config
        modContainer.registerConfig(ModConfig.Type.COMMON, HyggeConfig.SPEC);

        // Register this class for events
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Hyggecraft Advancement Blocker loaded!");
    }
}