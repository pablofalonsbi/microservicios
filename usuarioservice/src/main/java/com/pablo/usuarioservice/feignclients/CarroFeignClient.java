package com.pablo.usuarioservice.feignclients;

import feign.Body;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.pablo.usuarioservice.models.*;

import java.util.List;


@FeignClient(name = "carroservice",url = "http://localhost:8081", path = "/carros")
public interface CarroFeignClient {

	@PostMapping("/carro")
	public Carro save(@RequestBody Carro carro);

	@GetMapping("/usuario/{usuarioId}")
	public List<Carro> getCarros(@PathVariable int usuarioId);

}
