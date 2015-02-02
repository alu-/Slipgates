package net.byteberry.slipgates.proxy;

import net.byteberry.slipgates.block.PortalBlock;
import net.byteberry.slipgates.block.PortalCapacitor;
import net.byteberry.slipgates.block.PortalCharger;
import net.byteberry.slipgates.block.PortalEmitter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy {

	public static Block PortalBlock;
	public static Block PortalCapacitor;
	public static Block PortalCharger;
	public static Block PortalEmitter;
	
	public void preInit(FMLPreInitializationEvent event) {
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
	
	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void serverStopping(FMLServerStoppingEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
