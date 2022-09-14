package qx.leizige.camel.routes.components.handler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class SpringBootRestHandler {

	@GetMapping(value = "/add")
	public void add(@RequestParam String id,@RequestParam String name){
		System.out.println("id = " + id);
		System.out.println("name = " + name);
	}

	@GetMapping(value = "/add/1")
	public void add1(@RequestParam String id,@RequestParam String name){
		System.out.println("add1 id = " + id);
		System.out.println("add1 name = " + name);
	}
}
