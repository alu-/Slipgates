package net.byteberry.slipgates.proxy;

import net.byteberry.slipgates.Slipgates;
import net.byteberry.slipgates.block.PortalBlock;
import net.byteberry.slipgates.block.PortalCapacitor;
import net.byteberry.slipgates.block.PortalCharger;
import net.byteberry.slipgates.block.PortalEmitter;
import net.byteberry.slipgates.gui.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IProxy {

	public static Block PortalBlock;
	public static Block PortalCapacitor;
	public static Block PortalCharger;
	public static Block PortalEmitter;

	public void preInit(FMLPreInitializationEvent event) {
		// Create and register blocks
		// TODO change materials to more correct types
		PortalBlock = new PortalBlock(Material.glass);
		PortalCapacitor = new PortalCapacitor(Material.glass);
		PortalCharger = new PortalCharger(Material.glass);
		PortalEmitter = new PortalEmitter(Material.glass);

		GameRegistry.registerBlock(PortalBlock, "portalBlock");
		GameRegistry.registerBlock(PortalCapacitor, "portalCapacitor");
		GameRegistry.registerBlock(PortalCharger, "portalCharger");
		GameRegistry.registerBlock(PortalEmitter, "portalEmitter");
	}

	public void init(FMLInitializationEvent event) {
		// Register entities
		GameRegistry.registerTileEntity(net.byteberry.slipgates.tileentity.TileEntityPortalCharger.class, "tileEntityPortalCharger");
		GameRegistry.registerTileEntity(net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor.class, "tileEntityPortalCapacitor");
		GameRegistry.registerTileEntity(net.byteberry.slipgates.tileentity.TileEntityPortalEmitter.class, "tileEntityPortalEmitter");
		
		// Register the GUI handler
		NetworkRegistry.INSTANCE.registerGuiHandler(Slipgates.instance, new GuiHandler());
		
		// Network
		//PacketHandler.init();
		
		if (event.getSide().equals(Side.SERVER)) {
			// Load our portals from file
			Slipgates.instance.portalHandler.load();
			Slipgates.instance.logger.debug(Slipgates.portalHandler.getAllPortals());
		}

		// Hey Waila, watch me do stuff
		FMLInterModComms.sendMessage("Waila", "register", "net.byteberry.slipgates.waila.WailaDataProvider.callbackRegister");
	}

	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
	}

	public void serverStopping(FMLServerStoppingEvent event) {
		// Save our portals to file if server-side
		if (event.getSide().equals(Side.SERVER)) {
			Slipgates.instance.portalHandler.save();
		}
	}

}
