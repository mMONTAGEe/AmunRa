/*
 * ReadOnlyCore
 * Copyright (C) 2020 ROMVoid95
 *
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package _net.rom.stellar.item;

import java.awt.Color;

import _net.rom.stellar.event.ClientTicks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Implement on items to override the rendering of the enchanted effect. In addition to allowing the
 * control of the effect color, this fixes the "stacking effect" bug that has existed for
 * multi-layer models since Minecraft 1.8, if the item model extends {@link
 * _net.rom.stellar.client.model.LayeredBakedModel}. The mod's {@link
 * _net.rom.stellar.registry.StellarRegistry} will set the TEISR automatically.
 * <p>Also see {@link _net.rom.stellar.client.render.TEISRCustomEnchantedEffect}</p>
 *
 * 
 */
public interface ICustomEnchantColor {
    // Some NBT keys used to control color.
    String NBT_QUARK_RUNE_ATTACHED = "Quark:RuneAttached";
    String NBT_QUARK_RUNE_COLOR = "Quark:RuneColor";

    /**
     * Get the untruncated enchanted effect color. This defaults to checking for two NBT tags, one
     * added by  Lib and the other for Quark runes. See the constants above.
     *
     * @param stack The item
     * @return The effect color
     */
    default int getEffectColor(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();

        if (tagCompound != null) {
            if (tagCompound.hasKey(NBT_QUARK_RUNE_ATTACHED) && tagCompound.hasKey(NBT_QUARK_RUNE_COLOR)) {
                // Quark runes - stored int is either dye metadata or 16 for rainbow
                int value = tagCompound.getInteger(NBT_QUARK_RUNE_COLOR);
                if (value > 15)
                    return Color.HSBtoRGB(ClientTicks.totalTicks * 0.005f, 1f, 0.6f);
                else if (value >= 0)
                    return EnumDyeColor.byMetadata(value).getColorValue();
            }
        }

        return 0xFFFFFF;
    }

    /**
     * Whether or not the effect color should have its brightness truncated. Without this, the
     * effect would be fully opaque.
     *
     * @param stack The item
     * @return If brightness should be truncated
     */
    default boolean shouldTruncateBrightness(ItemStack stack) {
        return true;
    }

    /**
     * Get the max brightness of the effect, if {@link #shouldTruncateBrightness(ItemStack)} returns
     * true. Default value (396) is roughly equivalent to vanilla.
     *
     * @param stack The item
     * @return The max brightness
     */
    default int getEffectMaxBrightness(ItemStack stack) {
        return 396;
    }
}
