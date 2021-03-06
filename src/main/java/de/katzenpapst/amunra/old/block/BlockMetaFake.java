package de.katzenpapst.amunra.old.block;

import micdoodle8.mods.galacticraft.api.prefab.core.BlockMetaPair;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.core.tile.TileEntityMulti;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMetaFake extends BlockBasicMeta implements ITileEntityProvider {

	public BlockMetaFake(String name, Material mat) {
		super(name, mat);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int meta) {
		return this.getSubBlock(meta).createTileEntity(var1, meta);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return null;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return this.getSubBlock(meta).getPickBlock(target, world, x, y, z);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	public void makeFakeBlock(World world, BlockVec3 position, BlockVec3 mainBlock, BlockMetaPair bmp) {
		world.setBlock(position.x, position.y, position.z, this, bmp.getMetadata(), 3);
		((TileEntityMulti) world.getTileEntity(position.x, position.y, position.z)).setMainBlock(mainBlock);
	}

}
