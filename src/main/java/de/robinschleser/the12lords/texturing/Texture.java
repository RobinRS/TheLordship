package de.robinschleser.the12lords.texturing;

import java.io.File;

public class Texture {

    File textureFile;
    File textureFolder;
    String name;

    public Texture(File textureFolder, String name) {
        this.name = name;
        this.textureFolder = textureFolder;
        this.textureFile = new File(textureFolder.getAbsolutePath() + "//"+name+".jpg");
    }


    public String getName() {
        return name;
    }

    public File getTextureFile() {
        return textureFile;
    }

    public File getTextureFolder() {
        return textureFolder;
    }
}
