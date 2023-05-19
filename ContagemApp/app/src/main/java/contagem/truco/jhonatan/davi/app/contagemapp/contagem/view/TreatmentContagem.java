package contagem.truco.jhonatan.davi.app.contagemapp.contagem.view;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.presenter.ContagemPresenter;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.presenter.ContagemPresenterImpl;


/**
 * Created by User Davi on 28/10/2020.
 *
 * @Enum
 * @Interface
 * @Class
 */

/*public class TreatmentContagem implements Parcelable {

    @Expose
    private Boolean isReturn;

    @Expose
    private String title;

    @Expose
    private Integer type;

    @Expose
    private String url;

    @Expose
    private String  mensagem;

    @Expose
    private Object  object;

    public Response response;

    private ContagemPresenter presenter;

    *//*public TreatmentContagem(){
        this.presenter = new ContagemPresenterImpl();
    }

    public TreatmentContagem(Boolean isReturn, String  mensagem) {
        this.presenter = new ContagemPresenterImpl();
        this.isReturn  = isReturn;
        this.mensagem  = mensagem;
    }*//*



    public Boolean getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Boolean aReturn) {
        isReturn = aReturn;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = titulo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean isJSONValid(String json){
        return presenter.isJSONValid(json);
    }

    *//*public UtilPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(UtilPresenter presenter) {
        this.presenter = presenter;
    }

    public UtilPresenter setNewPresenter(Context context) {
        return presenter = new UtilPresenterImpl(context);
    }*//*

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mensagem);
    }

    protected TreatmentContagem(Parcel in) {
        mensagem = in.readString();
    }

    public static final Creator<TreatmentContagem> CREATOR = new Creator<TreatmentContagem>() {
        @Override
        public TreatmentContagem createFromParcel(Parcel in) {
            return new TreatmentContagem(in);
        }

        @Override
        public TreatmentContagem[] newArray(int size) {
            return new TreatmentContagem[size];
        }
    };

    public String isEmptyMsg(Object object) {

        String msgException = "[COD: ";

        if (object != null) {

            if(object instanceof DaoException){

                DaoException daoException = (DaoException) object;

                if(daoException.getMessage() != null){

                    if (daoException.getMessage().length() >= 160) {
                        msgException = daoException.getMessage().substring(0, 160);
                    } else {
                        msgException = daoException.getMessage();
                    }

                }

            } else if (object instanceof Exception) {

                Exception exception = (Exception) object;

                if (exception.getMessage() != null) {

                    if (exception.getMessage().length() >= 160) {
                        msgException = exception.getMessage().substring(0, 160);
                    } else {
                        msgException = exception.getMessage();
                    }

                } else if (exception.getLocalizedMessage() != null) {

                    if (exception.getLocalizedMessage().length() >= 160) {
                        msgException = exception.getLocalizedMessage().substring(0, 160);
                    } else {
                        msgException = exception.getLocalizedMessage();
                    }
                } else if (exception.getCause() != null) {

                    if (exception.getCause().getMessage() != null) {

                        if (exception.getCause().getMessage().length() >= 160) {
                            msgException = exception.getLocalizedMessage().substring(0, 160);
                        } else {
                            msgException = exception.getLocalizedMessage();
                        }

                    }
                }
            } else if(object instanceof String){

                String mensagem = (String) object;

                if (mensagem.length() >= 160) {
                    msgException = mensagem.substring(0, 160) + "] ";
                } else {
                    msgException = mensagem;
                }

            }
        }

        return msgException;
    }



    @DebugLog
    public String msgBodyTreatment(Object object) {

        String responseBody = "[COD: ";

        if (object instanceof Response) {

            Response response = (Response) object;

            responseBody += response.code() + "] ";
            responseBody += "Algo de errado aconteceu, tente novamente. Caso persista entre em contato com Administrador.";

        }else if (object instanceof String){

            String mensagem = (String) object;

            if (mensagem.length() >= 100) {
                responseBody = mensagem.substring(0, 100) + "] ";
            } else {
                responseBody = mensagem;
            }

        }


        return responseBody;
    }

    @DebugLog
    public String exceptionTreatment(Object object) {

        String responseBody;

        if (object instanceof UnknownHostException) {

            UnknownHostException unknownHostException = (UnknownHostException) object;

            responseBody = "Ops, Você não está conectado à internet, Verifique sua conexão e tente novamente.";

        } else if (object instanceof SocketTimeoutException) {

            SocketTimeoutException socketTimeoutException = (SocketTimeoutException) object;

            responseBody = "Ops, Verifique sua conexão e tente novamente." + " [Time out] ";

        } else if (object instanceof Exception) {

            Exception exception = (Exception) object;

            responseBody = "Algo de errado aconteceu, Tente novamente. Caso persista entre em contato com Administrador.";

        } else {

            responseBody = "Algo de errado aconteceu, Tente novamente. Caso persista entre em contato com Administrador.";
        }

        return responseBody;
    }


    public String getRegistrationOrDataPackage() {

        if (isTagHtml(getMensagem())) {
            setMensagem("Algo de errado aconteceu, Verifique sua conexão de rede ou pacote de dados!");
        }

        return getMensagem();
    }

    private boolean isTagHtml(String message) {

        Pattern pattern = Pattern.compile("<(\"[^\"]*\"|'[^']*'|[^'\">])*>");

        return pattern.matcher(message).find();
    }

    public void toast(Context _context, String mensagem, int error) {
        TastyToast.makeText(_context, mensagem, TastyToast.LENGTH_LONG, error).show();
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}*/
