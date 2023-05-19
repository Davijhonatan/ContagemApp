package contagem.truco.jhonatan.davi.app.contagemapp.contagem.login.presenter;


import android.app.Activity;
import android.content.Context;

import com.sdsmdg.tastytoast.TastyToast;

public class LoginPresenterImpl implements LoginPresenter{

    private Context context;
    private Activity activity;

    public LoginPresenterImpl (Context context, Activity activity){
        this.context  = context;
        this.activity = activity;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    @Override
    public void toast(String message, int status) {
        TastyToast.makeText(getContext(), message, TastyToast.LENGTH_LONG, status).show();
    }

}
