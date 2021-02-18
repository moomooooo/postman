package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Font;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.api.util.font.CustomFontRenderer;
import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;

public class ClientFont extends Module {
	public ModeSetting font = new ModeSetting("font", this, "Comic Sans Ms", "Comic Sans Ms", "Arial", "Verdana",  "smtn");
	
	public ClientFont() {
		super ("clientFont", "u have to re enable for it to change :(", Keyboard.KEY_NONE, Category.CLIENT);
		this.addSettings(font);
	}
	
	public void onEnable() {
		super.onEnable();
		if(font.is("Comic Sans Ms")) {
			Main.getInstance().customFontRenderer = new CustomFontRenderer(new Font("Comic Sans MS", Font.PLAIN, 18), true, true);
		}
		
		if(font.is("Arial")) {
			Main.getInstance().customFontRenderer = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 18), true, true);
		}
		
		if(font.is("Verdana")) {
			Main.getInstance().customFontRenderer = new CustomFontRenderer(new Font("Verdana", Font.PLAIN, 18), true, true);
		}
		
		if(font.is("smtn")) {
			Main.getInstance().customFontRenderer = new CustomFontRenderer(new Font("smtn", Font.PLAIN, 18), true, true);
		}
	}

}