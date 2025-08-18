package io.rong.models.agent;

import io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class Model {
    private String provider;
    private String name;
    private ModelOptions options;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelOptions getOptions() {
        return options;
    }

    public void setOptions(ModelOptions options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, Model.class);
    }

}
