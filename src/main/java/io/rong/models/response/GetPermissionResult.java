package io.rong.models.response;


import io.rong.util.GsonUtil;

import java.util.List;

/**
 * @author RongCloud
 */
public class GetPermissionResult extends PageResult {


    private List<PermissionSetting> permissionSettings;

    public GetPermissionResult(Integer code, String msg) {
        super(code, msg);
    }

    public List<PermissionSetting> getPermissionSettings() {
        return permissionSettings;
    }

    public void setPermissionSettings(List<PermissionSetting> permissionSettings) {
        this.permissionSettings = permissionSettings;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, GetPermissionResult.class);
    }
}
