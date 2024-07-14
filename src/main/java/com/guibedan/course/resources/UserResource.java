package com.guibedan.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guibedan.course.entities.Response;
import com.guibedan.course.entities.Users;
import com.guibedan.course.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	final UserService userService;

	public UserResource(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<Response<List<Users>>> findAll() {
		List<Users> list = userService.findAll();
		Response<List<Users>> res = new Response<List<Users>>("Lista de usuários cadastrados!", true, 200, list);

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<Users>> findById(@PathVariable(value = "id") Long id) {
		Response<Users> res = new Response<Users>();
		Users user = userService.findById(id);

		res.setSuccess(true);
		res.setMessage("Usuário encontrado!");
		res.setStatus(200);
		res.setContent(user);

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@PostMapping
	public ResponseEntity<Response<Users>> save(@RequestBody Users user) {
		userService.save(user);
		Response<Users> res = new Response<Users>("Usuário criado com sucesso!", true, 201, user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(res);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Users>> deleteById(@PathVariable(value = "id") Long id) {
		Response<Users> res = new Response<Users>();

		userService.deleteById(id);
		res.setSuccess(true);
		res.setMessage("Usuário deletado!");
		res.setStatus(200);
		res.setContent(null);

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<Users>> updateUserById(@PathVariable(value = "id") Long id, @RequestBody Users userBody) {
		Response<Users> res = new Response<Users>();
		userService.findById(id);

		userService.updateUserById(id, userBody);
		res.setSuccess(true);
		res.setMessage("Usuário atualizado!");
		res.setStatus(200);
		res.setContent(userBody);

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
}
