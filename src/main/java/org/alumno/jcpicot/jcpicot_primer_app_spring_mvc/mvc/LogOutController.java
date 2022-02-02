package org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.mvc;


import javax.servlet.http.HttpSession;

import org.alumno.jcpicot.jcpicot_primer_app_spring_mvc.model.ram.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes({"loginName","loginNickName","usuario"})
    public class LogOutController {

      @RequestMapping(value = ("logout") ,method=RequestMethod.GET)
      public String logout(@ModelAttribute Usuario usuario,ModelMap model,SessionStatus status) {
    	status.setComplete();
    	model.put("usuario", new Usuario("","","",""));
    	model.clear();
        return "redirect:login";
      }
    }