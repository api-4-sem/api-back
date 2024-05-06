package br.gov.sp.fatec.apipixel.core.service;

import br.gov.sp.fatec.apipixel.core.domain.dto.DadosRelatorio;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class RelatorioService {

    //Relatorio já vai receber como parametro os dados necessários para popular o relatorio
    // necessario criar um DTO para armazenar esses dados
    // papel dele vai ser só fazer toda a lógica para criar e retornar o InputStream
    // já vai coverter de byte para InputStream, pois é o que UC esta esperando

    public static String[] HEADERS={
            "Nome",
            "Trilha",
            "Expertise",
            "Status"
    };

    public static String SHEET_NAME="data";

    public static InputStreamResource dataToExcel(List<DadosRelatorio> dadosRelatorios) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            Row row = sheet.createRow(0);

            for (int i = 0;  i < HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            int rowIndex = 1;
            for (DadosRelatorio dadosRelatorio : dadosRelatorios) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;

                dataRow.createCell(0).setCellValue(dadosRelatorio.getNomeColaborador());
                dataRow.createCell(1).setCellValue(dadosRelatorio.getNomeTrilha());
                dataRow.createCell(2).setCellValue(dadosRelatorio.getNomeExpertise());
                dataRow.createCell(3).setCellValue(dadosRelatorio.getStatus().name());
            }
            workbook.write(out);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
            InputStreamResource file = new InputStreamResource(byteArrayInputStream);
            return file;

        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Erro ao exportar o arquivo.");
            return null;
        } finally{
            workbook.close();
            out.close();
        }
    }
}
