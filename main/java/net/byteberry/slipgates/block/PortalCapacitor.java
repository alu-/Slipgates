package net.byteberry.slipgates.block;

import org.apache.logging.log4j.core.LogEvent;

import net.byteberry.slipgates.Slipgates;
import net.byteberry.slipgates.client.gui.GuiPortalCapacitor;
import net.byteberry.slipgates.reference.GUIs;
import net.byteberry.slipgates.reference.Reference;
import net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PortalCapacitor extends Block implements ITileEntityProvider {

	public PortalCapacitor(Material material) {
		super(material);

		this.setHardness(0.1F)
				.setStepSound(Block.soundTypeGravel)
				.setCreativeTab(CreativeTabs.tabBlock)
				.setBlockName(Reference.MOD_ID + ":" + "portalCapacitor")
				.setBlockTextureName(Reference.MOD_ID + ":" + "portalCapacitor");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityPortalCapacitor();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c) {
		// TODO Fix right clicking the block with something in hand places that thing. 
		System.out.println("Block Activated");
		try {
			
			if( !world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityPortalCapacitor ) 
			{
				System.out.println("Open gui triggered");
				player.openGui(Slipgates.instance, GUIs.GUI_PORTAL_CAPACITOR.ordinal(), world, x, y, z);
				return true;
			}
			return false;
			
		} catch( Exception e ) {
			System.out.println( e.getMessage() );
			return false;
		}
	}

}
