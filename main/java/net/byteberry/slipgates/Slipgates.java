package net.byteberry.slipgates;

import org.apache.logging.log4j.Logger;

import net.byteberry.slipgates.reference.Reference;
import net.byteberry.slipgates.network.PacketHandler;
import net.byteberry.slipgates.proxy.*;
import net.byteberry.slipgates.block.*;
import net.byteberry.slipgates.gui.GuiHandler;
import net.byteberry.slipgates.handler.PortalHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

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
		logger = event.getModLog();
		logger.info("Loading " + Reference.MOD_NAME + " " + Reference.VERSION);

		// Register the GUI handler
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		// Register entities
		GameRegistry.registerTileEntity(net.byteberry.slipgates.tileentity.TileEntityPortalCharger.class, "tileEntityPortalCharger");
		GameRegistry.registerTileEntity(net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor.class, "tileEntityPortalCapacitor");
		GameRegistry.registerTileEntity(net.byteberry.slipgates.tileentity.TileEntityPortalEmitter.class, "tileEntityPortalEmitter");
		
		// Network
		PacketHandler.init();
		
		this.proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// TODO portal loading/saving needs to be server only? We should
		// probably move this to the proxies
		// Load our portals from file
		// portalHandler.load();

		// logger.debug(portalHandler.getAllPortals());
		
		// Tell Waila that we are here.
		FMLInterModComms.sendMessage("Waila", "register", "net.byteberry.slipgates.waila.WailaDataProvider.callbackRegister");
		
		this.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		logger.info("Finished loading " + Reference.MOD_NAME + " " + Reference.VERSION);
		this.proxy.postInit(event);
	}

	@Mod.EventHandler
	public void serverStopping(FMLServerStoppingEvent event) {
		// Save our portals to file
		// portalHandler.save();

		this.proxy.serverStopping(event);
	}

}
