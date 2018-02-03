package com.hellojd.shopex.thymeleaf.dialect;

import com.hellojd.shopex.bean.ProductCategoryBean;
import com.hellojd.shopex.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
import static com.hellojd.shopex.thymeleaf.dialect.ShopDialect.DIALECT_PREFIX;

/**
 * 获取产品类别快捷方式
 */
@Component
public class CategoryTagProcessor extends AbstractElementTagProcessor {
    private static final String TAG_NAME = "category";
    private static final int PRECEDENCE = 12000;
    @Autowired
    ProductCategoryService productCategoryService;
    public CategoryTagProcessor() {
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                DIALECT_PREFIX,     // Prefix to be applied to name for matching
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
        final String idTxt = tag.getAttributeValue("id");
        final IStandardExpression textExpression = parser.parseExpression(context, idTxt);
        final Object id = textExpression.execute(context);
        final ProductCategoryBean category = productCategoryService.getProductCategoryById(Long.valueOf(String.valueOf(id)));
        structureHandler.setLocalVariable("category",category);
        structureHandler.removeTags();
    }
}
