package pacote2;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Img_Parser
{
   public static int optionSelection;
   public static int optionSelection2;
   public static String linkInicial;
   public static Document pagina_HTML_Capturada;
   public static ArrayList<String> listaLinks;
   public static ArrayList<String> listaIMG;
   public static ArrayList<Element> listaElements;
   
   public static String extensao_IMG = ".jpg";
   public static String caminho_IMG = "H:/Temp/";
   public static String nome_IMG = "NOME";
   public static int valor_inicial_IMG;
   
   
   //------------------------------------------------------------------------------
   //-------------------------CAPTURADOR DA PAGINA---------------------------------
   //------------------------------------------------------------------------------
   public static Document capturaPagina_HTML(String link) throws IOException
    {
        pagina_HTML_Capturada = Jsoup.connect(link).get();
        
     return pagina_HTML_Capturada;
    }
 
   
   
   //------------------------------------------------------------------------------
   //--------------------------------MAIN------------------------------------------
   //------------------------------------------------------------------------------  
   public static void main(String[] args) throws InterruptedException, IOException
    {
        do{
            System.out.println("<----MENU PRINCPAL---->");
            System.out.println("");
            System.out.println("1. USO DE LINKS INTERMEDIÁRIOS");
            System.out.println("2. USO DE LINKS FINAIS");
            System.out.println("0. SAIR");
            System.out.println("");
            System.out.print("Digite a opção: ");
            Scanner in = new Scanner (System.in); optionSelection = in.nextInt();
            
            switch (optionSelection){
                case 1:
                    //------------------------------------------------------------------------------
                            //----------------------USO DE LINKS INTERMEDIÁRIOS [ INÍCIO ]------------------
                            //------------------------------------------------------------------------------
                             //       
                     //              linkInicial = "https://www.marvel.com/comics/series/2262/wolverine_1988_-_2003?byZone=marvel_site_zone&offset=0&byType=comic_series&dateStart=&dateEnd=&orderBy=release_date+asc&byId=2262&limit=108&count=20&totalcount=128";
                             //       
                             //     
                             //       /*if (!linkInicial.endsWith("/"))                                  	//Verificador de contra barra (negação)
                             //        {
                             //        linkInicial = linkInicial + "/";
                             //        }*/
                             //        
                             //        
                             //        System.out.println("Link inicial: " + linkInicial);


                     //            capturaPagina_HTML(linkInicial);                                       //Opção de GERAR LINKS INTERMEDIÁRIOS
                     //            System.out.println("Página HTML capturada.");
                     //
                     //            gerarLinks();   
                     //            capturaLinks_Unico(pagina_HTML_Capturada);
                     //            capturaLinks_2Way(pagina_HTML_Capturada);
                     //
                     //            capturaLinks_comPags(pagina_HTML_Capturada);
                     //
                     //
                     //
                     //
                     //               
                     //            downloadIMG(listaIMG);
                     //          downloadIMG_Alternative_fromElements(listaElements);




                             //------------------------------------------------------------------------------
                             //----------------------USO DE LINKS INTERMEDIÁRIOS [ FIM ]---------------------
                             //------------------------------------------------------------------------------
                    break;    

                case 2:
                    //------------------------------------------------------------------------------
                    //---------------------USO DE LINKS FINAIS [ INÍCIO ]---------------------------
                    //------------------------------------------------------------------------------     



                   gerarLinks();                                                            //Opção de GERAR LINKS FINAIS
                   
                   do{
                        System.out.println("");
                        System.out.println("<----MENU DOWNLOAD---->");
                        System.out.println("");
                        System.out.println("NOME DO ARQUIVO: " + nome_IMG);
                        System.out.println("CAMINHO: " + caminho_IMG);
                        System.out.println("EXTENSÂO: " + extensao_IMG);
                        System.out.println("------------------");
                        System.out.println("1 -  Alterar nome");
                        System.out.println("2 -  Alterar caminho");
                        System.out.println("3 -  Alterar extensão");
                        System.out.println("0 -  CONTINUAR");
                        System.out.println("");
                        System.out.print("Escolha opção: ");
                        in = new Scanner (System.in); optionSelection2 = in.nextInt();

                        switch (optionSelection2){
                            case 1:
                                System.out.println("");
                                System.out.println("Digite o NOME:");
                                System.out.print("> ");  in = new Scanner (System.in); nome_IMG = in.nextLine(); 
                                break;
                                
                            case 2:
                                System.out.println("");
                                System.out.println("Digite o CAMINHO:");
                                System.out.println("formato: [ H:/Temp/ ]");
                                System.out.print(">");  in = new Scanner (System.in); caminho_IMG = in.nextLine();
                                break;
                                
                            case 3:
                                System.out.println("");
                                System.out.println("Digite a EXTENSÃO:");
                                System.out.println("formato: [ .jpg ]");
                                System.out.print(">"); in = new Scanner (System.in); extensao_IMG = in.nextLine().trim();
                                break;
                                
                            case 0:
                                break;
                                
                            default:
                                System.out.println("");
                                System.out.println("Valor inexistente");
                                System.out.println("");
                                break;
                        }
                   }
                   while(optionSelection2 != 0);
                   
                   
                   int cont = 1;                                                            //Percorre LISTA DE LINKS FINAIS
                   for (String i : listaLinks)
                   {
                       System.out.println("");
                       System.out.println("Link " + cont + "/" + listaLinks.size() + ": " + i);
                       String link = i;
                       capturaPagina_HTML(link);
                       capturaIMG(pagina_HTML_Capturada);
                       downloadIMG(listaIMG);
                       cont++;
                   }

                    //------------------------------------------------------------------------------
                    //---------------------USO DE LINKS FINAIS [ FINAL ]---------------------------
                    //------------------------------------------------------------------------------
                    break;
                
                
                case 0:
                    System.out.println("");
                    System.out.println("Término do programa");
                    System.out.println("");
                    break;
                
                
                default:
                    System.out.println("");
                    System.out.println("Valor inexistente");
                    System.out.println("");
            }
        }
        
        while (optionSelection != 0);
    }
    
   
   
   
   
   
    public static ArrayList<String> gerarLinks() throws IOException
    {
        String url;
        int valor_final;
        listaLinks = new ArrayList<>();
        
        System.out.println("");
        System.out.println("Digite o ENDEREÇO da página (sem valores no final):");
        System.out.println("Formato: [ https://viewcomics.me/detective-comics-1937/issue- ]");
        System.out.print(">"); Scanner in = new Scanner (System.in); url = in.nextLine().trim();
                
        System.out.println("");
        System.out.println("Digite o valor INICIAL:");
        System.out.print(">"); in = new Scanner (System.in); valor_inicial_IMG = in.nextInt();
                
        System.out.println("");
        System.out.println("Digite o valor FINAL:");
        System.out.print(">"); in = new Scanner (System.in); valor_final = in.nextInt();
                
        System.out.println("");
        System.out.println("BUSCA POR:");
        System.out.println(url + valor_inicial_IMG);
        System.out.println("ATÉ");
        System.out.println(url + valor_final);
        
        for (int cont = valor_inicial_IMG; cont <= valor_final; cont++)
        {
            listaLinks.add(url + cont);
        }
    return listaLinks; 
    }
    
    
    
    
    
    public static ArrayList<String> capturaIMG(Document paginaHTML) throws InterruptedException
    {
        Elements imgElements = paginaHTML.select(".chapter-container img"); 
        listaIMG = new ArrayList<>();
        
        for (Element i : imgElements) 
        {
            System.out.println("Encontrado: " + i.attr("abs:src"));
            listaIMG.add(i.attr("abs:src"));
        }
    return listaIMG;
    }
    
    
    public static ArrayList<String> capturaLinks_Unico(Document paginaHTML)
    {
        Elements elements = paginaHTML.select(".cover-image.notranslate_alt");
        listaLinks = new ArrayList<>();
        
        for (Element i : elements) 
        {
            System.out.println("Encontrado: " + i.attr("abs:src"));
            listaLinks.add(i.attr("abs:src"));
        }
     
     return listaLinks;
    }
    
    
    
    public static ArrayList<String> capturaLinks_2Way(Document paginaHTML) throws IOException
    {
        Elements elements = paginaHTML.select(".row-item-image-url[href~=marvel]"); 
        
        listaLinks = new ArrayList<>();
        listaIMG = new ArrayList<>();
        int cont = 0;
        
        for (Element i : elements) 
        {
            System.out.println("Encontrado: " + i.attr("abs:href"));
            listaLinks.add(i.attr("abs:href"));
        }
        
        System.out.println(""); System.out.println("Link(s) capturado(s).");
        
        for (String ii : listaLinks) 
        {
           
           paginaHTML = Jsoup.connect(ii).get();
           elements = paginaHTML.select(".frame-img[src~=clean.jpg],[src~=detail.jpg]");
           System.out.println("Encontrado: " + elements);
           listaIMG.add(elements.attr("abs:src"));
           cont++;
        }
        System.out.println("Adicionado " + cont + " elemento(s)!");
        System.out.println("");
     return listaIMG;
    }
    
    
   public static ArrayList<Element> capturaLinks_comPags(Document paginaHTML) throws IOException
    {
        Elements classNext = paginaHTML.select(".next").remove();                                   //Detecção da QUANTIDADE DE PÁGINAS SEGUINTES e REMOÇÃO de uma linha (1)
        classNext = paginaHTML.select(".pagination a[href]");                                       //Detecção da QUANTIDADE DE PÁGINAS SEGUINTES (2)
        
        listaElements = new ArrayList<>();
        int cont = 0;
        
        for (Element ii : classNext) 
        {
           String linkNextPage = ii.attr("abs:href");
           paginaHTML = Jsoup.connect(linkNextPage).get();
           Elements elements = paginaHTML.select(".cover-image.notranslate_alt");
           
           
           for (Element i : elements) 
           {
               cont++;
               listaElements.add(i);
           }
        }
        System.out.println("Adicionado " + cont + " elemento(s)!");
        System.out.println("");
        
    return listaElements;
    }
    
    
    public static void downloadIMG(ArrayList<String> listaIMG) throws IOException, InterruptedException 
    {
        for (String imgURL : listaIMG)
        {
            
            String fileName = caminho_IMG +                                                    //Caminho
                              nome_IMG + " " + String.format("%03d", valor_inicial_IMG) +      //Nome
                              extensao_IMG;                                                    //Extensão
            

            if (imgURL .contains("detail.jpg"))                                               //Clausula condidicional para RENOMEAR arquivo
            {
               fileName = fileName.replace(".jpg"," (small).jpg");
               System.out.println("novo nome: " + fileName);
            }

            
            URL url = new URL(imgURL);
            
            InputStream in = url.openStream();
            
            //write bytes to the output stream
            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) 
            {
                //write bytes to the output stream
                for (int b; (b = in.read()) != -1;) 
                {
                    out.write(b);
                }
                
                System.out.println(fileName + " salvo!");
                valor_inicial_IMG++;
                
                //close the stream
                out.close();
            }
        }
    }    
    
    
    
    public static void downloadIMG_Alternative_fromElements(ArrayList<Element> listaIMG) throws IOException, InterruptedException 
    {
        for (Element i : listaIMG)
        {
            String fileName = caminho_IMG +                             //Caminho
                              i.attr("title") +                         //Nome Alternativo (retirado do TITLE)
                              extensao_IMG;                             //Extensão
            
            //Clausula condidicional para RENOMEAR arquivo
//            if (imgURL .contains("detail.jpg"))
//            {
//               fileName = fileName.replace(".jpg"," (small).jpg");
//               System.out.println("novo nome: " + fileName);
//            }
            
            URL url = new URL(i.attr("abs:src").replace("/180/","/1200/").replace("/30/","/1200/"));
            
            InputStream in = url.openStream();
            
            //write bytes to the output stream
            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) 
            {
                //write bytes to the output stream
                for (int b; (b = in.read()) != -1;) 
                {
                    out.write(b);
                }
                
                System.out.println(fileName + " salvo!");
                valor_inicial_IMG++;
                
                //close the stream
                out.close();
            }
        }
    }
}           
