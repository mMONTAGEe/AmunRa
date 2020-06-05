package de.katzenpapst.amunra.old.block.machine.mothershipEngine;

import de.katzenpapst.amunra.AmunRa;
import de.katzenpapst.amunra.old.block.BlockMachineMetaDummyRender;
import de.katzenpapst.amunra.old.block.SubBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMothershipBoosterMeta extends BlockMachineMetaDummyRender {

	public BlockMothershipBoosterMeta(String name, Material material) {
		super(name, material);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dropEntireInventory(World world, int x, int y, int z, Block block, int par6) {
		return; // NOOP
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return AmunRa.msBoosterRendererId;
	}

	@Override
	public boolean isBlockNormalCube() {
		return false;
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
		int metadata = world.getBlockMetadata(x, y, z);
		SubBlock sb = this.getSubBlock(metadata);
		if (sb != null) {
			sb.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
		}
	}

	@Override
	public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

}