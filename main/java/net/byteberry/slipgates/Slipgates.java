package net.byteberry.slipgates;

import org.apache.logging.log4j.Logger;

import net.byteberry.slipgates.reference.Reference;
import net.byteberry.slipgates.proxy.*;
import net.byteberry.slipgates.block.*;
import net.byteberry.slipgates.client.gui.GuiHandler;
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
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_ID, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Slipgates {
	public static Block PortalBlock;
	public static Block PortalCapacitor;
	public static Block PortalCharger;
	public static Block PortalEmitter;

	public final static PortalHandler portalHandler = new PortalHandler();

	@Mod.Instance(Reference.MOD_ID)
	public static Slipgates instance;

	private static Logger logger;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("Loading " + Reference.MOD_NAME + " " + Reference.VERSION);

		// Create and register blocks
		PortalBlock = new PortalBlock(Material.glass);
		PortalCapacitor = new PortalCapacitor(Material.glass);
		PortalCharger = new PortalCharger(Material.glass);
		PortalEmitter = new PortalEmitter(Material.glass);

		GameRegistry.registerBlock(PortalBlock, "portalBlock");
		GameRegistry.registerBlock(PortalCapacitor, "portalCapacitor");
		GameRegistry.registerBlock(PortalCharger, "portalCharger");
		GameRegistry.registerBlock(PortalEmitter, "portalEmitter");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Register entities
		GameRegistry.registerTileEntity(net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor.class, "tileEntityPortalCapacitor");
		GameRegistry.registerTileEntity(net.byteberry.slipgates.tileentity.TileEntityPortalEmitter.class, "tileEntityPortalEmitter");

		// Register the GUI handler
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

		// Load our portals from file
		portalHandler.load();

		logger.debug(portalHandler.getAllPortals());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		logger.info("Finished loading " + Reference.MOD_NAME + " " + Reference.VERSION);
	}

	@Mod.EventHandler
	public void serverStop(FMLServerStoppingEvent event) {
		// Save our portals to file
		portalHandler.save();
	}

}
