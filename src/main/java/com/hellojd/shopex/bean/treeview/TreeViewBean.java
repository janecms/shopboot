package com.hellojd.shopex.bean.treeview;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Administrator
 */
public class TreeViewBean implements Serializable {
    private Long href;
    private String text;
    private List<TreeViewBean> nodes = new ArrayList<>();
    State state =new State();

    public TreeViewBean(Long href, String text) {
        this.href = href;
        this.text = text;
    }

    public List<TreeViewBean> getNodes() {
        return nodes;
    }

    public Long getHref() {
        return href;
    }

    public void setHref(Long href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public State getState() {
        return state;
    }
    public void setSelected(boolean selected) {
        this.state.selected = selected;
    }
    public void setState(State state) {
        this.state = state;
    }
    public void addChild(TreeViewBean node){
        this.nodes.add(node);
    }
    class State{
        boolean selected;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}

