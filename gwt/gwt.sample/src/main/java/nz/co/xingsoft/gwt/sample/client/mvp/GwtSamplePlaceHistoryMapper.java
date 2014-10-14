package nz.co.xingsoft.gwt.sample.client.mvp;

import nz.co.xingsoft.gwt.sample.client.view.imagecrawl.ImageCrawlPalce;
import nz.co.xingsoft.gwt.sample.client.view.mturk.MTurkPlace;
import nz.co.xingsoft.gwt.sample.client.view.other.OtherPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ MTurkPlace.Tokenizer.class, ImageCrawlPalce.Tokenizer.class, OtherPlace.Tokenizer.class })
public interface GwtSamplePlaceHistoryMapper extends PlaceHistoryMapper {

}
