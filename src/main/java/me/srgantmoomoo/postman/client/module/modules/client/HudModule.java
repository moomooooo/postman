package me.srgantmoomoo.postman.client.module.modules.client;

import java.awt.Point;

import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.FixedComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;

/**
 * @author lukflug
 */

public abstract class HudModule extends Module {

	protected FixedComponent component;
	protected Point position;
	
	public HudModule (String title, String description, Point defaultPos) {
		super(title, description, Keyboard.KEY_NONE, Category.CLIENT);
		position = defaultPos;
		toggled = true;
	}
	
	public abstract void populate (Theme theme);

	public FixedComponent getComponent() {
		return component;
	}
	
	public void resetPosition() {
		component.setPosition(Main.getInstance().clickGui.guiInterface,position);
	}
}