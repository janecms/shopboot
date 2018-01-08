package com.hellojd.shopex.service.impl;

import com.hellojd.shopex.bean.ProductCategoryBean;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.repository.ProductCategoryRepository;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.util.TreeGridUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductCatalogServiceImpl  implements ProductCategoryService
{
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Override
    public Set<ProductCategoryBean> getRootProductCategoryList() {
        Set<ProductCategoryBean> rootSet = this.productCategoryRepository.getRootProductCategoryList();
        return TreeGridUtils.build(rootSet);
    }

    @Override
    public ProductCategoryBean getProductCategoryById(Long id) {
        return productCategoryRepository.getProductCategoryById(id);
    }
    @Override
    public List<TreeViewBean> buildCategoryTree(ProductCategoryBean selectNode){
        Set<ProductCategoryBean> rootSet = this.productCategoryRepository.getRootProductCategoryList();
        final Iterator<ProductCategoryBean> iter = rootSet.iterator();
        List<TreeViewBean> treeNodeList = new ArrayList<>();
        while (iter.hasNext()){
            final ProductCategoryBean productCategory = iter.next();
            TreeViewBean treeRootNode = new TreeViewBean(productCategory.getId(),productCategory.getName());
            if(selectNode!=null&& Objects.equals(selectNode.getId(),productCategory.getId())){
                treeRootNode.setSelected(true);
            }
            treeNodeList.add(treeRootNode);
            if(CollectionUtils.isEmpty(productCategory.getChildren())){
                continue;
            }else{
                //recur
                recurBuildCategoryTree(treeRootNode,productCategory.getChildren(),selectNode);
            }
        }

        return treeNodeList;
    }

    private void recurBuildCategoryTree(TreeViewBean parent,Set<ProductCategoryBean> children,ProductCategoryBean selectNode){
        final Iterator<ProductCategoryBean> iter = children.iterator();
        while (iter.hasNext()){
            final ProductCategoryBean productCategory = iter.next();
            TreeViewBean childNode = new TreeViewBean(productCategory.getId(),productCategory.getName());
            if(selectNode!=null&& Objects.equals(selectNode.getId(),productCategory.getId())){
                childNode.setSelected(true);
            }
            parent.addChild(childNode);
            if(CollectionUtils.isEmpty(productCategory.getChildren())){
                continue;
            }else{
                recurBuildCategoryTree(childNode,productCategory.getChildren(),selectNode);
            }
        }
    }
}
