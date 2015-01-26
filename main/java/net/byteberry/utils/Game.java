package net.byteberry.utils;

import net.minecraft.world.World;

public class Game {
	public static boolean isHost(final World world) {
		 return !world.isRemote;
	}
}
