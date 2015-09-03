/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.bmir.protege.web.server.projectconvert;

import com.gwtext.client.widgets.MessageBox;
import edu.stanford.bmir.protege.web.server.filedownload.DownloadFormat;
import edu.stanford.bmir.protege.web.server.owlapi.OWLAPIProjectDocumentStore;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import edu.stanford.bmir.protege.web.shared.revision.RevisionNumber;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author antonio
 */
public class OWLPIProjectConverter {
    
    private ProjectId projectId;
    
    private RevisionNumber revision;
    
    private DownloadFormat format;
    
    public OWLPIProjectConverter(ProjectId projectId, RevisionNumber revision, DownloadFormat format){
        this.projectId=projectId;
        this.revision=revision;
        this.format=format;
    }
    
    public void writeproject(HttpServletResponse response,OutputStream outputstream) throws IOException{
        try{
            OWLAPIProjectDocumentStore documentstore = OWLAPIProjectDocumentStore.getProjectDocumentStore(projectId);
            response.setContentType("text/plain");
            if(revision.isHead())
            {
                documentstore.exportProject(outputstream, format);
            }
            else
            {
                documentstore.exportProjectRevision(revision, outputstream, format);
            }
        }
        catch (OWLOntologyStorageException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public String convertontology(){
        String convertedontology="";
        try{
            OWLAPIProjectDocumentStore documentstore = OWLAPIProjectDocumentStore.getProjectDocumentStore(projectId);
            if(revision.isHead())
            {
                convertedontology=documentstore.convertproject(format);
            }
         /*   else
            {
                convertedontology=documentstore.convertProjectRevision(revision,format);
            } */
        }
            catch(Exception ex)
                    {
                    
                    }
        return convertedontology;
    }
}
