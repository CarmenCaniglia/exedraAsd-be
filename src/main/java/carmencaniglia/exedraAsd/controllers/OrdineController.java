package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Ordine;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.services.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {
    @Autowired
    private OrdineService ordineService;

    @GetMapping
    public Page<Ordine> getOrdini(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy){
        return ordineService.getOrdini(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Ordine getOrdineById(@PathVariable long id){
        return ordineService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ordine saveOrdine(@RequestBody Ordine newOrdinePayload){
        return ordineService.save(newOrdinePayload);
    }

    @PutMapping("/{id}")
    public Ordine findByIdAndUpdate(@PathVariable long id,@RequestBody Ordine modifiedOrdinePayload){
        return ordineService.findByIdAndUpdate(id, modifiedOrdinePayload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        ordineService.findByIdAndDelete(id);
    }
}