package org.romanzhula.dataanalyser.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class TextXPath {

    private final XML xml;
    private final String node;

    @Override
    public String toString() {
        return this.xml.nodes(node)
                .get(0)
                .xpath("text()")
                .get(0)
        ;
    }

}
