package com.question.controller;

import com.question.domain.MemberVO;
import com.question.mail.Email;
import com.question.mail.EmailSender;
import com.question.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login/*")
@Log4j
@AllArgsConstructor
public class LoginController {

    @Setter(onMethod_ = @Autowired)
    private LoginService service;

    @Setter(onMethod_ = @Autowired)
    private EmailSender emailSender;

    @Setter(onMethod_ = @Autowired)
    private Email email;

    @RequestMapping("/userSearch")
    public String userSearch(){return "/login/userSearch";}

    @RequestMapping("/signUp")
    public String signUp(){ return "/login/signUp"; }

    @GetMapping("/login")
    public String login() {
        return "/login/login";
    }

    @RequestMapping("/sessionLogout")
    public String logOut(){
        return "/login/sessionLogout";
    }

    @GetMapping("/check")
    public String loginForm(Model model, @RequestParam HashMap hash) {

        String id = (String) hash.get("id");
        String password = (String) hash.get("password");

        log.info("id =............................. " + id);
        log.info("password ...................= " + password);

        MemberVO member = service.chkUser(id);

        System.out.println("member = " + member);
        if (member.getPassword().equals(password)) {
            log.info(member.getName()+"님 로그인 success");
            model.addAttribute("testtest", "success");
            model.addAttribute("id", member.getId());
        } else {
            log.info("로그인 fail");
            model.addAttribute("testtest", "fail");
        }

        return "/login/sessionLogin";
    }

    @PostMapping("/signUp")
    public String signUp(MemberVO member, Model model) {

        if(service.signUp(member) == 1){
            model.addAttribute("result2", "success");
        }else{
            model.addAttribute("result2", "false");
        }
        return "/login/login";
    }

    @RequestMapping("/dupliId")
    @ResponseBody
    public JSONObject signUp(@RequestParam String id) {

        System.out.println("id = " + id);
        JSONObject jsonObject = new JSONObject();


        if(service.dupliChkId(id)) {
            System.out.println(service.dupliChkId(id));
            jsonObject.put("result", "중복된 아이디 입니다.");
        } else {
            jsonObject.put("result", "사용가능한 아이디 입니다.");
        }
        return jsonObject;

    }

    @RequestMapping(value = "/findingId" , method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findingId(@ModelAttribute MemberVO vo, Model model , HttpServletResponse response)throws Exception {

        System.out.println(vo.toString());
        ArrayList <String> idList = service.findId(vo);
        System.out.println(idList.toString());
        System.out.println(idList.get(0));
        String findId = "{\"id\":\""+idList+"\"}";

        System.out.println(findId);

        return findId;
    }
    @RequestMapping("/sendpw.do")
    public ModelAndView sendEmailAction (@RequestParam Map<String, Object> paramMap, ModelMap model, MemberVO member) throws Exception {
        ModelAndView mav;
        String id=(String) paramMap.get("id");
        String e_mail=(String) paramMap.get("email");
        ArrayList<String> pw=service.findPw(member);
        System.out.println(pw);
        if(pw!=null) {
            email.setContent("비밀번호는 "+pw+" 입니다.");
            email.setReceiver(e_mail);
            email.setSubject(id+"님 비밀번호 찾기 메일입니다.");
            emailSender.SendEmail(email);
            mav= new ModelAndView("redirect:/login/login");
            return mav;
        }
        else {
            mav=new ModelAndView("redirect:/login/sessionLogout");
            return mav;
        }
    }


}
