package br.com.springboot.api_rest_w_front.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import br.com.springboot.api_rest_w_front.model.Usuario;
import br.com.springboot.api_rest_w_front.repository.UsuarioRepository;

@RestController
@RequestMapping ("/springboot-rest-api-sample")
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;



    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	//salvando em um banco
    	usuarioRepository.save(usuario);
    	
    	return "Ola mundo, " + nome;
    }
    
    @GetMapping(value = "listatodos")
    @ResponseBody //retorna os dados para o corpo da resposta em json
    public ResponseEntity<java.util.List<Usuario>> listaUsuario(){
    	
    	//executar a consulta no banco de dados
    	java.util.List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	// retorna a lista em json
    	return new ResponseEntity<java.util.List<Usuario>> (usuarios, HttpStatus.OK); 
    	
    }
    
    @PostMapping(value = "salvar") // mapear a url
    @ResponseBody // descreve a resposta
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){ // recebe os dados para salvar
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @PutMapping(value = "atualizar") // mapear a url
    @ResponseBody // descreve a resposta
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario){ // recebe os dados para atualizar
    	if(usuario.getId()== null) {
    		return new ResponseEntity<String>("Id não informado.", HttpStatus.OK);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    	
    
    @DeleteMapping(value = "delete") // mapear a url
    @ResponseBody // descreve a resposta
    public ResponseEntity<String> deleteUsuario (@RequestParam Long iduser){ // recebe os dados para deletar
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("user deletado com sucesso", HttpStatus.OK);
    }
    
    @GetMapping(value = "buscaruserid") // mapear a url
    @ResponseBody // descreve a resposta
    public ResponseEntity<Usuario> buscarUsuarioId (@RequestParam(name = "iduser") Long iduser){ // recebe os dados para buscar
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarpornome") // mapear a url
    @ResponseBody // descreve a resposta
    public ResponseEntity<java.util.List<Usuario>> buscarPorNome ( @RequestParam(name = "name") String name){ // recebe os dados para consultar(pela query do repository)
    	java.util.List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	return new ResponseEntity<java.util.List<Usuario>>(usuario, HttpStatus.OK);
    }
   
    
   
}

