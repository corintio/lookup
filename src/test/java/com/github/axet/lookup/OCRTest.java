package com.github.axet.lookup;

import com.github.axet.lookup.common.ImageBinaryGrey;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OCRTest {

    OCR ocr;

    @Before
    public void setup() {
        ocr = new OCR(0.70f);
        // will go to com/github/axet/lookup/fonts folder and load all font
        // familys (here is only font_1 family in this library)
        ocr.loadFontsDirectory(OCRTest.class, new File("fonts"));

    }

    @Test
    public void shouldRecognizeUsingAllFamilies() {
        // recognize using all familys set
        String str = ocr.recognize(Capture.load(OCRTest.class, "test3.png"));
        assertThat(str, is("3662\n3 2€/€​"));

    }

    @Test
    public void shouldRecognizeUsingSpecifiedFamily() {
        // recognize using only one family set
        String str = ocr.recognize(Capture.load(OCRTest.class, "test3.png"), "font_1");
        assertThat(str, is("3662\n3 2€/€​"));

    }

    @Test
    public void shouldRecognizeUsingSpecifiedFamilyAndRectangle() {
        // recognize using only one family set and rectangle
        ImageBinaryGrey i = new ImageBinaryGrey(Capture.load(OCRTest.class, "full.png"));
        String str = ocr.recognize(i, 1285, 654, 1343, 677, ocr.getSymbols("font_1"));
        assertThat(str, is("4339"));
    }

    @Test
    public void shouldLoadOnlyOneFontFamily() {
        OCR ocr = new OCR(0.70f);

        // example how to load only one family
        // "com/github/axet/lookup/fonts/font_1"
        ocr.loadFont(OCRTest.class, new File("fonts", "font_1"));

        // recognize using only one family set and rectangle
        String str = this.ocr.recognize(Capture.load(OCRTest.class, "test3.png"));
        assertThat(str, is("3662\n3 2€/€​"));
    }
}
