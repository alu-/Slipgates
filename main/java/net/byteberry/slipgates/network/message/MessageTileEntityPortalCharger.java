package net.byteberry.slipgates.network.message;

import net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor;
import net.byteberry.slipgates.tileentity.TileEntityPortalCharger;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityPortalCharger implements IMessage, IMessageHandler<MessageTileEntityPortalCharger, IMessage> {
	public int x, y, z;
	public int power;

	public MessageTileEntityPortalCharger() {
	}

	public MessageTileEntityPortalCharger(TileEntityPortalCharger tileEntityPortalCharger) {
		this.x = tileEntityPortalCharger.xCoord;
		this.y = tileEntityPortalCharger.yCoord;
		this.z = tileEntityPortalCharger.zCoord;
		this.power = tileEntityPortalCharger.getEnergyStored(ForgeDirection.UP);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.power = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);

		buf.writeInt(power);
	}

	@Override
	public IMessage onMessage(MessageTileEntityPortalCharger message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if (tileEntity instanceof TileEntityPortalCharger) {
			// ((TileEntityPortalCharger) tileEntity).
		}

		return null;

	}

	@Override
	public String toString() {
		return String
				.format("MessageTileEntityAludel - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s, itemId: %s, metaData: %s, stackSize: %s, itemColor: %s",
						x, y, z, power);
	}
}
