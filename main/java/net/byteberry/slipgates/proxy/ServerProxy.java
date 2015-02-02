package net.byteberry.slipgates.proxy;

import net.byteberry.slipgates.Slipgates;
import net.byteberry.slipgates.gui.GuiHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ServerProxy extends CommonProxy {

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		
		// Load our portals from file
		System.out.println("Slipgates server proxy init");
		Slipgates.portalHandler.load();

		Slipgates.logger.debug(Slipgates.portalHandler.getAllPortals());
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void serverStopping(FMLServerStoppingEvent event) {
		super.serverStopping(event);
		
		// Save our portals to file
		System.out.println("Server stopping server proxy");
		Slipgates.portalHandler.save();
	}

}
