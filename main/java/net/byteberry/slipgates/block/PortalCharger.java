package net.byteberry.slipgates.block;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.byteberry.slipgates.reference.Reference;
import net.byteberry.slipgates.tileentity.TileEntityPortalCharger;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class PortalCharger extends Block implements ITileEntityProvider {
	
	public PortalCharger(Material material) {
		super(material);
		
		this.setHardness(0.1F)
	    .setStepSound(Block.soundTypeGravel)
	    .setCreativeTab(CreativeTabs.tabBlock)
	    .setBlockName(Reference.MOD_ID + ":" + "portalCharger")
	    .setBlockTextureName(Reference.MOD_ID + ":" + "portalCharger");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityPortalCharger();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c) {
		return false;
	}

}