/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.bmir.protege.web.client.ui.projectmanager;

import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import edu.stanford.bmir.protege.web.shared.revision.RevisionNumber;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.URL;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 *
 * @author chiara
 */
public class ProjectRevisionBundleConverter {

    private final ProjectId projectid;

    private final RevisionNumber revisionnumber;

    public ProjectRevisionBundleConverter(ProjectId projectid, RevisionNumber revisionnumber) {
        this.projectid = checkNotNull(projectid);
        this.revisionnumber = checkNotNull(revisionnumber);
    }

    public void convert() {
        String encodedProjectName = URL.encode(projectid.getId());
        String requestData = "";
        String baseURL = GWT.getHostPageBaseURL();
        String convertURL = baseURL + "convert?ontology=" + encodedProjectName + "&revision=" + revisionnumber.getValue();
        //  boolean opened=openedPage();
        //controllo se la pagina è stata aperta da un'altra pagina, in modo da avere un riferimento a cui inviare l'ontologia in RDF/XML
        //  if(opened==true)
        //  { //invio la richiesta alla servlet per ottenere l'ontologia convertita!!!
        RequestBuilder request = new RequestBuilder(RequestBuilder.GET, convertURL);
        try {
            request.sendRequest(requestData.toString(), new RequestCallback() //requestData vuoti poichè i parametri di interesse sono già specificati nell'URL
            {
                public void onError(Request request, Throwable e) {
                    Window.alert(e.getMessage());
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) //controllo che la response sia stata ricevuta in maniera corretta (stato 200)
                    {
                        String convertedProject = new String(response.getText());
                        returnTObundle(convertedProject);   //funzione javascript che invia la stringa ottenuta a TRILL on SWISH
                    } else {
                        Window.alert("Received HTTP status code other than 200 : " + response.getStatusText());
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
            Window.alert(e.getMessage());
        }
        //}
    }

    /* Funzioni javascript utilizzate, scritte seguendo la sintassi di JSNI*/
 /*   
    private native boolean openedPage()/*-{ 
             var Windowreference=$wnd.opener;
             if(Windowreference==null)
            {
            $wnd.alert("devi aprire la pagina da http://localhost:3020/trill_on_swish per utilizzare questa funzionalità");
            return false;
            }
            return true;
             }-;*/
//    private void returnTObundle(String converted) {
////        Window.open("http://localhost:8080/BundleQueryInterface/BundleQuery.jsp","_blank","");
////        FormPanel form = new FormPanel("_blank");
////        Window.alert("1");
//        SimplePanel panel = new SimplePanel();
//        FormPanel form = new FormPanel();
//        Element elFp = form.getElement();
//        elFp.setAttribute("target", "_blank");
//        panel.add(form);
////        Window.alert("2");
////        form.setAction("https://localhost:8080/BundleQueryInterface/BundleQuery.jsp");
//        form.setAction("https://localhost:8080/webprotege-2.5.0/");
////        Window.alert("3");
//        form.setMethod(FormPanel.METHOD_GET);
////        Window.alert("4");
//        Hidden onto = new Hidden("ontology", "aaaaqaa");
////        Window.alert("5");
//        form.setEncoding(FormPanel.ENCODING_URLENCODED);
//        form.add(onto);
//        Window.alert("" + form.isAttached());
//        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//            @Override
//            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
//                Window.alert("ok " + event.toDebugString());
//            }
//        });
//        form.addSubmitHandler(new FormPanel.SubmitHandler() {
//            @Override
//            public void onSubmit(FormPanel.SubmitEvent event) {
//                Window.alert("ho fatto submit " + event.toDebugString());
//            }
//        });
////        Window.alert("6");
//
//        form.submit();
//        Window.alert("7");
//
//    }
    private native void returnTObundle(String converted) /*-{
            console.log("returnTObundle");
            var url = "http://localhost:8080/BundleQueryInterface/BundleQuery.jsp";
       var windowName = '' + Math.floor(Math.random() * 10000000) ; //we need some name to reference it later, so assign a random number as the name (make sure the number doesn't have a decimal because IE will choke on it)
       var popupWindow =  window.open('', windowName, '');
       var form = document.createElement("form");
       console.log("creato form")
       form.setAttribute("method", "post");
       form.setAttribute("action", url);
       form.setAttribute("target", windowName);
       //form.setAttribute("enctype", "multipart/form-data");
       var hiddenField = document.createElement("input");
       hiddenField.setAttribute("type", "hidden");
       hiddenField.setAttribute("name", "ontology");
       hiddenField.setAttribute("value", converted);
       form.appendChild(hiddenField);
       console.log("fine creazione form");
            console.log(form);
       document.getElementsByTagName('body')[0].appendChild(form);
       console.log("form append");
       while(!popupWindow){
         //wait for popupwindow to open before submitting the form
       }
       console.log("inizio submit");
       form.submit();
       console.log("fine submit");

    }-*/;

}
