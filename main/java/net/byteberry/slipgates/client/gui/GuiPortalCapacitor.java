package net.byteberry.slipgates.client.gui;

import net.byteberry.slipgates.inventory.ContainerPortalCapacitor;
import net.byteberry.slipgates.reference.Reference;
import net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPortalCapacitor extends GuiScreen {

	private static final ResourceLocation guiTexture = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiGenericModal.png");
	public final int xSizeOfTexture = 176;
	public final int ySizeOfTexture = 88;
	private TileEntityPortalCapacitor tile;

	public GuiPortalCapacitor(TileEntityPortalCapacitor tile) {
		this.tile = tile;
	}

	protected void drawGuiForegroundLayer() {
		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		int scaledWidth = scaledRes.getScaledWidth();
		int scaledHeight = scaledRes.getScaledHeight();

		drawCenteredString(fontRendererObj, "Portal Block Power", width / 2, (height / 2) - 25, 0xFFFFFFFF);
		drawCenteredString(fontRendererObj, this.tile.getEnergyInSlipgate() + " RF", width / 2, (height / 2) - 5, 0xFFDE0000);
	}

	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		drawGuiBackgroundLayer();

		super.drawScreen(i, j, f); // Buttons

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		drawGuiForegroundLayer();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	protected void drawGuiBackgroundLayer() {
		this.mc.renderEngine.bindTexture(this.guiTexture);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
	}

	public boolean doesGuiPauseGame() {
		return false;
	}
}
