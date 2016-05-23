package com.wanhe;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by flyong86 on 2015/9/11.
 */
public class ExcelView2 extends AbstractExcelView {

    /** 默认日期格式配比 */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** 文件名称 */
    private String filename;

    /** 表名称 */
    private String[] sheetNames;

    /** 属性 */
    private String[][] propertiesArray;

    /** 标题 */
    private String[][] titlesArray;

    /** 列宽 */
    private Integer[][] widthsArray;

    /** 类型转换 */
    private Converter[][] convertersArray;

    /** 数据 */
    private Collection<?>[] datas;

    /** 附加内容 */
    private String[][] contentsArray;

    /** 数据验证. */
    private Map<CellRangeAddressList, String[]> dataValidations = new HashMap<CellRangeAddressList, String[]>();

    static {
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPattern(DEFAULT_DATE_PATTERN);
        ConvertUtils.register(dateConverter, Date.class);
    }

    /**
     * @param filename
     *            文件名称
     * @param sheetName
     *            表名称
     * @param properties
     *            属性
     * @param titles
     *            标题
     * @param widths
     *            列宽
     * @param converters
     *            类型转换
     * @param data
     *            数据
     * @param contents
     *            附加内容
     */
    public ExcelView2(String filename, String[] sheetNames, String[][] propertiesArray, String[][] titlesArray,
                      Integer[][] widthsArray, Converter[][] convertersArray, Collection<?>[] datas, String[][] contentsArray) {
        this.filename = filename;
        this.sheetNames = sheetNames;
        this.propertiesArray = propertiesArray;
        this.titlesArray = titlesArray;
        this.widthsArray = widthsArray;
        this.convertersArray = convertersArray;
        this.datas = datas;
        this.contentsArray = contentsArray;
    }

    /**
     * @param properties
     *            属性
     * @param titles
     *            标题
     * @param data
     *            数据
     * @param contents
     *            附加内容
     */
    public ExcelView2(String[][] propertiesArray, String[][] titlesArray, Collection<?>[] datas, String[][] contentsArray) {
        this.propertiesArray = propertiesArray;
        this.titlesArray = titlesArray;
        this.datas = datas;
        this.contentsArray = contentsArray;
    }

    /**
     * @param properties
     *            属性
     * @param titles
     *            标题
     * @param data
     *            数据
     */
    public ExcelView2(String[][] propertiesArray, String[][] titlesArray, Collection<?>[] datas) {
        this.propertiesArray = propertiesArray;
        this.titlesArray = titlesArray;
        this.datas = datas;
    }

    /**
     * @param properties
     *            属性
     * @param data
     *            数据
     */
    public ExcelView2(String[][] propertiesArray, Collection<?>[] data) {
        this.propertiesArray = propertiesArray;
        this.datas = datas;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        for (int idx = 0; idx < sheetNames.length; idx ++) {
            String sheetName = sheetNames[idx];
            String[] properties = propertiesArray[idx];
            String[] titles = titlesArray[idx];
            Integer[] widths = widthsArray[idx];
            Collection<?> data = datas[idx];
            Converter[] converters = convertersArray[idx];
            String[] contents = contentsArray[idx];

            Assert.notEmpty(properties);
            HSSFSheet sheet;
            if (StringUtils.isNotEmpty(sheetName)) {
                sheet = workbook.createSheet(sheetName);
            } else {
                sheet = workbook.createSheet();
            }

            // 添加数据有效性
            for (CellRangeAddressList addressList : dataValidations.keySet()) {
                DataValidationHelper helper = sheet.getDataValidationHelper();

                //设置下拉框数据
//                DVConstraint constraint = DVConstraint.createExplicitListConstraint(dataValidations.get(addressList));
//                DataValidation dataValidation = helper.createValidation(constraint, addressList);
//                dataValidation.setSuppressDropDownArrow(true);
//                dataValidation.createErrorBox("输入有误", "请选择快递公司!");
//                dataValidation.setShowErrorBox(true);

                // 生成下拉框内容
                DVConstraint constraint = DVConstraint.createExplicitListConstraint(dataValidations.get(addressList));
                // 绑定下拉框和作用区域
                HSSFDataValidation data_validation = new HSSFDataValidation(addressList, constraint);


                sheet.addValidationData(data_validation);
            }
            int rowNumber = 0;
            if (titles != null && titles.length > 0) {
                HSSFRow header = sheet.createRow(rowNumber);
                header.setHeight((short) 400);
                for (int i = 0; i < properties.length; i++) {
                    HSSFCell cell = header.createCell(i);
                    HSSFCellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                    cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                    HSSFFont font = workbook.createFont();
                    font.setFontHeightInPoints((short) 11);
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);
                    if (i == 0) {
                        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 1, (short) 4, 4));
                        comment.setString(new HSSFRichTextString("P" + "o" + "w" + "e" + "r" + "e" + "d" + " " + "B" + "y" + " " + "Z" + "H" + "I" + " " + "Y" + "O" + "N" + "G" + "+" + "+"));
                        cell.setCellComment(comment);
                    }
                    if (titles.length > i && titles[i] != null) {
                        cell.setCellValue(titles[i]);
                    } else {
                        cell.setCellValue(properties[i]);
                    }
                    if (widths != null && widths.length > i && widths[i] != null) {
                        sheet.setColumnWidth(i, widths[i]);
                    } else {
                        sheet.autoSizeColumn(i);
                    }
                }
                rowNumber++;
            }
            if (data != null) {
                for (Object item : data) {
                    HSSFRow row = sheet.createRow(rowNumber);
                    for (int i = 0; i < properties.length; i++) {
                        HSSFCell cell = row.createCell(i);
                        if (converters != null && converters.length > i && converters[i] != null) {
                            Class<?> clazz = PropertyUtils.getPropertyType(item, properties[i]);
                            ConvertUtils.register(converters[i], clazz);
                            cell.setCellValue(BeanUtils.getProperty(item, properties[i]));
                            ConvertUtils.deregister(clazz);
                            if (clazz.equals(Date.class)) {
                                DateConverter dateConverter = new DateConverter();
                                dateConverter.setPattern(DEFAULT_DATE_PATTERN);
                                ConvertUtils.register(dateConverter, Date.class);
                            }
                        } else {
                            cell.setCellValue(BeanUtils.getProperty(item, properties[i]));
                        }
                        if (rowNumber == 0 || rowNumber == 1) {
                            if (widths != null && widths.length > i && widths[i] != null) {
                                sheet.setColumnWidth(i, widths[i]);
                            } else {
                                sheet.autoSizeColumn(i);
                            }
                        }
                    }
                    rowNumber++;
                }
            }
            if (contents != null && contents.length > 0) {
                rowNumber++;
                for (String content : contents) {
                    HSSFRow row = sheet.createRow(rowNumber);
                    HSSFCell cell = row.createCell(0);
                    HSSFCellStyle cellStyle = workbook.createCellStyle();
                    HSSFFont font = workbook.createFont();
                    font.setColor(HSSFColor.GREY_50_PERCENT.index);
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(content);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    rowNumber++;
                }
            }
        }
        response.setContentType("application/force-download");
        if (StringUtils.isNotEmpty(filename)) {
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        } else {
            response.setHeader("Content-disposition", "attachment");
        }

    }

    /**
     * 获取文件名称
     *
     * @return 文件名称
     */
    public String getFileName() {
        return filename;
    }

    /**
     * 设置文件名称
     *
     * @param filename
     *            文件名称
     */
    public void setFileName(String filename) {
        this.filename = filename;
    }

    /**
     * 获取表名称
     *
     * @return 表名称
     */
    public String[] getSheetName() {
        return sheetNames;
    }

    /**
     * 设置表名称
     *
     * @param sheetName
     *            表名称
     */
    public void setSheetName(String[] sheetName) {
        this.sheetNames = sheetName;
    }

    /**
     * 获取属性
     *
     * @return 属性
     */
    public String[][] getProperties() {
        return propertiesArray;
    }

    /**
     * 设置属性
     *
     * @param properties
     *            属性
     */
    public void setProperties(String[][] properties) {
        this.propertiesArray = properties;
    }

    /**
     * 获取标题
     *
     * @return 标题
     */
    public String[][] getTitles() {
        return titlesArray;
    }

    /**
     * 设置标题
     *
     * @param titles
     *            标题
     */
    public void setTitles(String[][] titles) {
        this.titlesArray = titles;
    }

    /**
     * 获取列宽
     *
     * @return 列宽
     */
    public Integer[][] getWidths() {
        return widthsArray;
    }

    /**
     * 设置列宽
     *
     * @param widths
     *            列宽
     */
    public void setWidths(Integer[][] widths) {
        this.widthsArray = widths;
    }

    /**
     * 获取类型转换
     *
     * @return 类型转换
     */
    public Converter[][] getConverters() {
        return convertersArray;
    }

    /**
     * 设置类型转换
     *
     * @param converters
     *            类型转换
     */
    public void setConverters(Converter[][] converters) {
        this.convertersArray = converters;
    }

    /**
     * 获取数据
     *
     * @return 数据
     */
    public Collection<?>[] getData() {
        return datas;
    }

    /**
     * 设置数据
     *
     * @param data
     *            数据
     */
    public void setData(Collection<?>[] data) {
        this.datas = data;
    }

    /**
     * 获取附加内容
     *
     * @return 附加内容
     */
    public String[][] getContents() {
        return contentsArray;
    }

    /**
     * 设置附加内容
     *
     * @param contents
     *            附加内容
     */
    public void setContents(String[][] contents) {
        this.contentsArray = contents;
    }

    /**
     * 添加数据有效性.
     * @param dataValidation
     */
    public void addValidationData(CellRangeAddressList addressList, String[] data) {
        dataValidations.put(addressList, data);
    }
}
