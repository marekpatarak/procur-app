package com.pproject.app.control;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pproject.app.entity.Notice;
import com.pproject.app.repository.NoticeRepository;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;

	private static Logger logger = Logger.getLogger(NoticeService.class.getCanonicalName());

	public void fetchFeedForPreview() throws Exception{
		try {
			
			Document doc = FeedFetcher.fetchFeedFromUrl("https://www.uvo.gov.sk/rss/vestnik");

			NodeList nodeList = doc.getDocumentElement().getElementsByTagName("item");
			
			for(int temp = 0; temp < nodeList.getLength(); temp++) {
				Node nNode = nodeList.item(temp);
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
	
					String title = eElement.getElementsByTagName("title").item(0).getTextContent();
					String desc = eElement.getElementsByTagName("description").item(0).getTextContent();
					String link = eElement.getElementsByTagName("link").item(0).getTextContent();
					int guid = Integer.parseInt(eElement.getElementsByTagName("guid").item(0).getTextContent().substring(7));
					String pubDate = eElement.getElementsByTagName("pubDate").item(0).getTextContent();
					
					Notice notice = new Notice(guid, title, desc, link, pubDate);
					
					if (!noticeRepository.existsById(guid)) {
						try{
							noticeRepository.save(notice);
							logger.log(Level.INFO, "Notice GUID " + guid + " persisted ");
						} catch (DataException ex) {
							logger.log(Level.WARNING, "DataException occured " + ex.getMessage());
						}

						
					} else {
						logger.log(Level.INFO, "Notice " + guid + " not persisted, duplicate GUID found");
					}

				}

			}

			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
				throw new Exception(e);
			}

	}
}
