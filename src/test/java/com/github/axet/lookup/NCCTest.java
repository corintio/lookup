package com.github.axet.lookup;

import com.github.axet.lookup.common.GPoint;
import com.github.axet.lookup.common.ImageBinaryGrey;
import com.github.axet.lookup.common.ImageBinaryRGB;
import com.github.axet.lookup.proc.NCC;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NCCTest {

    BufferedImage image = Capture.load(OCRTest.class, "cyclopst1.png");
    BufferedImage template = Capture.load(OCRTest.class, "cyclopst3.png");

    @Test
    public void shouldWorkWithColorImages() {
        List<GPoint> pp = NCC
                .lookupAll(new ImageBinaryRGB(image), new ImageBinaryRGB(template), 0.9f);

        assertThat(pp.size(), is(1));
        assertThat(pp.get(0).x, is(21));
        assertThat(pp.get(0).y, is(7));
    }

    @Test
    public void shouldWorkWithGrayImages() {
        List<GPoint> pp = NCC.lookupAll(new ImageBinaryGrey(image), new ImageBinaryGrey(template),
                0.9f);

        assertThat(pp.size(), is(1));
        assertThat(pp.get(0).x, is(21));
        assertThat(pp.get(0).y, is(7));
    }
}
