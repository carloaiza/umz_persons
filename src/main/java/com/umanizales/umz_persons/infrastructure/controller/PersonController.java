package com.umanizales.umz_persons.infrastructure.controller;


import com.umanizales.umz_persons.application.dto.ErrorDTO;
import com.umanizales.umz_persons.application.dto.PersonDTO;
import com.umanizales.umz_persons.application.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "person")
@Validated
public class PersonController {
    @GetMapping
    public ResponseEntity<ResponseDTO> helloChampions()
    {
        return new ResponseEntity<>
                (new ResponseDTO("success","Hola Campeones",null), HttpStatus.OK);

    }

    @GetMapping("/dto")
    public PersonDTO getPerson()
    {
        return new PersonDTO("Carlos Loaiza","21732673",(byte)43);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> listPersons()
    {
        List<PersonDTO> listPersonDTOS = new ArrayList<>();
        /* Consultaba a la bds y no encontro resultados
        listPersonDTOS.add(new PersonDTO("Pedro Pérez","32344444",(byte)22));
        listPersonDTOS.add(new PersonDTO("Juán Juarez","23232322",(byte)31));
        listPersonDTOS.add(new PersonDTO("Pablo Paez","4545454545",(byte)23));

         */
        if(listPersonDTOS.size()>0)
            return new ResponseEntity<>
                (new ResponseDTO("success",listPersonDTOS,null), HttpStatus.OK);
        else {
            List<ErrorDTO> errorDTOS = new ArrayList<>();
            errorDTOS.add(new ErrorDTO(HttpStatus.NOT_FOUND.value(),"La consulta no generó resultado"));
            return new ResponseEntity<>
                    (new ResponseDTO("error", null, errorDTOS), HttpStatus.OK);
        }
    }


    @GetMapping("/{message1}/{message2}")
    public String getDoubleMessage(@PathVariable String message1, @PathVariable String message2)
    {
        return message1+" "+message2;
    }

    @PostMapping
    public PersonDTO savePerson(@RequestBody @Valid PersonDTO personDTO)
    {
        personDTO.setName("Bienvenido "+personDTO.getName());
        return personDTO;
    }

}
