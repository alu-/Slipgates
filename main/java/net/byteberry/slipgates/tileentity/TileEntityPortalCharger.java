package net.byteberry.slipgates.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;

public class TileEntityPortalCharger extends TileEntity implements IEnergyHandler {

	protected EnergyStorage storage = new EnergyStorage(1337, 1337);

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

	@Override
	public void updateEntity() {
		super.updateEntity();
		try {
			// Push power if TileEntity is of TileEntityPortalCapacitor
			if ((storage.getEnergyStored() > 0)) {
				for (int i = 0; i < 6; i++) {
					TileEntity tile = worldObj.getTileEntity(xCoord + ForgeDirection.getOrientation(i).offsetX, yCoord
							+ ForgeDirection.getOrientation(i).offsetY, zCoord + ForgeDirection.getOrientation(i).offsetZ);
					if (tile instanceof TileEntityPortalCapacitor) {
						storage.extractEnergy(((IEnergyReceiver) tile).receiveEnergy(ForgeDirection.getOrientation(i).getOpposite(), storage.extractEnergy(storage.getMaxExtract(), true), false), false);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
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

	/* IEnergyConnection */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		int energy = storage.receiveEnergy(maxReceive, simulate);
		if (!worldObj.isRemote && energy > 0) {
			this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			markDirty();
		}
		return energy;
	}

	/* IEnergyProvider */
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		int energy = storage.extractEnergy(maxExtract, simulate);
		if (!worldObj.isRemote && energy > 0) {
			this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			markDirty();
		}
		return energy;
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

	public int getMaxExtract(ForgeDirection from) {
		return storage.getMaxExtract();
	}

}
