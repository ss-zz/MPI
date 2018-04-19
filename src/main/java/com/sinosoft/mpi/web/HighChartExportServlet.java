package com.sinosoft.mpi.web;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.ServerException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 
 * @author chenzhongzheng
 * @deprecated highcharts图片导出功能
 */
public class HighChartExportServlet extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServerException, IOException {
		
		/**
		 * @author whn 20140610
		 */
		/*String encoding = request.getCharacterEncoding();
		if (encoding == null) {
			encoding = "ISO8859-1";
		}
		String type = request.getParameter("type");
		String svg = request.getParameter("svg");

		System.out.println("svg==>" + svg);
		String filename = request.getParameter("filename");
		*/
		/**
		 * author：chenzhongzheng
		 * 解决后台接受不到Request问题，问题原因：此时属于文件上传
		 */
		String type = null; 
        String svg = null; 
        String filename = null; 
        DiskFileItemFactory factory = new DiskFileItemFactory(); 
        ServletFileUpload upload = new ServletFileUpload(factory); 
        List items;
		try {
			items = upload.parseRequest(request);
        for (Iterator i = items.iterator(); i.hasNext();) {  
            FileItem fileItem = (FileItem) i.next();  
            String field = fileItem.getFieldName();  
            if(field.equals("type")){  
                      type = fileItem.getString();  
                      continue;  
            }else if(field.equals("svg")){  
                     svg = new String(fileItem.get(),"UTF-8");  
                     continue;  
            }else if(field.equals("filename")){  
                     filename = fileItem.getString();  
                     continue;  
            } 
        } 
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/**************************************/
		
		filename = filename == null ? "chart" : filename;
		ServletOutputStream out = response.getOutputStream();
		if (type != null && svg != null) {
			// svg = new String(svg.getBytes(encoding),"UTF-8");
			// System.out.println("svg UTF-8===>"+svg);
//			filename = new String(filename.getBytes(encoding), "UTF-8");
			svg = svg.replaceAll(":rect", "rect");
			String ext = "";
			Transcoder t = null;
			if (type.equals("image/png")) {
				ext = "png";
				t = new PNGTranscoder();
			} else if (type.equals("image/jpeg")) {
				ext = "jpg";
				t = new JPEGTranscoder();
			}
			/*
			 * else if (type.equals("application/pdf")) { ext = "pdf"; t = new
			 * PDFTranscoder(); }
			 */
			response.setContentType("charset=utf-8");
			response.addHeader("Content-Disposition",
					"attachment; filename=chart." + ext);
			response.addHeader("Content-Type", type);
			if (null != t) {
				TranscoderInput input = new TranscoderInput(new StringReader(
						svg));
				TranscoderOutput output = new TranscoderOutput(out);
				try {
					t.transcode(input, output);
				} catch (TranscoderException e) {
					svg = svg.replace("http://www.w3.org/2000/svg",
							"http://www.w3.org/TR/SVG11/");
					out.print("Problem transcoding stream. See the web logs for more details.");
					e.printStackTrace();
				}
			} else if (ext.equals("svg")) {
				out.print(svg);
			} else {
				out.print("Invalid type: " + type);
			}
		} else {
			response.addHeader("Content-Type", "text/html");
			out.println("Usage:\n\tParamet)er [svg]: The DOM Element to be converted. \n\tParameter [type]: The destination MIME type for the elment to be transcoded.");
		}
		out.flush();
		out.close();
	}
}