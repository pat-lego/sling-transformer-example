package com.adobe.aem.support.core.transformer;

import java.io.IOException;

import org.xml.sax.helpers.AttributesImpl;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.rewriter.DefaultTransformer;
import org.apache.sling.rewriter.ProcessingComponentConfiguration;
import org.apache.sling.rewriter.ProcessingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class SupportTransformer extends DefaultTransformer {

    private SlingHttpServletRequest request;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(ProcessingContext context,
            ProcessingComponentConfiguration config) throws IOException {
                this.request = context.getRequest();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                           Attributes attributes) throws SAXException {

        if ("a".equals(localName) && shouldBeTransformed()) {
            String href = attributes.getValue("href");
            String modifiedHref = modifyHref(href);
            AttributesImpl attributesImpl = new AttributesImpl(attributes);
            attributesImpl.setValue(attributes.getIndex("href"), modifiedHref);
            super.startElement(uri, localName, qName, attributesImpl);
        }  else {
            super.startElement(uri, localName, qName, attributes);
        }
    }

    private boolean shouldBeTransformed() {
        String requestUri = this.request.getRequestURI();
        if (requestUri.startsWith("/content")) {
            logger.info("Processing request path {} - resulting in [shouldBeTransformed] to be set to true", requestUri);
            return true;
        }

        logger.info("Processing request path {} - resulting in [shouldBeTransformed] to be set to false", requestUri);
        return false;
    }

    private String modifyHref(String href) {
        return "www.google.com";
    }

}
