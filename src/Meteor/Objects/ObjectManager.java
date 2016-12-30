package Meteor.Objects;

import java.util.ArrayList;
import java.util.List;

import Meteor.GameEngine.Manager;
import Meteor.GameEngine.Interfaces.Renderable;
import Meteor.GameEngine.Interfaces.Updatable;
import Meteor.Graphics.Context;

/**
 * {@code ObjectManager} is a object handler class.
 * <br>
 * This class is used to handle all of the object's.
 */
public class ObjectManager implements Updatable, Renderable {
	public List<Object> objectList = new ArrayList<>(); //The list of objects
	
	/**
	 * Method used to add a object to the list
	 * @param obj The object to add to the list
	 */
	public void add(Object obj) {
		objectList.add(obj);
	}
	
	/**
	 * Method used to remove an object from the list
	 * @param obj The object to remove from the list
	 */
	public void remove(Object obj) {
		objectList.remove(obj);
	}
	
	@Override public void update(Manager manager, double delta) {
		for (Object obj : objectList) {
			obj.update(manager, delta);
		}
	}
	
	@Override public void render(Manager manager, Context ctx) {
		for (Object obj : objectList) {
			obj.render(manager, ctx);
		}
	}
}
