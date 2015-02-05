package net.byteberry.slipgates.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import cpw.mods.fml.client.GuiScrollingList;

public class GuiPortalScrollList extends GuiScrollingList {
	private GuiPortalCapacitor parent;
	
	public GuiPortalScrollList( GuiPortalCapacitor parent) {
		super(parent.mc, 150, parent.height, 32, parent.height - 65 + 4, 10, 35);
		this.parent = parent;
		
		System.out.println("Parent height, width: " + parent.height + ", " + parent.width);
	}

	@Override
	protected int getSize() {
		return 1;
	}

	@Override
	protected void elementClicked(int index, boolean doubleClick) {
		// TODO Bug: all calls are index 0. Probably Height/Width problems.
		System.out.println("Clicked on index: " + index);
		System.out.println("DoubleClick: " + doubleClick);
	}
	
	@Override
	protected boolean isSelected(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void drawBackground() {
	}

	@Override
	protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5) {
		int base = 2;
		for( int i = 0; i < 10; i++ ) {
			this.parent.mc.fontRenderer.drawString(
					this.parent.mc.fontRenderer.trimStringToWidth("Test 1", listWidth - 10), 
					this.left + 3, 
					var3 + base,
					0xFF2222
			);
			base += 10;
		}
//		this.parent.mc.fontRenderer.drawString(this.parent.mc.fontRenderer.trimStringToWidth("Test 1", listWidth - 10), this.left + 3 , var3 + 2, 0xFF2222);
//		this.parent.mc.fontRenderer.drawString(this.parent.mc.fontRenderer.trimStringToWidth("TEST 2", listWidth - 10), this.left + 3 , var3 + 12, 0xFF2222);
//		this.parent.mc.fontRenderer.drawString(this.parent.mc.fontRenderer.trimStringToWidth("DISABLED", listWidth - 10), this.left + 3 , var3 + 22, 0xFF2222);
	}

}
