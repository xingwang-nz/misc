package nz.co.xingsoft.gwt.sample.server;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import nz.co.xingsoft.gwt.sample.server.imagecrawler.ImageCrawlLauncher;
import nz.co.xingsoft.gwt.sample.services.GwtSampleService;
import nz.co.xingsoft.gwt.sample.shared.dto.UserDto;
import nz.co.xingsoft.gwt.sample.shared.dto.imagecrawler.ImageResult;
import nz.co.xingsoft.gwt.sample.shared.validation.ValidatedParam;
import nz.co.xingsoft.gwt.sample.shared.validation.group.ValidationGroup1;
import nz.co.xingsoft.gwt.sample.shared.validation.group.ValidationGroup2;

public class GwtSampleServiceImpl implements GwtSampleService {

    @Override
    public String validateGroup1(final String dummyParameter, @ValidatedParam(groups = { ValidationGroup1.class }) final UserDto userDto) throws Exception {
        return userDto.getName();
    }

    @Override
    public String validateGroup2(@ValidatedParam(groups = { ValidationGroup2.class }) final UserDto userDto, final String dummyParameter) throws Exception {
        return userDto.getName();
    }

    private final String crawlRootFolderName = "/home/xwang/temp/webcrawler";
    private final String imageStorageFolderName = "/home/xwang/work/ecn/t4/trunk/gwt.sample/target/gwt.sample-1.11/GwtSample/web-crawl-images";

    @Override
    public ImageResult crawl(final String domain, final int depth) throws Exception {
        new ImageCrawlLauncher(crawlRootFolderName, imageStorageFolderName).startCrawl(new String[] { domain }, depth);
        final ImageResult result = new ImageResult();
        result.setSuccessful(true);
        result.setNames(vieImages());
        return result;
    }

    private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g|png|tiff?))$");

    @Override
    public List<String> vieImages() throws Exception {
        final List<String> imageNames = new ArrayList<String>();

        final File imageFolder = new File(imageStorageFolderName);

        File[] files = imageFolder.listFiles(new FileFilter() {

            @Override
            public boolean accept(final File file) {
                if (file.isDirectory()) {
                    return false;
                }

                return imgPatterns.matcher(file.getName()).matches();
            }
        });

        if (files != null && files.length > 0) {
            for (File file : files) {
                imageNames.add(file.getName());
            }
        }

        return imageNames;

    }
}
