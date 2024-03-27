package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OperatorServiceImplTest {

    @Mock
    OperatorRepository operatorRepository;

    @InjectMocks
    OperatorServiceImpl operatorService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllOperators() {
        List<Operator> operators = new ArrayList<>();
        operators.add(new Operator());
        operators.add(new Operator());
        Mockito.when(operatorRepository.findAll()).thenReturn(operators);

        List<Operator> result = operatorService.retrieveAllOperators();
        assertEquals(2, result.size());
    }

    @Test
    void addOperator() {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        Mockito.when(operatorRepository.save(operator)).thenReturn(operator);

        Operator result = operatorService.addOperator(operator);
        assertNotNull(result);
        assertEquals(1L, result.getIdOperateur());
    }

    @Test
    void deleteOperator() {
        Long id = 1L;

        assertDoesNotThrow(() -> operatorService.deleteOperator(id));
        Mockito.verify(operatorRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void updateOperator() {
        Operator operator = new Operator();
        operator.setIdOperateur(2L);
        Mockito.when(operatorRepository.save(operator)).thenReturn(operator);
        operator.setIdOperateur(1L);
        Operator result = operatorService.updateOperator(operator);
        assertNotNull(result);
        assertEquals(1L, result.getIdOperateur());
    }

    @Test
    void retrieveOperator() {
        Long id = 1L;
        Operator operator = new Operator();
        operator.setIdOperateur(id);
        Mockito.when(operatorRepository.findById(id)).thenReturn(Optional.of(operator));

        Operator result = operatorService.retrieveOperator(id);
        assertNotNull(result);
        assertEquals(id, result.getIdOperateur());
    }
}