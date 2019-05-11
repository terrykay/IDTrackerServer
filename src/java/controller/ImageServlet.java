/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.annotation.MultipartConfig;
import org.apache.commons.fileupload.*;
import resources.PreferencesBean;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import session.SessionFacade;
import userIdEntity.Session;
/**
 *
 * @author tezk
 */
@MultipartConfig
public class ImageServlet extends HttpServlet {
    @EJB
    SessionFacade sessionFacade;
    private static String SAVE_DIR = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sessionId = request.getParameter("sessionid");
        Session aSession = sessionFacade.findBySessionId(sessionId);
        if (aSession == null)
            return;
        // gets absolute path of the web application
        //String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        //String savePath = appPath + File.separator + SAVE_DIR;
        PreferencesBean pb = PreferencesBean.getInstance();
        SAVE_DIR = pb.getPathProperty();

        String url = request.getParameter("url");
        System.out.println("Getting image, provided url = "+url);
        String userID = request.getParameter("id");
        //URL r = this.getClass().getResource("/resources/bulb.png");
        response.setContentType("image/png;charset=UTF-8");
        //File file = new File(r.getFile());
        File file = new File(SAVE_DIR+userID+File.separator+url);
        response.setContentLength((int) file.length());

        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        in.close();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean isMultipart;
    private int maxFileSize = 20 * 1024 * 1024; // 10MB max image
    private int maxMemSize = 4 * 1024;
    private File file;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {
        System.out.println("Entering ImageServet doPost");
        String sessionId = request.getHeader("sessionid");
        Session aSession = sessionFacade.findBySessionId(sessionId);
        if (aSession == null)
            return;
        // Check that we have a file upload request
        PreferencesBean pb = PreferencesBean.getInstance();
        SAVE_DIR = pb.getPathProperty();
        String details = request.getHeader("details");
        String customerId = request.getHeader("customerid");
        String filename = request.getHeader("filename").replaceAll("[^A-Za-z0-9.]", "");
        
        String fileurl = request.getHeader("fileurl");
       
        File testDir = new File(SAVE_DIR+customerId);
        if (!testDir.exists())
            testDir.mkdirs();
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            response.sendError(500, "Not multipart");
            return;
        }
        System.out.println("received multipart - out = "+out);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            System.out.println("ImageServlet save image start");
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileFileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    int tryNumber = 0;
                    // Write the file
                   /* if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }*/
                   do {
                    file = new File(SAVE_DIR+customerId+File.separator+(tryNumber>0?tryNumber:"")+filename);
                    System.out.println("Trying to save : "+file.getAbsolutePath());
                   } while (file.exists() && tryNumber++<26);
                    fi.write(file);
                    out.println("Uploaded Filename: " + filename + "<br>");
                    out.println("full path = "+file.getAbsolutePath());
                    out.println("%url="+(tryNumber>0?tryNumber:"")+filename+"%");
                    System.out.println("%url="+(tryNumber>0?tryNumber:"")+filename+"%");
                }
            }
            out.println("</body>");
            out.println("</html>");
            System.out.println("ImageServlet save image completed");
        } catch (Exception ex) {
            System.out.println("Exception saving image");
            System.out.println(ex);
            response.sendError(500, "Error saving");
        }
        out.flush();
        out.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
