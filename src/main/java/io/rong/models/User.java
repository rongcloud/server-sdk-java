package io.rong.models;

public class User {
    /**
     * userId: User ID, with a maximum length of 64 bytes. It is the unique identifier for the user within the App.
     * It must be ensured that the same User ID is not repeated within the same App. Duplicate User IDs will be treated as the same user.
     */
    public String id;
    /**
     * User name, with a maximum length of 128 bytes. It is used to display the user's name in push notifications.
     * The updated user name will take effect within 5 minutes after refreshing. (Optional, refresh if provided, ignore if not provided)
     */
    public String name;
    /**
     * portraitUri: User avatar URI, with a maximum length of 1024 bytes.
     * It is used to display the user's avatar in push notifications. (Optional, refresh if provided, ignore if not provided)
     */
    public String portraitUri;

    public User(String id, String name,String portraitUri) {
        this.id = id;
        this.name = name;
        this.portraitUri = portraitUri;
    }
    /**
     * Set the user ID.
     *
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the user ID.
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortraitUri() {
        return this.portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }
}
