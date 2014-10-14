package nz.co.xingsoft.gwt.sample.server.imagecrawler;

import java.io.File;

import edu.uci.ics.crawler4j.util.IO;

public class ImageFileWriter implements ImageWriter {

    private final File storageFolder;

    public ImageFileWriter(final String storageFolderName) {
        storageFolder = new File(storageFolderName);
        if (storageFolder.exists()) {
            deleteFolder(storageFolder);
        }
        storageFolder.mkdirs();

    }

    private void deleteFolder(final File folder) {
        File[] files = folder.listFiles();
        if (files != null) { // some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    @Override
    public void write(final String imageUrl, final byte[] image) throws Exception {
        // get a unique name for storing this image
        final String extension = imageUrl.substring(imageUrl.lastIndexOf("."));
        final String hashedName = Cryptography.MD5(imageUrl) + extension;

        // store image
        final String imagefilename = storageFolder.getAbsolutePath() + "/" + hashedName;
        IO.writeBytesToFile(image, imagefilename);

        System.out.println("Stored: " + imageUrl + " - " + imagefilename);

    }

}
