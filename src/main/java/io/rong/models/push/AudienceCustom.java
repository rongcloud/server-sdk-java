package io.rong.models.push;

import java.util.ArrayList;
import java.util.List;


/**
 * Push Plus tag-based push notification
 *
 * @author lang
 */
public class AudienceCustom extends Audience {
    public static String AND = "AND";
    public static String OR = "OR";

    public List<TagItem> getTagItems() {
        return tagItems;
    }

    public void setTagItems(TagItem... ts) {
        for (TagItem t : ts) {
            tagItems.add(t);
        }
    }

    protected List<TagItem> tagItems = new ArrayList<TagItem>();


    public static class TagItem {
        private String tags[] = {};
        private boolean isNot = false;
        private String tagsOperator = OR;
        private String itemsOperator = OR;

        public String[] getTags() {
            return tags;
        }

        public void setTags(String[] tags) {
            this.tags = tags;
        }

        public boolean getIsNot() {
            return isNot;
        }

        public void setIsNot(boolean isNot) {
            this.isNot = isNot;
        }

        public String getTagsOperator() {
            return tagsOperator;
        }

        public void setTagsOperator(String tagsOperator) {
            this.tagsOperator = tagsOperator;
        }

        public String getItemsOperator() {
            return itemsOperator;
        }

        public void setItemsOperator(String itemsOperator) {
            this.itemsOperator = itemsOperator;
        }
    }

}
