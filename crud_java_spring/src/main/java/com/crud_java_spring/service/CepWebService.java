package com.crud_java_spring.service;

import java.net.URL;
import java.util.Iterator;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


@Getter@Setter
public class CepWebService {
	
    private String complemento = "";
    private String cidade = "";
    private String bairro = "";
    private String logradouro = "";
    private int resultado = 0;

    @SuppressWarnings("rawtypes")
    public CepWebService(String cep) {

        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");

            Document document = getDocumento(url);

            Element root = document.getRootElement();

            for (Iterator i = root.elementIterator(); i.hasNext();) {
                Element element = (Element) i.next();

                if (element.getQualifiedName().equals("complemento")) {
                    setComplemento(element.getText());
                }

                if (element.getQualifiedName().equals("cidade")) {
                    setCidade(element.getText());
                }

                if (element.getQualifiedName().equals("bairro")) {
                    setBairro(element.getText());
                }

                if (element.getQualifiedName().equals("logradouro")) {
                    setLogradouro(element.getText());
                }

                if (element.getQualifiedName().equals("resultado")) {
                    setResultado(Integer.parseInt(element.getText()));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);

        return document;
    }

   
}
