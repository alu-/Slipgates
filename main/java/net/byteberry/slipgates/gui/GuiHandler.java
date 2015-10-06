package net.byteberry.slipgates.gui;

import net.byteberry.slipgates.client.gui.GuiPortalCapacitor;
import net.byteberry.slipgates.gui.container.ContainerBasic;
import net.byteberry.slipgates.reference.GUIs;
import net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("Server GUI running on " + (world.isRemote ? "client" : "server" ) );
		try {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (id == GUIs.GUI_PORTAL_CAPACITOR.ordinal() && tile instanceof TileEntityPortalCapacitor) {
				return new ContainerBasic(player.inventory, (TileEntityPortalCapacitor) tile);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("Client GUI running on " + (world.isRemote ? "client" : "server" ) );
		try {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (id == GUIs.GUI_PORTAL_CAPACITOR.ordinal() && tile instanceof TileEntityPortalCapacitor) {
				return new GuiPortalCapacitor(player.inventory, (TileEntityPortalCapacitor) tile);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
