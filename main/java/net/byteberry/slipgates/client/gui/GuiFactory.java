package net.byteberry.slipgates.client.gui;

import java.util.Set;

import net.byteberry.slipgates.inventory.ContainerPortalCapacitor;
import net.byteberry.slipgates.reference.GUIs;
import net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.client.IModGuiFactory;

public class GuiFactory implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraftInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
//		System.out.println("Server GUI");
//		try {
//			if (id == GUIs.GUI_PORTAL_CAPACITOR.ordinal() ) {
//				TileEntityPortalCapacitor tileEntityPortalCapacitor = (TileEntityPortalCapacitor) world.getTileEntity(x, y, z);
//				return new ContainerPortalCapacitor();
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
//		System.out.println("Client GUI");
//		try {
//			if (id == GUIs.GUI_PORTAL_CAPACITOR.ordinal()) {
//				TileEntityPortalCapacitor tileEntityPortalCapacitor = (TileEntityPortalCapacitor) world.getTileEntity(x, y, z);
//				return new GuiPortalCapacitor(tileEntityPortalCapacitor);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
}
