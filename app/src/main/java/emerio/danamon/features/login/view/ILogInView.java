package emerio.danamon.features.login.view;

import emerio.danamon.base.IBaseView;

/**
 * Created by Emerio on 8/28/2018.
 */

public interface ILogInView extends IBaseView {

    void onLoginSuccessful(UserData userData);

    void showEmailError(int errorMessage);

    void showPasswordError(int errorMessage);

    void onForgotPasswordOTPSuccess(OTPResponse otpResponse);

}
