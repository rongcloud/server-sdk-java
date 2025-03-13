package io.rong.models.response;

import io.rong.models.Result;
import io.rong.util.GsonUtil;
import java.util.List;

/**
 * The result of listing gag group users.
 */
public class ListGagGroupUserResult extends Result {
    // List of muted users in the group.
    List<GagGroupUser> members;

	public ListGagGroupUserResult(Integer code, String msg, List<GagGroupUser> members) {
		super(code, msg);
		this.members = members;
	}

	public ListGagGroupUserResult(List<GagGroupUser> members) {
		this.members = members;
	}

    /**
     * Get the list of muted users.

     * @return List
     */
    public List<GagGroupUser> getMembers() {
        return this.members;
    }

    /**
     * Set the list of muted users.

     */
    public void setMembers(List<GagGroupUser> members) {
        this.members = members;
    }

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListGagGroupUserResult.class);
	}
}
