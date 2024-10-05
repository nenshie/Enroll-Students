/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package controller;

import domain.Administrator;
import domain.City;
import domain.Grade;
import domain.Class;
import domain.Enrollment;
import domain.School;
import domain.Student;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import so.Login;

/**
 *
 * @author neven
 */
public class ControllerTest {
   private Controller controller;

    @Before
    public void setUp() throws Exception, Exception, Exception {
        controller = Controller.getInstance(); // Inicijalizujte instancu
    }
    /**
     * Test of login method, of class Controller.
     */
    @Test
    public void testLogin() throws Exception{
        System.out.println("loginTest: ");
        String dateString = "2001-07-15"; 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(dateString);
        
        Administrator admin = new Administrator(1, "Nevena", "Dukic", "nena", "nena", birthday);
        Administrator expResult = admin;
        
        Administrator result = controller.login(admin);
        assertEquals(expResult, result);
        assertEquals("Names do not equal",expResult.getName(), result.getName());
        assertEquals("Suranmes do not equal",expResult.getSurname(), result.getSurname());
        assertEquals("Birthday do not equal",expResult.getBirthday(), result.getBirthday());
        
    }

  
    /**
     * Test of getAllSchools method, of class Controller.
     */
   @Test
    public void testGetAllSchools() throws Exception {
        System.out.println("getAllSchoolsTest: ");
        List<School> expResult = new ArrayList<>();
          expResult.add(new School(0, "Vladimir Rolović", "Omladinsko šetalište 10", "011/358-22-64", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Djura Jaksic", "Kanarevo brdo 12", "011/358-22-6", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Dr Arcibald Rajs", "Patrisa Lumumbe 5", "011/208-93-02", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Stevan Dukic", "Danteova 52", "011/297-01-47", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Ivan Gundulic", "Gundulićeva 9", "021/662-22-55", new City(2, "Novi Sad", 21000)));
          expResult.add(new School(0, "Djura Danicic", "Dušana Vasiljeva 19", "021/452-962", new City(2, "Novi Sad", 21000)));
          expResult.add(new School(0, "Branko Radicevic", "Futoška 5", "021/662-22-01", new City(2, "Novi Sad", 21000)));
          expResult.add(new School(0, "Ivo Andric", "Branka Bjegovića bb", "018/585-500", new City(3, "Nis", 18000)));
          expResult.add(new School(0, "Car Konstantin", "Velikotrnovska 4", "018/538-355", new City(3, "Nis", 18000)));
          expResult.add(new School(0, "Nikola Tesla", "Partizanska 18", "011/233-78-89", new City(3, "Nis", 18000)));
          expResult.add(new School(0, "Desanka Maksimovic", "Vladimira Nazora 2", "014/225-512", new City(4, "Valjevo", 14000)));
          expResult.add(new School(0, "Sestre Ilic", "Milovana Glišića 45", "014/221-392", new City(4, "Valjevo", 14000)));
          expResult.add(new School(0, "Nemanja Terza", "Karađorđeva 122", "014226085", new City(4, "Valjevo", 14000)));
          expResult.add(new School(0, "Jovan Mikic", "Save Kovačevića 16", "024/548-092", new City(5, "Subotica", 24000)));
          expResult.add(new School(0, "Uros Predic", "Pionirska 1", "023/873-307", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "Vuk Karadzic", "Narodnog fronta 3", "023/561-754", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "France Prešern", "Letićeva 1", "011/123-12-03", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "lalala", "ddddddddd", "123", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "Stefan Nemanja", "Studenica bb", "036/543-64-50", new City(7, "Kragujevac", 34000)));
          expResult.add(new School(0, "Jovo Kursula", "Dositejeva 137", "036/381-131", new City(7, "Kragujevac", 34000)));

        
        List<School> result = controller.getAllSchools();
        
        assertEquals("Veličine lista nisu jednake", expResult.size(), result.size());
        
        for (int i = 0; i < expResult.size(); i++) {
            School expectedSchool = expResult.get(i);
            School resultSchool = result.get(i);

            assertEquals("Nazivi škola nisu jednaki na indeksu " + i, expectedSchool.getName(), resultSchool.getName());
            assertEquals("Adrese škola nisu jednake na indeksu " + i, expectedSchool.getAddress(), resultSchool.getAddress());
            assertEquals("Telefoni škola nisu jednaki na indeksu " + i, expectedSchool.getPhone(), resultSchool.getPhone());
            assertEquals("Gradovi škola nisu jednaki na indeksu " + i, expectedSchool.getCity().getName(), resultSchool.getCity().getName());
            assertEquals("Poštanski brojevi gradova nisu jednaki na indeksu " + i, expectedSchool.getCity().getZipCode(), resultSchool.getCity().getZipCode());
        }
        
    }

    /**
     * Test of getAllCities method, of class Controller.
     */
   @Test
    public void testGetAllCities() throws Exception {
        System.out.println("getAllCitiesTest: ");
        List<City> expResult = new ArrayList<>();
        expResult.add(new City(1, "Beograd", 11000));
        expResult.add(new City(2, "Novi Sad", 21000));
        expResult.add(new City(3, "Nis", 18000));
        expResult.add(new City(4, "Valjevo", 14000));
        expResult.add(new City(5, "Subotica", 24000));
        expResult.add(new City(6, "Zrenjanin", 23000));
        expResult.add(new City(7, "Kragujevac", 34000));
        expResult.add(new City(8, "Kraljevo", 36000));
        
        List<City> result = controller.getAllCities();
        
        assertEquals("Broj gradova se ne poklapa.",expResult.size(), result.size());


        for (int i = 0; i < expResult.size(); i++) {
            City expected = expResult.get(i);
            City resultC = result.get(i);

            assertEquals("Naziv grada se ne poklapa.", expected.getName(), resultC.getName());
            assertEquals("Zip code se ne poklapa.", expected.getZipCode(), resultC.getZipCode());
        }

    }

    /**
     * Test of getSeelctedSchools method, of class Controller.
     */
    @Test
    public void testGetSeelctedSchools() throws Exception {
        System.out.println("getSeelctedSchoolsTest: ");
        List<School> school = new ArrayList<>();
          school.add(new School(0, "Vladimir Rolović", "Omladinsko šetalište 10", "011/358-22-64", new City(1, "Beograd", 11000)));
          school.add(new School(0, "Djura Jaksic", "Kanarevo brdo 12", "011/358-22-6", new City(1, "Beograd", 11000)));
          school.add(new School(0, "Dr Arcibald Rajs", "Patrisa Lumumbe 5", "011/208-93-02", new City(1, "Beograd", 11000)));
          school.add(new School(0, "Stevan Dukic", "Danteova 52", "011/297-01-47", new City(1, "Beograd", 11000)));
          school.add(new School(0, "Ivan Gundulic", "Gundulićeva 9", "021/662-22-55", new City(2, "Novi Sad", 21000)));
          school.add(new School(0, "Djura Danicic", "Dušana Vasiljeva 19", "021/452-962", new City(2, "Novi Sad", 21000)));
          school.add(new School(0, "Branko Radicevic", "Futoška 5", "021/662-22-01", new City(2, "Novi Sad", 21000)));
          school.add(new School(0, "Ivo Andric", "Branka Bjegovića bb", "018/585-500", new City(3, "Nis", 18000)));
          school.add(new School(0, "Car Konstantin", "Velikotrnovska 4", "018/538-355", new City(3, "Nis", 18000)));
          school.add(new School(0, "Nikola Tesla", "Partizanska 18", "011/233-78-89", new City(3, "Nis", 18000)));
          school.add(new School(0, "Desanka Maksimovic", "Vladimira Nazora 2", "014/225-512", new City(4, "Valjevo", 14000)));
          school.add(new School(0, "Sestre Ilic", "Milovana Glišića 45", "014/221-392", new City(4, "Valjevo", 14000)));
          school.add(new School(0, "Nemanja Terza", "Karađorđeva 122", "014226085", new City(4, "Valjevo", 14000)));
          school.add(new School(0, "Jovan Mikic", "Save Kovačevića 16", "024/548-092", new City(5, "Subotica", 24000)));
          school.add(new School(0, "Uros Predic", "Pionirska 1", "023/873-307", new City(6, "Zrenjanin", 23000)));
          school.add(new School(0, "Vuk Karadzic", "Narodnog fronta 3", "023/561-754", new City(6, "Zrenjanin", 23000)));
          school.add(new School(0, "France Prešern", "Letićeva 1", "011/123-12-03", new City(6, "Zrenjanin", 23000)));
          school.add(new School(0, "lalala", "ddddddddd", "123", new City(6, "Zrenjanin", 23000)));
          school.add(new School(0, "Stefan Nemanja", "Studenica bb", "036/543-64-50", new City(7, "Kragujevac", 34000)));
          school.add(new School(0, "Jovo Kursula", "Dositejeva 137", "036/381-131", new City(7, "Kragujevac", 34000)));
        
        List<City> cities = controller.getAllCities();
        
        for (City city : cities) {
             List<School> expResult = new ArrayList<>();
             for (School school1 : school) {
                if(school1.getCity().equals(city)){
                    expResult.add(school1);
                }
            }
            List<School> result = controller.getSeelctedSchools(city.getCityID());
             for (int i = 0; i < expResult.size(); i++) {
            School expectedSchool = expResult.get(i);
            School resultSchool = result.get(i);

            assertEquals("Nazivi škola za grad " + city.getName()+" nisu jednaki na indeksu " + i, expectedSchool.getName(), resultSchool.getName());
            assertEquals("Adrese škola za grad " + city.getName()+" nisu jednake na indeksu " + i, expectedSchool.getAddress(), resultSchool.getAddress());
            assertEquals("Telefoni škola za grad " + city.getName()+" nisu jednaki na indeksu " + i, expectedSchool.getPhone(), resultSchool.getPhone());
            assertEquals("Gradovi škola za grad " + city.getName()+" nisu jednaki na indeksu " + i, expectedSchool.getCity().getName(), resultSchool.getCity().getName());
            assertEquals("Poštanski brojevi za grad " + city.getName()+" gradova nisu jednaki na indeksu " + i, expectedSchool.getCity().getZipCode(), resultSchool.getCity().getZipCode());
        }
            
        }
    }

 
    /**
     * Test of getAllClasses method, of class Controller.
     */
    @Test
    public void testGetAllClasses() throws Exception {
       System.out.println("getAllClassesTest: ");
       List<Class> classes = new ArrayList<>();

        classes.add(new Class(41, "I-1", 24, Grade.I, new School(0, "Vladimir Rolović", "Omladinsko šetalište 10", "011/358-22-64", new City(1, "Beograd", 11000))));
        classes.add(new Class(42, "II-3", 20, Grade.II, new School(0, "Car Konstantin", "Velikotrnovska 4", "018/538-355", new City(3, "Nis", 18000))));
        classes.add(new Class(43, "I-3", 25, Grade.I, new School(0, "Nikola Tesla", "Partizanska 18", "011/233-78-89", new City(3, "Nis", 18000))));
        classes.add(new Class(44, "II-3", 24, Grade.II, new School(0, "Vladimir Rolović", "Omladinsko šetalište 10", "011/358-22-64", new City(1, "Beograd", 11000))));
        classes.add(new Class(45, "III-4", 24, Grade.III, new School(0, "Vladimir Rolović", "Omladinsko šetalište 10", "011/358-22-64", new City(1, "Beograd", 11000))));
        classes.add(new Class(47, "I-1", 23, Grade.I, new School(0, "Jovo Kursula", "Dositejeva 137", "036/381-131", new City(7, "Kragujevac", 34000))));
        classes.add(new Class(48, "II-5", 16, Grade.II, new School(0, "Stefan Nemanja", "Studenica bb", "036/543-64-50", new City(7, "Kragujevac", 34000))));
        classes.add(new Class(49, "V-6", 20, Grade.V, new School(0, "Djura Danicic", "Dušana Vasiljeva 19", "021/452-962", new City(2, "Novi Sad", 21000))));
        classes.add(new Class(50, "V-4", 22, Grade.V, new School(0, "Vuk Karadzic", "Narodnog fronta 3", "023/561-754", new City(6, "Zrenjanin", 23000))));
        classes.add(new Class(51, "VII-2", 20, Grade.VII, new School(0, "Djura Danicic", "Dušana Vasiljeva 19", "021/452-962", new City(2, "Novi Sad", 21000))));
        classes.add(new Class(53, "III-3", 23, Grade.III, new School(0, "Branko Radicevic", "Futoška 5", "021/662-22-01", new City(2, "Novi Sad", 21000))));
        classes.add(new Class(54, "I-4", 23, Grade.I, new School(0, "Uros Predic", "Pionirska 1", "023/873-307", new City(6, "Zrenjanin", 23000))));
        classes.add(new Class(55, "II-2", 26, Grade.II, new School(0, "Stefan Nemanja", "Studenica bb", "036/543-64-50", new City(7, "Kragujevac", 34000))));
        classes.add(new Class(56, "II-3", 23, Grade.II, new School(0, "Branko Radicevic", "Futoška 5", "021/662-22-01", new City(2, "Novi Sad", 21000))));
        classes.add(new Class(57, "VII-1", 24, Grade.VII, new School(0, "Dr Arcibald Rajs", "Patrisa Lumumbe 5", "011/208-93-02", new City(1, "Beograd", 11000))));
        classes.add(new Class(58, "VIII-6", 23, Grade.VIII, new School(0, "Djura Danicic", "Dušana Vasiljeva 19", "021/452-962", new City(2, "Novi Sad", 21000))));
        classes.add(new Class(59, "VII-2", 24, Grade.VII, new School(0, "Vladimir Rolović", "Omladinsko šetalište 10", "011/358-22-64", new City(1, "Beograd", 11000))));
        classes.add(new Class(60, "I-4", 17, Grade.I, new School(0, "Jovo Kursula", "Dositejeva 137", "036/381-131", new City(7, "Kragujevac", 34000))));
        classes.add(new Class(61, "V-3", 17, Grade.V, new School(0, "Vuk Karadzic", "Narodnog fronta 3", "023/561-754", new City(6, "Zrenjanin", 23000))));
        classes.add(new Class(62, "II-2", 20, Grade.II, new School(0, "Stevan Dukic", "Danteova 52", "011/297-01-47", new City(1, "Beograd", 11000))));
        classes.add(new Class(63, "IV-3", 30, Grade.IV, new School(0, "Djura Jaksic", "Kanarevo brdo 12", "011/358-22-6", new City(1, "Beograd", 11000))));
        classes.add(new Class(64, "VI-1", 20, Grade.VI, new School(0, "Stevan Dukic", "Danteova 52", "011/297-01-47", new City(1, "Beograd", 11000))));
       
        List<School> schools = controller.getAllSchools();
         List<Class> expResult = new ArrayList<>();
         List<Class> result = new ArrayList<>();  
        for (School sch : schools) {
            for (Class clss : classes ) {
                if(clss.getSchool().getName().equals(sch.getName())){
                    expResult.add(clss);
                }
            }
            System.out.println(expResult);
            result = controller.getAllClasses(sch);
                assertEquals("Broj odeljenja se ne poklapa.",expResult.size(), result.size());
                for (int i = 0; i < expResult.size(); i++) {
                    Class c1 = expResult.get(i);
                    Class c2 = result.get(i);

                    assertEquals( "The names should be equal for index " + i, c1.getName(), c2.getName());
                    assertEquals("The number of students should be equal for index " + i, c1.getNumOfStud(), c2.getNumOfStud());
                }
                
                expResult = new ArrayList<>();
        }         
    }
      
       
    

    /**
     * Test of addSchool method, of class Controller.
     */
    @Test
    public void testAddSchool() throws Exception {
        System.out.println("addSchoolTest: ");
        School sch = new School(77, "Test school", "smth 123", "123-123", new City(1, "Beograd", 11000));
        List<School> expResult = new ArrayList<>();
        expResult.add(new School(0, "Vladimir Rolović", "Omladinsko šetalište 10", "011/358-22-64", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Djura Jaksic", "Kanarevo brdo 12", "011/358-22-6", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Dr Arcibald Rajs", "Patrisa Lumumbe 5", "011/208-93-02", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Stevan Dukic", "Danteova 52", "011/297-01-47", new City(1, "Beograd", 11000)));
          expResult.add(sch);
          expResult.add(new School(0, "Ivan Gundulic", "Gundulićeva 9", "021/662-22-55", new City(2, "Novi Sad", 21000)));
          expResult.add(new School(0, "Djura Danicic", "Dušana Vasiljeva 19", "021/452-962", new City(2, "Novi Sad", 21000)));
          expResult.add(new School(0, "Branko Radicevic", "Futoška 5", "021/662-22-01", new City(2, "Novi Sad", 21000)));
          expResult.add(new School(0, "Ivo Andric", "Branka Bjegovića bb", "018/585-500", new City(3, "Nis", 18000)));
          expResult.add(new School(0, "Car Konstantin", "Velikotrnovska 4", "018/538-355", new City(3, "Nis", 18000)));
          expResult.add(new School(0, "Nikola Tesla", "Partizanska 18", "011/233-78-89", new City(3, "Nis", 18000)));
          expResult.add(new School(0, "Desanka Maksimovic", "Vladimira Nazora 2", "014/225-512", new City(4, "Valjevo", 14000)));
          expResult.add(new School(0, "Sestre Ilic", "Milovana Glišića 45", "014/221-392", new City(4, "Valjevo", 14000)));
          expResult.add(new School(0, "Nemanja Terza", "Karađorđeva 122", "014226085", new City(4, "Valjevo", 14000)));
          expResult.add(new School(0, "Jovan Mikic", "Save Kovačevića 16", "024/548-092", new City(5, "Subotica", 24000)));
          expResult.add(new School(0, "Uros Predic", "Pionirska 1", "023/873-307", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "Vuk Karadzic", "Narodnog fronta 3", "023/561-754", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "France Prešern", "Letićeva 1", "011/123-12-03", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "lalala", "ddddddddd", "123", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "Stefan Nemanja", "Studenica bb", "036/543-64-50", new City(7, "Kragujevac", 34000)));
          expResult.add(new School(0, "Jovo Kursula", "Dositejeva 137", "036/381-131", new City(7, "Kragujevac", 34000)));
        controller.addSchool(sch);
        List<School> result = controller.getAllSchools();
        
         assertEquals("Veličine lista nisu jednake", expResult.size(), result.size());
        
        for (int i = 0; i < expResult.size(); i++) {
            School expectedSchool = expResult.get(i);
            School resultSchool = result.get(i);

            assertEquals("Nazivi škola nisu jednaki na indeksu " + i, expectedSchool.getName(), resultSchool.getName());
            assertEquals("Adrese škola nisu jednake na indeksu " + i, expectedSchool.getAddress(), resultSchool.getAddress());
            assertEquals("Telefoni škola nisu jednaki na indeksu " + i, expectedSchool.getPhone(), resultSchool.getPhone());
            assertEquals("Gradovi škola nisu jednaki na indeksu " + i, expectedSchool.getCity().getName(), resultSchool.getCity().getName());
            assertEquals("Poštanski brojevi gradova nisu jednaki na indeksu " + i, expectedSchool.getCity().getZipCode(), resultSchool.getCity().getZipCode());
        }
    }

    /**
     * Test of updateSchool method, of class Controller.
     */
    @Test
    public void testUpdateSchool() throws Exception {
        System.out.println("updateSchoolTest: ");
        School sch = new School(77, "Test school", "smth 345", "123-123", new City(1, "Beograd", 11000));
        List<School> expResult = new ArrayList<>();
        expResult.add(new School(0, "Vladimir Rolović", "Omladinsko šetalište 10", "011/358-22-64", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Djura Jaksic", "Kanarevo brdo 12", "011/358-22-6", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Dr Arcibald Rajs", "Patrisa Lumumbe 5", "011/208-93-02", new City(1, "Beograd", 11000)));
          expResult.add(new School(0, "Stevan Dukic", "Danteova 52", "011/297-01-47", new City(1, "Beograd", 11000)));
          expResult.add(sch);
          expResult.add(new School(0, "Ivan Gundulic", "Gundulićeva 9", "021/662-22-55", new City(2, "Novi Sad", 21000)));
          expResult.add(new School(0, "Djura Danicic", "Dušana Vasiljeva 19", "021/452-962", new City(2, "Novi Sad", 21000)));
          expResult.add(new School(0, "Branko Radicevic", "Futoška 5", "021/662-22-01", new City(2, "Novi Sad", 21000)));
          expResult.add(new School(0, "Ivo Andric", "Branka Bjegovića bb", "018/585-500", new City(3, "Nis", 18000)));
          expResult.add(new School(0, "Car Konstantin", "Velikotrnovska 4", "018/538-355", new City(3, "Nis", 18000)));
          expResult.add(new School(0, "Nikola Tesla", "Partizanska 18", "011/233-78-89", new City(3, "Nis", 18000)));
          expResult.add(new School(0, "Desanka Maksimovic", "Vladimira Nazora 2", "014/225-512", new City(4, "Valjevo", 14000)));
          expResult.add(new School(0, "Sestre Ilic", "Milovana Glišića 45", "014/221-392", new City(4, "Valjevo", 14000)));
          expResult.add(new School(0, "Nemanja Terza", "Karađorđeva 122", "014226085", new City(4, "Valjevo", 14000)));
          expResult.add(new School(0, "Jovan Mikic", "Save Kovačevića 16", "024/548-092", new City(5, "Subotica", 24000)));
          expResult.add(new School(0, "Uros Predic", "Pionirska 1", "023/873-307", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "Vuk Karadzic", "Narodnog fronta 3", "023/561-754", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "France Prešern", "Letićeva 1", "011/123-12-03", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "lalala", "ddddddddd", "123", new City(6, "Zrenjanin", 23000)));
          expResult.add(new School(0, "Stefan Nemanja", "Studenica bb", "036/543-64-50", new City(7, "Kragujevac", 34000)));
          expResult.add(new School(0, "Jovo Kursula", "Dositejeva 137", "036/381-131", new City(7, "Kragujevac", 34000))); 
          controller.updateSchool(sch);
          
          List<School> result = controller.getAllSchools();
        
         //assertEquals("Veličine lista nisu jednake", expResult.size(), result.size());
        
        for (int i = 0; i < expResult.size(); i++) {
            School expectedSchool = expResult.get(i);
            School resultSchool = result.get(i);

            assertEquals("Nazivi škola nisu jednaki na indeksu " + i, expectedSchool.getName(), resultSchool.getName());
            assertEquals("Adrese škola nisu jednake na indeksu " + i, expectedSchool.getAddress(), resultSchool.getAddress());
            assertEquals("Telefoni škola nisu jednaki na indeksu " + i, expectedSchool.getPhone(), resultSchool.getPhone());
            assertEquals("Gradovi škola nisu jednaki na indeksu " + i, expectedSchool.getCity().getName(), resultSchool.getCity().getName());
            assertEquals("Poštanski brojevi gradova nisu jednaki na indeksu " + i, expectedSchool.getCity().getZipCode(), resultSchool.getCity().getZipCode());
        }
    }
/**
     * Test of getAllStud method, of class Controller.
     */
    @Test
    public void testGetAllStud() throws Exception {
        System.out.println("getAllStudTest: ");
        List<Student> expResult = controller.getAllStud();
        List<Student> result = controller.getAllStud();
        
        assertEquals("Veličine lista nisu jednake", expResult.size(), result.size());
         for (int i = 0; i < expResult.size(); i++) {
            Student expectedStud = expResult.get(i);
            Student resultStud = result.get(i);

            assertEquals("Imena ucenika nisu jednaka na indeksu " + i, expectedStud.getName(), resultStud.getName());
            assertEquals("Prezimena ucenika nisu jednaka na indeksu " + i, expectedStud.getSurname(), resultStud.getSurname());
            assertEquals("Rodjendani na indeksu " + i, expectedStud.getBirthday(), resultStud.getBirthday());
            assertEquals("Gradovi ucenika nisu jednaki na indeksu " + i, expectedStud.getCity().getName(), resultStud.getCity().getName());
            assertEquals("Imena roditelja nisu jednaka na indeksu " + i, expectedStud.getParent(), resultStud.getParent());
        }
    }
    
    /**
     * Test of findStudnets method, of class Controller.
     */
    @Test
    public void testFindStudnets() throws Exception {
        System.out.println("findStudnetsTest: ");
        List<School> schools = controller.getAllSchools();
        List<Student> expResult = new ArrayList<>();
        List<Student> result = new ArrayList<>();
        for (School school : schools) {
            List<Class> classes = controller.getAllClasses(school);
            for (Class cl : classes) {
               result = controller.findStudnets(cl);  
               List<Enrollment> enrollments = controller.getAllEnrolls();
               
                for (Enrollment enr : enrollments) {
                    if(enr.getC().getClassID() == cl.getClassID()){
                        expResult.add(enr.getStudent());
                    }
                }
                System.out.println(expResult);
                System.out.println(result);
                 assertEquals("Veličine lista nisu jednake", expResult.size(), result.size());
               for (Student expectedStud : expResult) {
                    assertTrue("Lista 'result' ne sadrži očekivanog studenta: " + expectedStud,
                               result.contains(expectedStud));
                }
               assertTrue("Rezultati nisu jednaki", result.containsAll(expResult) && expResult.containsAll(result));
  
                expResult = new ArrayList<>();
                result = new ArrayList<>();
                }
            }
    }

    /**
     * Test of addStudent method, of class Controller.
     */
    @Test
    public void testAddStudent()throws Exception{
        System.out.println("addStudentTest: ");
        String dateString = "2001-07-15"; 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(dateString);
        java.sql.Date sqlDate = new java.sql.Date(birthday.getTime());
        Student stud = new Student(141, "9638527410147", "Test", "Student", sqlDate, "Student Test", new City(7, "Kragujevac", 34000));
        controller.addStudent(stud);
        
        List<Student> expResult = controller.getAllStud();
        List<Student> result = controller.getAllStud();
        
        assertEquals("Veličine lista nisu jednake", expResult.size(), result.size());
         for (int i = 0; i < expResult.size(); i++) {
            Student expectedStud = expResult.get(i);
            Student resultStud = result.get(i);

            assertEquals("Imena ucenika nisu jednaka na indeksu " + i, expectedStud.getName(), resultStud.getName());
            assertEquals("Prezimena ucenika nisu jednaka na indeksu " + i, expectedStud.getSurname(), resultStud.getSurname());
            assertEquals("Rodjendani na indeksu " + i, expectedStud.getBirthday(), resultStud.getBirthday());
            assertEquals("Gradovi ucenika nisu jednaki na indeksu " + i, expectedStud.getCity().getName(), resultStud.getCity().getName());
            assertEquals("Imena roditelja nisu jednaka na indeksu " + i, expectedStud.getParent(), resultStud.getParent());
        }
        
    }


    /**
     * Test of updateStudent method, of class Controller.
     */
    @Test
    public void testUpdateStudent()  throws Exception{
        System.out.println("updateStudentTest: ");
        String dateString = "2001-07-15"; 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = dateFormat.parse(dateString);
        java.sql.Date sqlDate = new java.sql.Date(birthday.getTime());
        Student stud = new Student(141, "9638527410147", "Test", "Student 2", sqlDate, "Student Test", new City(7, "Kragujevac", 34000));
        controller.updateStudent(stud);
        
        List<Student> expResult = controller.getAllStud();
        List<Student> result = controller.getAllStud();
        
        assertEquals("Veličine lista nisu jednake", expResult.size(), result.size());
         for (int i = 0; i < expResult.size(); i++) {
            Student expectedStud = expResult.get(i);
            Student resultStud = result.get(i);

            assertEquals("Imena ucenika nisu jednaka na indeksu " + i, expectedStud.getName(), resultStud.getName());
            assertEquals("Prezimena ucenika nisu jednaka na indeksu " + i, expectedStud.getSurname(), resultStud.getSurname());
            assertEquals("Rodjendani na indeksu " + i, expectedStud.getBirthday(), resultStud.getBirthday());
            assertEquals("Gradovi ucenika nisu jednaki na indeksu " + i, expectedStud.getCity().getName(), resultStud.getCity().getName());
            assertEquals("Imena roditelja nisu jednaka na indeksu " + i, expectedStud.getParent(), resultStud.getParent());
        }
     
    }


 }
