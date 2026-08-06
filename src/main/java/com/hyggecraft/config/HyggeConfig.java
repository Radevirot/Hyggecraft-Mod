package com.hyggecraft.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class HyggeConfig {

    public static final HyggeConfig CONFIG;
    public static final ModConfigSpec SPEC;

    public final ModConfigSpec.ConfigValue<List<? extends String>> blockedDimensions;

    private HyggeConfig(ModConfigSpec.Builder builder) {

        builder.push("general");

        blockedDimensions = builder
                .comment("Dimensions where players cannot earn advancements.")
                .defineListAllowEmpty(
                        "advancement_disabled_dimensions",
                        List.of("hyggecraft:creative_superflat"),
                        () -> "",
                        o -> o instanceof String
                );

        builder.pop();
    }

    static {
        Pair<HyggeConfig, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(HyggeConfig::new);

        CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }
}