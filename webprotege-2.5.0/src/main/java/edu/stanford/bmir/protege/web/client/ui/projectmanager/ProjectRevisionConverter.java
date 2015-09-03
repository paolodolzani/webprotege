/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.bmir.protege.web.client.ui.projectmanager;

import edu.stanford.bmir.protege.web.shared.download.DownloadFormatExtension;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import edu.stanford.bmir.protege.web.shared.revision.RevisionNumber;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.gwtext.client.widgets.MessageBox;

/**
 *
 * @author antonio
 */
public class ProjectRevisionConverter {
    private final ProjectId projectid;
    
    private final RevisionNumber revisionnumber;
    
    private String convertedProject;
    
    public ProjectRevisionConverter(ProjectId projectid, RevisionNumber revisionnumber){
        this.projectid = checkNotNull(projectid);
        this.revisionnumber= checkNotNull(revisionnumber);
    }
    
    public void convert(){
        String encodedProjectName = URL.encode(projectid.getId());
        String baseURL = GWT.getHostPageBaseURL();
        String convertURL = baseURL + "convert?ontology=" + encodedProjectName + "&revision=" + revisionnumber.getValue();
        Window.open(convertURL,"Convert and return to trill", "");        
    }
    
    public String getConvertedProject(){
        return this.convertedProject;
    }
}
