package com.hellojd.shopex.thymeleaf.dialect;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

import static com.hellojd.shopex.thymeleaf.dialect.ShopDialect.DIALECT_PREFIX;

/**
 * 缩写
 * @author Administrator
 */
public class AbbreviateAttributeTagProcessor extends AbstractElementTagProcessor {
    private static final String TAG_NAME = "abbreviate";
    private static final int PRECEDENCE = 10000;
    protected AbbreviateAttributeTagProcessor(String dialectPrefix) {
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix,     // Prefix to be applied to name for matching
                TAG_NAME,          // Tag name: match specifically this tag
                true,              // Apply dialect prefix to tag name
                null,              // No attribute name: will match by tag name
                false,             // No prefix to be applied to attribute name
                PRECEDENCE);       // Precedence (inside dialect's own precedence)
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
        final IEngineConfiguration configuration = context.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
//        parser.parseExpression(expression);
        final String text = tag.getAttributeValue("text");
        final String maxLength = tag.getAttributeValue("maxlength");
         String padding = tag.getAttributeValue("padding");
        if (StringUtils.isBlank(padding)){
            padding="...";
        }else{
            padding=String.valueOf(parser.parseExpression(context, padding));
        }
        final IStandardExpression textExpression = parser.parseExpression(context, text);
        final IStandardExpression maxLengthException = parser.parseExpression(context, maxLength);
        final Object result = textExpression.execute(context);
        final Integer length =NumberUtils.toInt(String.valueOf(maxLengthException.execute(context)),50);
//        structureHandler.setBody(String.valueOf(result),false);
         String realText = String.valueOf(result);
        if(StringUtils.isNotEmpty(realText)){
            if(StringUtils.length(realText)>length){
                realText=StringUtils.substring(realText,0,length)+padding;
            }
        }
        structureHandler.replaceWith(HtmlEscape.escapeHtml5(realText),false);
//        structureHandler.removeTags();
    }
}
