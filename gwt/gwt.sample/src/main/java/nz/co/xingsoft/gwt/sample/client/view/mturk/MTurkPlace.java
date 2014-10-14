package nz.co.xingsoft.gwt.sample.client.view.mturk;

import nz.co.ecngroup.shared.web.common.place.ParameterTokenizerPlace;
import nz.co.ecngroup.shared.web.common.place.ParameterTokenizerPlaceTokenizer;

import com.google.gwt.place.shared.PlaceTokenizer;

public class MTurkPlace
        extends ParameterTokenizerPlace {
    public static class Tokenizer
            extends ParameterTokenizerPlaceTokenizer<MTurkPlace>
            implements PlaceTokenizer<MTurkPlace> {

        @Override
        protected MTurkPlace placeInstance(final String token) {
            return new MTurkPlace();
        }

    }
}
