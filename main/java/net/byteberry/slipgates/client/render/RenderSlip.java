package net.byteberry.slipgates.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSlip extends Render {
	private Tessellator tessellator = Tessellator.instance;

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		// TODO Auto-generated method stub

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO Auto-generated method stub
		return null;
	}

	// GL11 stuff
	private void fadeOutCamera() {
		/*
		 * TODO Color to gray Gray to black This includes the players GUI Show
		 * slipstream, without GUI
		 */
		

	}

	private void RenderFadeBlend(boolean bFading, int WindowWidth, int WindowHeight, float r, float g, float b, float frametime, float fadetime) {
		float fBlackAlpha = -1;

		if (bFading) {
			// begin a new fade if necessary
			if (fBlackAlpha < 0) {
				fBlackAlpha = 0;
			}
		} else {
			// if the previous fade has just ended don't fade at all
			if (fBlackAlpha < 0) {
				return;
			}
		}

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();

		// when laying out a 2D view, sometimes "top-left is the origin" makes
		// more sense.
		// it is the direction i read in, after all. live with it, weenies.
		GL11.glOrtho(0, WindowWidth, WindowHeight, 0, -1, 1);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();

		GL11.glColor4f(r, g, b, fBlackAlpha);

		GL11.glBegin(GL11.GL_QUADS);

		GL11.glVertex3f(0, 0, 0);
		GL11.glVertex3f(WindowWidth, 0, 0);
		GL11.glVertex3f(WindowWidth, WindowHeight, 0);
		GL11.glVertex3f(0, WindowHeight, 0);

		GL11.glEnd();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPopMatrix();

		// bring the fade up or down over a fadetime second period
		if (bFading) {
			fBlackAlpha += (frametime / fadetime);
		} else {
			fBlackAlpha -= (frametime / fadetime);
		}
	}

	// GL11 stuff
	private void slipTunnel() {

	}

	private boolean isCookieGate() {
		// TODO Figure out when to have some cookies
		// Perhaps we should randomly render a item in the slipstream? Also,
		// slipstream is the new word.
		return false;
	}

}
