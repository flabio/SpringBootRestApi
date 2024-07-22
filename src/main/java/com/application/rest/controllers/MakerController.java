package com.application.rest.controllers;

import com.application.rest.controllers.dto.MakerDTO;
import com.application.rest.entities.Maker;
import com.application.rest.mapper.MakerMapper;
import com.application.rest.service.IMakerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maker")
public class MakerController {

    private IMakerService makerService;
    private MakerMapper makerMapper;
    @Autowired
    public MakerController(IMakerService makerService,MakerMapper makerMapper){

        this.makerService=makerService;
        this.makerMapper=makerMapper;
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<MakerDTO> makerDTOList = makerMapper.toMakerDTOList(makerService.findAll());
        return ResponseEntity.ok(makerDTOList);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
       Optional<Maker> makerOptional= makerService.findById(id);
        if(makerOptional.isPresent()){
            Maker maker = makerOptional.get();
            MakerDTO makerDTO= MakerDTO.builder()
                    .id(maker.getId())
                    .name(maker.getName())
                    .productList(maker.getProductList())
                    .build();
            return  ResponseEntity.ok(makerDTO);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MakerDTO makerDTO) throws URISyntaxException {
        if(makerDTO.getName().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        makerService.save(Maker.builder()
                        .id(makerDTO.getId())
                .name(makerDTO.getName())
                .build());
        return ResponseEntity.created(new URI("/api/maker/save")).build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaker(@PathVariable Long id, @RequestBody MakerDTO makerDTO){
        Optional<Maker> makerOptional = makerService.findById(id);
        if(makerOptional.isPresent()){
            Maker maker = makerOptional.get();
            maker.setName(makerDTO.getName());
            makerService.save(maker);
            return  ResponseEntity.ok("Registro Actualizado");
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(id!=null){
            makerService.deleteById(id);
            return ResponseEntity.ok("Registro ELiminado");
        }
        return ResponseEntity.notFound().build();
    }
}
