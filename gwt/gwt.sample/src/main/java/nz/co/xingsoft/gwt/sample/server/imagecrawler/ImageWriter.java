package nz.co.xingsoft.gwt.sample.server.imagecrawler;

public interface ImageWriter {
    void write(String imageUrl, byte[] image) throws Exception;
}
