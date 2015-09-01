/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.bmir.protege.web.client.ui.projectmanager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import edu.stanford.bmir.protege.web.client.download.ProjectRevisionDownloader;
import edu.stanford.bmir.protege.web.shared.download.DownloadFormatExtension;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import edu.stanford.bmir.protege.web.shared.revision.RevisionNumber;
/**
 *
 * @author antonio
 */
public class ConvertAndReturnRequestHandlerImpl implements ConvertAndReturnRequestHandler{
    @Override
    public void handleProjectConvertRequest(final ProjectId projectId){
        GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onFailure(Throwable reason) {
            }

            @Override
            public void onSuccess() {
               /* DownloadSettingsDialog.showDialog(new DownloadFormatExtensionHandler() {
                    @Override
                    public void handleDownload(DownloadFormatExtension extension) {*/
                        doConversion(projectId, DownloadFormatExtension.owl);
                        //doConversion(projectID);
                    }
                });
            }
       /* });*/
    
    
     private void doConversion(ProjectId projectId, DownloadFormatExtension extension) {
        RevisionNumber head = RevisionNumber.getHeadRevisionNumber();
        ProjectRevisionDownloader downloader = new ProjectRevisionDownloader(projectId, head, extension);
        downloader.download();
        jscode("ciao mondo");
        //altjscode("ciao");
    }
     private native void jscode(String messaggio)/*-{ 
             $doc.write("<form target='_blank' method='post' action='http://127.0.0.1:3020/trill_on_swish/' name='ConversionHiddenForm' ><input type='hidden' name='convertedProject' value=''></form>");
             $doc.ConversionHiddenForm.convertedProject.value = messaggio; 
             $doc.ConversionHiddenForm.submit();
             $doc.location.href="http://127.0.0.1:8084/webprotege/";
             }-*/;
     private native void altjscode(String messaggio)/*-{
             var convertedProject= messaggio;
             jQuery.get("http://www.google.it",convertedProject,null,String);
             }-*/;
}
    /*private void doConversion(ProjectId projectId){
        RevisionNumber head=RevisionNumber.getHeadRevisionNumber();
        ProjectRevisionConverter converter=new ProjectRevisionConverter(projectId,head);
        converter.convert();
        jscode(converter.getConvertedProject());
    }
}*/

     