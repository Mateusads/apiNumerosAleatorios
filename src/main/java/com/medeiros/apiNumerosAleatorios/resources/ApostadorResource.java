package com.medeiros.apiNumerosAleatorios.resources;

import com.medeiros.apiNumerosAleatorios.dto.ApostadorRequestDTO;
import com.medeiros.apiNumerosAleatorios.entities.Apostador;
import com.medeiros.apiNumerosAleatorios.services.impl.ApostadorServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/apostador")
public class ApostadorResource {

    private final ApostadorServiceImpl apostadorServiceImpl;

    public ApostadorResource(ApostadorServiceImpl apostadorServiceImpl) {
        this.apostadorServiceImpl = apostadorServiceImpl;
    }

    @GetMapping(value = "")
    public ResponseEntity findAll() {
        List<Apostador> sorteios = apostadorServiceImpl.findAll();
        return ResponseEntity.ok().body(ApostadorMapper.toDtos(sorteios));
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity findByEmail(@PathVariable String email) {
        var sorteios = apostadorServiceImpl.findByEmail(email);
        return ResponseEntity.ok().body(ApostadorMapper.toDtos(sorteios));
    }

    @PostMapping
    public ResponseEntity sorteioNumero(@Valid @RequestBody ApostadorRequestDTO apostadorRequestDTO) {
        var sorteio = apostadorServiceImpl.sorteio(apostadorRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}").buildAndExpand(sorteio).toUri();
        return ResponseEntity.created(uri).body(ApostadorMapper.toDto(sorteio));
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity delete(@PathVariable String email) {
        apostadorServiceImpl.delete(email);
        return ResponseEntity.noContent().build();
    }

}