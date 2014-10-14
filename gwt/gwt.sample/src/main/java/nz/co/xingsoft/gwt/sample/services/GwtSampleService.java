package nz.co.xingsoft.gwt.sample.services;

import java.util.List;

import nz.co.xingsoft.gwt.sample.shared.dto.UserDto;
import nz.co.xingsoft.gwt.sample.shared.dto.imagecrawler.ImageResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtSampleService")
public interface GwtSampleService extends RemoteService {
    String validateGroup1(String dummyParameter, UserDto userDto) throws Exception;

    String validateGroup2(UserDto userDto, String dummyParameter) throws Exception;

    ImageResult crawl(String domain, int depth) throws Exception;

    List<String> vieImages() throws Exception;
}
