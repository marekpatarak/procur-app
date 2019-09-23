package com.pproject.app.control;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pproject.app.entity.BusinessEntity;
import com.pproject.app.entity.NoticeType;
import com.pproject.app.entity.PublicProcurer;
import com.pproject.app.repository.BusinessEntityRepository;
import com.pproject.app.repository.NoticeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pproject.app.entity.Notice;
import com.pproject.app.repository.NoticeRepository;

@Controller
@RequestMapping(path="/")
public class MainController {

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private BusinessEntityRepository businessEntityRepository;

	@Autowired
	private NoticeTypeRepository noticeTypeRepository;
	
	@Autowired
	private NoticeService noticeService;

	@Autowired
	private Configuration configuration;

	public static int pageLimit = 50;
	
	@GetMapping(path="/addnotice")
	public @ResponseBody String addNewNotice (@RequestParam int guid, @RequestParam String title, @RequestParam String description, @RequestParam String link, @RequestParam String pubDate) {
		Notice n = new Notice();
		n.setGuid(guid);
		n.setTitle(title);
		n.setDescription(description);
		n.setLink(link);
		n.setPubDate(pubDate);
		noticeRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/")
	public String redirectLandingPage() {
		return "redirect:/landingpage/";

	}

	@GetMapping(path="/landingpage")
	public String getLandingPage() {

		return "landingpage";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Notice> getAllNotices() {
		return noticeRepository.findAll();
	}

	@GetMapping(path="/fetch")
	public String fetchFeedAndPersist() {
		try {
			noticeService.fetchFeedForPreview();

		} catch (Exception e) {
			return "error";
		}
		return "success";
	}

	@GetMapping(path="/notices")
	public String fetchNoticesForPreview() {

		return "redirect:/notices/1";
	}

	@GetMapping(path="/notices/{page}")
	public String fetchNoticesPerPage(@PathVariable("page") String page, Model model) {

		List<Notice> noticeList = (List<Notice>)noticeRepository.findAll();
		Collections.sort(noticeList);
		model.addAttribute("noticeList", noticeList.subList(pageLimit*Integer.parseInt(page), pageLimit*Integer.parseInt(page)+pageLimit));
		model.addAttribute("currentPage", Integer.parseInt(page));
		return "noticepreview";
	}

	@GetMapping(path="/notices/filter/type/{type}")
	public String fetchNoticesByType(@PathVariable("type") String type, Model model) {

		List<Notice> noticeList = noticeRepository.findNoticesByTitleContaining(" - " + type);
		Collections.sort(noticeList);
		model.addAttribute("noticeList", noticeList);
		return "noticepreview";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {

		model.addAttribute("businessentity", new BusinessEntity());
		return "register";
	}

	@GetMapping("/registerprocurer")
	public String showRegisterFormProcurer(Model model) {

		model.addAttribute("publicprocurer", new PublicProcurer());
		return "registerprocurer";
	}

	@GetMapping("/config")
	public @ResponseBody Iterable<NoticeType>  showConfig() {

		return configuration.getNoticeTypeSet();
	}

	@GetMapping("/config/update")
	public String updateConfig() {

		List<Notice> noticeList = (List<Notice>)noticeRepository.findAll();

		for(Notice notice : noticeList) {
			String noticeType = getNoticeType(notice);

			if(noticeType != null && !noticeTypeRepository.existsById(noticeType)) {
				NoticeType noticeTypeObject = new NoticeType(noticeType);
				noticeTypeRepository.save(noticeTypeObject);
			}
		}

		configuration.update();
		return "success";

	}




	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute BusinessEntity businessEntity) {

		businessEntityRepository.save(businessEntity);
		return "success";
	}


	private String getNoticeType(Notice notice) {

		String reg = "- \\w{3} :";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(notice.getTitle());

		if(matcher.find()) {
			String find = matcher.group();
			if (find.toUpperCase().equals(find)) {
				return find.substring(2,5);
			}
		}
		return null;
	}
}
