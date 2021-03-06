package co.edu.poligran.serviciosalestudiante.service.dto;


import org.joda.time.LocalDateTime;

import java.time.DayOfWeek;
import java.util.Date;

public class BloquePlantillaDTO extends BaseDTO {
    private static final long serialVersionUID = -5815240480060535900L;
    private DayOfWeek dia;

    private TipoEspacioDTO tipoEspacio;

    private Date horaInicio;

    private Date horaFin;

    public BloqueDTO obtenerBloqueDTO(EspacioDTO espacioDTO, Date dia) {
        BloqueDTO bloqueDTO = new BloqueDTO();
        bloqueDTO.setEspacio(espacioDTO);
        bloqueDTO.setDia(dia);

        LocalDateTime inicio = new LocalDateTime(horaInicio);
        long inicioMillis = inicio.getMillisOfDay();
        bloqueDTO.setTiempoInicio(new Date(dia.getTime() + inicioMillis));

        LocalDateTime fin = new LocalDateTime(horaFin);
        long finMillis = fin.getMillisOfDay();
        bloqueDTO.setTiempoFin(new Date(dia.getTime() + finMillis));

        return bloqueDTO;
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    public TipoEspacioDTO getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacioDTO tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public Date getHoraInicio() {
        return (this.horaInicio != null ? (Date) horaInicio.clone() : null);
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = (horaInicio != null ? (Date) horaInicio.clone() : null);
    }

    public Date getHoraFin() {
        return (this.horaFin != null ? (Date) this.horaFin.clone() : null);
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = (horaFin != null ? (Date) horaFin.clone() : null);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
