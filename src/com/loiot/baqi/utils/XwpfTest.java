package com.loiot.baqi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.loiot.commons.utils.StringUtil;

public class XwpfTest {  
   
   /** 
    * 通过XWPFDocument对内容进行访问。对于XWPF文档而言，用这种方式进行读操作更佳。 
    * @throws Exception 
    */  
   public void testReadByDoc() throws Exception {  
      InputStream is = new FileInputStream("D:\\zhaop\\7-31\\产品经理-吕念02.docx");  
      XWPFDocument doc = new XWPFDocument(is);  
      List<XWPFParagraph> paras = doc.getParagraphs();  
      for (XWPFParagraph para : paras) {  
         //当前段落的属性  
//       CTPPr pr = para.getCTP().getPPr();  
         System.out.println(para.getText());  
      }  
      //获取文档中所有的表格  
      List<XWPFTable> tables = doc.getTables();  
      List<XWPFTableRow> rows;  
      List<XWPFTableCell> cells;  
      for (XWPFTable table : tables) {  
         //表格属性  
//       CTTblPr pr = table.getCTTbl().getTblPr();  
         //获取表格对应的行  
         rows = table.getRows();  
         for (XWPFTableRow row : rows) {  
            //获取行对应的单元格  
            cells = row.getTableCells();  
            for (XWPFTableCell cell : cells) {  
                System.out.println(cell.getText());;  
            }  
         }  
      }  
      this.close(is);  
   }  
    
   /** 
    * 关闭输入流 
    * @param is 
    */  
   private void close(InputStream is) {  
      if (is != null) {  
         try {  
            is.close();  
         } catch (IOException e) {  
            e.printStackTrace();  
         }  
      }  
   }
   
   public static void main(String[] args) {
	  try {
		//new XwpfTest().testReadByDoc();
		  //String testS ="http://www.jlb.com/upfile/jl/2015-10-19/Android开发工程师-5年-马兵.doc";
  	    List ff=StringUtil.splitToString("211", ",");

		  
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
    
}  