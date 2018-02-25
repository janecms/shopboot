package com.hellojd.shopex.bean;
import com.hellojd.shopex.entity.Goods;
import com.hellojd.shopex.entity.Product;
import com.hellojd.shopex.entity.SpecificationValue;
import java.beans.Transient;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * @author Administrator
 */
public class GoodsBean extends Goods {
    private Set<ProductBean> products = new HashSet();

    public Set<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductBean> products) {
        this.products = products;
    }

    @Transient
    public Set<SpecificationValue> getSpecificationValues()
    {
        HashSet result = new HashSet();
        if (getProducts() != null)
        {
            Iterator<ProductBean> sblings = getProducts().iterator();
            while (sblings.hasNext())
            {
                ProductBean productBean = sblings.next();
                result.addAll(productBean.getSpecificationValues());
            }
        }
        return result;
    }
}
