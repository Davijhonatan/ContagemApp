package contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.view.adapter;

import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import contagem.truco.jhonatan.davi.app.contagemapp.R;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.presenter.ContagemPresenter;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.login.view.ResumoPontos;

public class RecyclerViewAdapterContagem extends RecyclerView.Adapter<RecyclerViewAdapterContagem.ViewHolder>{

    private List<ResumoPontos> resumoPontos;

    private ContagemPresenter presenter;

    private OnItemSelection onItemSelected;

    public RecyclerViewAdapterContagem(ContagemPresenter presenter, List <ResumoPontos> resumoPontos) {
        this.presenter = presenter;
        this.resumoPontos = resumoPontos;

    }

    public interface OnItemSelection{
        void onSelection(Object o);

    }

    public void setOnItemSelection(OnItemSelection onItemSelected){
        this.onItemSelected = onItemSelected;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_adapter_contagem, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        ResumoPontos resumo = resumoPontos.get(position);

        holder._resumo_txt.setText("Time"+ " " + resumo.getTime() + " " + "marcou" + " " + resumo.getPonto() + " " +  "ponto nessa rodada." );

        if (resumo.getBoolean()){
            holder._resumo_txt.setTextColor(presenter.getContext().getColor(R.color.button_app));
        }else{
            holder._resumo_txt.setTextColor(presenter.getContext().getColor(R.color.button_app_txt));

        }

        holder._resumo_hora.setText(resumo.getHora());
        holder._resumo_hora.setTextColor(presenter.getContext().getColor(R.color.button_app_gree));

        Typeface typeface = Typeface.createFromAsset(presenter.getContext().getAssets(), "fonts/njnaruto.ttf");
        holder._resumo_txt.setTypeface(typeface);
        holder._resumo_hora.setTypeface(typeface);

    }

    @Override
    public int getItemCount() {
        return resumoPontos.size();
    }

    public void setItemList(List <ResumoPontos> resumoPontos) {
        this.resumoPontos = resumoPontos;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView _resumo_txt;
        private AppCompatTextView _resumo_hora;

        ViewHolder(View itemView) {
            super(itemView);

            _resumo_txt  = itemView.findViewById(R.id.item_recycler_view_adapter_resumo_txt);
            _resumo_hora  = itemView.findViewById(R.id.item_recycler_view_adapter_resumo_hora);


        }
    }
}