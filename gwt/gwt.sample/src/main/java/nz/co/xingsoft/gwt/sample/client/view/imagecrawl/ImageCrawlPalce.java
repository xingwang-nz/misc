package nz.co.xingsoft.gwt.sample.client.view.imagecrawl;

import nz.co.ecngroup.shared.web.common.place.ParameterTokenizerPlace;
import nz.co.ecngroup.shared.web.common.place.ParameterTokenizerPlaceTokenizer;

import com.google.gwt.place.shared.PlaceTokenizer;

public class ImageCrawlPalce
        extends ParameterTokenizerPlace {
    public static class Tokenizer
            extends ParameterTokenizerPlaceTokenizer<ImageCrawlPalce>
            implements PlaceTokenizer<ImageCrawlPalce> {

        @Override
        protected ImageCrawlPalce placeInstance(final String token) {
            return new ImageCrawlPalce();
        }

    }
}
