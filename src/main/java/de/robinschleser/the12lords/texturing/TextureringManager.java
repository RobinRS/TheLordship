package de.robinschleser.the12lords.texturing;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

public class TextureringManager {

    public final File texturesPath = new File(System.getenv("APPDATA") + "//The12Lordships//.textures//");
    public ArrayList<Texture> textures;

    public TextureringManager() {
        textures = new ArrayList<>();
        if(!texturesPath.isDirectory())
            texturesPath.mkdirs();
        Path path = texturesPath.toPath();
        try {
            Files.setAttribute(path, "dos:hidden", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadTexturesfromServer(String serverIP) throws IOException {
        URL url = new URL("http://"+serverIP+"/textures.zip");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream in = connection.getInputStream();
        FileOutputStream out = new FileOutputStream(texturesPath.getAbsolutePath() + "textures.zip");
        copy(in, out, 1024);
        out.close();


    }

    public static void copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
        byte[] buf = new byte[bufferSize];
        int n = input.read(buf);
        while (n >= 0) {
            output.write(buf, 0, n);
            n = input.read(buf);
        }
        output.flush();
    }

    public void loadAllTextures() {
        FileFilter jpegFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.getAbsolutePath().contains(".jpg"))
                    return true;
                return false;
            }
        };
        for (File f : Objects.requireNonNull(texturesPath.listFiles(jpegFilter))){
            Texture texture = new Texture(texturesPath, f.getName());
            textures.add(texture);
        }
    }

    public Texture getTextureFromName(String name) {
        for (Texture texture : textures) {
            if (texture.getName().equalsIgnoreCase(name))
                return texture;

        }
        return getTextureFromName("404");
    }


}
