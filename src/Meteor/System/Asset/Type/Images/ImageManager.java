package Meteor.System.Asset.Type.Images;

import java.util.HashMap;
import java.util.Map;

import Meteor.Graphics.Bitmap;
import Meteor.System.Error;
import Meteor.System.Util;
import Meteor.System.Asset.AssetManager;

/**
 * {@code ImageManager} is an image manager class.
 * <br>
 * This class can be used to grab and use images.
 */
public class ImageManager
{
    public static String CLASS_NAME = "imageManager"; //The title of the class
    public static String MAP_NAME = "imageMap"; //The title of the hash map
    private static String className = "[" + ImageManager.CLASS_NAME + "]:"; //The class name formatted

    public static Map<String, Bitmap> IMAGE_MAP = new HashMap<>(); //The {@code HashMap<>()} used to hold the images

    /**
     * Determines if a image with a given key exists in the hash table.
     *
     * @param key The lower-cased key attached to the image.
     * @return If the image with a given key exists in the hash table.
     */
    private static boolean checkMap(String key)
    {
        return IMAGE_MAP.containsKey(key.toLowerCase());
    }

    /**
     * Method used to add a bitmap to the hash table.
     *
     * @param key The lower-cased key attached to the bitmap.
     * @param bmp The bitmap to add to the hash table.
     */
    public static void add(String key, Bitmap bmp)
    {
        ImageManager.IMAGE_MAP.put(key, bmp);
        Util.logAdd(ImageManager.CLASS_NAME, key, ImageManager.MAP_NAME);
    }


    /**
     * Method used to grab a image contained inside the hash table.
     *
     * @param name  The name attached to the image.
     * @param isKey Weather or not the name parameter is the key generated by the {@code AssetManager}.
     * @return The image from the hash table.
     */
    public static Bitmap get(String name, boolean isKey)
    {
        String key = "";
        if (!isKey) key = AssetManager.createKey(Image.TYPE, name);
        else key = name;

        Bitmap image = null;
        if (checkMap(key)) image = IMAGE_MAP.get(key);
        else new Error(Error.KeyNotFoundException(ImageManager.CLASS_NAME, key, ImageManager.MAP_NAME));

        return image;
    }

    /**
     * Method used to remove an image from the hash table.
     *
     * @param name  The name attached to the image.
     * @param isKey Weather or not the name parameter is the key generated by the {@code AssetManager}.
     */
    public static void remove(String name, boolean isKey)
    {
        String key = "";
        if (!isKey) key = AssetManager.createKey(Image.TYPE, name);
        else key = name;
        key = key.toLowerCase();
        if (checkMap(key))
        {
            IMAGE_MAP.remove(key);
            Util.log(className + "[" + key + "] was removed from the [" + ImageManager.MAP_NAME + "].");
        } else new Error(Error.KeyNotFoundException(ImageManager.CLASS_NAME, key, ImageManager.MAP_NAME));
    }

    /**
     * Method used to clean up memory used by
     * certain processes.
     */
    public static void cleanUp()
    {
        IMAGE_MAP.clear();
    }
}
