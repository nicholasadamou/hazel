package Meteor.System.Asset.Type.Audios;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Meteor.System.Asset.Asset;
import Meteor.System.Error;

/**
 * <p>Representation of audio resources used in the
 * program. Play back is done on a separate thread.</p>
 */
public class Audio extends Asset
{
    public static final String TYPE = "audio";

    public Audio(String key, String filePath)
    {
        super(Audio.TYPE, key, filePath);
    }

    @Override
    public synchronized void load()
    {
        if (filePath == null)
        {
            new Error(Error.filePathException(Audio.TYPE));
            return;
        }

        Clip clip = null;

        try
        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(assetLoader.getResourceAsStream(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            target = clip;
        } catch (Exception e)
        {
            e.printStackTrace();
            new Error(Error.FileNotFoundException(Audio.TYPE, filePath));
        }
    }

    public synchronized static Clip load(Class<?> className, String filePath)
    {
        if (filePath == null)
        {
            new Error(Error.filePathException(Audio.TYPE));
        }

        Clip clip = null;

        try
        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(assetLoader.getResourceAsStream(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception e)
        {
            e.printStackTrace();
            new Error(Error.FileNotFoundException(Audio.TYPE, filePath));
        }

        return clip;
    }

    @Override
    public synchronized Clip getData()
    {
        if ((Clip) target == null)
        {
            new Error("[" + Audio.TYPE + "]: [" + fileName + "] has not been loaded.");
        }

        return (Clip) target;
    }
}