package ru.pvorobey.checkrunnerrestful;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.pvorobey.checkrunnerrestful.dao.CardRepository;
import ru.pvorobey.checkrunnerrestful.dao.ProductRepository;
import ru.pvorobey.checkrunnerrestful.dto.ProductDTO;
import ru.pvorobey.checkrunnerrestful.entity.Card;
import ru.pvorobey.checkrunnerrestful.entity.Product;
import ru.pvorobey.checkrunnerrestful.exeptions.IdTypeCardNotFoundException;
import ru.pvorobey.checkrunnerrestful.exeptions.IdTypeProductNotFoundException;
import ru.pvorobey.checkrunnerrestful.service.ApplicationService;
import ru.pvorobey.checkrunnerrestful.service.mapper.CardDTOMapper;
import ru.pvorobey.checkrunnerrestful.service.mapper.ProductDTOMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class CheckRunnerRestfulApplicationTests {

    @MockBean
    ProductDTOMapper productDTOMapper;
    @MockBean
    CardDTOMapper cardDTOMapper;

    @Autowired
    ApplicationService applicationService;

    @MockBean
    ProductRepository productRepository;
    @MockBean
    CardRepository cardRepository;



    @Test
    void getIdTypeProductNotFoundException() {
        when(productRepository.findById(5L)).thenThrow(IdTypeProductNotFoundException.class);

        assertThrows(IdTypeProductNotFoundException.class, ()-> applicationService.getProduct(5L));
    }

    @Test
    void getIdCardProductNotFoundException() {
        when(cardRepository.findById(5L)).thenThrow(IdTypeCardNotFoundException.class);

        assertThrows(IdTypeCardNotFoundException.class, ()-> applicationService.
                saveCard(new Card(12L, "9999", 5)));
    }

    @Test
    void allProducts(){
        Product productOne = new Product(2L, "apple", 2.7);
        Product productTwo = new Product(4L,"Banana", 3.8);
        List<Product> products = Arrays.asList(productOne,productTwo);
        //List<ProductDTO> employeesDTO = productDTOMapper.toListProductDTO(products);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> actual = applicationService.getAllProducts();

        verify(productRepository, times(1)).findAll();

        assertEquals(products,actual);
    }


    @Test
    void getProductById() {

        Product productOne = new Product(2L, "apple", 2.7);
        ProductDTO productTwo = new ProductDTO(4L,"Banana", 3.8);

        when(productRepository.findById(4L)).thenReturn(Optional.of(productOne));
        when(productDTOMapper.toProductDTO(productOne)).thenReturn(productTwo);

        ProductDTO productDTO = applicationService.getProduct(4L);

        verify(productRepository, times(1)).findById(4L);
        verify(productDTOMapper, times(1)).toProductDTO(productOne);

        assertEquals(productTwo, productDTO);
    }
}
