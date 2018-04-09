package lt.kaunascoding.web.controller;


import lt.kaunascoding.web.model.Preke;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;

import java.util.ArrayList;


@Controller
public class FindingsController {

    @RequestMapping("/findings")
    String output(
            @RequestParam(value = "query", required = false) String q,
            Model model

    ) {
        System.out.println("Vartotojas nori rasti:" + q);


        ArrayList<Preke> skelbiuListas = skelbiuLtPaieska(q);
        ArrayList<Preke> topocentrasListas = topoLtPaieska(q);
        ArrayList<Preke> senukuListas = senukuLtPaieska(q);



        model.addAttribute("skelbiuList", skelbiuListas);
        model.addAttribute("topocentroList", topocentrasListas);
        model.addAttribute("senukuList", senukuListas);

        return "findings";
    }

    private ArrayList<Preke> skelbiuLtPaieska(String querry) {

        ArrayList<Preke> result = new ArrayList<>();
        String skelbiuLTURL = "https://www.skelbiu.lt/skelbimai/?keywords=" + querry;
        Document doc = null;
        try {
            doc = Jsoup.connect(skelbiuLTURL).get();


            Elements skelbiuKainos = doc.select(".simpleAds .itemReview .adsPrice");
            Elements skelbiuNames = doc.select(" .simpleAds .itemReview h3 a");
            Elements skelbiuImages = doc.select(".simpleAds img");
            Elements skelbiuLinkas = doc.select(".simpleAds > a");

            for (int i = 0; i < skelbiuKainos.size(); i++) {
                Element prekesKaina = skelbiuKainos.get(i);
                Element prekesName = skelbiuNames.get(i);
                Element prekesImage = skelbiuImages.get(i);
                Element prekeslinkas = skelbiuLinkas.get(i);


                Preke pr = new Preke();
                pr.seller = "Skelbiu.lt";
                pr.name = prekesName.text();
                pr.imageURL = prekesImage.attr("src");
                pr.linkas = prekeslinkas.attr("href");
                try {
                    pr.price = Float.parseFloat(prekesKaina.text().split(" ")[0]);
                } catch (NumberFormatException nrE) {
                    continue;
                }
                System.out.println(pr.name + ": " + pr.price + " " + pr.imageURL+ ":"+ pr.linkas );
                result.add(pr);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

    private ArrayList<Preke> topoLtPaieska(String querry) {

        ArrayList<Preke> result = new ArrayList<>();
        String topocentroURL = "https://www.topocentras.lt/catalogsearch/result/?q=" + querry;
        Document doc = null;
        try {
            doc = Jsoup.connect(topocentroURL).get();


            Elements skelbiuKainos = doc.select(".additional-info > .price-wrapper > .price");
            Elements skelbiuNames = doc.select(".additional-info > a");
            Elements skelbiuImages = doc.select(".item > .photo > img");
            Elements skelbiuLinkas = doc.select(".clearfix.item > a");

            for (int i = 0; i < skelbiuKainos.size(); i++) {
                Element prekesKaina = skelbiuKainos.get(i);
                Element prekesName = skelbiuNames.get(i);
                Element prekesImage = skelbiuImages.get(i);
                Element prekesprekes = skelbiuLinkas.get(i);


                Preke pr = new Preke();
                pr.seller = "topocentras.lt";
                pr.name = prekesName.text();
                pr.imageURL = prekesImage.attr("data-src");
                pr.linkas = prekesprekes.attr("href");
                try {
                    // 19,90&nbsp;E
                    String price=prekesKaina.text().toString().split("\\u00a0")[0];// 19,90&nbsp;E -> 19,90

                    String[] elements = price.split(",");// 19,90 -> ["19","90"]

                    pr.price = Float.parseFloat(elements[0]+"."+elements[1]);// ["19","90"] -> "19.90" -> 19.90
                } catch (NumberFormatException nrE) {
                    continue;
                }
                System.out.println(pr.name + ": " + pr.price + " " + pr.imageURL+ ":"+ pr.linkas);
                result.add(pr);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
    private ArrayList<Preke> senukuLtPaieska(String querry) {

        ArrayList<Preke> result = new ArrayList<>();
        String skelbiuLTURL = "https://www.senukai.lt/c?q=" + querry;
        Document doc = null;
        try {
            doc = Jsoup.connect(skelbiuLTURL).get();


            Elements senukuKainos = doc.select(".wishlist-prices > span > span:nth-child(1)");
            Elements senukuNames = doc.select(".product-item .item-name a");
            Elements senukuImages = doc.select(".image-wrap > img");
            Elements senukuLinkas = doc.select(".item-name > a");

            for (int i = 0; i < senukuKainos.size(); i++) {
                Element prekesKaina = senukuKainos.get(i);
                Element prekesName = senukuNames.get(i);
                Element prekesImage = senukuImages.get(i);
                Element prekeslinkas = senukuLinkas.get(i);


                Preke pr = new Preke();
                pr.seller = "senukai.lt";
                pr.name = prekesName.text();
                pr.imageURL = prekesImage.attr("src");
                pr.linkas = prekeslinkas.attr("href");
                try {
                    pr.price = Float.parseFloat(prekesKaina.text().replace(',','.'));
                } catch (NumberFormatException nrE) {
                    continue;
                }
                System.out.println(pr.name + ": " + pr.price + " " + pr.imageURL+ ":"+ pr.linkas );
                result.add(pr);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
//    @RequestMapping("/langasDu")
//    String atsakymas(
//            @RequestParam(value = "from", required = false) String from,
//            @RequestParam(value = "to", required = false) String to,
//            @RequestParam(value = "depart", required = false) String depart,
//            @RequestParam(value = "return", required = false) String returndate,
//            @RequestParam(value = "Travellers", required = false) String travellers,
//            Model model) {
//
//        URL url = null;
//        try {
//            url = new URL("http://apigateway.ryanair.com/pub/v1/flightinfo/2/flights");
//
//        JSONTokener tokener = new JSONTokener(new InputStreamReader(url.openStream()));
//        JSONObject root = new JSONObject(tokener);
//
//            System.out.println(root);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//
//
//        model.addAttribute("labas", "Veikiu!!!!");
//        return "langasDu";
//    }

}
