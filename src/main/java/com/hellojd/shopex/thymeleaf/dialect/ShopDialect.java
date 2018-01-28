package com.hellojd.shopex.thymeleaf.dialect;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;
/**
 * @author Administrator
 */
public class ShopDialect extends AbstractProcessorDialect {
    private static final String DIALECT_NAME = "score Dialect";
    public ShopDialect() {
        super(DIALECT_NAME, "shop", StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new PathAttributeTagProcessor(dialectPrefix));
        return processors;
    }
}
