package com.andlopper.customer.api.controller.v1;

import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import com.andlopper.customer.api.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/download")
public class DownloadController {

    private final CustomerService customerService;

    public DownloadController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public void downloadExcel(HttpServletResponse response) throws IOException {
        List<CustomerResponse> customers = customerService.getAllCustomers();

        // Cria um novo arquivo Excel usando o Apache POI
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clientes");

        // Cria o cabeçalho da planilha
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nome");
        headerRow.createCell(2).setCellValue("Telefone");
        headerRow.createCell(3).setCellValue("E-mail");

        // Preenche os dados dos clientes
        int rowNum = 1;
        for (CustomerResponse customer : customers) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(customer.getId());
            row.createCell(1).setCellValue(customer.getName());
            row.createCell(2).setCellValue(customer.getPhone());
            row.createCell(3).setCellValue(customer.getEmail());
        }

        // Escreve o conteúdo do arquivo Excel em um fluxo de bytes
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        bos.close();

        // Configura os cabeçalhos da resposta HTTP para download
        response.setHeader("Content-Disposition", "attachment; filename=clientes.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Escreve os bytes do arquivo diretamente na resposta
        response.getOutputStream().write(bos.toByteArray());
        response.flushBuffer();
    }
}
