package com.topscore.order.dispatcher.excel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV文件导出工具类
 * <p>
 * Created on 2014-08-07
 *
 * @author
 * @reviewer
 */
public class CSVUtils {

    /**
     * CSV文件生成方法
     *
     * @param head       1
     * @param dataList   2
     * @param outPutPath 3
     * @param filename   4
     * @return 5
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList,
                                     String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvWtriter != null) {
                    csvWtriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     *
     * @param row       1
     * @param csvWriter 2
     * @throws IOException 3
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            String rowStr = "\"" + data + "\",";
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

    public static void main(String[] args) {
//        KissCat kc1 = new KissCat(1, "好");
//        KissCat kc2 = new KissCat(2, "运");
//        KissCat kc3 = new KissCat(3, "来");
//
//        List<KissCat> kissCats = new ArrayList<>();
//        kissCats.add(kc1);
//        kissCats.add(kc2);
//        kissCats.add(kc3);

        List<Object> headers = new ArrayList<>();
        headers.add("编号");
        headers.add("名称");

        List<List<Object>> data = new ArrayList<>();
        List<Object> row1 = new ArrayList<>();
        row1.add("1");
        row1.add("KC");
        data.add(row1);

        List<Object> row2 = new ArrayList<>();
        row2.add("2");
        row2.add("KK");
        data.add(row2);

        createCSVFile(headers, data, "D:\\", "导出");
    }
}