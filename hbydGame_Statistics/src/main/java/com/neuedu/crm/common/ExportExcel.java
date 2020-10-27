package com.neuedu.crm.common;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;

/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档 转载时请保留以下信息，注明出处！
 *
 * @param <T> 应用泛型，代表任意一个符合javabean风格的类
 *        注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx() byte[]表jpg格式的图片数据
 * @author leno
 * @version v1.0
 */
public class ExportExcel<T> {
	public void exportExcel(Collection<T> dataset, OutputStream out) {
		exportExcel("业绩报表", null, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out) {
		exportExcel("业绩报表", headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String sheetName, String[] headers, Collection<T> dataset, OutputStream out) {
		exportExcel(sheetName, headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
		exportExcel("业绩报表", headers, dataset, out, pattern);
	}

	public static String[] filedNames = new String[] { "address", "residence", "companyName", "companyAddress",
			"spouseInfo", "remark" };

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 *
	 * @param title   表格标题名
	 * @param headers 表格属性列名数组
	 * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *                javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 左对齐文本样式
		HSSFCellStyle style3 = workbook.createCellStyle();
		style3.setFillForegroundColor(HSSFColor.WHITE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style3.setWrapText(true);// 设置自动换行
		style3.setFont(font2);
		// 需要左对齐的字段
		// List<String> filedList = Arrays.asList(filedNames);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		HSSFFont font3 = workbook.createFont();
		font3.setColor(HSSFColor.BLACK.index);

		short columnType = 0;
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();

			if (t instanceof Object && columnType != 1) {
				columnType = 1;
			} else if (t instanceof Object && columnType != 2) {
				columnType = 2;
			}

			Class tCls = t.getClass();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
//				if (columnType != 0 && columnType != 3) {
//					if (filedList.contains(fieldName)) {
//						cell.setCellStyle(style3);
//					}
//				}
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Integer) {
						int intValue = (Integer) value;
						cell.setCellValue(intValue);
					} else if (value instanceof Double) {
						double dValue = (Double) value;
						cell.setCellValue(dValue);
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
								index);
						anchor.setAnchorType(2);
						/*
						 * patriarch.createPicture(anchor, workbook.addPicture( bsValue,
						 * HSSFWorkbook.PICTURE_TYPE_JPEG));
						 */
					} else {
						// 其它数据类型都当作字符串简单处理
						if (value == null) {
							textValue = "--";
						} else {
							textValue = value.toString();
						}

					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(textValue);
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		// 设置列宽
		setSheetColumnWidth(sheet, columnType);
		try {
			// 写入文件流
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭文件流
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * exportExcel 方法的的扩展方法，用来处理数据量超过65535条数据时会抛出异常的问题。
	 *
	 * @param title   表格标题名
	 * @param headers 表格属性列名数组
	 * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *                javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public void exportExcelBigData(String title, String[] headers, List<T> dataset, OutputStream out, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		HSSFFont font3 = workbook.createFont();
		font3.setColor(HSSFColor.BLACK.index);

		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 左对齐文本样式
		HSSFCellStyle style3 = workbook.createCellStyle();
		style3.setFillForegroundColor(HSSFColor.WHITE.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style3.setWrapText(true);// 设置自动换行
		style3.setFont(font2);
		// 需要左对齐的字段
		// List<String> filedList = Arrays.asList(filedNames);

		// 工作表名后面的数字，如表1，表2
		int titleRownm = 0;
		// 记录总行数
		int rownum = 0;
		// 记录每个sheet的行数
		int tempnum = 0;
		// 分页条数达到此条数则创建工作表
		int page = 65500;

		while (true) {
			tempnum = 0;
			// 生成一个表格
			titleRownm++;
			HSSFSheet sheet = workbook.createSheet(dataset.size() > page ? title + titleRownm : title);
			// 产生标题行
			HSSFRow row = sheet.createRow(tempnum);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}

			rownum++;
			// 遍历集合数据，产生数据行
			for (int j = rownum - titleRownm; j < dataset.size(); j++) {
				// 设置表格默认列宽度为15个字节
				sheet.setDefaultColumnWidth((short) 15);
				rownum++;
				// 产生表格标题行

				short columnType = 0;
				row = sheet.createRow(++tempnum);
				T t = (T) dataset.get(j);

				if (t instanceof Object && columnType != 1) {
					columnType = 1;
				} else if (t instanceof Object && columnType != 2) {
					columnType = 2;
				}

				Class tCls = t.getClass();
				// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
				Field[] fields = t.getClass().getDeclaredFields();
				for (short i = 0; i < fields.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style2);
					Field field = fields[i];
					String fieldName = field.getName();
//				if (columnType != 0 && columnType != 3) {
//					if (filedList.contains(fieldName)) {
//						cell.setCellStyle(style3);
//					}
//				}
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					try {
						Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
						Object value = getMethod.invoke(t, new Object[] {});
						// 判断值的类型后进行强制类型转换
						String textValue = null;
						if (value instanceof Integer) {
							int intValue = (Integer) value;
							cell.setCellValue(intValue);
						} else if (value instanceof Double) {
							double dValue = (Double) value;
							cell.setCellValue(dValue);
						} else if (value instanceof Date) {
							Date date = (Date) value;
							SimpleDateFormat sdf = new SimpleDateFormat(pattern);
							textValue = sdf.format(date);
						} else if (value instanceof byte[]) {
							// 有图片时，设置行高为60px;
							row.setHeightInPoints(60);
							// 设置图片所在列宽度为80px,注意这里单位的一个换算
							sheet.setColumnWidth(i, (short) (35.7 * 80));
							// sheet.autoSizeColumn(i);
							byte[] bsValue = (byte[]) value;
							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, j + 1, (short) 6,
									j + 1);
							anchor.setAnchorType(2);
							/*
							 * patriarch.createPicture(anchor, workbook.addPicture( bsValue,
							 * HSSFWorkbook.PICTURE_TYPE_JPEG));
							 */
						} else {
							// 其它数据类型都当作字符串简单处理
							if (value == null) {
								textValue = "--";
							} else {
								textValue = value.toString();
							}

						}
						// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
						if (textValue != null) {
							Pattern p = Pattern.compile("^//d+(//.//d+)?$");
							Matcher matcher = p.matcher(textValue);
							if (matcher.matches()) {
								// 是数字当作double处理
								cell.setCellValue(textValue);
							} else {
								HSSFRichTextString richString = new HSSFRichTextString(textValue);
								richString.applyFont(font3);
								cell.setCellValue(richString);
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} finally {
						// 清理资源
					}
				}
				// 达到上限以后停止循环
				if (rownum % page == 0) {
					// 设置列宽
					setSheetColumnWidth(sheet, columnType);
					break;
				}
			}

			if (rownum - titleRownm == dataset.size()) {
				break;
			}
		}

		try {
			// 写入文件流
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭文件流
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 设置列宽
	 */
	public void setSheetColumnWidth(HSSFSheet sheet, short columnType) {
		if (columnType == 0) {
			setColumnType0(sheet);
		} else if (columnType == 1) {
			setBillResource(sheet);
		} else if (columnType == 3) {
			sheet.setColumnWidth(0, 5120);
			sheet.setColumnWidth(1, 5120);
			sheet.setColumnWidth(2, 5120);
			sheet.setColumnWidth(4, 5120);
			sheet.setColumnWidth(5, 5120);
		} else if (columnType == 2) {
			setColumnType2(sheet);
		} else if (columnType == 4) {
			setBillCarLoan(sheet);
		} else if (columnType == 5) {
			setBillHouseLoan(sheet);
		} else if (columnType == 6) {
			setBillInstallmentCarLoan(sheet);
		} else if (columnType == 7) {
			setBillInstallmentConsumptionLoan(sheet);
		} else if (columnType == 8) {
			setBillPolicyLoan(sheet);
		} else if (columnType == 9) {
			setActivityUserStyle(sheet);
		} else if (columnType == 10) {
			setHandleExportBean(sheet);
		}
	}

	public void setBillHouseLoan(HSSFSheet sheet) {
		int column1 = 25 * 256;
		int column2 = 12 * 256;// 4字title
		int column3 = 20 * 256;
		int column4 = 15 * 256;
		int column5 = 8 * 256;// 2字title
		sheet.setColumnWidth(0, column1);
		sheet.setColumnWidth(1, column3);
		sheet.setColumnWidth(2, column5);
		sheet.setColumnWidth(3, column3);
		sheet.setColumnWidth(4, column2);
		sheet.setColumnWidth(5, column5);
		sheet.setColumnWidth(6, column5);
		sheet.setColumnWidth(7, column3);
		sheet.setColumnWidth(8, column3);
		sheet.setColumnWidth(9, column2);
		sheet.setColumnWidth(10, column4);
		sheet.setColumnWidth(11, column3);
		sheet.setColumnWidth(12, column3);
		sheet.setColumnWidth(13, column4);
		sheet.setColumnWidth(14, column2);
		sheet.setColumnWidth(15, column2);
		sheet.setColumnWidth(16, column3);
		sheet.setColumnWidth(17, column4);
		sheet.setColumnWidth(18, column4);
		sheet.setColumnWidth(19, column4);
		sheet.setColumnWidth(20, column4);
		sheet.setColumnWidth(21, column4);
		sheet.setColumnWidth(22, column4);
		sheet.setColumnWidth(23, column4);
		sheet.setColumnWidth(24, column1);
		sheet.setColumnWidth(25, column4);
		sheet.setColumnWidth(26, column4);
		sheet.setColumnWidth(27, column3);
		sheet.setColumnWidth(28, column1);
		sheet.setColumnWidth(29, column1);
	}

	public void setBillInstallmentCarLoan(HSSFSheet sheet) {
		int column1 = 25 * 256;
		int column2 = 12 * 256;// 4字title
		int column3 = 20 * 256;
		int column4 = 15 * 256;
		int column5 = 8 * 256;// 2字title
		sheet.setColumnWidth(0, column1);
		sheet.setColumnWidth(1, column3);
		sheet.setColumnWidth(2, column5);
		sheet.setColumnWidth(3, column3);
		sheet.setColumnWidth(4, column2);
		sheet.setColumnWidth(5, column5);
		sheet.setColumnWidth(6, column5);
		sheet.setColumnWidth(7, column3);
		sheet.setColumnWidth(8, column3);
		sheet.setColumnWidth(9, column2);
		sheet.setColumnWidth(10, column4);
		sheet.setColumnWidth(11, column3);
		sheet.setColumnWidth(12, column3);
		sheet.setColumnWidth(13, column4);
		sheet.setColumnWidth(14, column2);
		sheet.setColumnWidth(15, column2);
		sheet.setColumnWidth(16, column2);
		sheet.setColumnWidth(17, column1);
		sheet.setColumnWidth(18, column4);
		sheet.setColumnWidth(19, column2);
		sheet.setColumnWidth(20, column4);
		sheet.setColumnWidth(21, column4);
		sheet.setColumnWidth(22, column3);
		sheet.setColumnWidth(23, column2);
		sheet.setColumnWidth(24, column1);
		sheet.setColumnWidth(25, column4);
		sheet.setColumnWidth(26, column4);
		sheet.setColumnWidth(27, column3);
		sheet.setColumnWidth(28, column3);
		sheet.setColumnWidth(32, column1);
		sheet.setColumnWidth(33, column1);
		sheet.setColumnWidth(34, 30 * 256);
		sheet.setColumnWidth(35, 30 * 256);
		sheet.setColumnWidth(36, column1);
		sheet.setColumnWidth(37, 80 * 256);
		sheet.setColumnWidth(38, column4);

	}

	public void setBillPolicyLoan(HSSFSheet sheet) {
		int column1 = 25 * 256;
		int column2 = 12 * 256;// 4字title
		int column3 = 20 * 256;
		int column4 = 15 * 256;
		int column5 = 8 * 256;// 2字title
		sheet.setColumnWidth(0, column1);
		sheet.setColumnWidth(1, column3);
		sheet.setColumnWidth(2, column5);
		sheet.setColumnWidth(3, column3);
		sheet.setColumnWidth(4, column2);
		sheet.setColumnWidth(5, column5);
		sheet.setColumnWidth(6, column5);
		sheet.setColumnWidth(7, column3);
		sheet.setColumnWidth(8, column3);
		sheet.setColumnWidth(9, column2);
		sheet.setColumnWidth(10, column4);
		sheet.setColumnWidth(11, column3);
		sheet.setColumnWidth(12, column3);
		sheet.setColumnWidth(13, column4);
		sheet.setColumnWidth(14, column2);
		sheet.setColumnWidth(15, column2);
		sheet.setColumnWidth(16, column2);
		sheet.setColumnWidth(17, column1);
		sheet.setColumnWidth(18, column4);
		sheet.setColumnWidth(19, column2);
		sheet.setColumnWidth(20, column4);
		sheet.setColumnWidth(21, column4);
		sheet.setColumnWidth(22, column4);
		sheet.setColumnWidth(23, column1);
		sheet.setColumnWidth(24, column2);
		sheet.setColumnWidth(25, column4);
		sheet.setColumnWidth(26, column4);
		sheet.setColumnWidth(27, column3);
		sheet.setColumnWidth(28, column1);
		sheet.setColumnWidth(34, column1);
		sheet.setColumnWidth(35, 30 * 256);
		sheet.setColumnWidth(36, column1);
		sheet.setColumnWidth(37, column1);
		sheet.setColumnWidth(38, 60 * 256);
	}

	public void setActivityUserStyle(HSSFSheet sheet) {
		int column1 = 12 * 256;// 3个字
		int column2 = 20 * 256;
		int column3 = 17 * 256;// 4字title
		int column4 = 50 * 256;// 4字title
		sheet.setColumnWidth(0, column1);
		sheet.setColumnWidth(1, column3);
		sheet.setColumnWidth(2, column4);
		sheet.setColumnWidth(3, column2);
		sheet.setColumnWidth(4, column3);
		sheet.setColumnWidth(5, column3);
		sheet.setColumnWidth(6, column2);
	}

	public void setHandleExportBean(HSSFSheet sheet) {
		int column1 = 12 * 256;// 3个字
		int column2 = 20 * 256;
		int column3 = 17 * 256;// 4字title
		int column4 = 35 * 256;// 4字title
		sheet.setColumnWidth(0, column1);
		sheet.setColumnWidth(1, column3);
		sheet.setColumnWidth(2, column2);
		sheet.setColumnWidth(3, column2);
		sheet.setColumnWidth(4, column4);
		sheet.setColumnWidth(5, column4);
	}

	public void setBillCarLoan(HSSFSheet sheet) {
		int column1 = 25 * 256;
		int column2 = 12 * 256;// 4字title
		int column3 = 20 * 256;
		int column4 = 15 * 256;
		int column5 = 8 * 256;// 2字title
		sheet.setColumnWidth(0, column1);
		sheet.setColumnWidth(1, column3);
		sheet.setColumnWidth(2, column5);
		sheet.setColumnWidth(3, column3);
		sheet.setColumnWidth(4, column2);
		sheet.setColumnWidth(5, column5);
		sheet.setColumnWidth(6, column5);
		sheet.setColumnWidth(7, column3);
		sheet.setColumnWidth(8, column3);
		sheet.setColumnWidth(9, column2);
		sheet.setColumnWidth(10, column4);
		sheet.setColumnWidth(11, column3);
		sheet.setColumnWidth(12, column3);
		sheet.setColumnWidth(13, column4);
		sheet.setColumnWidth(14, column2);
		sheet.setColumnWidth(15, column2);
		sheet.setColumnWidth(16, column1);
		sheet.setColumnWidth(17, column1);
		sheet.setColumnWidth(18, column4);
		sheet.setColumnWidth(19, column2);
		sheet.setColumnWidth(20, column4);
		sheet.setColumnWidth(21, column4);
		sheet.setColumnWidth(22, column4);
		sheet.setColumnWidth(23, column4);
		sheet.setColumnWidth(24, column2);
		sheet.setColumnWidth(25, column4);
		sheet.setColumnWidth(26, column4);
		sheet.setColumnWidth(27, column3);
	}

	public void setBillInstallmentConsumptionLoan(HSSFSheet sheet) {
		int column1 = 25 * 256;
		int column2 = 12 * 256;// 4字title
		int column3 = 20 * 256;
		int column4 = 15 * 256;
		int column5 = 8 * 256;// 2字title
		sheet.setColumnWidth(0, column1);
		sheet.setColumnWidth(1, column3);
		sheet.setColumnWidth(2, column5);
		sheet.setColumnWidth(3, column3);
		sheet.setColumnWidth(4, column2);
		sheet.setColumnWidth(5, column5);
		sheet.setColumnWidth(6, column5);
		sheet.setColumnWidth(7, column3);
		sheet.setColumnWidth(8, column3);
		sheet.setColumnWidth(9, column3);
		sheet.setColumnWidth(10, column4);
		sheet.setColumnWidth(11, column3);
		sheet.setColumnWidth(12, column3);
		sheet.setColumnWidth(13, column4);
		sheet.setColumnWidth(14, column2);
		sheet.setColumnWidth(15, column2);
		sheet.setColumnWidth(16, column2);
		sheet.setColumnWidth(17, column1);
		sheet.setColumnWidth(18, column4);
		sheet.setColumnWidth(19, column2);
		sheet.setColumnWidth(20, column4);
		sheet.setColumnWidth(21, column3);
		sheet.setColumnWidth(22, column4);
		sheet.setColumnWidth(23, column2);
		sheet.setColumnWidth(24, column2);
		sheet.setColumnWidth(25, column4);
		sheet.setColumnWidth(26, column4);
		sheet.setColumnWidth(27, column3);
		sheet.setColumnWidth(28, column3);
	}

	public void setColumnType2(HSSFSheet sheet) {
		int column1 = 25 * 256;
		int column2 = 12 * 256;
		int column3 = 20 * 256;
		int column4 = 15 * 256;
		int column5 = 8 * 256;
		sheet.setColumnWidth(0, column4);
		sheet.setColumnWidth(1, column1);
		sheet.setColumnWidth(2, column5);
		sheet.setColumnWidth(3, column1);
		sheet.setColumnWidth(4, column3);

	}

	public void setBillResource(HSSFSheet sheet) {
		int column1 = 25 * 256;
		int column2 = 12 * 256;
		int column3 = 20 * 256;
		int column4 = 15 * 256;
		int column5 = 8 * 256;
		sheet.setColumnWidth(0, column4);
		sheet.setColumnWidth(1, column1);
		sheet.setColumnWidth(2, column1);
		sheet.setColumnWidth(3, column5);
		sheet.setColumnWidth(4, column3);
		sheet.setColumnWidth(5, column5);

	}

	public void setColumnType0(HSSFSheet sheet) {
		sheet.setColumnWidth(0, 2560);
		sheet.setColumnWidth(1, 2560);
		sheet.setColumnWidth(2, 2560);
		sheet.setColumnWidth(4, 5120);
		sheet.setColumnWidth(5, 5120);
		sheet.setColumnWidth(6, 2560);
		sheet.setColumnWidth(7, 2560);
		sheet.setColumnWidth(8, 2560);
		sheet.setColumnWidth(9, 2560);
		sheet.setColumnWidth(10, 2560);
		sheet.setColumnWidth(11, 2560);
		sheet.setColumnWidth(12, 2560);
		sheet.setColumnWidth(13, 2560);
		sheet.setColumnWidth(14, 2560);
	}

}
