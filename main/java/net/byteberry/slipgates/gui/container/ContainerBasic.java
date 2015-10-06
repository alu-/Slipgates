package net.byteberry.slipgates.gui.container;

import net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerBasic extends Container {
	
	protected TileEntityPortalCapacitor tileEntity;
	
	public ContainerBasic(InventoryPlayer inventory, TileEntityPortalCapacitor tileEntityPortalCapacitor) {
		super();
		this.tileEntity = tileEntityPortalCapacitor;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
}
