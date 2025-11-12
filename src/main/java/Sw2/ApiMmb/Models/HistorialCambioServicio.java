package Sw2.ApiMmb.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "HistorialServicio")
public class HistorialCambioServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHistorial")
    private Integer idHistorial;

    @Column(name = "IdServicio")
    private Integer idServicio;

    @Column(name = "TipoCambio")
    private String tipoCambio;

    @Column(name = "CampoModificado")
    private String campoModificado;

    @Column(name = "ValorAnterior")
    private String valorAnterior;

    @Column(name = "ValorNuevo")
    private String valorNuevo;

    @Column(name = "Usuario")
    private String usuario;

    @Column(name = "FechaCambio")
    private String fechaCambio;

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getCampoModificado() {
        return campoModificado;
    }

    public void setCampoModificado(String campoModificado) {
        this.campoModificado = campoModificado;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

}
