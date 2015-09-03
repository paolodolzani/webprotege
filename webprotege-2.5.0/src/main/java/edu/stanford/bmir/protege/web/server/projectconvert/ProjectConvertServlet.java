/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.bmir.protege.web.server.projectconvert;

import edu.stanford.bmir.protege.web.server.filedownload.DownloadFormat;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import edu.stanford.bmir.protege.web.shared.revision.RevisionNumber;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author antonio
 */
public class ProjectConvertServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
   ProjectConversionParameters convParameters = new ProjectConversionParameters(request);
    if(convParameters.isProjectConvert()){
        ProjectId projectId = convParameters.getProjectId();
        RevisionNumber revisionnumber = convParameters.getRequestedRevision();
        DownloadFormat format = convParameters.getFormat();
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); //usato per scrivere output
        OWLPIProjectConverter converter=new OWLPIProjectConverter(projectId,revisionnumber,format);
        converter.writeproject(response,bos);
        bos.flush();
    }
    else{
        System.out.println("richiesta errata");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
    
    }  
}
