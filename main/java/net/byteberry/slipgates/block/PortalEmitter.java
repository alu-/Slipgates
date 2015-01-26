package net.byteberry.slipgates.block;

import net.byteberry.slipgates.reference.Reference;
import net.byteberry.slipgates.tileentity.TileEntityPortalEmitter;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PortalEmitter extends Block implements ITileEntityProvider {

	public PortalEmitter(Material material) {
		super(material);
		
		this.setHardness(0.1F)
	    .setStepSound(Block.soundTypeGravel)
	    .setCreativeTab(CreativeTabs.tabBlock)
	    .setBlockName(Reference.MOD_ID + ":" + "portalEmitter")
	    .setBlockTextureName(Reference.MOD_ID + ":" + "portalEmitter");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityPortalEmitter();
	}

}
