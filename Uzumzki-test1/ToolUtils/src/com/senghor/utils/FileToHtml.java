package src.com.senghor.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

/**
 *  将文档转换成html的工具类
 * @author Senghor<br>
 * @date 2018年5月24日 下午4:53:56
 */
public class FileToHtml {
    
    /**
     * /**
     * 2003版本word转换成html
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public static void doc(String filePath, String fileName, String htmlName, String pathName) throws IOException, TransformerException, ParserConfigurationException {
        final String imagepath = filePath + pathName + "/image/word/media/";
        final String file = filePath + fileName;
        InputStream input = new FileInputStream(new File(file));
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        //设置图片存放的位置
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                File imgPath = new File(imagepath);
                if(!imgPath.exists()){//图片目录不存在则创建
                    imgPath.mkdirs();
                }
                long date = new Date().getTime();
                File file = new File(imagepath + date + ".png");
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(content);
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return  "image/word/media/" + date + ".png";
            }
        });
        
        //解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        
        File htmlFile = new File(filePath + pathName + "/" +  htmlName);
        OutputStream outStream = new FileOutputStream(htmlFile);
        
        //也可以使用字符数组流获取解析的内容
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
//        OutputStream outStream = new BufferedOutputStream(baos);

        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        
        serializer.transform(domSource, streamResult);

        outStream.close();
    }
    

//    public static void main(String[] args) throws Exception {
//        wordToHtml("E:/fileToHtml/word/记录问题2018-4-16 - 副本.docx");
//        excelToHtml("E:/fileToHtml/excel/yiyiye数据库设计.xlsx");
//        txtToHtml("E:/fileToHtml/txt/新建文本文档.txt");
//    }

    /**
     * 转换docx
     * 
     * @param filePath
     * @param fileName
     * @param htmlName
     * @throws Exception
     */
    public static void docx(String filePath, String fileName, String htmlName, String pathName) throws Exception {
        final String file = filePath + fileName;
        File f = new File(file);
        // ) 加载word文档生成 XWPFDocument对象
        InputStream in = new FileInputStream(f);
        XWPFDocument document = new XWPFDocument(in);
        filePath = filePath + pathName + "/";
        // ) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
        File imageFolderFile = new File(filePath + "image/");
        XHTMLOptions options = XHTMLOptions.create();
        options.setExtractor(new FileImageExtractor(imageFolderFile));
        options.URIResolver(new BasicURIResolver("image"));
        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);
        // ) 将 XWPFDocument转换成XHTML
        OutputStream out = new FileOutputStream(new File(filePath + htmlName));
        XHTMLConverter.getInstance().convert(document, out, options);
    }

    /**
     * word转html文件
     * 
     * @author Senghor<br>
     * @date 2018年5月25日 上午10:16:45
     */
    public static void wordToHtml(String filePath) {

        File file = new File(filePath);
        // 截取文件名称
        String name = file.getName().substring(0, file.getName().lastIndexOf("."));
        filePath = filePath.replaceAll(file.getName(), "");

        String toPath = filePath + name;

        judeDirExists(new File(toPath));

        if (file.getName().endsWith(".docx") || file.getName().endsWith(".DOCX")) {
            try {
                FileToHtml.docx(filePath, file.getName(), name + ".html", name);
            } catch (Exception e) {
                e.printStackTrace();
//                System.out.println("docx文件转html失败");
            }
        } else {
            try {
                FileToHtml.doc(filePath, file.getName(), name + ".html", name);
            } catch (Exception e) {
                e.printStackTrace();
//                System.out.println("doc文件转html失败");
            }
        }
    }

    // 判断文件夹是否存在
    public static Boolean judeDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                // 存在
                return true;
            } else {
                // 存在同名的
                return true;
            }
        } else {
            // 不存在则创建
            file.mkdir();
            return false;
        }
    }

    /**
     * excel转html
     * 
     * @author Senghor<br>
     * @date 2018年5月25日 下午2:51:31
     */
    public static void excelToHtml(String fileUrl) {
        try {
            String toPath = "";
            judeDirExists(new File(toPath));
            String paths = null;
            String str = null;
            File file = new File(fileUrl);
            paths = file + "";// excel的全路径
            // 把Excel里面读出的内容都存到字符串中去
            str = mains(paths);
            // HTML 文件的名字
            String excelName = file.getName().substring(0, file.getName().lastIndexOf("."));
            toPath = fileUrl.replaceAll(file.getName(), "") + excelName;
            judeDirExists(new File(toPath));
            // 生成 html 页面的路径
            paths = toPath + "/" + excelName + ".html";
            // 创建一个文本文件
            File f = new File(paths);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            // 把内容存到文本文件中去
            pw.append(str);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试
     * 
     * @param args
     */
    public static String mains(String path) {

        InputStream is = null;
        String htmlExcel = null;
        try {
            File sourcefile = new File(path);
            is = new FileInputStream(sourcefile);
            Workbook wb = WorkbookFactory.create(is);// 此WorkbookFactory在POI-3.10版本中使用需要添加dom4j
            if (wb instanceof XSSFWorkbook) {
                XSSFWorkbook xWb = (XSSFWorkbook) wb;
                htmlExcel = getExcelInfo(xWb, true);
            } else if (wb instanceof HSSFWorkbook) {
                HSSFWorkbook hWb = (HSSFWorkbook) wb;
                htmlExcel = getExcelInfo(hWb, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return htmlExcel;
    }

    /**
     * 程序入口方法
     * 
     * @param filePath
     *            文件的路径
     * @param isWithStyle
     *            是否需要表格样式 包含 字体 颜色 边框 对齐方式
     * @return <table>
     *         ...
     *         </table>
     *         字符串
     */
    public String readExcelToHtml(String filePath, boolean isWithStyle) {

        InputStream is = null;
        String htmlExcel = null;
        try {
            File sourcefile = new File(filePath);
            is = new FileInputStream(sourcefile);
            Workbook wb = WorkbookFactory.create(is);
            if (wb instanceof XSSFWorkbook) {
                XSSFWorkbook xWb = (XSSFWorkbook) wb;
                htmlExcel = getExcelInfo(xWb, isWithStyle);
            } else if (wb instanceof HSSFWorkbook) {
                HSSFWorkbook hWb = (HSSFWorkbook) wb;
                htmlExcel = getExcelInfo(hWb, isWithStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return htmlExcel;
    }

    public static String getExcelInfo(Workbook wb, boolean isWithStyle) {

        StringBuffer sb = new StringBuffer();
        Sheet sheet = wb.getSheetAt(0);// 获取第一个Sheet的内容
        int lastRowNum = sheet.getLastRowNum();
        Map<String, String> map[] = getRowSpanColSpanMap(sheet);
        sb.append("<!DOCTYPE html><html><head>" + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" + "<title>excel</title>"
                + "</head>" + "<body><table style='border-collapse:collapse;' width='100%'>");
        Row row = null; // 兼容
        Cell cell = null; // 兼容
        for (int rowNum = sheet.getFirstRowNum(); rowNum <= lastRowNum; rowNum++) {
            row = sheet.getRow(rowNum);
            if (row == null) {
                sb.append("<tr><td>  </td></tr>");
                continue;
            }
            sb.append("<tr>");
            int lastColNum = row.getLastCellNum();
            for (int colNum = 0; colNum < lastColNum; colNum++) {
                cell = row.getCell(colNum);
                if (cell == null) { // 特殊情况 空白的单元格会返回null
                    sb.append("<td> </td>");
                    continue;
                }
                String stringValue = getCellValue(cell);
                if (map[0].containsKey(rowNum + "," + colNum)) {
                    String pointString = map[0].get(rowNum + "," + colNum);
                    map[0].remove(rowNum + "," + colNum);
                    int bottomeRow = Integer.valueOf(pointString.split(",")[0]);
                    int bottomeCol = Integer.valueOf(pointString.split(",")[1]);
                    int rowSpan = bottomeRow - rowNum + 1;
                    int colSpan = bottomeCol - colNum + 1;
                    sb.append("<td rowspan= '" + rowSpan + "' colspan= '" + colSpan + "' ");
                } else if (map[1].containsKey(rowNum + "," + colNum)) {
                    map[1].remove(rowNum + "," + colNum);
                    continue;
                } else {
                    sb.append("<td ");
                }
                // 判断是否需要样式
                if (isWithStyle) {
                    dealExcelStyle(wb, sheet, cell, sb);// 处理单元格样式
                }
                sb.append(">");
                if (stringValue == null || "".equals(stringValue.trim())) {
                    sb.append("   ");
                } else {
                    // 将ascii码为160的空格转换为html下的空格（ ）
                    if (stringValue.contains("x")) {
                        sb.append("<input type='text' id='" + stringValue + "' name='" + stringValue + "' value='' />");
                    } else {
                        sb.append(stringValue.replace(String.valueOf((char) 160), " "));
                    }
                }
                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table></body></html>");
        return sb.toString();
    }

    private static Map<String, String>[] getRowSpanColSpanMap(Sheet sheet) {
        Map<String, String> map0 = new HashMap<String, String>();
        Map<String, String> map1 = new HashMap<String, String>();
        int mergedNum = sheet.getNumMergedRegions();
        CellRangeAddress range = null;
        for (int i = 0; i < mergedNum; i++) {
            range = (CellRangeAddress) sheet.getMergedRegion(i);
            int topRow = range.getFirstRow();
            int topCol = range.getFirstColumn();
            int bottomRow = range.getLastRow();
            int bottomCol = range.getLastColumn();
            map0.put(topRow + "," + topCol, bottomRow + "," + bottomCol);
            // System.out.println(topRow + "," + topCol + "," + bottomRow + ","
            // + bottomCol);
            int tempRow = topRow;
            while (tempRow <= bottomRow) {
                int tempCol = topCol;
                while (tempCol <= bottomCol) {
                    map1.put(tempRow + "," + tempCol, "");
                    tempCol++;
                }
                tempRow++;
            }
            map1.remove(topRow + "," + topCol);
        }
        Map[] map = { map0, map1 };
        return map;
    }

    /**
     * 获取表格单元格Cell内容
     * 
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String result = new String();
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_NUMERIC:// 数字类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                SimpleDateFormat sdf = null;
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                    sdf = new SimpleDateFormat("HH:mm");
                } else {// 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
                Date date = cell.getDateCellValue();
                result = sdf.format(date);
            } else if (cell.getCellStyle().getDataFormat() == 58) {
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                double value = cell.getNumericCellValue();
                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                result = sdf.format(date);
            } else {
                double value = cell.getNumericCellValue();
                CellStyle style = cell.getCellStyle();
                DecimalFormat format = new DecimalFormat();
                String temp = style.getDataFormatString();
                // 单元格设置成常规
                if (temp.equals("General")) {
                    format.applyPattern("#");
                }
                result = format.format(value);
            }
            break;
        case Cell.CELL_TYPE_STRING:// String类型
            result = cell.getRichStringCellValue().toString();
            break;
        case Cell.CELL_TYPE_BLANK:
            result = "";
            break;
        default:
            result = "";
            break;
        }
        return result;
    }

    /**
     * 处理表格样式
     * 
     * @param wb
     * @param sheet
     * @param cell
     * @param sb
     */
    private static void dealExcelStyle(Workbook wb, Sheet sheet, Cell cell, StringBuffer sb) {

        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle != null) {
            short alignment = cellStyle.getAlignment();
            sb.append("align='" + convertAlignToHtml(alignment) + "' ");// 单元格内容的水平对齐方式
            short verticalAlignment = cellStyle.getVerticalAlignment();
            sb.append("valign='" + convertVerticalAlignToHtml(verticalAlignment) + "' ");// 单元格中内容的垂直排列方式
            if (wb instanceof XSSFWorkbook) {
                XSSFFont xf = ((XSSFCellStyle) cellStyle).getFont();
                short boldWeight = xf.getBoldweight();
                sb.append("style='");
                sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
                sb.append("font-size: " + xf.getFontHeight() / 2 + "%;"); // 字体大小
                int columnWidth = sheet.getColumnWidth(cell.getColumnIndex());
                sb.append("width:" + columnWidth + "px;");
                XSSFColor xc = xf.getXSSFColor();
                if (xc != null && !"".equals(xc)) {
                    sb.append("color:#" + xc.getARGBHex().substring(2) + ";"); // 字体颜色
                }

                XSSFColor bgColor = (XSSFColor) cellStyle.getFillForegroundColorColor();
                if (bgColor != null && !"".equals(bgColor)) {
                    sb.append("background-color:#" + bgColor.getARGBHex().substring(2) + ";"); // 背景颜色
                }
                sb.append(getBorderStyle(0, cellStyle.getBorderTop(), ((XSSFCellStyle) cellStyle).getTopBorderXSSFColor()));
                sb.append(getBorderStyle(1, cellStyle.getBorderRight(), ((XSSFCellStyle) cellStyle).getRightBorderXSSFColor()));
                sb.append(getBorderStyle(2, cellStyle.getBorderBottom(), ((XSSFCellStyle) cellStyle).getBottomBorderXSSFColor()));
                sb.append(getBorderStyle(3, cellStyle.getBorderLeft(), ((XSSFCellStyle) cellStyle).getLeftBorderXSSFColor()));

            } else if (wb instanceof HSSFWorkbook) {
                HSSFFont hf = ((HSSFCellStyle) cellStyle).getFont(wb);
                short boldWeight = hf.getBoldweight();
                short fontColor = hf.getColor();
                sb.append("style='");
                HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette(); // 类HSSFPalette用于求的颜色的国际标准形式
                HSSFColor hc = palette.getColor(fontColor);
                sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
                sb.append("font-size: " + hf.getFontHeight() / 2 + "%;"); // 字体大小
                String fontColorStr = convertToStardColor(hc);
                if (fontColorStr != null && !"".equals(fontColorStr.trim())) {
                    sb.append("color:" + fontColorStr + ";"); // 字体颜色
                }
                int columnWidth = sheet.getColumnWidth(cell.getColumnIndex());
                sb.append("width:" + columnWidth + "px;");
                short bgColor = cellStyle.getFillForegroundColor();
                hc = palette.getColor(bgColor);
                String bgColorStr = convertToStardColor(hc);
                if (bgColorStr != null && !"".equals(bgColorStr.trim())) {
                    sb.append("background-color:" + bgColorStr + ";"); // 背景颜色
                }
                sb.append(getBorderStyle(palette, 0, cellStyle.getBorderTop(), cellStyle.getTopBorderColor()));
                sb.append(getBorderStyle(palette, 1, cellStyle.getBorderRight(), cellStyle.getRightBorderColor()));
                sb.append(getBorderStyle(palette, 3, cellStyle.getBorderLeft(), cellStyle.getLeftBorderColor()));
                sb.append(getBorderStyle(palette, 2, cellStyle.getBorderBottom(), cellStyle.getBottomBorderColor()));
            }

            sb.append("' ");
        }
    }

    /**
     * 单元格内容的水平对齐方式
     * 
     * @param alignment
     * @return
     */
    private static String convertAlignToHtml(short alignment) {

        String align = "left";
        switch (alignment) {
        case CellStyle.ALIGN_LEFT:
            align = "left";
            break;
        case CellStyle.ALIGN_CENTER:
            align = "center";
            break;
        case CellStyle.ALIGN_RIGHT:
            align = "right";
            break;
        default:
            break;
        }
        return align;
    }

    /**
     * 单元格中内容的垂直排列方式
     * 
     * @param verticalAlignment
     * @return
     */
    private static String convertVerticalAlignToHtml(short verticalAlignment) {

        String valign = "middle";
        switch (verticalAlignment) {
        case CellStyle.VERTICAL_BOTTOM:
            valign = "bottom";
            break;
        case CellStyle.VERTICAL_CENTER:
            valign = "center";
            break;
        case CellStyle.VERTICAL_TOP:
            valign = "top";
            break;
        default:
            break;
        }
        return valign;
    }

    private static String convertToStardColor(HSSFColor hc) {

        StringBuffer sb = new StringBuffer("");
        if (hc != null) {
            if (HSSFColor.AUTOMATIC.index == hc.getIndex()) {
                return null;
            }
            sb.append("#");
            for (int i = 0; i < hc.getTriplet().length; i++) {
                sb.append(fillWithZero(Integer.toHexString(hc.getTriplet()[i])));
            }
        }

        return sb.toString();
    }

    private static String fillWithZero(String str) {
        if (str != null && str.length() < 2) {
            return "0" + str;
        }
        return str;
    }

    static String[] bordesr = { "border-top:", "border-right:", "border-bottom:", "border-left:" };
    static String[] borderStyles = { "solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid", "solid", "solid",
            "solid", "solid" };

    private static String getBorderStyle(HSSFPalette palette, int b, short s, short t) {
        if (s == 0)
            return bordesr[b] + borderStyles[s] + "#d0d7e5 1px;";
        String borderColorStr = convertToStardColor(palette.getColor(t));
        borderColorStr = borderColorStr == null || borderColorStr.length() < 1 ? "#000000" : borderColorStr;
        return bordesr[b] + borderStyles[s] + borderColorStr + " 1px;";

    }

    private static String getBorderStyle(int b, short s, XSSFColor xc) {

        if (s == 0)
            return bordesr[b] + borderStyles[s] + "#d0d7e5 1px;";
        if (xc != null && !"".equals(xc)) {
            String borderColorStr = xc.getARGBHex();// t.getARGBHex();
            borderColorStr = borderColorStr == null || borderColorStr.length() < 1 ? "#000000" : borderColorStr.substring(2);
            return bordesr[b] + borderStyles[s] + borderColorStr + " 1px;";
        }

        return "";
    }

    /**
     * txt转html
     * @author Senghor<br>
     * @date 2018年5月25日 下午3:07:42
     */
    public static void txtToHtml(String fileUrl) {
        ReadFile(fileUrl);
        File file = new File(fileUrl);
        // 截取文件名称
        String name = file.getName().substring(0, file.getName().lastIndexOf("."));
        fileUrl = fileUrl.replaceAll(file.getName(), "");

        String toPath = fileUrl + name;

        judeDirExists(new File(toPath));
        
        writerFile(toPath + "/" + name + ".html");
    }
    static String textHtml = "";
    static String color = "#00688B";
    //读取文件
    public static void ReadFile(String filePath) {
        BufferedReader bu = null;
        InputStreamReader in = null;
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                in = new InputStreamReader(new FileInputStream(file));
                bu = new BufferedReader(in);
                String lineText = null;
                textHtml = "<!DOCTYPE html><html><head>" + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" + "<title>txt</title>"
                        + "</head>" + "<body>";
                while ((lineText = bu.readLine()) != null) {
                    lineText = changeToHtml(lineText);
                    lineText += "</br>";
                    textHtml += lineText;
                }
                textHtml += "</body></html>";
            } else {
//                System.out.println("文件不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bu.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    //输出文件
    public static void writerFile(String writepath) {
        File file = new File(writepath);
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(file));
//            System.out.println(textHtml);
            output.write(textHtml);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    //文件转换
    public static String changeToHtml(String text) {
        text = text.replace("&", "&");
        text = text.replace(" ", " ");
        text = text.replace("<", "<");
        text = text.replace(">", ">");
        text = text.replace("\"", "\"");
        text = text.replace(" ", "    ");
        text = text.replace("public", "<b><font color='"+color+"'>public</font></b>");
        text = text.replace("class", "<b><font color='"+color+"'>class</font></b>");
        text = text.replace("static", "<b><font color='"+color+"'>static</font></b>");
        text = text.replace("void", "<b><font color='"+color+"'>void</font></b>");
        String t = text.replace("//", "<font color=green>//");
        if (!text.equals(t)) {
//            System.out.println("t:"+t);
            text = t + "</font>";
        }
        return text;
    }
 
}
