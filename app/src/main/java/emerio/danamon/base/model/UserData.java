package emerio.danamon.base.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Emerio on 8/28/2018.
 */

public class UserData extends RealmObject {

    @PrimaryKey
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("tokens")
    @Expose
    private AccessToken tokens;

    @SerializedName("userInfo")
    @Expose
    private UserInfo userInfo;
}
