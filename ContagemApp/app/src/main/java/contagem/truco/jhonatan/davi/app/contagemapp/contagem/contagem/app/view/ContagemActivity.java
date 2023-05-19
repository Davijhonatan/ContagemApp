package contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.view;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sdsmdg.tastytoast.TastyToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import contagem.truco.jhonatan.davi.app.contagemapp.R;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.presenter.ContagemPresenter;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.presenter.ContagemPresenterImpl;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.view.adapter.RecyclerViewAdapterContagem;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.login.view.ResumoPontos;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.view.InitMVP;

@SuppressWarnings({"unchecked", "all"})
public class ContagemActivity extends AppCompatActivity implements InitMVP, View.OnClickListener {

    private ContagemPresenter presenter;

    private RecyclerView _recycler_view;

    private AppCompatImageView _botao_reset;

    private AppCompatImageView _botao_mais_time_one;
    private AppCompatImageView _botao_mais_time_two;

    private AppCompatImageView _botao_menos_time_one;
    private AppCompatImageView _botao_menos_time_two;

    private AppCompatTextView _placar_time_one;
    private AppCompatTextView _placar_time_two;

    private AppCompatTextView _placar_time_one_final;
    private AppCompatTextView _placar_time_two_final;

    private AppCompatTextView _placar_time_one_get;
    private AppCompatTextView _placar_time_two_get;

    private AppCompatTextView _nome_time_one_txt;
    private AppCompatTextView _nome_time_two_txt;

    private AppCompatTextView _placar_one_txt;
    private AppCompatTextView _placar_two_txt;

    private AppCompatImageView _botao_edit_nome_placar;
    private AppCompatTextView  _placar_txt_view;
    private AppCompatTextView  _tento_txt_view;

    private LinearLayout      _button_historico;
    private AppCompatTextView _button_historico_txt;

    private List<ResumoPontos> resumeList = new ArrayList<>();

    private int count = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contagem_two_lyout);

        init();
        load();
        task();
    }

    @Override
    public void init() {

        _recycler_view = findViewById(R.id.item_recycler_view_main);

        _botao_reset = findViewById(R.id.item_view_botao_reset);
        _botao_reset.setOnClickListener(this);

        _botao_mais_time_one = findViewById(R.id.item_view_botao_mais_time_one);
        _botao_mais_time_two = findViewById(R.id.item_view_botao_mais_time_two);

        _botao_menos_time_one = findViewById(R.id.item_view_botao_menos_time_one);
        _botao_menos_time_two = findViewById(R.id.item_view_botao_menos_time_two);

        _placar_time_one = findViewById(R.id.item_view_placar_time_one);
        _placar_time_one.setText("0");

        _placar_time_two = findViewById(R.id.item_view_placar_time_two);
        _placar_time_two.setText("0");

        _placar_time_one_get = findViewById(R.id.item_layout_contagem_nome_time_one);
        _placar_time_one_final = findViewById(R.id.item_layout_contagem_placar_time_one);
        _placar_time_one_final.setText("0");

        _placar_time_two_get = findViewById(R.id.item_layout_contagem_nome_time_two);
        _placar_time_two_final = findViewById(R.id.item_layout_contagem_placar_time_two);
        _placar_time_two_final.setText("0");

        Intent intentOne = getIntent();
        String one = (String) intentOne.getSerializableExtra("one");
        String two = (String) intentOne.getSerializableExtra("two");

        _placar_time_one_get.setText(one);
        _placar_time_two_get.setText(two);

        _botao_mais_time_one.setColorFilter(getResources().getColor(R.color.navajo_white_escuro));
        _botao_menos_time_one.setColorFilter(getResources().getColor(R.color.navajo_white_escuro));

        _botao_mais_time_two.setColorFilter(getResources().getColor(R.color.navajo_white_escuro));
        _botao_menos_time_two.setColorFilter(getResources().getColor(R.color.navajo_white_escuro));

        _botao_reset.setColorFilter(getResources().getColor(R.color.navajo_white_medio));

        _nome_time_one_txt = findViewById(R.id.item_layout_contagem_nome_time_one_txt);
        _nome_time_two_txt = findViewById(R.id.item_layout_contagem_nome_time_one_two);

        _placar_one_txt = findViewById(R.id.item_contagem_two_layout_placar_one_txt);
        _placar_two_txt = findViewById(R.id.item_contagem_two_layout_placar_two_txt);

        _tento_txt_view = findViewById(R.id.item_layout_contagem_tento_txt_view);
        _tento_txt_view.setText(R.string.tentos);

        _placar_txt_view = findViewById(R.id.item_layout_contagem_placar_txt_view);
        _placar_txt_view.setText(R.string.placar);

        _button_historico      = findViewById(R.id.item_view_contagem_button_historico);
        _button_historico_txt  = findViewById(R.id.item_view_contagem_button_historico_txt);
        _button_historico_txt.setText("Histórico da partida");

        _button_historico_txt.setOnClickListener(this);

        _botao_mais_time_one.setOnClickListener(this);
        _botao_menos_time_one.setOnClickListener(this);

        _botao_menos_time_two.setOnClickListener(this);
        _botao_mais_time_two.setOnClickListener(this);

        _botao_edit_nome_placar = findViewById(R.id.item_view_botao_reset_placar);
        _botao_edit_nome_placar.setColorFilter(getResources().getColor(R.color.navajo_white_claro));
        _botao_edit_nome_placar.setOnClickListener(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/vintage_one.ttf");
        _placar_time_one.setTypeface(typeface);
        _placar_time_two.setTypeface(typeface);

        Typeface typefaceTwo = Typeface.createFromAsset(getAssets(), "fonts/njnaruto.ttf");
        _placar_txt_view.setTypeface(typefaceTwo);
        _nome_time_one_txt.setTypeface(typefaceTwo);
        _placar_time_one_get.setTypeface(typefaceTwo);
        _placar_one_txt.setTypeface(typefaceTwo);
        _nome_time_two_txt.setTypeface(typefaceTwo);
        _placar_time_two_get.setTypeface(typefaceTwo);
        _placar_two_txt.setTypeface(typefaceTwo);
        _placar_time_one_final.setTypeface(typefaceTwo);
        _placar_time_two_final.setTypeface(typefaceTwo);
        _tento_txt_view.setTypeface(typefaceTwo);
        _button_historico_txt.setTypeface(typefaceTwo);

    }

    @Override
    public void load() {

        presenter = new ContagemPresenterImpl(getContext(), getActivity());
        presenter.view(_recycler_view);

    }

    @Override
    public void task() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public Context getContext() {
        return this;
    }


    public Activity getActivity() {
        return this;
    }

    @Override
    public void finish() {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());

        builder.customView(R.layout.dialog_defult_yes_or_no, false);

        MaterialDialog _dialog = builder.build();

        AppCompatTextView _titulo = (AppCompatTextView) _dialog.findViewById(R.id.item_view_dialog_yes_or_no_titulo);
        _titulo.setText("  Voltar a tela incial ");
        AppCompatTextView _descricao = (AppCompatTextView) _dialog.findViewById(R.id.item_view_dialog_yes_or_no_descricao);
        _descricao.setText(" Deseja voltar a tela incial? o jogo iniciado sera perdido... ");

        AppCompatImageView _icon = (AppCompatImageView) _dialog.findViewById(R.id.item_view_dialog_yes_or_no_icon);

        if (_dialog.getWindow() != null){
            _dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        AppCompatButton _yes = (AppCompatButton) _dialog.findViewById(R.id.item_view_dialog_resumo_pontos_btn_yes);
        _yes.setTag(_dialog);
        _yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialDialog dialog = (MaterialDialog) v.getTag();

                overridePendingTransition(R.anim.direita, R.anim.fade_in);
                dialog.dismiss();
                ContagemActivity.super.finish();

            }
        });

        AppCompatButton _no = (AppCompatButton) _dialog.findViewById(R.id.item_view_dialog_resumo_pontos_btn_no);
        _no.setTag(_dialog);
        _no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialDialog dialog = (MaterialDialog) v.getTag();

                dialog.dismiss();
            }
        });

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/vintage_one.ttf");
        _titulo.setTypeface(typeface);
        _descricao.setTypeface(typeface);
        _yes.setTypeface(typeface);
        _no.setTypeface(typeface);

        _dialog.show();

     }

    @SuppressWarnings({"unchecked", "all"})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.item_view_botao_mais_time_one:

                Integer quantTimeOne = Integer.valueOf(_placar_time_one.getText().toString());

                if (quantTimeOne >= 0 && quantTimeOne <= 11) {

                    Integer valor = quantTimeOne + 1;
                    String subtracao = String.valueOf(valor);

                    _placar_time_one.setText(subtracao);

                } else {

                    Integer valor = quantTimeOne - 0;
                    String subtracao = String.valueOf(valor);

                    _placar_time_one.setText(subtracao);
                }

                Integer placarFinalTimeOne = Integer.valueOf(_placar_time_one_final.getText().toString());

                if (quantTimeOne == 11) {
                    Integer tento = placarFinalTimeOne + 1;
                    String ponto = String.valueOf(tento);
                    _placar_time_one_final.setText(ponto);
                    _placar_time_one.setText("0");
                    _placar_time_two.setText("0");

                    presenter.toast(" Prabéns para a equipe 1, Vocês venceram a partida!!! ", TastyToast.SUCCESS);

                }

                String horaOne = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                int valueOne = count++;

                ResumoPontos resOne = new ResumoPontos();
                resOne.setPonto(1);
                resOne.setTime(_placar_time_one_get.getText().toString());
                resOne.setHora(horaOne);
                resOne.setBoolean(true);
                resumeList.add(resOne);

                break;

            case R.id.item_view_botao_mais_time_two:

                Integer quantOne = Integer.valueOf(_placar_time_two.getText().toString());

                if (quantOne >= 0 && quantOne <= 11) {

                    Integer valor = quantOne + 1;
                    String subtracao = String.valueOf(valor);

                    _placar_time_two.setText(subtracao);

                } else {

                    Integer valor = quantOne - 0;
                    String subtracao = String.valueOf(valor);

                    _placar_time_two.setText(subtracao);
                }

                Integer placarFinalTimeTwo = Integer.valueOf(_placar_time_two_final.getText().toString());

                if (quantOne == 11) {
                    Integer tento = placarFinalTimeTwo + 1;
                    String ponto = String.valueOf(tento);
                    _placar_time_two_final.setText(ponto);
                    _placar_time_one.setText("0");
                    _placar_time_two.setText("0");

                    presenter.toast(" Prabéns para a equipe 2, Vocês venceram a partida!!! ", TastyToast.SUCCESS);

                }

                String horaTwo = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                int valueTwo = count++;

                ResumoPontos resTwo = new ResumoPontos();
                resTwo.setPonto(1);
                resTwo.setTime(_placar_time_two_get.getText().toString());
                resTwo.setHora(horaTwo);
                resTwo.setBoolean(false);
                resumeList.add(resTwo);

                break;

            case R.id.item_view_botao_menos_time_one:

                Integer quantTimeTwo = Integer.valueOf(_placar_time_one.getText().toString());

                if (quantTimeTwo >= 1 && quantTimeTwo <= 12) {

                    Integer valor = quantTimeTwo - 1;
                    String sub = String.valueOf(valor);

                    _placar_time_one.setText(sub);

                } else {

                    Integer valor = quantTimeTwo;
                    String sub = String.valueOf(valor);

                    _placar_time_one.setText(sub);
                }

                break;

            case R.id.item_view_botao_menos_time_two:

                Integer quantTwo = Integer.valueOf(_placar_time_two.getText().toString());

                if (quantTwo >= 1 && quantTwo <= 12) {

                    Integer valor = quantTwo - 1;
                    String sub = String.valueOf(valor);

                    _placar_time_two.setText(sub);

                } else {

                    Integer valor = quantTwo;
                    String sub = String.valueOf(valor);

                    _placar_time_two.setText(sub);
                }

                break;

            case R.id.item_view_time_one_botao_acres_three_ponto:

                Integer quant = Integer.valueOf(_placar_time_one.getText().toString());

                if (quant >= 0 && quant <= 9) {

                    Integer valor = quant + 3;
                    String soma = String.valueOf(valor);

                    _placar_time_one.setText(soma.toString());
                } else {
                    Integer valor = quant + 0;
                    String soma = String.valueOf(valor);

                    _placar_time_one.setText(soma.toString());
                }

                break;

            case R.id.item_view_time_two_botao_acres_three_ponto:

                Integer quantidade = Integer.valueOf(_placar_time_two.getText().toString());

                if (quantidade >= 0 && quantidade <= 9) {

                    Integer soma = quantidade + 3;
                    String result = String.valueOf(soma);

                    _placar_time_two.setText(result.toString());

                } else {

                    Integer soma = quantidade + 0;
                    String result = String.valueOf(soma);

                    _placar_time_two.setText(result.toString());

                }

                break;

            case R.id.item_view_botao_reset:

                Integer placarOne = Integer.valueOf(_placar_time_one.getText().toString());
                Integer placarTwo = Integer.valueOf(_placar_time_two.getText().toString());

                if (placarOne.equals(0) && placarTwo.equals(0)) {
                    presenter.toast("O placar ainda esta zerado, comecem o jogo!", TastyToast.INFO);
                } else if (placarOne > 0 && placarTwo.equals(0)) {
                    resertPlacar();
                } else if (placarTwo > 0 && placarOne.equals(0)) {
                    resertPlacar();
                } else if (placarOne > 0 && placarTwo > 0) {
                    resertPlacar();
                }

                break;

            case R.id.item_view_contagem_button_historico_txt:

                Integer placarTimeOne = Integer.valueOf(_placar_time_one.getText().toString());

                Integer placarTimeTwo = Integer.valueOf(_placar_time_two.getText().toString());

                if (placarTimeOne == 0 && placarTimeTwo == 0){
                    resumeList.clear();

                }

                if (!resumeList.isEmpty()) {

                    MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());

                    builder.customView(R.layout.dialog_custom_view_resumo_pontos, false);

                    MaterialDialog _dialog = builder.build();

                    AppCompatTextView _titulo = (AppCompatTextView) _dialog.findViewById(R.id.item_view_dialog_txt);
                    RecyclerView _adpter = (RecyclerView) _dialog.findViewById(R.id.item_view_dialog_recycler_view);
                    AppCompatButton _bnt_sair = (AppCompatButton) _dialog.findViewById(R.id.item_view_dialog_resumo_pontos_btn_sair);

                    Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/njnaruto.ttf");
                    _titulo.setTypeface(typeface);
                    _bnt_sair.setTypeface(typeface);

                    if (_dialog.getWindow() != null){
                        _dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }

                    _titulo.setText("Resumo de Pontuação");

                    _bnt_sair.setTag(_dialog);
                    _bnt_sair.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MaterialDialog dialog = (MaterialDialog) v.getTag();
                            dialog.dismiss();
                        }
                    });

                    RecyclerViewAdapterContagem recyclerViewAdapterContagem = new RecyclerViewAdapterContagem(presenter, resumeList);
                    _adpter.setHasFixedSize(true);
                    _adpter.setLayoutManager(new LinearLayoutManager(getContext()));
                    _adpter.setAdapter(recyclerViewAdapterContagem);

                    _dialog.show();

                }else {
                    presenter.toast("Placar zerado, comecem os jogos.", TastyToast.INFO);
                }

                break;


            case R.id.item_view_botao_reset_placar:

                MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());

                builder.customView(R.layout.dialog_defult_yes_or_no, false);

                MaterialDialog _dialog = builder.build();

                AppCompatTextView _titulo = (AppCompatTextView) _dialog.findViewById(R.id.item_view_dialog_yes_or_no_titulo);
                _titulo.setText(" Deseja resetar o jogo? ");
                AppCompatTextView _descricao = (AppCompatTextView) _dialog.findViewById(R.id.item_view_dialog_yes_or_no_descricao);
                _descricao.setText(" Para resetar o jogo e preciso voltar ao menu incial, clique no botao (SIM) para começar um novo jogo. ");

                if (_dialog.getWindow() != null){
                    _dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }

                AppCompatButton _yes = (AppCompatButton) _dialog.findViewById(R.id.item_view_dialog_resumo_pontos_btn_yes);
                _yes.setTag(_dialog);
                _yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MaterialDialog dialog = (MaterialDialog) v.getTag();

                        overridePendingTransition(R.anim.direita, R.anim.fade_in);
                        dialog.dismiss();
                        ContagemActivity.super.finish();

                    }
                });

                AppCompatButton _no = (AppCompatButton) _dialog.findViewById(R.id.item_view_dialog_resumo_pontos_btn_no);
                _no.setTag(_dialog);
                _no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MaterialDialog dialog = (MaterialDialog) v.getTag();

                        dialog.dismiss();
                    }
                });

                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/vintage_one.ttf");
                _titulo.setTypeface(typeface);
                _descricao.setTypeface(typeface);
                _yes.setTypeface(typeface);
                _no.setTypeface(typeface);

                _dialog.show();

                break;

        }

    }

    @SuppressWarnings({"unchecked", "all"})
    public void resertPlacar() {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());

        builder.customView(R.layout.dialog_defult_yes_or_no, false);

        MaterialDialog _dialog = builder.build();

        AppCompatTextView _titulo = (AppCompatTextView) _dialog.findViewById(R.id.item_view_dialog_yes_or_no_titulo);
        _titulo.setText(" Resetar o placar ");
        AppCompatTextView _descricao = (AppCompatTextView) _dialog.findViewById(R.id.item_view_dialog_yes_or_no_descricao);
        _descricao.setText(" Deseja resetar o placar para começar um novo jogo? ");

        if (_dialog.getWindow() != null){
            _dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        AppCompatButton _yes = (AppCompatButton) _dialog.findViewById(R.id.item_view_dialog_resumo_pontos_btn_yes);
        _yes.setTag(_dialog);
        _yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialDialog dialog = (MaterialDialog) v.getTag();

                Integer placarOne = Integer.valueOf(_placar_time_one.getText().toString());
                Integer placarTwo = Integer.valueOf(_placar_time_two.getText().toString());

                _placar_time_one.setText("0");
                _placar_time_two.setText("0");

                dialog.dismiss();

            }
        });

        AppCompatButton _no = (AppCompatButton) _dialog.findViewById(R.id.item_view_dialog_resumo_pontos_btn_no);
        _no.setTag(_dialog);
        _no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialDialog dialog = (MaterialDialog) v.getTag();

                dialog.dismiss();
            }
        });

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/vintage_one.ttf");
        _titulo.setTypeface(typeface);
        _descricao.setTypeface(typeface);
        _yes.setTypeface(typeface);
        _no.setTypeface(typeface);

        _dialog.show();

    }

}
