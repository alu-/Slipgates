package net.byteberry.slipgates.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPortalCapacitor extends TileEntity implements IEnergyReceiver {

	protected EnergyStorage storage = new EnergyStorage(2000000);

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		System.out.println("Read NBT");
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		System.out.println("Write NBT");
		try {
			super.writeToNBT(nbt);
			storage.writeToNBT(nbt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}

	/* IEnergyConnection (connect to energy transportation blocks) */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		// TODO check if this hampers recieveEnergy method
		return false;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		// TODO check if direction block is charger
		System.out.println("RECEIVE ENERGY");

		int energy = storage.receiveEnergy(maxReceive, simulate);
		if (!worldObj.isRemote) {
			this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			markDirty();
		}
		return energy;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}

}
