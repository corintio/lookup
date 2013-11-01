package com.github.axet.lookup;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LookupTest {

    @Test
    public void shouldFindTheHeadOfTheCyclops() {
        BufferedImage image = Capture.load(OCRTest.class, "cyclopst1.png");
        BufferedImage template = Capture.load(OCRTest.class, "cyclopst3.png");

        List<Point> pp = LookupColor.lookupAll(image, template, 0.20f);

        assertThat(pp.size(), is(1));
        assertThat(pp.get(0).x, is(37));
        assertThat(pp.get(0).y, is(19));
    }
}
