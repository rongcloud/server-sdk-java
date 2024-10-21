package io.rong.models.profile;


/**
 * @author RongCloud
 */
public class PagingQueryUserProfilesModel {

    private int page;
    private int size;
    private int order;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
