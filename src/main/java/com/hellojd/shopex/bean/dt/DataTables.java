package com.hellojd.shopex.bean.dt;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

@Data
@NoArgsConstructor
public class DataTables<T> {
    int draw=1;
    int start;
    int length;
    int recordsTotal;
    int recordsFiltered;
    Collection<T> data;
    Map[] order;
    public DataTables(Collection<T> data) {
        this.data = data;
        int size = data.size();
        this.recordsTotal= size;
        this.recordsFiltered=size;
    }
}
