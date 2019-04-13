package de.robinschleser.the12lords.texturing;

import java.io.File;

public class Texture {

    private File textureFile;
    private File textureFolder;
    private String name;

    Texture(File textureFolder, String name) {
        this.name = name;
        this.textureFolder = textureFolder;
        this.textureFile = new File(textureFolder.getAbsolutePath() + "//"+name+".jpg");
    }


    String getName() {
        return name;
    }

    public File getTextureFile() {
        return textureFile;
    }

    public File getTextureFolder() {
        return textureFolder;
    }
}
