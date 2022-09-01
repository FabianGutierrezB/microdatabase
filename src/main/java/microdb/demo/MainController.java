package microdb.demo;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path = "/user")
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/add2", consumes = "application/json", produces = "application/json")
    public @ResponseBody User addUser2(@RequestBody User user) {
        System.out.println("Usuario encontrado");
        return userRepository.save(user);
    }

    @GetMapping(value = "/find", consumes = "application/json", produces = "application/json")
    public @ResponseBody Optional<User> getUser(@RequestBody User user) {
        logger.error("Usuario encontrado, id:" + user.getId());
        return userRepository.findById(user.getId());
    }

    @PostMapping(path = "/add")
    public @ResponseBody Respuesta addNewUser(@RequestParam String nombre, @RequestParam(required = false) String correo) {

        Respuesta respuesta = new Respuesta();
        if (correo == null) {
            respuesta.setResultado("No se pudo agregar");
            respuesta.setError("Correo vacío");
            return respuesta;
        }

        System.out.println("Usuario encontrado");

        User userNuevo = new User();
        userNuevo.setNombre(nombre);
        try {
            userNuevo.setCorreo(correo);
        } catch (Exception e1) {
            respuesta.setResultado("No se pudo agregar");
            respuesta.setError(e1.getMessage());
            return respuesta;
        }

        try {
            userRepository.save(userNuevo);
            respuesta.setResultado("Usuario salvado");
        } catch (Exception e) {
            respuesta.setResultado("No se pudo agregar");
            respuesta.setError(e.getMessage());
        }

        return respuesta;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

  
    @GetMapping(value = "/consultaU", consumes = "application/json", produces = "application/json")
    public @ResponseBody Respuesta consultaUserCal(@RequestBody Input input) {
   
    // buscar persona
    Optional <User>  users = userRepository.findById(Integer.parseInt(input.getId_user()));
      
    
    Respuesta res =new Respuesta();

    if (!users.isEmpty()){
        User user = users.get();
        res.setNombrePersona(user.getNombre());
    }
    // invocar el micro de calculadora
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/" + input.getOperacion() + "/" + input.getUno() + "/" +input.getDos();
    ResponseEntity <RespuestaCall> resCalculadora = restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<>(){});
    resCalculadora.getBody();
    
   // armar la respuesta
    res.setNumero(Double.valueOf(resCalculadora.getBody().getResultado()));

    return res;
    }


}