/**
 * 
 */
package com.cw.cms.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月29日 下午6:54:11
 */
@Controller
@RequestMapping("/admin")
public class AdminHomeController {

	
	@RequestMapping({"/", "/index"})
	public String home(){
		return "admin/home";
	}
}
