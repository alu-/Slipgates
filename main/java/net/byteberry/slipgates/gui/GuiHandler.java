package net.byteberry.slipgates.gui;

import net.byteberry.slipgates.client.gui.GuiPortalCapacitor;
import net.byteberry.slipgates.inventory.ContainerPortalCapacitor;
import net.byteberry.slipgates.reference.GUIs;
import net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		System.out.println("Server GUI");
		try {
			if (id == GUIs.GUI_PORTAL_CAPACITOR.ordinal() ) {
				TileEntityPortalCapacitor tileEntityPortalCapacitor = (TileEntityPortalCapacitor) world.getTileEntity(x, y, z);
				return new ContainerPortalCapacitor();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
		System.out.println("Client GUI");
		try {
			if (id == GUIs.GUI_PORTAL_CAPACITOR.ordinal()) {
				TileEntityPortalCapacitor tileEntityPortalCapacitor = (TileEntityPortalCapacitor) world.getTileEntity(x, y, z);
				return new GuiPortalCapacitor(tileEntityPortalCapacitor);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
