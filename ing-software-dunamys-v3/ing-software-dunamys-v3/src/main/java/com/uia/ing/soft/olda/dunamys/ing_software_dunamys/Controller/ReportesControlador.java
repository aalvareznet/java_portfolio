package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ReporteServicio;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReportesControlador {
    private final ReporteServicio servicio;

    public ReportesControlador(ReporteServicio servicio){
        this.servicio = servicio;
    }

    @GetMapping("/clientes/pdf")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<byte[]> descargaReporteDeClientes(){
        try {
            byte[] pdfBytes = servicio.generarReporteClientesPdf();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline")
                    .filename("reporte_clientes.pdf")
                    .build());
            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
