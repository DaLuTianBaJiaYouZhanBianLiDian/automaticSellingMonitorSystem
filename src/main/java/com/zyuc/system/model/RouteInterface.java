package com.zyuc.system.model;

import com.zyuc.common.model.CommonContent;

/**
 * 路由接口信息
 * @author verdant
 */
public class RouteInterface extends CommonContent{
    private String index;
    private String descr;
    private String speed;
    private String routeip;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getRouteip() {
        return routeip;
    }

    public void setRouteip(String routeip) {
        this.routeip = routeip;
    }
}
