/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sw2.Modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Pipea
 */
public class HistorialCambioServicio {
    
    private Integer idHistorial;
    private Integer idServicio;
    private String tipoCambio;
    private String campoModificado;
    private String valorAnterior;
    private String valorNuevo;
    private String usuario;
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
