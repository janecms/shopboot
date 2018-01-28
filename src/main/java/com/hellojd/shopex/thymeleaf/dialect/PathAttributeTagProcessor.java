package com.hellojd.shopex.thymeleaf.dialect;

import org.apache.commons.lang.StringUtils;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
/**
 * @author Administrator
 */
public class PathAttributeTagProcessor extends AbstractAttributeTagProcessor {
    private static final String ATTR_NAME = "path";
    private static final int PRECEDENCE = 10000;

    protected PathAttributeTagProcessor(String dialectPrefix) {
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix,     // Prefix to be applied to name for matching
                null,              // No tag name: match any tag name
                false,             // No prefix to be applied to tag name
                ATTR_NAME,         // Name of the attribute that will be matched
                true,              // Apply dialect prefix to attribute name
                PRECEDENCE,        // Precedence (inside dialect's own precedence)
                true);             // Remove the matched attribute afterwards
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler structureHandler) {
        final IEngineConfiguration configuration = context.getConfiguration();
        final IStandardExpressionParser parser =
                StandardExpressions.getExpressionParser(configuration);
        final String templatContext = "/eshop/naggy/";
        if (StringUtils.equalsIgnoreCase("link",tag.getElementCompleteName())){
            structureHandler.setAttribute("href", templatContext +attributeValue);
        }else{
            structureHandler.setAttribute("src", templatContext +attributeValue);
        }
        //        final IStandardExpression expression = parser.parseExpression(context, attributeValue);
//        final String path =(String) expression.execute(context);

    }
}
