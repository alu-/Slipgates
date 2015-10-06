package net.byteberry.slipgates;

import net.byteberry.slipgates.handler.PortalHandler;
import net.byteberry.slipgates.proxy.CommonProxy;
import net.byteberry.slipgates.reference.Reference;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Slipgates {
	public final static PortalHandler portalHandler = new PortalHandler();

	@Mod.Instance(Reference.MOD_ID)
	public static Slipgates instance;

	public static Logger logger;
	
	/*
	 * The ClientProxy is called on startup if minecraft is started from a
	 * Combined Client
	 * 
	 * The ServerProxy is called on startup if minecraft is started from a
	 * Dedicated Server
	 * 
	 * A game process runs on the Server Side if it executes the world update
	 * tasks etc.
	 * 
	 * A game process runs on the Client Side if it executes rendering and shows
	 * the world to a player who controls his character
	 */
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		this.logger = event.getModLog();
		this.logger.info("Pre initialization " + Reference.MOD_NAME + " " + Reference.VERSION);
		
		this.proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		this.logger.info("Init " + Reference.MOD_NAME + " " + Reference.VERSION);
		
		this.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		this.logger.info("Finished loading " + Reference.MOD_NAME + " " + Reference.VERSION);
		
		this.proxy.postInit(event);
	}

	@Mod.EventHandler
	public void serverStopping(FMLServerStoppingEvent event) {
		this.logger.info("Server stopping " + Reference.MOD_NAME + " " + Reference.VERSION);
		
		// Save our portals to file
		// portalHandler.save();

		this.proxy.serverStopping(event);
	}

}
