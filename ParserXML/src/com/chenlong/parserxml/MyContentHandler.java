package com.chenlong.parserxml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler{
	private String hisname, address, money, sex, status;
	private String tagName;
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("-----------begin-------------");
	}
	

	@Override
	public void endDocument() throws SAXException {
		System.out.println("-----------end-------------");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		tagName=localName;
		System.out.println(uri+" | "+localName+" | "+qName);
		if(attributes==null){
			return ;
		}
		for(int i=0;i<attributes.getLength();i++){
			System.out.println(attributes.getLocalName(i)+"="+attributes.getValue(i));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("End the element: "+localName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String string=new String(ch,start, length);
		System.out.println(string);
		if(tagName.equals("name")){
			hisname=string;
		}else if(tagName.equals("sex")){
			sex=string;
		}else if(tagName.equals("status")){
			status=string;
		}else if(tagName.equals("address")){
			address=string;
		}else if(tagName.equals("money")){
			money=string;
		}
	}
	

}
