/*
 * Colormatic
 * Copyright (C) 2019  Thalia Nero
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.kvverti.colormatic.mixin;

import io.github.kvverti.colormatic.Colormatic;

import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Provides water color customization capability.
 */
@Mixin(BiomeColors.class)
public abstract class BiomeColorsMixin {

    @Dynamic("water color lambda method")
    @Inject(method = "method_4963", at = @At("HEAD"), cancellable = true)
    private static void onWaterColor(Biome biome, BlockPos pos, CallbackInfoReturnable<Integer> info) {
        if(Colormatic.WATER_COLORS.hasCustomColormap()) {
            info.setReturnValue(Colormatic.WATER_COLORS.getColor(biome, pos));
        }
    }

    // currently unused in vanilla
    @Dynamic("underwater color lambda method")
    @Inject(method = "method_4964", at = @At("HEAD"), cancellable = true)
    private static void onUnderwaterColor(Biome biome, BlockPos pos, CallbackInfoReturnable<Integer> info) {
        if(Colormatic.UNDERWATER_COLORS.hasCustomColormap()) {
            info.setReturnValue(Colormatic.UNDERWATER_COLORS.getColor(biome, pos));
        }
    }
}
