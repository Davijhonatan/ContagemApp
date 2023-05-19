package contagem.truco.jhonatan.davi.app.contagemapp.contagem.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.renderscript.Sampler;

public interface LoginPresenter {

    Context getContext();

    Activity getActivity();

    void toast(String message, int status);


}
