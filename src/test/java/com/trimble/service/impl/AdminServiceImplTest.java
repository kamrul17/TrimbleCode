package com.trimble.service.impl;

import com.trimble.entities.Admin;
import com.trimble.repositories.AdminRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @Mock
    private AdminRepository repository;
@InjectMocks
    private AdminServiceImpl adminService;

private static Admin admin;
private static  List<Admin> adminList;
//-----------
@BeforeAll
static void setUpAdminObj() {
    System.out.println("Setting up Admin Object...");
            admin=new Admin();
    admin.setAdminId(1L);
    admin.setUsername("");
    admin.setPassword("abcd");
}
@BeforeAll
static void setUpAdminList() {
    System.out.println("Setting up Admin list...");
     adminList = new ArrayList<>();

    Admin admin1 = new Admin();
    admin1.setAdminId(1L);
    admin1.setUsername("sam");
    admin1.setPassword("abcd");

    Admin admin2 = new Admin();
    admin2.setAdminId(2L);
    admin2.setUsername("john");
    admin2.setPassword("efgh");

    Admin admin3 = new Admin();
    admin3.setAdminId(3L);
    admin3.setUsername("alice");
    admin3.setPassword("ijkl");

// Add to list
    adminList.add(admin1);
    adminList.add(admin2);
    adminList.add(admin3);


}

    //-----------
    @Test
    public void registerAdminShouldRegisterAdmin(){
        System.out.println("test 1----------");
//        ---------------------
    when(repository.save(admin)).thenReturn(admin);
         Admin registerAdmin = adminService.registerAdmin(admin);
         assertEquals(registerAdmin.getAdminId(),admin.getAdminId());
       assertNotNull(registerAdmin);
    }
    @Test
    public void registerAdminShouldThrowExceptionIfUsernameIsEmpty(){

         RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            adminService.registerAdmin(admin);
        });

         assertEquals("Username cannot be empty",runtimeException.getMessage());
         verify(repository,times(0)).save(any(Admin.class));
    }

    @Test
    public void getAllAdminShouldReturnAdminList(){

     //        ------------------
        when(repository.findAll()).thenReturn(adminList);
         List<Admin> allAdmins = adminService.getAllAdmins();
         assertNotNull(allAdmins);
    }
    @Test
    public void deleteAdminById(){
doNothing().when(repository).deleteById(1L);
        adminService.deleteAdminById(1L);
        verify(repository,times(1)).deleteById(1L);
    }
@Test
    public void validateAdminNameTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
         Method validateAdminName = AdminServiceImpl.class.getDeclaredMethod("validateAdminName", String.class);
         validateAdminName.setAccessible(true);
         boolean admin1 = (boolean)validateAdminName.invoke(adminService, "nwqknlmwq;");
         assertTrue(admin1);
    }
    @Test
    public void validateAdminNameTestIfNameIsEMpty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateAdminName = AdminServiceImpl.class.getDeclaredMethod("validateAdminName", String.class);
        validateAdminName.setAccessible(true);
        boolean admin1 = (boolean)validateAdminName.invoke(adminService, "");
        assertFalse(admin1);
    }
}