package emerio.danamon.base;

/**
 * Created by Emerio on 8/28/2018.
 */

public interface IBaseView {

    void onAuthError();

    void showHttpError();

    void showServerError();

    void networkUnavailableError();

}
