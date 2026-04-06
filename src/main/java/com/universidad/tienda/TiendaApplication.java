package com.universidad.tienda;

import com.universidad.tienda.decorator.OrdenServicio;
import com.universidad.tienda.facade.NotificacionFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TiendaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaApplication.class, args);
    }

    //PRUEBA DECORATOR
    @Bean
    public CommandLineRunner testDecorator(
            @Qualifier("ordenCompleto") OrdenServicio servicio) {
        return args -> {
            System.out.println("----- TEST DECORATOR -----");
            servicio.procesarOrden("ORD-001", 50000);
        };
    }

    //PRUEBA FACADE
    @Bean
    public CommandLineRunner testFacade(NotificacionFacade facade) {
        return args -> {
            System.out.println("----- TEST FACADE -----");

            facade.notificarCompraExitosa(
                    "cliente@email.com",
                    "3001234567",
                    "TOKEN123",
                    "ORD-001"
            );

            facade.notificarErrorPago(
                    "cliente@email.com",
                    "3001234567",
                    "ORD-002"
            );
        };
    }
}