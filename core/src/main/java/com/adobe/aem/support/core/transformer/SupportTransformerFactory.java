package com.adobe.aem.support.core.transformer;

import org.apache.sling.rewriter.Transformer;
import org.apache.sling.rewriter.TransformerFactory;
import org.osgi.service.component.annotations.Component;

@Component(
    service = TransformerFactory.class,
    immediate = true,
    property = {
        "pipeline.type=support-rewriter"
    }
)
public class SupportTransformerFactory implements TransformerFactory {

    @Override
    public Transformer createTransformer() {
       return new SupportTransformer();
    }
    
}
