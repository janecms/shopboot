package com.hellojd.shopex.thymeleaf.dialect;

import com.hellojd.shopex.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.ArrayList;
import java.util.List;

import static com.hellojd.shopex.thymeleaf.dialect.ShopDialect.DIALECT_PREFIX;

@Component
public class CategoryListTagProcessor extends AbstractElementTagProcessor {
    private static final String TAG_NAME = "categoryList";
    private static final int PRECEDENCE = 12000;
    @Autowired
    ProductCategoryService productCategoryService;
    public CategoryListTagProcessor() {
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
        List catids =new ArrayList();
        catids.add("A");
        catids.add("B");
        catids.add("C");
        catids.add("D");

        structureHandler.setLocalVariable("categoryList",catids);
        structureHandler.removeTags();
    }
}
