package net.byteberry.slipgates.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import net.byteberry.slipgates.Slipgates;
import net.byteberry.slipgates.block.PortalCharger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPortalCapacitor extends TileEntity implements IEnergyReceiver {

	protected EnergyStorage storage = new EnergyStorage(2000000);
	protected IEnergyHandler portalCharger;

	public TileEntityPortalCapacitor() {
		super();
		this.portalCharger = null;
	}

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

		if (!worldObj.isRemote) {
			// Check if Charger is connected
			if (this.portalCharger instanceof IEnergyHandler) {
				TileEntity maybeCharger;
				for (int i = -1; i < 3; i++) {
					// Check the x cardinals
					maybeCharger = worldObj.getTileEntity(this.xCoord + i, this.yCoord, this.zCoord);
					if (maybeCharger instanceof TileEntityPortalCharger) {
						this.portalCharger = (IEnergyHandler) maybeCharger;
						Slipgates.logger.debug("Portal Capacitor: I've connected a charger");
						break;
					}

					// Check the z cardinals
					maybeCharger = worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + i);
					if (maybeCharger instanceof TileEntityPortalCharger) {
						this.portalCharger = (IEnergyHandler) maybeCharger;
						Slipgates.logger.debug("Portal Capacitor: I've connected a charger");
						break;
					}
				}
			}
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

	/* IEnergyConnection (connect to energy transportation blocks) */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		// TODO check if this hampers recieveEnergy method
		return false;
	}

	/* IEnergyReceiver */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		System.out.println("Capacitor->receiveEnergy() ran!");
		int energy = storage.receiveEnergy(maxReceive, simulate);
		if (!worldObj.isRemote && energy > 0) {
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
