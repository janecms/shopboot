package com.hellojd.shopex.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.hellojd.shopex.bean.AttributeBean;
import com.hellojd.shopex.bean.ParameterGroupBean;
import com.hellojd.shopex.repository.AttributeRepository;
import com.hellojd.shopex.repository.ParameterGroupRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hellojd.shopex.bean.ProductCategoryBean;
import com.hellojd.shopex.bean.treeview.TreeViewBean;
import com.hellojd.shopex.entity.Brand;
import com.hellojd.shopex.entity.ProductCategory;
import com.hellojd.shopex.entity.ProductCategoryBrand;
import com.hellojd.shopex.repository.BrandRepository;
import com.hellojd.shopex.repository.ProductCategoryRepository;
import com.hellojd.shopex.service.ProductCategoryService;
import com.hellojd.shopex.util.BeanUtils;
import com.hellojd.shopex.util.TreeGridUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class ProductCatalogServiceImpl extends ShopBaseServiceImpl<ProductCategoryRepository, ProductCategory>
    implements ProductCategoryService, InitializingBean {
  @Autowired
  ProductCategoryRepository productCategoryRepository;
  @Autowired
  BrandRepository brandRepository;
  @Autowired
  AttributeRepository attributeRepository;
  @Autowired
  ParameterGroupRepository parameterGroupRepository;


  Map<Long, ProductCategoryBean> localCategoryMap = new HashMap<>();

  @Override
  public Set<ProductCategoryBean> getRootProductCategoryList() {
    Set<ProductCategoryBean> rootSet = new HashSet<>();
    localCategoryMap.forEach((catId, cat) -> {
      if (cat.getParent() == null) {
        rootSet.add(localCategoryMap.get(catId));
      }
    });
    return TreeGridUtils.build(rootSet);
  }

  @Override
  public ProductCategoryBean getProductCategoryById(Long id) {
    if(id==null){return null;}
    return localCategoryMap.get(id);
  }


  @Override
  public List<TreeViewBean> buildCategoryTree(ProductCategory selectNode) {
    Set<ProductCategoryBean> rootSet = this.getRootProductCategoryList();
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

  @Transactional(rollbackFor = Exception.class)
  @Override
  public int update(ProductCategoryBean categoryBean, List<Long> brandIds) {
    final Long categoryId = categoryBean.getId();
    final ProductCategoryBean categoryPO = this.getProductCategoryById(categoryId);

    Set<ProductCategoryBrand> reqBrands = new HashSet<>();
    if (CollectionUtils.isNotEmpty(brandIds)) {
      brandIds.forEach(brandId -> {
        reqBrands.add(new ProductCategoryBrand(categoryId, brandId));
      });
    }
    Set<ProductCategoryBrand> persists = new HashSet<>();
    final Set<Brand> brands = categoryPO.getBrands();
    if (CollectionUtils.isNotEmpty(brands)) {
      brands.forEach(item -> {
        persists.add(new ProductCategoryBrand(categoryId, item.getId()));
      });
    }
    this.updateRefResult(reqBrands, persists, new RefResultUpdater<ProductCategoryBrand>() {
      @Override
      public void execute(RefResult<ProductCategoryBrand> refResult) {
        refResult.savingSet.forEach(item -> {
          brandRepository.saveProductCategoryBrand(item);
        });
        refResult.deletingSet.forEach(item -> {
          brandRepository.deleteProductCategoryBrand(item);
        });
      }
    });
    BeanUtils.copyProperties(categoryBean, categoryPO, new String[]{"treePath", "grade", "children", "products", "parameterGroups", "attributes", "promotions"});
    this.flush();
    return this.productCategoryRepository.updateById(categoryPO);
  }

  private void recurBuildCategoryTree(TreeViewBean parent, Set<ProductCategoryBean> children, ProductCategory selectNode) {
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


  @Override
  public void afterPropertiesSet() {
    List<ProductCategory> allSet = this.productCategoryRepository.getAll();
    allSet.forEach(pc -> {
      ProductCategoryBean productCategoryConsumer = new ProductCategoryBean(pc);
      final Long categoryId = pc.getId();
      Set<Brand> brands = brandRepository.getBrands(categoryId);
      productCategoryConsumer.setBrands(brands);
      final Set<ParameterGroupBean> parameterGroups = parameterGroupRepository.getParameterGroups(categoryId);
      productCategoryConsumer.setParameterGroups(parameterGroups);
      final Set<AttributeBean> attributes = attributeRepository.getAttributes(categoryId);
      productCategoryConsumer.setAttributes(attributes);
      localCategoryMap.put(categoryId, productCategoryConsumer);
      Long parentId = pc.getParentId();
      if (parentId != null) {
        ProductCategoryBean parent = localCategoryMap.get(parentId);
        if (parent != null) {
          parent.addChild(productCategoryConsumer);
          productCategoryConsumer.setParent(parent);
        }
      }
    });
  }

  //重新加载
  public void flush() {
    localCategoryMap = new HashMap<>();
    this.afterPropertiesSet();
  }

}
