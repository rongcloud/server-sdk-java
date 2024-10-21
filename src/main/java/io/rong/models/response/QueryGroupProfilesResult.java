package io.rong.models.response;


import io.rong.models.profile.EntrustGroupProfileModel;
import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class QueryGroupProfilesResult extends ResponseResult {

    private List<EntrustGroupProfileModel> profiles;

    public QueryGroupProfilesResult(Integer code, String msg) {
        super(code, msg);
    }

    public List<EntrustGroupProfileModel> getProfiles() {
        return profiles;
    }

    public QueryGroupProfilesResult setProfiles(List<EntrustGroupProfileModel> profiles) {
        this.profiles = profiles;
        return this;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, QueryGroupProfilesResult.class);
    }
}
