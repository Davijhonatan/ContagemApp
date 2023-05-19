package contagem.truco.jhonatan.davi.app.contagemapp.contagem.login.view;

public class ResumoPontos {

    private Integer ponto;
    private String time;
    private String hora;
    private Boolean aBoolean;

    public ResumoPontos(){

    }

    ResumoPontos(Integer ponto, String time, Boolean aBoolean){
        this.ponto    = ponto;
        this.time     = time;
        this.aBoolean = aBoolean;

    }

    public Integer getPonto() {
        return ponto;
    }

    public void setPonto(Integer ponto) {
        this.ponto = ponto;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getBoolean() {
        return aBoolean;
    }

    public void setBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
