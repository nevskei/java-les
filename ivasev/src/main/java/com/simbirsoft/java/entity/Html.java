/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.java.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Html implements HtmlInterface {
    private StringBuilder html = new StringBuilder("<!DOCTYPE html><html>\n"
        + "    <head>\n"
        + "       <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
        + "       <title>Java</title>\n"
        + "       <!-- Latest compiled and minified CSS -->\n"
        + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\n"
        + "\n"
        + "<!-- Optional theme -->\n"
        + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">\n"
        + "\n"
        + "<!-- Latest compiled and minified JavaScript -->\n"
        + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>"
        + "    </head>\n"
        + "    <body style=\"width: 21cm; height: 29.7cm; \"><h3 class=\"text-center\">Резюме</h3><p class=\"text-center\">на должность Java-стажор</p><strong></strong><div class=\"row list-group-item\">\n"
        + "  <div class=\"col-md-8\">");
    private String endHtml = new String("    </body>\n"
        + "</html>");
    
    private List<String> firstBlockItems = new ArrayList<>();
    private List<String> secondBlockItems = new ArrayList<>();
    
    public Html(Properties properties) {
        
            firstBlockItems.add("FIO");
            firstBlockItems.add("DOB");
            firstBlockItems.add("phone");
            firstBlockItems.add("email");
            firstBlockItems.add("skype");
            firstBlockItems.forEach((strln) -> {
                Property property =  properties.get(strln);
                if (property != null) {
                    html.append("<div class=\"row\">\n"
                        + "  <div class=\"col-md-4 text-right\"><strong>"+property.getTitle()+": </strong></div>\n"
                        + "  <div class=\"col-md-8\">"+property.getValue()+"</div>\n"
                        + "</div>");
                }
            });
            html.append("</div>\n"
                + "  <div class=\"col-md-4\">");

                Property avatar =  properties.get("avatar");
                if (avatar != null && !avatar.getValue().isEmpty()) {
                    html.append("<img src=\""+avatar.getValue()+"\">");
                }
            html.append("</div>\n"
                + "</div>\n");


            secondBlockItems.add("target");
            secondBlockItems.add("experience");
            secondBlockItems.add("education");
            secondBlockItems.add("addeducation");

            secondBlockItems.forEach((String strln) -> {
                Property property =  properties.get(strln);
                if (property != null) {
                    html.append("<div class=\"row list-group-item\"><div><strong>"+property.getTitle()+": </strong></div>\n"
                        + "  <div>"+property.getValue()+"</div></div>");
                }
            });
    }
    
    public StringBuilder get() {
        html.append(endHtml);
        return html;
    }
}
