/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import controller.idtrackerws;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tezk
 */
public class LoginServiceTest {
    
    private idtrackerws idTrackerWs;
    
    public LoginServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
                idTrackerWs = new idtrackerws();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testLogin() {
    }
}
