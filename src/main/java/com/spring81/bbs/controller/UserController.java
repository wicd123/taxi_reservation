package com.spring81.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring81.bbs.commons.WebConstants;
import com.spring81.bbs.model.ModelUser;
import com.spring81.bbs.service.IServiceUser;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	@Qualifier("serviceuser")
	IServiceUser usersvr;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login(Model model
	        , @RequestParam(value = "url", defaultValue="") String url
	        , HttpServletRequest request
	        , HttpSession session ) {
		logger.info("login : get");
		
		if( session.getAttribute(WebConstants.SESSION_NAME) != null )
		    return "redirect:/";
		
		
		if(url.isEmpty() )
		    url = request.getHeader("Referer");
		
		model.addAttribute("url", url);
		
		return "user/login";
		
		
	}
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String home(Model model
            , RedirectAttributes rttr
            , @RequestParam(value="userid") String userid
            , @RequestParam(value="passwd") String passwd
            , @RequestParam(value="url") String url
            , HttpSession session) {
        logger.info("login : post");
        
        ModelUser result = usersvr.login(userid,  passwd);
        
        // 로그인 성공시 : session 에 로그인 정보 저장
        // 로그인 실패시 : msg에 로그인 메시지 출력되게...
        
        if(result != null){ //로그인 성공시
            session.setAttribute(WebConstants.SESSION_NAME, result);
            
            if(url.isEmpty()){
                return "redirect:/";
            }
            else{
                return "redirect:" + url;
            }
            
        }
        else{ // 로그인 실패시
            rttr.addFlashAttribute("msg", "로그인실패");
            
            return "redirect:/user/login?url=" + url;
            
        }
        
    }
	   @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	    public String logout(Model model
	            , HttpSession session) {
	        logger.info("logout : get");
	        
            session.removeAttribute(WebConstants.SESSION_NAME);
	        
	        return "redirect:/";
	        
	        
	    }
	   @RequestMapping(value = "/user/register", method = RequestMethod.GET)
       public String register(Model model
               , HttpSession session) {
           logger.info("register : get");
           
           
           if( session.getAttribute(WebConstants.SESSION_NAME) != null )
               return "redirect:/";
           
           return "user/register";
           
       }
	   @RequestMapping(value = "/user/checkuserid", method = RequestMethod.GET)
	   @ResponseBody
       public int checkuserid(Model model
               , @RequestParam(value="userid", defaultValue="")String userid) {
           logger.info("checkuserid : get");
           
           int result = usersvr.checkuserid(userid);
           
           
           return result;
           
       }
	   @RequestMapping(value = "/user/register", method = RequestMethod.POST)
       public String register(Model model
               , @ModelAttribute ModelUser user
               , HttpSession session) {
           logger.info("register : POST");
           
           usersvr.insertUser(user);
           
           return "user/register_post";
           
       }
	   @RequestMapping(value = "/user/usermodify", method = RequestMethod.GET)
       public String usermodify(Model model
               , HttpSession session) {
           logger.info("register : get");
           
           ModelUser user = (ModelUser) session.getAttribute(WebConstants.SESSION_NAME);
           if( user == null )
               return "redirect:/user/login";
           
           model.addAttribute("user", user);
           
           return "user/usermodify";
           
       }
	   @RequestMapping(value = "/user/usermodify", method = RequestMethod.POST)
       public String usermodify(Model model
               , @ModelAttribute ModelUser updateValue
               , HttpSession session) {
           logger.info("usermodify : POST");


           ModelUser user = (ModelUser) session.getAttribute(WebConstants.SESSION_NAME);
           if( user == null )
               return "redirect:/user/login";
           
           ModelUser searchValue = new ModelUser(user.getUserno() );
           
           int result = usersvr.updateUserInfo(updateValue, searchValue);
           
           if(result == 1){
               // 세션을 다시 만든다.
               // 1.로그인
               // 2.세션생성
               // 3. 회원정보 수정 -->DB의 회원 정보와 세션의 회원 정보 불일치 발생
               // 4. 세션 재생성
               session.setAttribute(WebConstants.SESSION_NAME, usersvr.selectUserOne(user.getUserno()));
           }
           
           return "redirect:/";
	   }
	   @RequestMapping(value = "/user/changepassword", method = RequestMethod.GET)
       public String changepassword(Model model
               , HttpSession session) {
           logger.info("changepassword : get");
           
           ModelUser user = (ModelUser) session.getAttribute(WebConstants.SESSION_NAME);
           
           if( user == null )
               return "redirect:/user/login";
           
           model.addAttribute("user1", user);
           
           return "user/changepassword";
           
       }
       @RequestMapping(value = "/user/changepassword", method = RequestMethod.POST)
       public String changepassword(Model model
               , RedirectAttributes rttr
               , @RequestParam(value="currentPasswd", defaultValue="") String currentPasswd
               , @RequestParam(value="newPasswd", defaultValue="") String newPasswd
               , HttpSession session) {
           logger.info("changepassword : POST");

           ModelUser user = (ModelUser) session.getAttribute(WebConstants.SESSION_NAME);
           
           
           int result = usersvr.updatePasswd(newPasswd, currentPasswd, user.getUserid());
           
           
           if(result == 1){
               return "user/changepassword_post";
           }
           else{
               rttr.addFlashAttribute("msg", "DB 오류로 인한 패스워드 변경 실패");
               return "redirect:/user/changepassword";
           }
           
           
       }
	
}
