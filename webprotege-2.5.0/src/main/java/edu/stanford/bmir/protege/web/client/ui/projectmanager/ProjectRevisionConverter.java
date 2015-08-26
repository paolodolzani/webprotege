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

/**
 *
 * @author antonio
 */
public class ProjectRevisionConverter {
    private final ProjectId projectid;
    
    private final RevisionNumber revisionnumber;
    
    private final DownloadFormatExtension downloadformatextension;
    
    private String convertedProject;
    
    public ProjectRevisionConverter(ProjectId projectid, RevisionNumber revisionnumber){
        this.projectid = checkNotNull(projectid);
        this.revisionnumber= checkNotNull(revisionnumber);
        this.downloadformatextension= DownloadFormatExtension.owl;
    }
    
    public void convert(){
        //metodo da implementare
    }
    
    public String getConvertedProject(){
        return this.convertedProject;
    }
}
