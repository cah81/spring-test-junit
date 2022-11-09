package com.example.demo.testrepository;

import com.example.demo.student.Gender;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @DisplayName("Valida si se selecciona email")
    @Test
    void itShouldCheckIfStudentSelectExistsEmail() {

        //given
        String email = "test@gmail.com";
        Student student = new Student("carlos",email, Gender.MALE);
        underTest.save(student);

        //when
        boolean expected = underTest.selectExistsEmail(email);
        //then
        assertThat(expected).isTrue();
    }

    @DisplayName("Valida si no se selecciona email")
    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {

        //given
        String email = "test@gmail.com";


        //when
        boolean expected = underTest.selectExistsEmail(email);
        //then
        assertThat(expected).isFalse();
    }
}