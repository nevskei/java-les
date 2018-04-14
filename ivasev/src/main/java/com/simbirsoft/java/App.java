package com.simbirsoft.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
    public static void main( String[] args ) throws IOException
    {
        Map<String, String> mapName = new HashMap<String, String>() {
            {
                put("FIO", "ФИО");
                put("DOB", "Дата рождения");
                put("phone", "Телефон");
                put("email", "e-mail");
                put("skype", "Skype");
                put("avatar", "");
                put("target", "Цель");
                put("experience", "Опыт работы");
                put("education", "Образование");
                put("addeducation", "Доп. образ. и курсы");
            }
        };

        Map<String, String> mapTemplate = new HashMap<>();

        Path path = Paths.get("src/main/java/com/simbirsoft/java/properties");
        if (Files.exists(path)) {
            Files.lines(path).forEach((strln) -> {
                int seporat = strln.indexOf("=");
                String tag = strln.substring(0, seporat);
                if (mapName.get(tag) != null) {
                    mapTemplate.put(tag, strln.substring(seporat + 1));
                }
            });
            StringBuilder html = new StringBuilder("<!DOCTYPE html><html>\n"
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
                + "    <body style=\"width: 21cm; height: 29.7cm; \">");
            html.append("<h3 class=\"text-center\">Резюме</h3><p class=\"text-center\">на должность Java-стажор</p><strong></strong><div class=\"row list-group-item\">\n"
                + "  <div class=\"col-md-8\">");
            List<String> itemsRow1 = new ArrayList<>();
            itemsRow1.add("FIO");
            itemsRow1.add("DOB");
            itemsRow1.add("phone");
            itemsRow1.add("email");
            itemsRow1.add("skype");
            itemsRow1.forEach((strln) -> {
                if (mapTemplate.get(strln) != null) {
                    html.append("<div class=\"row\">\n"
                        + "  <div class=\"col-md-4 text-right\"><strong>"+mapName.get(strln)+": </strong></div>\n"
                        + "  <div class=\"col-md-8\">"+mapTemplate.get(strln)+"</div>\n"
                        + "</div>");
                }
            });
            html.append("</div>\n"
                + "  <div class=\"col-md-4\">");

                if (mapTemplate.get("avatar") != null) {
                    html.append("<img src=\""+mapTemplate.get("avatar")+"\">");
                }
            html.append("</div>\n"
                + "</div>\n");

            List<String> itemsRow2 = new ArrayList<>();

            itemsRow2.add("target");
            itemsRow2.add("experience");
            itemsRow2.add("education");
            itemsRow2.add("addeducation");

            itemsRow2.forEach((String strln) -> {
                if (mapTemplate.get(strln) != null) {
                    html.append("<div class=\"row list-group-item\"><div><strong>"+mapName.get(strln)+": </strong></div>\n"
                        + "  <div>"+mapTemplate.get(strln)+"</div></div>");
                }
            });
            html.append( "    </body>\n"
                + "</html>");
            html.append("");
            byte[] src = html.toString().getBytes();
            try {
                Files.write(Paths.get("src/main/java/com/simbirsoft/java/index.html"), src);
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Файл не существует.");
        }
    }
}
