package net.byteberry.slipgates.tileentity;

import net.byteberry.slipgates.block.PortalBlock;
import net.byteberry.slipgates.block.PortalCapacitor;
import net.byteberry.utils.Game;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPortalEmitter extends TileEntity {
	public enum MultiBlockState {
		VALID, INVALID, UNKNOWN // UNKNOWN means it is probably unloaded
	};

	private int clock = 1337; // Because why not.
	private MultiBlockState state = MultiBlockState.UNKNOWN;

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.state = state.values()[nbt.getInteger("state")];
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		nbt.setInteger("state", this.state.ordinal());
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (Game.isHost(this.getWorldObj())) {
			clock++;
			if (clock % 100 == 0) {
				// Do check for multiblock
				if (canMultiblockForm()) {
					if (this.state.equals(MultiBlockState.VALID)) {
						// No change
					} else {
						// TODO: Notify client of this change somehow. Also needs to be on invalid I guess.
						this.state = MultiBlockState.VALID;
					}
				} else {
					this.state = MultiBlockState.INVALID;
				}
			}
		}
	}

	// Check 3x3x1 around this block for valid multiblock structure
	private boolean canMultiblockForm() {
		// Check cardinal directions for a PortalCapacitor, could be optimized
		// by detecting multiple capacitors..
		World theWorld = this.getWorldObj();
		int capacitorX = 0, capacitorY = 0, capacitorZ = 0;
		boolean foundCapacitor = false;
		boolean cardinalIsX = false;
		for (int i = -1; i < 2; i += 2) {
			// x cardinal
			if (theWorld.getBlock(this.xCoord + i, this.yCoord, this.zCoord).getUnlocalizedName().equals("tile.slipgates:portalCapacitor")) {
				capacitorX = this.xCoord + i;
				capacitorY = this.yCoord;
				capacitorZ = this.zCoord;
				foundCapacitor = true;
				cardinalIsX = true;
				break;
			}

			// z cardinal
			if (theWorld.getBlock(this.xCoord, this.yCoord, this.zCoord + i).getUnlocalizedName().equals("tile.slipgates:portalCapacitor")) {
				capacitorX = this.xCoord;
				capacitorY = this.yCoord;
				capacitorZ = this.zCoord + i;
				foundCapacitor = true;
				cardinalIsX = false;
				break;
			}

		}

		if (!foundCapacitor) {
			return false;
		}

		// If found, check sides opposite for a charger
		int chargerX = 0, chargerY = 0, chargerZ = 0;
		boolean chargerFound = false;
		if (cardinalIsX) {
			// Check the x cardinal
			if (theWorld.getBlock(capacitorX, capacitorY, capacitorZ + 1).getUnlocalizedName().equals("tile.slipgates:portalCharger")) {
				chargerX = capacitorX;
				chargerY = capacitorY;
				chargerZ = capacitorZ + 1;
				chargerFound = true;
			}
			if (theWorld.getBlock(capacitorX, capacitorY, capacitorZ - 1).getUnlocalizedName().equals("tile.slipgates:portalCharger")) {
				chargerX = capacitorX;
				chargerY = capacitorY;
				chargerZ = capacitorZ - 1;
				chargerFound = true;
			}
		} else {
			// Check the y cardinal
			if (theWorld.getBlock(capacitorX + 1, capacitorY, capacitorZ).getUnlocalizedName().equals("tile.slipgates:portalCharger")) {
				chargerX = capacitorX + 1;
				chargerY = capacitorY;
				chargerZ = capacitorZ;
				chargerFound = true;

			}
			if (theWorld.getBlock(capacitorX - 1, capacitorY, capacitorZ).getUnlocalizedName().equals("tile.slipgates:portalCharger")) {
				chargerX = capacitorX - 1;
				chargerY = capacitorY;
				chargerZ = capacitorZ;
				chargerFound = true;
			}
		}
		if (!chargerFound) {
			return false;
		}

		// Check the remaining blocks for PortalBlocks
		for (int x = this.xCoord - 1; x < this.xCoord + 2; x++) {
			for (int z = this.zCoord - 1; z < this.zCoord + 2; z++) {
				// Skip emitter, charger and capacitor coords
				if ((x == this.xCoord && z == this.zCoord) || (x == chargerX && z == chargerZ) || (x == capacitorX && z == capacitorZ)) {
					continue;
				}

				if (!theWorld.getBlock(x, this.yCoord, z).getUnlocalizedName().equals("tile.slipgates:portalBlock")) {
					return false;
				}
			}
		}

		return true;
	}
}
