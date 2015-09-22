package net.byteberry.slipgates.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPortalCapacitor extends TileEntity implements IEnergyReceiver {
	
	protected EnergyStorage storage = new EnergyStorage(32000);
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
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
		return storage.receiveEnergy(maxReceive, simulate);
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
