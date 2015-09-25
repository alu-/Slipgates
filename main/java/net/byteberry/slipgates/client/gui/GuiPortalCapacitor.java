package net.byteberry.slipgates.client.gui;

import net.byteberry.slipgates.inventory.ContainerPortalCapacitor;
import net.byteberry.slipgates.reference.Reference;
import net.byteberry.slipgates.tileentity.TileEntityPortalCapacitor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.GuiScrollingList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPortalCapacitor extends GuiScreen {

	private static final ResourceLocation guiTexture = new ResourceLocation(Reference.MOD_ID, "textures/gui/guiGenericModal.png");
	public final int xSizeOfTexture = 176;
	public final int ySizeOfTexture = 88;
	private TileEntityPortalCapacitor tile;
	private GuiPortalScrollList optionList;

	public GuiPortalCapacitor(TileEntityPortalCapacitor tile) {
		this.tile = tile;
		// Perhaps we need to rework the widths and heights, i suspect our real size of guiscreen is much larger than our background and data
		//this.height
		//this.width
	}

	@Override
	public void initGui() {
		//this.optionList = new GuiPortalScrollList(this);
		//this.optionList.registerScrollButtons(this.buttonList, 7, 8);
		//this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
	}

	protected void drawGuiForegroundLayer(int i, int j, float f) {
		// TODO remove ScaledResolution? we aren't using it.. yet.
//		ScaledResolution scaledRes = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
//		int scaledWidth = scaledRes.getScaledWidth();
//		int scaledHeight = scaledRes.getScaledHeight();

		// TODO implement a scroll list of all the active and formed portals
		// check GuiIngameModOptions extends GuiScreen for a scrolling gui list
		// We need to extend GuiScrollingList.
		// http://cmicro.github.io/NeatCraft/forge-javadoc/cpw/mods/fml/client/GuiScrollingList.html
		
		//this.optionList.drawScreen(i, j, f);
		
		// TODO redesign background so we can fit a scrollable list with active portals and their cost to teleport
		
		drawCenteredString(fontRendererObj, "Portal Block Power", width / 2, (height / 2) - 25, 0xFFFFFFFF);
		drawCenteredString(fontRendererObj, this.tile.getEnergyStored(ForgeDirection.DOWN) + " RF", width / 2, (height / 2) - 5, 0xFFDE0000);
	}

	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		drawGuiBackgroundLayer();

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		drawGuiForegroundLayer(i, j, f);

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
