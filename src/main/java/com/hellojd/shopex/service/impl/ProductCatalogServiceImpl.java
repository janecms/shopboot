package com.hellojd.shopex.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hellojd.shopex.bean.ProductCategoryBean;
import com.hellojd.shopex.bean.RefBean;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.entity.ProductCategoryBrand;
import com.hellojd.shopex.repository.ProductCategoryRepository;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.util.TreeGridUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import  com.hellojd.shopex.util.BeanUtils;
import java.util.*;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class ProductCatalogServiceImpl extends  ShopBaseServiceImpl<ProductCategoryRepository,ProductCategory>
        implements ProductCategoryService {
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
    public List<TreeViewBean> buildCategoryTree(ProductCategoryBean selectNode) {
        Set<ProductCategoryBean> rootSet = this.productCategoryRepository.getRootProductCategoryList();
        final Iterator<ProductCategoryBean> iter = rootSet.iterator();
        List<TreeViewBean> treeNodeList = new ArrayList<>();
        while (iter.hasNext()) {
            final ProductCategoryBean productCategory = iter.next();
            TreeViewBean treeRootNode = new TreeViewBean(productCategory.getId(), productCategory.getName());
            if (selectNode != null && Objects.equals(selectNode.getId(), productCategory.getId())) {
                treeRootNode.setSelected(true);
            }
            treeNodeList.add(treeRootNode);
            if (CollectionUtils.isEmpty(productCategory.getChildren())) {
                continue;
            } else {
                //recur
                recurBuildCategoryTree(treeRootNode, productCategory.getChildren(), selectNode);
            }
        }

        return treeNodeList;
    }
    @Transactional
    @Override
    public int update(ProductCategoryBean categoryBean,List<Long> brandIds) {
        final Long categoryId = categoryBean.getId();
        final ProductCategoryBean categoryPO = this.productCategoryRepository.getProductCategoryById(categoryId);

        Set<ProductCategoryBrand> reqBrands=new HashSet<>();
        if(CollectionUtils.isNotEmpty(brandIds)){
            brandIds.forEach(brandId->{
                reqBrands.add(new ProductCategoryBrand(categoryId,brandId));
            });
        }
        Set<ProductCategoryBrand> persists =new HashSet<>();
        final Set<Brand> brands = categoryPO.getBrands();
        if(CollectionUtils.isNotEmpty(brands)){
            brands.forEach(item ->{
                persists.add(new ProductCategoryBrand(categoryId,item.getId()));
            });
        }
//        this.sysnProductCategoryBrands(categoryBean.getId(),reqBrands,categoryPO.getBrands());
        this.updateRefResult(reqBrands,persists,new RefResultUpdater<ProductCategoryBrand>(){
            @Override
            public void execute(RefResult<ProductCategoryBrand> refResult) {
                refResult.savingSet.forEach(item ->{
                    productCategoryRepository.saveProductCategoryBrand(item);
                });
                refResult.deletingSet.forEach(item ->{
                    productCategoryRepository.deleteProductCategoryBrand(item);
                });
            }
        });
        BeanUtils.copyProperties(categoryBean, categoryPO, new String[]{"treePath", "grade", "children", "products", "parameterGroups", "attributes", "promotions"});
        return this.productCategoryRepository.updateById(categoryPO);
    }

    @Deprecated
    private void sysnProductCategoryBrands(Long categoryId,Set<ProductCategoryBrand> requests,Set<Brand> brands){
        Collection<ProductCategoryBrand> saves =null;
        Collection<ProductCategoryBrand> deletes =null;
        Set<ProductCategoryBrand> poSet =new HashSet<>();
        if(CollectionUtils.isNotEmpty(brands)){
            brands.forEach(item ->{
                poSet.add(new ProductCategoryBrand(categoryId,item.getId()));
                });
        }
        if(CollectionUtils.isEmpty(poSet)){
            saves=requests;
        }else{
            if (CollectionUtils.isEmpty(requests)){
                deletes=poSet;
            }else{
                saves=CollectionUtils.removeAll(requests,poSet);
                deletes=CollectionUtils.removeAll(poSet,requests);
            }
        }
        if(CollectionUtils.isNotEmpty(saves)){
            Set<ProductCategoryBrand> saveSet = new HashSet<>(saves);
            saveSet.forEach(brand ->{
                this.productCategoryRepository.saveProductCategoryBrand(brand);
            });
            log.info("save brand for categoryId:{},num:{}",categoryId,saveSet.size());
        }
        if(CollectionUtils.isNotEmpty(deletes)){
            Set<ProductCategoryBrand> deleteSet = new HashSet<>(deletes);
            deleteSet.forEach(brand ->{
                this.productCategoryRepository.deleteProductCategoryBrand(brand);
            });
            log.info("delete brand for categoryId:{},num:{}",categoryId,deleteSet.size());
        }
    }
    private void recurBuildCategoryTree(TreeViewBean parent, Set<ProductCategoryBean> children, ProductCategoryBean selectNode) {
        final Iterator<ProductCategoryBean> iter = children.iterator();
        while (iter.hasNext()) {
            final ProductCategoryBean productCategory = iter.next();
            TreeViewBean childNode = new TreeViewBean(productCategory.getId(), productCategory.getName());
            if (selectNode != null && Objects.equals(selectNode.getId(), productCategory.getId())) {
                childNode.setSelected(true);
            }
            parent.addChild(childNode);
            if (CollectionUtils.isEmpty(productCategory.getChildren())) {
                continue;
            } else {
                recurBuildCategoryTree(childNode, productCategory.getChildren(), selectNode);
            }
        }
    }


}
