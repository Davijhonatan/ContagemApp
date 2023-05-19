package contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.renderscript.Sampler;

import androidx.annotation.ColorRes;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

public interface ContagemPresenter {

    Activity getActivity();

    Context getContext();

    Color getColor();

    void toast(String message, int success);

    void view(RecyclerView recyclerView);

    void dialogSinais(Object o);
}
