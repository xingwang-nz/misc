package nz.co.xingsoft.gwt.sample.services;

import java.util.List;

import nz.co.xingsoft.gwt.sample.shared.dto.UserDto;
import nz.co.xingsoft.gwt.sample.shared.dto.imagecrawler.ImageResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwtSampleServiceAsync {

    void validateGroup1(String dummyParameter, UserDto userDto, AsyncCallback<String> callback);

    void validateGroup2(UserDto userDto, String dummyParameter, AsyncCallback<String> callback);

    void crawl(String domain, int depth, AsyncCallback<ImageResult> callback);

    void vieImages(AsyncCallback<List<String>> callback);

}
