package contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sdsmdg.tastytoast.TastyToast;

import androidx.recyclerview.widget.RecyclerView;

public class ContagemPresenterImpl implements ContagemPresenter {

    private Activity activity;
    private Context context;
    private RecyclerView recyclerView;

    private Color color;


    public ContagemPresenterImpl(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    @Override
    public Context getContext() {
        return context;
    }


    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void view(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

    }

    @Override
    public void dialogSinais(Object o) {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());

        builder.title("Sinais das manilhas");
        builder.content(o.toString());
        builder.positiveText("Sair");
        builder.show();

    }

    @Override
    public void toast(String message, int status) {
       TastyToast.makeText(getContext(), message, TastyToast.LENGTH_LONG, status).show();
    }
}