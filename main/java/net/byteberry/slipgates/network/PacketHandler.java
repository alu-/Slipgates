package net.byteberry.slipgates.network;

import net.byteberry.slipgates.network.message.MessageTileEntityPortalCharger;
import net.byteberry.slipgates.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

	public static void init() {
		//INSTANCE.registerMessage(MessageTileEntityPortalCharger.class, MessageTileEntityPortalCharger.class, 0, Side.CLIENT);
	}
}
