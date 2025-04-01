package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ClienteRepositorio;

@Service
public class ReporteServicio {
    private final TemplateEngine templateEngine;
    private final ClienteRepositorio clienteRepositorio;

    public ReporteServicio(TemplateEngine templateEngine, ClienteRepositorio clienteRepositorio){
        this.templateEngine = templateEngine;
        this.clienteRepositorio = clienteRepositorio;
    }

    public byte[] generarReporteClientesPdf() throws IOException{
        java.util.List<Cliente> clientes = clienteRepositorio.findAll();

        Context context = new Context();
        context.setVariable("clientes", clientes);

        String htmlContent = templateEngine.process("reporteClientes", context);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(htmlContent, "");
        builder.toStream(outputStream);
        builder.run();

        return outputStream.toByteArray();
    }
}
