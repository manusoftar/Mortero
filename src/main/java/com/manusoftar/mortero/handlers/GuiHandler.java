package com.manusoftar.mortero.handlers;

import com.manusoftar.mortero.gui.Contenedor;
import com.manusoftar.mortero.gui.Screen;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    
		
	    return new ContenedorBase(player.inventory);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    return new Contenedor(new ContenedorBase(player.inventory));

	    //return null;
	}

}
