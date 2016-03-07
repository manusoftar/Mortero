package com.manusoftar.mortero.gui;

import org.lwjgl.opengl.GL11;

import com.manusoftar.mortero.Mortero;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Contenedor extends GuiContainer {
   

	private static final ResourceLocation grinderGuiTextures = new ResourceLocation(Mortero.MODID +":textures/gui/mortero_gui.png");
    private final InventoryPlayer inventoryPlayer = null;
    private final IInventory tileGrinder = null;
    private ResourceLocation texture;
   
    
    public Contenedor(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		
		this.ySize = 166;
		this.xSize = 176;
		
		texture = new ResourceLocation(Mortero.MODID+":textures/gui/mortero_gui.png");
		//System.out.println("Textura GUI: " + texture.getResourcePath());
		//Minecraft.getMinecraft().thePlayer.sendChatMessage("Textura GUI: " + texture.getResourcePath());
	}
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    		 this.drawString(this.fontRendererObj, "Mortero", (width-this.fontRendererObj.getStringWidth("Mortero"))/2, 20, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		//this.drawBackground(0);
		// texture = mc.renderEngine.getTexture(new ResourceLocation("/gui/trap.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        //System.out.println("Ancho: " + width + "\nAlto: " + height);
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
        
	}

   
    

    
 }
