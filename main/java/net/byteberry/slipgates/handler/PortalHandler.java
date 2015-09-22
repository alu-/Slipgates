package net.byteberry.slipgates.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.lwjgl.util.vector.Vector3f;

import net.byteberry.utils.Game;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

// Class in charge of keeping track on portal multiblock positions
public class PortalHandler {
	private HashMap dimensionToCoordinates = new HashMap<Integer, ArrayList<Vector3f>>();

	public PortalHandler() {
		// yay!

		// Vector3f greger = new Vector3f(1,1,1);
		// ArrayList test = new ArrayList<Vector3f>();
		// test.add(greger);
		// dimensionToCoordinates.put(0, test);
	}

	public void load() {
		try {
			FileInputStream fis = new FileInputStream("portals.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			dimensionToCoordinates = (HashMap) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("portals.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dimensionToCoordinates);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addPortal(World world, int x, int y, int z) {
		Vector3f coords = new Vector3f(x, y, z);
		Integer dimensionId = world.provider.dimensionId;

		if (dimensionToCoordinates.containsKey(dimensionId)) {
			ArrayList portals = (ArrayList) dimensionToCoordinates.get(dimensionId);
			if (portals.contains(coords)) {
				// Whoopsie, this portal is already here..
			} else {
				portals.add(coords);
			}
		} else {
			ArrayList portals = new ArrayList<Vector3f>();
			portals.add(coords);
			dimensionToCoordinates.put(dimensionId, portals);
		}
		System.out.println("I've hopefully added a portal.");
	}

	public void removePortal(World world, int x, int y, int z) {
		Vector3f coords = new Vector3f(x, y, z);
		Integer dimensionId = world.provider.dimensionId;

		if (dimensionToCoordinates.containsKey(dimensionId)) {
			ArrayList portals = (ArrayList) dimensionToCoordinates.get(dimensionId);
			if (portals.contains(coords)) {
				portals.remove(coords);
			} else {
				// Whoopsie, this portal is missing from registry
			}
		} else {
			// Whoopsie, this dimension is missing from registry
		}

	}

	public void getAllPortalsInDimension(Integer dimensionId) {
		if (dimensionToCoordinates.containsKey(dimensionId)) {
			ArrayList test = (ArrayList) dimensionToCoordinates.get(dimensionId);
			Iterator<Vector3f> iterator = test.iterator();

			while (iterator.hasNext()) {
				Vector3f element = iterator.next();
				System.out.print("Portal: " + element.toString());
			}
		}
	}

	public String getAllPortals() {
		String portals = "";

		Iterator hashIterator = dimensionToCoordinates.entrySet().iterator();
		while (hashIterator.hasNext()) {
			Map.Entry pairs = (Entry) hashIterator.next();

			System.out.println(pairs.getKey() + " = " + pairs.getValue());
		}

		// Iterator<Vector3f> iterator = test.iterator();
		// while (iterator.hasNext()) {
		// Vector3f element = iterator.next();
		// System.out.print("Portal: " + element.toString());
		// }
		return portals;
	}
	
	public boolean canSendPlayerThruPortal(EntityPlayer player) {
		// TODO: Check portal timeout
		
		// TODO: Check portal energy
		return false;
	}
	
	public boolean sendPlayerThruPortal( EntityPlayer player) {
		// TODO: Make player invulnerable
		
		// TODO: Stop drawing player for other players
		
		// TODO: Start client renderer (Fade, slipstream, emerging)
		
		
		return false;
		
	}

}
