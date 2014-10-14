package nz.co.xingsoft.gwt.sample.client.view.other;

import nz.co.ecngroup.shared.web.common.place.ParameterTokenizerPlace;
import nz.co.ecngroup.shared.web.common.place.ParameterTokenizerPlaceTokenizer;

import com.google.gwt.place.shared.PlaceTokenizer;

public class OtherPlace
        extends ParameterTokenizerPlace {
    public static class Tokenizer
            extends ParameterTokenizerPlaceTokenizer<OtherPlace>
            implements PlaceTokenizer<OtherPlace> {

        @Override
        protected OtherPlace placeInstance(final String token) {
            return new OtherPlace();
        }

    }
}
