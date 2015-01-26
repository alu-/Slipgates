package net.byteberry.slipgates.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.byteberry.slipgates.Slipgates;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
// Perhaps move from IEnergyHandler to something else. We need to funnel energy into the capacitor
//TODO FIX java.lang.RuntimeException: class net.byteberry.slipgates.tileentity.TileEntityPortalCharger is missing a mapping! This is a bug!

public class TileEntityPortalCharger extends TileEntity implements IEnergyHandler {

	protected EnergyStorage storage = new EnergyStorage(32000);

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		try {
			super.writeToNBT(nbt);
			storage.writeToNBT(nbt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* IEnergyConnection */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	/* IEnergyProvider */
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
	}

	/* IEnergyReceiver and IEnergyProvider */
	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}

}
