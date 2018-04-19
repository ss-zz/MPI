package com.sinosoft.mpi.web;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author chenzhongzheng
 *
 */
@Controller

public class ExportExcelServlet extends javax.servlet.http.HttpServlet
    implements javax.servlet.Servlet {
  
	private static final long serialVersionUID = 1L;
	//public class ExportExcelAction {	
	private static Log logger = LogFactory.getLog(ExportExcelServlet.class);
	private ObjectMapper jsonHelper = new ObjectMapper();
	public ExportExcelServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServerException, IOException {
		exportExcel(request,response);
	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@RequestMapping("expExcel.ac")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		ServletOutputStream out = null;
		String encoding = request.getCharacterEncoding();
		if(encoding==null){
			encoding = "ISO8859-1";
		}
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//String title = request.getParameter("title");//excel标题			
		//String svg = request.getParameter("svg"); //svg数据
		String title="测试";
		String gridJson = request.getParameter("gridjson"); //grid数据
		logger.debug("gridjson=>>>>"+gridJson);
		
		/*try {
			gridJson = java.net.URLDecoder.decode(gridJson, "utf-8");
			logger.debug("gridjson=22"+gridJson);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		//生成excel
		//System.out.println("gridjson===>>"+gridJson);
		HSSFWorkbook wb= new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet();
	    HSSFRow row = null;
	    HSSFCell cell = null;
	    List<Map> items  =null;
	    
		if(gridJson!=null){
			HashMap gridMap = null;
			
			try {
				// 转码
				gridMap = jsonHelper.readValue(gridJson, HashMap.class);
				//gridMap = jsonHelper.readValue(gridJson.getBytes(encoding), HashMap.class);
				title = new String(title.getBytes(encoding));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			//response.setContentType("application/msexcel;charset=utf-8");
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			//excel中文名字
			response.setHeader("Content-Disposition", "attachment;filename=excel.xls");
		    List<String> colNames =(List<String>) gridMap.get("colNames");
		    List<String> colIndexs =(List<String>) gridMap.get("colIndexs");
		    items= (List<Map>) gridMap.get("items");
		    //生成表头
	    	row = sheet.createRow(0);
	    	for( int i = 0 ; i < colNames.size() ; i ++){
	    		cell = row.createCell((short) i);
	    		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	    		//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	    		cell.setCellValue(colNames.get(i));
	    		logger.debug("生成一行="+colNames.get(i));
	    	}
		    if(items != null && items.size() > 0){
		    	//生成表数据
		    	for(int i = 0 ; i < items.size() ; i++ ){
		    		//生成一行
			    	logger.debug("生成一行");
			    	row = sheet.createRow(i+1);
			    	//拿一行数据
			    	Map item = items.get(i);
			    	Map<String,Object> ite = (Map<String,Object>)item;
			    	for(int j = 0 ; j < colIndexs.size() ; j++){
			    		String index = colIndexs.get(j);
			    		cell = row.createCell((short) j);
			    		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			    		//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			    		Object cv = ite.get(index);
			    		if( cv != null){
							cell.setCellValue( cv.toString());
			    			logger.debug("生成一行="+cv.toString());
			    		}
			    	}
		    	}
		    	
		    }else{
		    	logger.debug("items没有数据");
		    }
		    try {
		    	/*File file=new File("c:/t.xls");
		    	FileOutputStream fos=new FileOutputStream(file);
		    	wb.write(fos);
		    	fos.flush();
		    	fos.close();*/
				wb.write(out);
			 	out.flush();
			 	out.close();
			 	logger.debug("正常解析,解析完毕");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.debug(e.getStackTrace());
			}
		    
		}else{
			logger.debug("gridJson没有数据");
		}
		
	}
	
   }
