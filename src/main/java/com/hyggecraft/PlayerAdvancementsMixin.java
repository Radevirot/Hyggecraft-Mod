package com.hyggecraft.mixin;

import com.hyggecraft.config.HyggeConfig;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(PlayerAdvancements.class)

public abstract class PlayerAdvancementsMixin {

    @Shadow
    private ServerPlayer player;

    @Unique
    private static final Identifier CREATIVE_DIM =
            Identifier.fromNamespaceAndPath(
                    "minecraft",
                    "overworld"
            );

    @Inject(
            method = "award",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hyggecraft$award(
            AdvancementHolder holder,
            String criterion,
            CallbackInfoReturnable<Boolean> cir
    ) {

        if (HyggeConfig.CONFIG.blockedDimensions
                .get()
                .contains(player.level().dimension().identifier().toString())) {

            cir.setReturnValue(false);
        }
    }

}
