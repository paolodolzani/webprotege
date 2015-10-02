/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.bmir.protege.web.client.ui.projectmanager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import edu.stanford.bmir.protege.web.shared.revision.RevisionNumber;

/**
 *
 * @author chiara
 */
public class BundleRequestHandlerImpl implements BundleRequestHandler {

    private int mode = 0;

    @Override
    public void handleProjectConvertRequest(final ProjectId projectId, int mode) {
        this.mode = mode;
        GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onFailure(Throwable reason) {
            }

            @Override
            public void onSuccess() {

                doConversion(projectId, getMode());   //provvede ad effettuare la conversione passando l'ID del progetto
                //mode 0=trill 1=bundle
            }
        });
    }

    private void doConversion(ProjectId projectId, int mode) {
        RevisionNumber head = RevisionNumber.getHeadRevisionNumber();
        if (mode == 0) {
            ProjectRevisionConverter converter = new ProjectRevisionConverter(projectId, head);
            converter.convert();
        } else {
            ProjectRevisionBundleConverter converter = new ProjectRevisionBundleConverter(projectId, head);
            converter.convert();
        }
    }

    private int getMode() {
        return this.mode;
    }

}
