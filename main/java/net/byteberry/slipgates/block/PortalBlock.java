package net.byteberry.slipgates.block;

import net.byteberry.slipgates.Slipgates;
import net.byteberry.slipgates.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PortalBlock extends Block {

	public PortalBlock(Material material) {
		super(material);
		
	    this.setHardness(0.1F)
	    .setStepSound(Block.soundTypeGravel)
	    .setCreativeTab(CreativeTabs.tabBlock)
	    .setBlockName(Reference.MOD_ID + ":" + "portalBlock")
	    .setBlockTextureName(Reference.MOD_ID + ":" + "portalBlock");
	}

}
