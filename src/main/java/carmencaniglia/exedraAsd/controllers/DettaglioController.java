package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.DettaglioOrdine;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.DettaglioDTO;
import carmencaniglia.exedraAsd.payloads.DettaglioResponseDTO;
import carmencaniglia.exedraAsd.services.DettaglioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dettaglioOrdine")
public class DettaglioController {

    @Autowired
    private DettaglioService dettaglioService;

    @GetMapping
    public Page<DettaglioOrdine> getDettagli(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String orderBy){
        return dettaglioService.getDettagli(page,size,orderBy);
    }

    @GetMapping("/{id}")
    public DettaglioOrdine getDettaglioById(@PathVariable long id){
        return dettaglioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DettaglioResponseDTO saveDettaglio(@RequestBody @Validated DettaglioDTO newDettaglioDTO, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload!");
        }else{
           DettaglioOrdine savedDettaglio = dettaglioService.save(newDettaglioDTO);
           return new DettaglioResponseDTO(savedDettaglio.getId());
        }
    }

    @PutMapping("/{id}")
    public DettaglioResponseDTO findByIdAndUpdate(@PathVariable long id,@RequestBody @Validated DettaglioDTO dettaglioDTO){
        DettaglioOrdine updateDettaglio = dettaglioService.findByIdAndUpdate(id, dettaglioDTO);
        return new DettaglioResponseDTO(updateDettaglio.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        dettaglioService.findByIdAndDelete(id);
    }
}
