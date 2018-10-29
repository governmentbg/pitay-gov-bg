package indexbg.pdoi.system;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.aspose.words.Bookmark;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.CompositeNode;
import com.aspose.words.Document;
import com.aspose.words.HeaderFooter;
import com.aspose.words.HeaderFooterType;
import com.aspose.words.HorizontalAlignment;
import com.aspose.words.ImportFormatMode;
import com.aspose.words.Node;
import com.aspose.words.NodeImporter;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.ProtectionType;
import com.aspose.words.RelativeHorizontalPosition;
import com.aspose.words.RelativeVerticalPosition;
import com.aspose.words.SaveFormat;
import com.aspose.words.Section;
import com.aspose.words.SectionStart;
import com.aspose.words.Shape;
import com.aspose.words.ShapeType;
import com.aspose.words.VerticalAlignment;
import com.aspose.words.WrapType;

public class MSWordBookmarks {

	
	static final Logger LOGGER = Logger.getLogger(MSWordBookmarks.class);
	
	/**
	 * Методът проверява за наличието на конкретен Bookmar в MS Word document/docx
	 * и му въвежда значение
	 * 
	 * @param byte [] baiStream:  FileContent (class Files) на MS Word document/docx;
	 * @param ArrayList<String> paramS: значения на bookmarks
	   
	 * @return byte[] bytearray:  FileContent (class Files) със значение в bookmark; 
	 */
	
	public  byte[] setBookmarksObrazci(byte [] byteStream, ArrayList<String> paramS) throws Exception {
		
		byte [] bytearray = null;
		
		
		com.aspose.words.Document docFromDB = new com.aspose.words.Document(new ByteArrayInputStream(byteStream));
		com.aspose.words.DocumentBuilder builder = new com.aspose.words.DocumentBuilder(docFromDB);
		builder.moveToDocumentStart();
		BookmarkCollection bmc = docFromDB.getRange().getBookmarks();
		
		String nameBM;
		
		for (int i = 0; i < paramS.size(); i++) {			

			nameBM = "";
			if (i < 10){
				nameBM = "book0" + i; 
			}else{
				nameBM = "book" + i; 
			}
			
			if (bmc.get(nameBM) != null){
				bmc.get(nameBM).setText(paramS.get(i));
				//System.out.println(bmc.get(nameBM).getText());
				
			}
				
		}
		
		ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
		docFromDB.save(dstStream, SaveFormat.DOC);
		bytearray = dstStream.toByteArray();

		return bytearray;
	}
	
	
	
	public  byte[] setBookmarks(byte [] byteStream, HashMap<String,String> hashBooks,boolean protect)throws Exception{
		return setBookmarks(byteStream, hashBooks, protect ,false);
	}
	
	
	/**
	 * Върти bookmarks и взема стойностите от мап-а.
	 * @param byteStream
	 * @param hashBooks
	 * @return
	 * @throws Exception
	 */
	public  byte[] setBookmarks(byte [] byteStream, HashMap<String,String> hashBooks,boolean protect,boolean wm) throws Exception {
		setLicense();
		byte [] bytearray = null;
		
		
		com.aspose.words.Document docFromDB = new com.aspose.words.Document(new ByteArrayInputStream(byteStream));
		com.aspose.words.DocumentBuilder builder = new com.aspose.words.DocumentBuilder(docFromDB);
		builder.moveToDocumentStart();
		BookmarkCollection bmc = docFromDB.getRange().getBookmarks();
		
		
		for (Bookmark bookmark : bmc) {
			if (hashBooks.containsKey(bookmark.getName()) && hashBooks.get(bookmark.getName())!=null) {
				bookmark.setText(hashBooks.get(bookmark.getName()));	
			}
//			System.out.println("hashBook.put(\""+bookmark.getName()+"\",\"\");");
		}
	
		ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
		
		if(protect){
			docFromDB.protect(ProtectionType.READ_ONLY, "secret2015");
		}
		
		if(wm){
			insertWatermarkText(docFromDB,"чернова");
		}
		
		docFromDB.save(dstStream, SaveFormat.DOC);
		
		
		
		bytearray = dstStream.toByteArray();

		return bytearray;
	}
	
	
	/**
	 *  Зарежда лиценза за работа с MS Word documents.
	 * 
	*/
	
	public void setLicense() throws Exception {
		com.aspose.words.License license = new com.aspose.words.License();
		String name="Bullshit.iv";
		license.setLicense(getClass().getClassLoader().getResourceAsStream(name));
	}
	
	
	public byte[] insertShablons(byte [] byteStream1, byte [] byteStream2, String bookMarkAfter) throws Exception {
		
		byte [] bytearray = null;
		
		com.aspose.words.Document doc1 = new com.aspose.words.Document(new ByteArrayInputStream(byteStream1));
		com.aspose.words.Document doc2 = new com.aspose.words.Document(new ByteArrayInputStream(byteStream2));
	
	
		Node insertAfterNode = doc1.getRange().getBookmarks().get(bookMarkAfter).getBookmarkStart().getParentNode();
		
		//zachistvame ako ima nqkakav default-en text v doca i dobavqme kvoto ni e pusnato.
		doc1.getRange().getBookmarks().get(bookMarkAfter).setText("");
		
	    if ((insertAfterNode.getNodeType() != NodeType.PARAGRAPH) &&
	      (insertAfterNode.getNodeType() != NodeType.TABLE)) {
	        throw new IllegalArgumentException("The destination node should be either a paragraph or table.");
	    }

	    @SuppressWarnings("rawtypes")
		CompositeNode dstStory = insertAfterNode.getParentNode();

	    NodeImporter importer = new NodeImporter(doc2, insertAfterNode.getDocument(), ImportFormatMode.KEEP_SOURCE_FORMATTING);

	    for (Section srcSection : doc2.getSections())
	    {
	        for (Node srcNode : (Iterable<Node>) srcSection.getBody())
	        {

	            if (srcNode.getNodeType() == (NodeType.PARAGRAPH)) {
	                Paragraph para = (Paragraph) srcNode;
	                if (para.isEndOfSection() && !para.hasChildNodes()){
	                    continue;
	                }
	            }

	            Node newNode = importer.importNode(srcNode, true);
	            dstStory.insertAfter(newNode, insertAfterNode);
	            insertAfterNode = newNode;
	        }
	    }
		
	    ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
		doc1.save(dstStream, SaveFormat.DOC);
		bytearray = dstStream.toByteArray();

	
		return bytearray;
	
	}
	
	
	
	public byte[] appendShablons(byte [] byteStream1, byte [] byteStream2,boolean newPage) throws Exception {
		
		byte [] bytearray = null;

		com.aspose.words.Document doc1 = new com.aspose.words.Document(new ByteArrayInputStream(byteStream1));
		com.aspose.words.Document doc2 = new com.aspose.words.Document(new ByteArrayInputStream(byteStream2));
		
		if(newPage){
			doc2.getFirstSection().getPageSetup().setSectionStart(SectionStart.NEW_PAGE);
		}
		
		doc1.appendDocument(doc2, ImportFormatMode.KEEP_SOURCE_FORMATTING);
		
		 for (int i = doc1.getSections().getCount() - 2; i >= 0; i--)
		    {
		        
		        doc1.getLastSection().prependContent(doc1.getSections().get(i));
		        doc1.getSections().get(i).remove();
		    }
		
		ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
		doc1.save(dstStream, SaveFormat.DOC);
		bytearray = dstStream.toByteArray();
		
		
		
		return bytearray;
	}
	
	
	
	public byte[] appendManyShablons(ArrayList <byte []> byteStream,boolean newPage, boolean protect) throws Exception {
		
		byte [] bytearray = null;
		com.aspose.words.Document doc = new com.aspose.words.Document();
		
		for(int i = 0; i <  byteStream.size(); i++){	
			
			com.aspose.words.Document docSec = new com.aspose.words.Document(new ByteArrayInputStream(byteStream.get(i)));
			
			if(newPage && i!=0){
				docSec.getFirstSection().getPageSetup().setSectionStart(SectionStart.NEW_PAGE);
			}
			
			doc.appendDocument(docSec, ImportFormatMode.KEEP_SOURCE_FORMATTING);
			
			ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
			doc.save(dstStream, SaveFormat.DOC);

		}
		
		if(doc.getSections().getCount()>0){
			doc.getSections().get(0).remove();
		}
		
		if(protect){
			doc.protect(ProtectionType.READ_ONLY, "secret2015");
		}
		
		ByteArrayOutputStream dstStreamEnd = new ByteArrayOutputStream();
		doc.save(dstStreamEnd, SaveFormat.DOC);
		bytearray = dstStreamEnd.toByteArray();
		
		return bytearray;
	}
	
	
	/**
	 * Inserts a watermark into a document.
	 *
	 * @param doc The input document file.
	 * @param watermarkText Text of the watermark.
	 */
	private void insertWatermarkText(Document doc, String watermarkText) throws Exception {
	    // Create a watermark shape. This will be a WordArt shape.
	    // You are free to try other shape types as watermarks.
	    Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);

	    // Set up the text of the watermark.
	    watermark.getTextPath().setText(watermarkText);
	    watermark.getTextPath().setFontFamily("Arial");
	    watermark.setWidth(300);
	    watermark.setHeight(50);
	    // Text will be directed from the bottom-left to the top-right corner.
	    watermark.setRotation(-40);
	    // Remove the following two lines if you need a solid black text.
	    watermark.getFill().setColor(new Color(223, 223, 223)); // Try LightGray to get more Word-style watermark
	    watermark.setStrokeColor(new Color(223, 223, 223)); // Try LightGray to get more Word-style watermark
	    
	    // Place the watermark in the page center.
	    watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
	    watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
	    watermark.setWrapType(WrapType.NONE);
	    watermark.setVerticalAlignment(VerticalAlignment.CENTER);
	    watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);

	    // Create a new paragraph and append the watermark to this paragraph.
	    Paragraph watermarkPara = new Paragraph(doc);
	    watermarkPara.appendChild(watermark);

	    // Insert the watermark into all headers of each document section.
	    for (Section sect : doc.getSections()) {
	        // There could be up to three different headers in each section, since we want
	        // the watermark to appear on all pages, insert into all headers.
	        insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
	        insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
	        insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
	    }
	   // System.out.println("Watermark Set");
	}

	private void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect, int headerType) throws Exception {
	    HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);

	    if (header == null)  {
	        // There is no header of the specified type in the current section, create it.
	        header = new HeaderFooter(sect.getDocument(), headerType);
	        sect.getHeadersFooters().add(header);
	    }

	    // Insert a clone of the watermark into the header.
	    header.appendChild(watermarkPara.deepClone(true));
	}
	
}