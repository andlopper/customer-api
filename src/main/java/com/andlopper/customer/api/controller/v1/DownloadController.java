package com.andlopper.customer.api.controller.v1;

import com.andlopper.customer.api.controller.v1.response.CustomerResponse;
import com.andlopper.customer.api.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
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
        sheet.setColumnWidth(0, 1000);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 10000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        HSSFFont font = ((HSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        headerStyle.setFont(font);

        // Cria o cabeçalho da planilha
        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Id");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Nome");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Telefone");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Email");
        headerCell.setCellStyle(headerStyle);

        CellStyle rowStyle = workbook.createCellStyle();
        rowStyle.setAlignment(HorizontalAlignment.CENTER);
        rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        rowStyle.setBorderTop(BorderStyle.THIN);
        rowStyle.setBorderBottom(BorderStyle.THIN);
        rowStyle.setBorderLeft(BorderStyle.THIN);
        rowStyle.setBorderRight(BorderStyle.THIN);

        // Preenche os dados dos clientes
        int rowNum = 1;
        for (CustomerResponse customer : customers) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(customer.getId());
            row.createCell(1).setCellValue(customer.getName());
            row.createCell(2).setCellValue(customer.getPhone());
            row.createCell(3).setCellValue(customer.getEmail());

            for (Cell cell : row) {
                cell.setCellStyle(rowStyle);
            }
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
